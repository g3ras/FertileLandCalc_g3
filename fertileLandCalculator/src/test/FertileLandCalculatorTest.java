package com.fertileLandCalculator;


import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FertileLandCalculatorTest {
	    
	    int length=400, width=600;
	    
	    FertileLandCalculator fcalc = new FertileLandCalculator();
	    
	       
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
	     * Test of getFertileLandArea method, of class FertileLandCalculator.
	     * @throws Exception 
	     */
	    
	    @Test
	    public void testGetFertileLandArea_1() throws Exception {
	        String[] strSTDIN = {"0 292 399 307"};
	        String STDOUT = "116800 116800 ";
	        
	        
	        assertEquals(STDOUT, fcalc.getFertileLandArea(strSTDIN,length,width));
	  
	    }
	    
	  
	    @Test
	    public void testGetFertileLandArea_2() throws Exception {
	        String[] strSTDIN = {"48 192 351 207", "48 392 351 407", "120 52 135 547", "260 52 275 547"};
	        String STDOUT = "22816 192608 ";
	        
	        
	        assertEquals(STDOUT, fcalc.getFertileLandArea(strSTDIN,length,width));
	  
	    } 
	    
	
	    @Test
	    public void testGetFertileLandArea_3() throws Exception {
	        String[] strSTDIN = {"0 3 4 4"};
	        String STDOUT = "239990 ";
	        
	        
	        assertEquals(STDOUT, fcalc.getFertileLandArea(strSTDIN,length,width));
	  
	    }
	    
	  
	    @Test
	    public void testGetFertileLandArea_4() throws Exception {
	        String[] strSTDIN = {"0 0 0 599"};
	        String STDOUT = "239400 ";
	        
	        
	        assertEquals(STDOUT, fcalc.getFertileLandArea(strSTDIN,length,width));
	  
	    }
	    
	    
	    @Test
	    public void testGetFertileLandArea_5() throws Exception {
	        String[] strSTDIN = {"0 0 1 1", "2 0 2 599"};
	        String STDOUT = "1196 238200 ";
	        
	        
	        assertEquals(STDOUT, fcalc.getFertileLandArea(strSTDIN,length,width));
	  
	    }
	    
	    @Test
	    public void testGetFertileLandArea_6() throws Exception {
	        String[] strSTDIN = {"0 0 399 599"};
	        String STDOUT = "No Fertile area found.";
	        
	        
	        assertEquals(STDOUT, fcalc.getFertileLandArea(strSTDIN,length,width));
	  
	    }
	    
	}






