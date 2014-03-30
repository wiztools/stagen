package org.wiztools.stagen;

import com.google.inject.ImplementedBy;
import java.io.File;
import java.util.Map;

/**
 *
 * @author subwiz
 */
@ImplementedBy(JsonDataLoader.class)
public interface DataLoader {
    Map<String, Object> getData(File dataFile) throws ExecutorException;
}
