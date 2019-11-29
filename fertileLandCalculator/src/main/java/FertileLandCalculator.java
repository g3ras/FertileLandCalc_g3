package com.fertileLandCalculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue; 

public class FertileLandCalculator {	
	  private static Map<String,Coordinates> areaMatrixMap = new HashMap<String,Coordinates>();	
	  	  
	  public FertileLandCalculator() {}
	
	  /** This method finds and calculates the fertile land area from the given input coordinates of the barrenLands
	  * a) calculate the totalBarrenCooridinates and store it as a List<Coordinates>
	  * b) populate the areaMatrix Map with barren lands marked as checked and fertile land as unchecked in the coordinates
	  * c) calculate the fertileLandArea by traversing through the areaMatrix coordinates using counter, queue for adding nearby coordinates keys and validating if checked  
	  * @param inputCoordinates - (left bottom, right upper) of barrenLand
	  * @return get the String of fertile land area sorted out from smallest to largest separated by space
	  */ 
	  
	  public  String getFertileLandArea(String[] inputCoordinates,int length,int width) throws Exception { 
		  Map<String,Coordinates> totalBarrenLandCoordinates = new HashMap<String,Coordinates>();
	         List<Integer> fertileLandArr = new ArrayList<>();
	         String fertileLandArea = ""; 
	         try {	                  
		         totalBarrenLandCoordinates = getBarrenLandCoordinates(inputCoordinates);
		         	         
		         populateAreaMatrix(totalBarrenLandCoordinates,length,width); 
		         String firstKey = 0+","+0;
		         fertileLandArr = calculateFertileLandArea(fertileLandArr, length, width, firstKey);	
		         Collections.sort(fertileLandArr);
		         if(!fertileLandArr.isEmpty()) {  	    	    
		             for (Integer fLandArea : fertileLandArr) { 
		            	 fertileLandArea += fLandArea.toString() + " "; 
		           } 
		         } else { 
		        	 fertileLandArea = "No Fertile area found."; 
		         } 
	         }catch(Exception e) {
	        	 System.out.println("Invalid input."+e);
	         }
	         return fertileLandArea; 
	  }  
	     
	     
      /** 
	  * This method gets all the coordinates of the barren land.
	  * @param inputCoordinates String[] 
	  * @return  List<Coordinates> totalBarrenLandCoordinates
	  */ 
	  private  Map<String,Coordinates> getBarrenLandCoordinates(String[] inputCoordinates) { 
		  Map<String,Coordinates> totalBarrenLandCoordinates = new HashMap<String,Coordinates>();
   		    for (String str:inputCoordinates) { 	        	 
	        	 Integer x0 = 0, x1 = 0, y0 = 0, y1 = 0;
	             String[] barrenCoordinateStr = str.split(" ");
	             for (int i = 0; i < barrenCoordinateStr.length; i++) {	            	
	            	 x0 = Integer.parseInt(barrenCoordinateStr[0]);
	            	 y0 = Integer.parseInt(barrenCoordinateStr[1]);
	            	 x1 = Integer.parseInt(barrenCoordinateStr[2]);
	            	 y1 = Integer.parseInt(barrenCoordinateStr[3]);	            	            	 
	             }
	             for (int i = x0; i <= x1; i++) { 
	                 for (int j = y0; j <= y1; j++) { 
	                     Coordinates coordinates = new Coordinates(i, j); 	                     
	                     totalBarrenLandCoordinates.put(i+","+j,coordinates); 
	                 } 
	             }
	         }
   		    return totalBarrenLandCoordinates;
	  }
	  
	     
	  /** 
	  * For each coordinate, if it's present in the totalBarrenLand list, mark that coordinate as barren and checked 
	  * set the coordinates to the areaMatrixMap
	  * @param totalBarrenLandCoordinates List<Coordinates> 
	  * @param length
	  * @param width
	  * @return void
	  */ 
	  private void populateAreaMatrix(Map<String,Coordinates> totalBarrenLandCoordinates,int length,int width) {
		  for (int y = 0; y < width; y++) {			  
	          for (int x = 0; x < length; x++) {
	             Coordinates co = new Coordinates(x, y);	              
	              if (totalBarrenLandCoordinates.containsKey(x+","+y)) {                 
                      co.setIsBarren(true); 
                      co.setChecked(true);                      
	                } else { 
	                   co.setIsBarren(false); 
	                }
	              areaMatrixMap.put(x+","+y, co); 	             
	          } 
	      }
		
      } 
	 
	     
	  /** 
	  * Check through areaMatrixMap, find first unchecked coordinate, 
	  * traverseAndCheckFertileLand connected to that coordinate, and return the total area 
	  * @param fland
	  * @param length
	  * @param width  
	  * @param String key 
	  * @return List of area of each fertile land 
	  */ 
	  private List<Integer> calculateFertileLandArea(List<Integer> fLand, int length,int width,String firstKey) {
		  String[] firstKeys = firstKey.split(",");
		  int xVal = Integer.parseInt(firstKeys[0]),yVal = Integer.parseInt(firstKeys[1]);
		  for (int x = xVal; x < length; x++) {
			  for (int y = yVal; y < width; y++) { 
	        	  String key = x+","+y;	        	 
	              Coordinates co = areaMatrixMap.get(key); 
	              if (!co.isChecked()) {
	                  Map totalFertileAreaMap = traverseAndCheckFertileLand(areaMatrixMap, length, width, key);	                
	                  fLand.add((int)totalFertileAreaMap.get("count"));	  	                 
	                  String lastCheckedKey = (String)totalFertileAreaMap.get("lastCheckedKey");	                  
	                  calculateFertileLandArea(fLand, length, width,lastCheckedKey); 
	               } 
	          } 
	      } 
	      return fLand; 
	  } 
	 
	 
	  /** 
	  * Loop through each coordinate in the fertile land to validate if it traversed and checked 
	  * @param areaMatrix
	  * @param length
	  * @param width 
	  * @param x 
	  * @param y 
	  * @return Map (lastCheckedKey,fertile Land area) 
	  */
	  private Map traverseAndCheckFertileLand(Map<String,Coordinates> areaMatrix, int length, int width, String key) { 
	      int count = 0; 
	      //System.out.println(areaMatrix);
	      Map<String,Object> fertileLandAreaMap = new HashMap<String,Object>();
	      Queue<String> queue = new LinkedList<String>();  
	      queue.add(key);
	      while (!queue.isEmpty()) { // while loop until all the key elements in the queue are checked 
	    	   //System.out.println(queue);
	            String k = queue.remove(); //get the queue head element
	            if(isCoordinateUnChecked(areaMatrix, k,length,width)) {  
	            	String[] qco = k.split(",");
	       		    int qx = Integer.parseInt(qco[0]),qy=Integer.parseInt(qco[1]);
	            	//Count the coordinates of the fertile land that marked as checked
	            	count += 1;  
	            	//Get the nearby coordinates for the passed key in the queue
	                int x0=qx-1, x1=qx+1, y0=qy-1, y1=qy+1;	 
	                String key1 = qx+","+y0;
	                String key2 = qx+","+y1;
	                String key3 = x0+","+qy;
	                String key4 = x1+","+qy;
	                
	               //verify if the nearby coordinates are checked or not, if not checked add the corresponding keys to the queue
	                if (y0 >= 0 && !areaMatrix.get(key1).isChecked()) 	                  
	                	queue.add(key1); 
	                 
	                if (y1 < width && !areaMatrix.get(key2).isChecked()) 		              
	                	queue.add(key2); 
	                 
	                 if ( x0 >= 0 && !areaMatrix.get(key3).isChecked()) 
	                	 queue.add(key3); 
	                 
	                 if (x1 < length && !areaMatrix.get(key4).isChecked()) 	                	
	                	 queue.add(key4);
	                 
	                 fertileLandAreaMap.put("lastCheckedKey",k);
	             }
	      }
	      fertileLandAreaMap.put("count", count);
	      return fertileLandAreaMap; 
	  }
	     
	 
	  /** 
	  * verify if coordinate has been checked already - if not, set checked to true 
	  * @param areaMatrix Map 
	  * @param Coordinates
	  * @param length
	  * @param width 
	  * @return boolean value representing whether coordinate c has been checked or not 
	  */ 
	  private static boolean isCoordinateUnChecked(Map<String,Coordinates> areaMatrix, String k,int length,int width) {
		   String[] co = k.split(",");
		   int x = Integer.parseInt(co[0]),y=Integer.parseInt(co[1]);
           //verify if the Coordinate c is not outside the boundaries of the areaMatrix
	       if (x < 0 ||y < 0 || x >= length ||y >= width) { 
	           return false; 
	       }
	       Coordinates coordinateToCheck = areaMatrix.get(k);	 
	       if (coordinateToCheck.isChecked()) { 
	           return false; 
	       } 	 
	      coordinateToCheck.setChecked(true);	 
	      return true; 
	  }  

} 



