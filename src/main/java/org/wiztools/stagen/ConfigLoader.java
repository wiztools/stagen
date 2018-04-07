package org.wiztools.stagen;

import java.io.File;
import java.util.Map;

/**
 *
 * @author subwiz
 */
public interface ConfigLoader extends FileExtension {
    Map<String, Object> getConfigMap(File dataFile) throws ExecutorException;
}
