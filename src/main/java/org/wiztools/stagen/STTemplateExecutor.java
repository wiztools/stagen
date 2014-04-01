package org.wiztools.stagen;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Map;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;
import org.stringtemplate.v4.STRawGroupDir;
import org.wiztools.commons.Charsets;

/**
 *
 * @author subwiz
 */
public class STTemplateExecutor implements TemplateExecutor {
    
    private static final char delimiterStartChar = '$';
    private static final char delimiterStopChar = '$';

    @Override
    public String render(Map<String, Object> data, File templateFile) throws ExecutorException {
        try {
            final File tmplDir = templateFile.getParentFile();
            final String tmplName = Util.getBaseFileName(templateFile.getName());
            
            STGroupDir stg = new STRawGroupDir(tmplDir.toURI().toURL(),
                    Charsets.UTF_8.name(),
                    delimiterStartChar,
                    delimiterStopChar);
            
            ST st = stg.getInstanceOf(tmplName);
            
            // Populate data:
            data.entrySet().stream().forEach((e) -> {
                st.add(e.getKey(), e.getValue());
            });
            
            return st.render();
        }
        catch(MalformedURLException ex) {
            throw new ExecutorException(ex);
        }
    }

    @Override
    public String getFileExtension() {
        return ".st";
    }
    
}
