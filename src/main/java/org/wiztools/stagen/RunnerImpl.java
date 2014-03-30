package org.wiztools.stagen;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.wiztools.commons.Charsets;
import org.wiztools.commons.FileUtil;

/**
 *
 * @author subwiz
 */
public class RunnerImpl implements Runner {
    
    private static final Logger LOG = Logger.getLogger(RunnerImpl.class.getName());
    
    @Inject private ContentTransformExecutor cte;
    @Inject private TemplateExecutor te;
    @Inject private DataLoader dl;
    
    @Override
    public void run(File baseDir) throws IOException, ExecutorException {
        // init all the directories:
        final File contentDir = Constants.getContentDir(baseDir);
        final File templateDir = Constants.getTemplateDir(baseDir);
        final File staticDir = Constants.getStaticDir(baseDir);
        final File outDir = Constants.getOutDir(baseDir);
        
        LOG.log(Level.INFO, "Content dir: {0}.", contentDir);
        LOG.log(Level.INFO, "Template dir: {0}.", templateDir);
        LOG.log(Level.INFO, "Static dir: {0}.", staticDir);
        LOG.log(Level.INFO, "Out dir: {0}.", outDir);
        
        // init master data:
        final Map<String, Object> masterData = dl.getData(
                new File(baseDir, "master.json"));
        LOG.info("Loaded master data from `master.json'.");
                
        // init the out directories:
        outDir.mkdirs();
        Util.copyDirectory(staticDir.toPath(), outDir.toPath());
        LOG.info("Copied static contents.");
        
        // Iterate through the content in contents dir:
        for(File contentFile: contentDir.listFiles()) {
            // Create a copy of the map for use with this instance:
            Map<String, Object> data = new HashMap<>();
            data.putAll(masterData);
            
            // Content transform:
            String content = cte.transform(contentFile);
            data.put("_content", content);
            LOG.info("Transformed content.");
            
            final String baseFileName = Util.getBaseFileName(contentFile.getName());
            
            // Get template:
            final File templateFile = new File(templateDir, baseFileName + ".st");
            if(templateFile.exists() && templateFile.canRead()) {
                final String template = FileUtil.getContentAsString(
                        templateFile, Charsets.UTF_8);
                final String rendered = te.render(data, template);

                // Render and write HTML:
                FileUtil.writeString(new File(outDir, baseFileName + ".html"),
                        rendered, Charsets.UTF_8);
            }
            else {
                LOG.log(Level.WARNING, "Template file {0} does not exist. Skipping.",
                        templateFile.getName());
            }
        }
    }
}
