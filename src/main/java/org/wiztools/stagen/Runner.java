package org.wiztools.stagen;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author subwiz
 */
public interface Runner {

    void run(File baseDir) throws IOException, ExecutorException;
    
}
