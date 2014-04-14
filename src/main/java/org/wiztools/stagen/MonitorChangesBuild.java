package org.wiztools.stagen;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import static java.nio.file.StandardWatchEventKinds.*;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author subwiz
 */
public class MonitorChangesBuild implements Runnable {
    
    private static final Logger LOG = Logger.getLogger(MonitorChangesBuild.class.getName());
    
    private final File baseDir;
    private final WatchService watcher;
    
    public MonitorChangesBuild(File baseDir, WatchService wk) {
        this.baseDir = baseDir;
        this.watcher = wk;
    }

    @Override
    public void run() {
        
        while(true) {
            WatchKey key;
            try {
                key = watcher.take();
            }
            catch(InterruptedException ex) {
                return;
            }
            
            for (WatchEvent<?> event: key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();
                if (kind == OVERFLOW) {
                    continue;
                }
                
                WatchEvent<Path> ev = (WatchEvent<Path>)event;
                
                Path filename = ev.context();
                LOG.info(MessageFormat.format("Detected change: {0}", filename));
                
                // Generate site:
                RunnerGen gen = ServiceLocator.getInstance(RunnerGen.class);
                RunnerClean clean = ServiceLocator.getInstance(RunnerClean.class);
                try {
                    clean.run(baseDir);
                    gen.run(baseDir);
                }
                catch(ExecutorException | IOException ex) {
                    LOG.log(Level.WARNING, null, ex);
                }
            }
            
            boolean valid = key.reset();
            if (!valid) {
                break;
            }
        }
    }
    
}
