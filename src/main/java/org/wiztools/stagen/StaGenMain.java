package org.wiztools.stagen;

import com.sampullara.cli.Args;
import com.sampullara.cli.Argument;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author subwiz
 */
public class StaGenMain {
    
    private static final Logger LOG = Logger.getLogger(StaGenMain.class.getName());
    
    private static class CliCommand {
        @Argument(value = "verbose",
                alias = "v",
                description = "Generate verbose output.",
                required = false)
        private boolean verbose = false;
        
    }
    
    public static void main(String[] args) throws ExecutorException, IOException {
        
        CliCommand cmd = new CliCommand();
        List<String> params = null;
        
        try {
            params = Args.parse(cmd, args);
        }
        catch(IllegalArgumentException ex) {
            System.err.println(ex.getMessage());
            Args.usage(cmd);
            System.exit(1);
        }
        
        if(cmd.verbose) {
            Logger.getLogger("org.wiztools.stagen").setLevel(Level.INFO);
        }
        
        Runner runner = ServiceLocator.getInstance(Runner.class);
        runner.run(Constants.DEFAULT_DIR);
    }
}
