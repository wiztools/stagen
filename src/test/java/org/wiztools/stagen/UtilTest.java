package org.wiztools.stagen;

import org.wiztools.stagen.Util;
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
public class UtilTest {
    
    public UtilTest() {
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
     * Test of getBaseFileName method, of class Util.
     */
    @Test
    public void testGetBaseFileName() {
        System.out.println("getBaseFileName");
        String fileName = "aarthi.wiz";
        String expResult = "aarthi";
        String result = Util.getBaseFileName(fileName);
        assertEquals(expResult, result);
    }
    
}
