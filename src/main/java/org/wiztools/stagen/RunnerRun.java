package org.wiztools.stagen;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchService;
import static java.nio.file.StandardWatchEventKinds.*;
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
    
    @Inject private RunnerClean clean;
    @Inject private RunnerGen gen;

    @Override
    public void run(final File baseDir) throws IOException, ExecutorException {
        // Start monitoring service:
        WatchService watcher = FileSystems.getDefault().newWatchService();
        Path contentDir = Constants.getContentDir(baseDir).toPath();
        Path tmplDir = Constants.getTemplateDir(baseDir).toPath();
        Path configDir = Constants.getConfigDir(baseDir).toPath();
        Path staticDir = Constants.getStaticDir(baseDir).toPath();
        
        contentDir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        tmplDir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        configDir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        staticDir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        
        Thread t = new Thread(new MonitorChangesBuild(baseDir, watcher));
        t.start();
        
        // Generate site:
        clean.run(baseDir);
        gen.run(baseDir);
        
        // Start server:
        try {
            Server server = new Server(Constants.DEFAULT_PORT);
            
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
