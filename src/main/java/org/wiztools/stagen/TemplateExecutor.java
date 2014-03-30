package org.wiztools.stagen;

import com.google.inject.ImplementedBy;
import java.util.Map;

/**
 *
 * @author subwiz
 */
@ImplementedBy(STTemplateExecutor.class)
public interface TemplateExecutor extends FileExtension {
    public String render(Map<String, Object> data, String template) throws ExecutorException;
}
