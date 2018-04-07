package org.wiztools.stagen;

import java.io.File;

/**
 *
 * @author subwiz
 */
public interface ContentTransformExecutor extends FileExtension {
    String transform(File file) throws ExecutorException;
}
