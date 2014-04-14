package org.wiztools.stagen;

import java.io.File;

/**
 *
 * @author subwiz
 */
public final class Constants {
    
    public static final int DEFAULT_PORT = 20202;
    
    public static final String HTML_EXTENSION = ".html";

    public static final File DEFAULT_DIR = new File(".");
    
    private static final String CONTENT_DIR = "content";
    private static final String TEMPLATE_DIR = "template";
    private static final String CONFIG_DIR = "config";
    private static final String STATIC_DIR = "static";
    private static final String OUT_DIR = "target";
    
    
    public static File getContentDir(File baseDir) {
        return new File(baseDir, CONTENT_DIR);
    }
    
    public static File getTemplateDir(File baseDir) {
        return new File(baseDir, TEMPLATE_DIR);
    }
    
    public static File getConfigDir(File baseDir) {
        return new File(baseDir, CONFIG_DIR);
    }
    
    public static File getStaticDir(File baseDir) {
        return new File(baseDir, STATIC_DIR);
    }
    
    public static File getOutDir(File baseDir) {
        return new File(baseDir, OUT_DIR);
    }
}
