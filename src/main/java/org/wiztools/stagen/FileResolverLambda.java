package org.wiztools.stagen;

import java.io.File;

/**
 *
 * @author subwiz
 */
@FunctionalInterface
public interface FileResolverLambda {
    public File getFile();
}
