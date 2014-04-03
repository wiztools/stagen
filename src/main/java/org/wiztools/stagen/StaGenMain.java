package org.wiztools.stagen;

import com.sampullara.cli.Args;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author subwiz
 */
public class StaGenMain {
    
    private static final Logger LOG = Logger.getLogger(StaGenMain.class.getName());
    
    private static void printHelp(PrintStream out) {
        out.println("Usage: stagen [options] (command)");
        
        out.println("Where `options' are:");
        out.println("  -v  -verbose  Verbose output.");
        out.println("  -f  -force    Clean existing content in target dir before site generation.");
        out.println("  -h  -help     Print this help message.");
        
        out.println("`command' can be one of:");
        out.println("  init          Create a new project structure.");
        out.println("  gen           Generate site.");
        out.println("  clean         Delete target directory.");
    }
    
    public static void main(String[] args) {
        
        CliCommand cmd = ServiceLocator.getInstance(CliCommand.class);
        List<String> params = null;
        
        String tCmd = null;
        try {
            params = Args.parse(cmd, args);
            
            if(cmd.help) {
                printHelp(System.out);
                System.exit(0);
            }
            
            if(params.size() != 1) {
                throw new IllegalArgumentException("One (only one) command is required.");
            }
            tCmd = params.get(0);
            if(!Arrays.asList(new String[]{"init", "gen", "clean"}).contains(tCmd)) {
                throw new IllegalArgumentException("Command not recognized: " + tCmd);
            }
        }
        catch(IllegalArgumentException ex) {
            System.err.println(ex.getMessage());
            printHelp(System.err);
            System.exit(1);
        }
        
        final String command = tCmd;
        
        if(cmd.verbose) {
            Logger.getLogger(StaGenMain.class.getPackage().getName())
                    .setLevel(Level.INFO);
        }
        
        try {
            final Runner runner;
            switch(command) {
                case "init":
                    runner = ServiceLocator.getInstance(RunnerInit.class);
                    break;
                case "gen":
                    runner = ServiceLocator.getInstance(RunnerGen.class);
                    break;
                case "clean":
                    runner = ServiceLocator.getInstance(RunnerClean.class);
                    break;
                default:
                    throw new ExecutorException("Unknown command: " + command);
            }
            runner.run(Constants.DEFAULT_DIR);
        }
        catch(ValidationException ex) {
            System.err.println(ex.getMessage());
            System.exit(2);
        }
        catch(ExecutorException | IOException ex) {
            if(cmd.verbose) {
                ex.printStackTrace(System.err);
            }
            else {
                System.err.println(ex.getMessage());
            }
            System.exit(3);
        }
    }
}
