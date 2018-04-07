package org.wiztools.stagen;

import java.io.File;
import java.util.Map;

/**
 *
 * @author subwiz
 */
public interface TemplateExecutor extends FileExtension {
    public String render(Map<String, Object> config, File templateFile) throws ExecutorException;
}
