package org.wiztools.stagen;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import org.wiztools.commons.Charsets;
import org.wiztools.commons.FileUtil;

/**
 *
 * @author subwiz
 */
public class RunnerImpl implements Runner {
    
    @Inject private ContentTransformExecutor cte;
    @Inject private TemplateExecutor te;
    
    @Override
    public void run(File baseDir) throws IOException, ExecutorException {
        
        final Map<String, Object> data = new HashMap<>();
        
        final File contentDir = Constants.getContentDir(baseDir);
        final File templateDir = Constants.getTemplateDir(baseDir);
        final File staticDir = Constants.getStaticDir(baseDir);
        final File outDir = Constants.getOutDir(baseDir);
        
        // init the out directories:
        outDir.mkdirs();
        Util.copyDirectory(staticDir.toPath(), outDir.toPath());
        
        // Iterate through the content in contents dir:
        for(File contentFile: contentDir.listFiles()) {
            // Content transform:
            String content = cte.transform(contentFile);
            data.put("_content", content);
            
            final String baseFileName = Util.getBaseFileName(contentFile.getName());
            
            // Get template:
            final File templateFile = new File(templateDir, baseFileName + ".st");
            final String template = FileUtil.getContentAsString(templateFile, Charsets.UTF_8);
            final String rendered = te.render(data, template);
            
            // Render and write HTML:
            FileUtil.writeString(new File(outDir, baseFileName + ".html"),
                    rendered, Charsets.UTF_8);
        }
    }
}
