package org.wiztools.stagen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringWriter;
import org.tautua.markdownpapers.Markdown;
import org.tautua.markdownpapers.parser.ParseException;

/**
 *
 * @author subwiz
 */
public class MDContentTransformExecutor implements ContentTransformExecutor {

    @Override
    public String transform(File file) throws ExecutorException {
        try {
            Markdown md = new Markdown();
            StringWriter sw = new StringWriter();
            md.transform(new FileReader(file), sw);
            return sw.toString();
        }
        catch(FileNotFoundException | ParseException ex) {
            throw new ExecutorException(ex);
        }
    }
    
}
