package org.wiztools.stagen;

import com.sampullara.cli.Args;
import java.io.File;
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
        out.println("  -p  -port     Port to use for `run' command.");
        out.println("  -h  -help     Print this help message.");
        
        out.println("`command' can be one of:");
        out.println("  init          Create a new project structure.");
        out.println("  gen           Generate site.");
        out.println("  run           Run embedded server at default port " + Constants.DEFAULT_PORT + ".");
        out.println("  clean         Delete target directory.");
    }
    
    private static void runCommand(String command, List<String> params)
            throws IOException, ExecutorException {
        if("init".equals(command)) {
            File f = null;
            if(params.size() == 1) {
                String folderName = params.get(1);
                f = new File(folderName);
            }
            else if(params.size() > 1) {
                throw new IllegalArgumentException("init command can have only one parameter.");
            }
            else {
                f = Constants.DEFAULT_DIR;
            }
            final Runner runner = ServiceLocator.getInstance(RunnerInit.class);
            runner.run(f);
        }
        else if("clean".equals(command)) {
            final Runner runner = ServiceLocator.getInstance(RunnerClean.class);
            runner.run(Constants.DEFAULT_DIR);
            
            // Clean can be combined with other commands:
            if(!params.isEmpty()) {
                String newCommand = params.get(0);
                params.remove(0);
                runCommand(newCommand, params);
            }
        }
        else if("gen".equals(command)) {
            final Runner runner = ServiceLocator.getInstance(RunnerGen.class);
            runner.run(Constants.DEFAULT_DIR);
        }
        else if("run".equals("command")) {
            final Runner runner = ServiceLocator.getInstance(RunnerRun.class);
            runner.run(Constants.DEFAULT_DIR);
        }
        else {
            throw new ExecutorException("Unknown command: " + command);
        }
    }
    
    public static void main(String[] args) {
        
        CliCommand cmd = ServiceLocator.getInstance(CliCommand.class);
        
        try {
            final List<String> params = Args.parse(cmd, args);
            
            if(cmd.help) {
                printHelp(System.out);
                System.exit(0);
            }
            
            if(cmd.verbose) {
                Logger.getLogger(StaGenMain.class.getPackage().getName())
                        .setLevel(Level.INFO);
            }
            
            if(params.isEmpty()) {
                throw new IllegalArgumentException("Command parameter is missing.");
            }
            
            final String command = params.get(0);
            if(!Arrays.asList(new String[]{"init", "gen", "run", "clean"}).contains(command)) {
                throw new IllegalArgumentException("Command not recognized: " + command);
            }
            
            // Run the command(s):
            params.remove(0);
            runCommand(command, params);
        }
        catch(IllegalArgumentException ex) {
            System.err.println(ex.getMessage());
            printHelp(System.err);
            System.exit(1);
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
