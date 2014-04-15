package org.wiztools.stagen;

import java.io.File;
import java.text.MessageFormat;
import javax.inject.Inject;

/**
 *
 * @author subwiz
 */
public class ValidatorUtil {
    
    @Inject private ConfigLoader exeConfig;
    @Inject private CliCommand cliCmd;
    
    public void validate(File masterConfigFile,
                File contentDir,
                File templateDir,
                File outDir
            ) throws ValidationException {
        if(!contentDir.exists() || !contentDir.isDirectory()) {
            throw new ValidationException("Content directory not available.");
        }
        if(!templateDir.exists() || !templateDir.isDirectory()) {
            throw new ValidationException("Template directory not available.");
        }
        if(!masterConfigFile.isFile()) {
            throw new ValidationException(
                    MessageFormat.format(
                            "Configuration file `{0}{1}' not available.",
                            Constants.MASTER_CONFIG,
                            exeConfig.getFileExtension()));
        }
        if(!cliCmd.force) {
            if(!Util.isDirEmptyOrNotExists(outDir)) {
                throw new ValidationException("Target directory not empty. Stopping...");
            }
        }
    }
    
}
