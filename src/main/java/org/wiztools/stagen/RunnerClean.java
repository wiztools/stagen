package org.wiztools.stagen;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author subwiz
 */
public class RunnerClean implements Runner {

    @Override
    public void run(File baseDir) throws IOException, ExecutorException {
        final File outDir = Constants.getOutDir(baseDir);
        FileUtils.deleteDirectory(outDir);
    }

}
