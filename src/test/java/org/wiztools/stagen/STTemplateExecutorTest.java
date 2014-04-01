package org.wiztools.stagen;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
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
public class STTemplateExecutorTest {
    
    public STTemplateExecutorTest() {
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
     * Test of render method, of class STTemplateExecutor.
     */
    @Test
    public void testRender() throws Exception {
        System.out.println("render");
        Map<String, Object> data = new HashMap<>();
        data.put("name", "Subhash");
        File templateFile = new File("src/test/resources/template/test.st");
        STTemplateExecutor instance = new STTemplateExecutor();
        String expResult = "Hello Subhash!";
        String result = instance.render(data, templateFile);
        assertEquals(expResult, result);
    }
    
}
