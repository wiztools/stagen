package org.wiztools.stagen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.wiztools.commons.StreamUtil;
import org.wiztools.commons.ZipUtil;

/**
 *
 * @author subwiz
 */
public class RunnerInit implements Runner {

    @Override
    public void run(File baseDir) throws IOException, ExecutorException {
        if(Util.isDirEmpty(baseDir)) {
            File tmpFile = File.createTempFile("stagen_template_", ".zip");
            InputStream is = getClass().getResourceAsStream("/stagen_tmpl/stagen_init.zip");
            StreamUtil.copy(is, new FileOutputStream(tmpFile));
            ZipUtil.unzip(tmpFile, baseDir);
            tmpFile.delete();
        }
        else {
            throw new ValidationException("Directory not empty--can `init' only an empty directory.");
        }
    }
    
}
