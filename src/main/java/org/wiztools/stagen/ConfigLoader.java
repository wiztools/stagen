package org.wiztools.stagen;

import com.google.inject.ImplementedBy;
import java.io.File;
import java.util.Map;

/**
 *
 * @author subwiz
 */
@ImplementedBy(JsonConfigLoader.class)
public interface ConfigLoader extends FileExtension {
    Map<String, Object> getConfigMap(File dataFile) throws ExecutorException;
}
