package org.wiztools.stagen;

import java.util.Map;
import org.stringtemplate.v4.ST;

/**
 *
 * @author subwiz
 */
public class STTemplateExecutor implements TemplateExecutor {
    
    private static final char startTemplate = '$';
    private static final char endTemplate = '$';

    @Override
    public String render(Map<String, Object> data, String template) throws ExecutorException {
        ST st = new ST(template, startTemplate, endTemplate);
        data.entrySet().stream().forEach((e) -> {
            st.add(e.getKey(), e.getValue());
        });
        return st.render();
    }
    
}
