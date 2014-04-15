package org.wiztools.stagen;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.WatchService;
import static java.nio.file.StandardWatchEventKinds.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;

/**
 *
 * @author subwiz
 */
public class RunnerRun implements Runner {
    
    private static final Logger LOG = Logger.getLogger(RunnerRun.class.getName());
    
    @Inject private RunnerClean clean;
    @Inject private RunnerGen gen;
    @Inject private CliCommand cmd;
    
    private void register(final WatchService watcher, Path ... dirs) throws IOException {
        for(Path dir: dirs) {
            Files.walkFileTree(dir, new SimpleFileVisitor<Path>(){
                @Override
                public FileVisitResult preVisitDirectory(Path d, BasicFileAttributes attrs) throws IOException {
                    d.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
                    return FileVisitResult.CONTINUE;
                }
            });
        }
    }

    @Override
    public void run(final File baseDir) throws IOException, ExecutorException {
        // Start monitoring service:
        Path contentDir = Constants.getContentDir(baseDir).toPath();
        Path tmplDir = Constants.getTemplateDir(baseDir).toPath();
        Path configDir = Constants.getConfigDir(baseDir).toPath();
        Path staticDir = Constants.getStaticDir(baseDir).toPath();
        
        final WatchService watcher = FileSystems.getDefault().newWatchService();
        register(watcher, contentDir, tmplDir, configDir, staticDir);
        
        Thread t = new Thread(new MonitorChangesBuild(baseDir, watcher));
        t.start();
        
        // Generate site:
        clean.run(baseDir);
        gen.run(baseDir);
        
        // Start server:
        try {
            LOG.log(Level.INFO, "Starting HTTP server at port: {0}", cmd.port);
            Server server = new Server(cmd.port);
            
            ResourceHandler rh = new ResourceHandler();
            rh.setDirectoriesListed(true);
            rh.setWelcomeFiles(new String[]{"index.html"});
            rh.setResourceBase(Constants.getOutDir(baseDir).getPath());
            
            HandlerList handlers = new HandlerList();
            handlers.setHandlers(new Handler[]{rh, new DefaultHandler()});
            server.setHandler(handlers);
            
            server.start();
            server.join();
        }
        catch(Exception ex) {
            throw new ExecutorException(ex);
        }
    }
    
}
