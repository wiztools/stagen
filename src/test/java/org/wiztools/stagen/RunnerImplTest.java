package org.wiztools.stagen;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author subwiz
 */
public class RunnerImplTest {

    public RunnerImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of run method, of class RunnerImpl.
     */
    @Test
    public void testRun() throws Exception {
        System.out.println("run");
        File tmplDir = new File("src/test/resources");
        File baseDir = new File("build/test-site");
        baseDir.mkdirs();
        FileUtils.cleanDirectory(baseDir);
        Util.copyDirectory(tmplDir.toPath(), baseDir.toPath());

        Runner instance = ServiceLocator.getInstance(Runner.class);
        instance.run(baseDir);

        if(!Constants.getOutDir(baseDir).exists()) {
            fail("Out dir does not exist.");
        }
    }

}
