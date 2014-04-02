package org.wiztools.stagen;

import com.google.inject.ImplementedBy;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author subwiz
 */
@ImplementedBy(RunnerGen.class)
public interface Runner {

    void run(File baseDir) throws IOException, ExecutorException;
    
}
