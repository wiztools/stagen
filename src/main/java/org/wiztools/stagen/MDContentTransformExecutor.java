package org.wiztools.stagen;

import java.io.File;
import java.io.IOException;
import org.markdown4j.Markdown4jProcessor;

/**
 *
 * @author subwiz
 */
public class MDContentTransformExecutor implements ContentTransformExecutor {

    @Override
    public String transform(File file) throws ExecutorException {
        
        try {
            final String out = new Markdown4jProcessor().process(file);
            return out;
        }
        catch(IOException ex) {
            throw new ExecutorException(ex);
        }
    }

    @Override
    public String getFileExtension() {
        return ".md";
    }
    
}
