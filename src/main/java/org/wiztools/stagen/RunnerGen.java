package org.wiztools.stagen;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.apache.commons.io.FileUtils;
import org.wiztools.commons.Charsets;
import org.wiztools.commons.FileUtil;

/**
 *
 * @author subwiz
 */
public class RunnerGen implements Runner {
    
    private static final Logger LOG = Logger.getLogger(RunnerGen.class.getName());
    
    @Inject private ContentTransformExecutor exeContent;
    @Inject private TemplateExecutor exeTmpl;
    @Inject private ConfigLoader exeConfig;
    @Inject private CliCommand cliCmd;
    @Inject private ValidatorUtil validator;
    
    @Override
    public void run(File baseDir) throws IOException, ExecutorException {
        // init all the directories:
        final File contentDir = Constants.getContentDir(baseDir);
        final File templateDir = Constants.getTemplateDir(baseDir);
        final File configDir = Constants.getConfigDir(baseDir);
        final File staticDir = Constants.getStaticDir(baseDir);
        final File outDir = Constants.getOutDir(baseDir);
        
        LOG.log(Level.INFO, "Content dir: {0}.", contentDir);
        LOG.log(Level.INFO, "Template dir: {0}.", templateDir);
        LOG.log(Level.INFO, "Static dir: {0}.", staticDir);
        LOG.log(Level.INFO, "Out dir: {0}.", outDir);
        
        final File masterConfigFile = new File(configDir,
                Constants.MASTER_CONFIG + exeConfig.getFileExtension());
        
        // Validation:
        validator.validate(masterConfigFile, contentDir, templateDir, outDir);
        
        // init master config:
        final Map<String, Object> masterConfMap = Collections.unmodifiableMap(
                exeConfig.getConfigMap(masterConfigFile));
        LOG.log(Level.INFO, "Loaded master config from `{0}'.",
                masterConfigFile.getName());
                
        // init the out directories:
        outDir.mkdirs();
        FileUtils.cleanDirectory(outDir);
        if(staticDir.exists() && staticDir.isDirectory()) {
            Util.copyDirectory(staticDir.toPath(), outDir.toPath());
            LOG.info("Copied static contents.");
        }
        else {
            LOG.warning("Static directory not available--no content copied.");
        }
        
        // Iterate through the content in contents dir:
        for(File contentFile: contentDir.listFiles(
                (f)->{return f.getName().endsWith(exeContent.getFileExtension());})) {
            // Get base file name:
            final String fileName = contentFile.getName();
            final String baseFileName = Util.getBaseFileName(fileName);
            
            LOG.log(Level.INFO, "Start processing content: {0}", fileName);
            
            // Create a copy of the map for use with this instance:
            final Map<String, Object> confMap = new HashMap<>();
            confMap.putAll(masterConfMap);
            
            // Custom config:
            final File customConfFile = new File(
                    configDir,
                    baseFileName + exeConfig.getFileExtension());
            if(customConfFile.exists()) {
                confMap.putAll(exeConfig.getConfigMap(customConfFile));
                LOG.log(Level.INFO, "Custom config loaded: {0}",
                        customConfFile.getName());
            }
            else {
                LOG.log(Level.INFO, "Custom config NOT available for content: {0}",
                        fileName);
            }
            
            // Content transform:
            String content = exeContent.transform(contentFile);
            confMap.put("_content", content);
            LOG.log(Level.INFO, "Transformed content: {0}", fileName);
            
            // Get template:
            final File templateFile = Util.resolveFile(()->{
                // Page specific template available?
                File customTemplate = new File(templateDir,
                        baseFileName + exeTmpl.getFileExtension());
                if(!customTemplate.exists()) {
                    // Return default template:
                    LOG.log(Level.INFO, "Using default template for content {0}", fileName);
                    return new File(templateDir,
                            Constants.DEFAULT_TMPL + exeTmpl.getFileExtension());
                }
                else {
                    LOG.log(Level.INFO, "Using custom template for content {0}", fileName);
                    return customTemplate;
                }
            });
            if(templateFile.exists()) {
                final String rendered = exeTmpl.render(confMap, templateFile);

                // Render and write HTML:
                final File htmlFile = new File(outDir,
                        baseFileName + Constants.HTML_EXTENSION);
                FileUtil.writeString(htmlFile, rendered, Charsets.UTF_8);
                LOG.log(Level.INFO,"Successfully generated: {0}", htmlFile.getName());
            }
            else {
                LOG.log(Level.WARNING, "Template file {0} does not exist. Skipping.",
                        templateFile.getName());
            }
        }
    }
}
