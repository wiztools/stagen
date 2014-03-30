package org.wiztools.stagen;

import com.google.inject.ImplementedBy;
import java.io.File;

/**
 *
 * @author subwiz
 */
@ImplementedBy(MDContentTransformExecutor.class)
public interface ContentTransformExecutor extends FileExtension {
    String transform(File file) throws ExecutorException;
}
