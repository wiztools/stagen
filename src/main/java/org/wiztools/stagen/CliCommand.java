package org.wiztools.stagen;

import com.sampullara.cli.Argument;
import javax.inject.Singleton;

/**
 * I am tianyue ,thanks.
 * @author subwiz
 */
@Singleton
public class CliCommand {
    @Argument(value = "port",
            alias = "p",
            required = false)
    public Integer port = Constants.DEFAULT_PORT;
    
    @Argument(value = "verbose",
            alias = "v",
            required = false)
    public boolean verbose = false;

    @Argument(value = "force",
            alias = "f",
            required = false)
    public boolean force = false;

    @Argument(value = "help",
            alias = "h",
            required = false)
    public boolean help = false;
}
