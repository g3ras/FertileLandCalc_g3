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
	  * c) calculate the fertileLandArea by traversing through the areaMatrix coordinates using counter, queue for storing and validating nearby coordinates  
	  * @param inputCoordinates - (left bottom, right upper) of barrenLand
	  * @return get the String of fertile land area sorted out from smallest to largest separated by space
	  */ 
	  
	  public  String getFertileLandArea(String[] inputCoordinates,int length,int width) throws Exception { 
	    	 List<Coordinates> totalBarrenLandCoordinates = new ArrayList<>();
	         List<Integer> fertileLandArr = new ArrayList<>();
	         String fertileLandArea = ""; 
	         try {	                  
		         totalBarrenLandCoordinates = getBarrenLandCoordinates(inputCoordinates);
		         	         
		         populateAreaMatrix(totalBarrenLandCoordinates,length,width); 
		         
		         fertileLandArr = calculateFertileLandArea(fertileLandArr, length, width, 0, 0);	
		         Collections.sort(fertileLandArr);
		         if(!fertileLandArr.isEmpty()) {  	    	    
		             for (Integer fLandArea : fertileLandArr) { 
		            	 fertileLandArea += fLandArea.toString() + " "; 
		           } 
		         } else { 
		        	 fertileLandArea = "No Fertile area found."; 
		         } 
	         }catch(Exception e) {
	        	 System.out.println("Invalid input.");
	         }
	         return fertileLandArea; 
	  }  
	     
	     
      /** 
	  * This method gets all the coordinates of the barren land.
	  * @param inputCoordinates String[] 
	  * @return  List<Coordinates> totalBarrenLandCoordinates
	  */ 
	  private  List<Coordinates> getBarrenLandCoordinates(String[] inputCoordinates) { 
		  List<Coordinates> totalBarrenLandCoordinates = new ArrayList<>();
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
	                     totalBarrenLandCoordinates.add(coordinates); 
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
	  private void populateAreaMatrix(List<Coordinates> totalBarrenLandCoordinates,int length,int width) {
		  for (int y = 0; y < width; y++) { 
	          for (int x = 0; x < length; x++) {
	             Coordinates co = new Coordinates(x, y);	              
	              for (Coordinates c : totalBarrenLandCoordinates) {	                 
	                  if (c.getX() == x && c.getY() == y) { 
	                      co.setIsBarren(true); 
	                      co.setChecked(true); 
	                      break; 
	                   } else { 
	                       co.setIsBarren(false); 
	                   } 
	              } 
	              String key = String.valueOf(x)+","+String.valueOf(y);
	              areaMatrixMap.put(key, co); 	                 
	          } 
	      }
		
      } 
	 
	     
	  /** 
	  * Check through areaMatrixMap, find first unchecked coordinate, 
	  * traverseAndCheckFertileLand connected to that coordinate, and return the total area 
	  * @param fland
	  * @param length
	  * @param width  
	  * @param xVal 
	  * @param yVal 
	  * @return List of area of each fertile land 
	  */ 
	  private List<Integer> calculateFertileLandArea(List<Integer> fLand, int length,int width,int xVal, int yVal) { 
	      for (int y = yVal; y < width; y++) { 
	          for (int x = xVal; x < length; x++) {
	        	  String key = String.valueOf(x)+","+String.valueOf(y);	               	                 
	              Coordinates co = areaMatrixMap.get(key); 
	              if (!co.isChecked()) {
	                  int totalFertileArea = traverseAndCheckFertileLand(areaMatrixMap, length, width, x, y); 
	                  fLand.add(totalFertileArea); 
	                  calculateFertileLandArea(fLand, x, y, length, width); 
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
	  * @return int count - fertile Land area 
	  */
	  private int traverseAndCheckFertileLand(Map<String,Coordinates> areaMatrix, int length, int width, int x, int y) { 
	      int count = 0; 
	      Queue<Coordinates> queue = new LinkedList<Coordinates>();  
	      queue.add(new Coordinates(x, y)); 	     	       
	      while (!queue.isEmpty()) { // while loop until all the elements in the queue are checked 
	            Coordinates c = queue.remove(); //get the queue head element	             
	            if(isCoordinateUnChecked(areaMatrix, c,length,width)) {       
	            	//Count the coordinates of the fertile land that marked as checked
	            	count += 1;  
	            	//Get the nearby coordinates for the passed coordinate in the queue
	                int x0=c.getX()-1, x1=c.getX()+1, y0=c.getY()-1, y1=c.getY()+1;	 
	                String key1 = String.valueOf(c.getX())+","+String.valueOf(y0);
	                String key2 = String.valueOf(c.getX())+","+String.valueOf(y1);
	                String key3 = String.valueOf(x0)+","+String.valueOf(c.getY());
	                String key4 = String.valueOf(x1)+","+String.valueOf(c.getY());
	               //verify if the nearby coordinates are checked or not, if not checked add to the queue
	                if (y0 >= 0 && !areaMatrix.get(key1).isChecked()) 	                  
	                	queue.add(new Coordinates(c.getX(), y0)); 
	                 
	                if (y1 < width && !areaMatrix.get(key2).isChecked()) 		              
	                	queue.add(new Coordinates(c.getX(), y1)); 
	                 
	                 if ( x0 >= 0 && !areaMatrix.get(key3).isChecked()) 
	                	 queue.add(new Coordinates(x0, c.getY())); 
	                 
	                 if (x1 < length && !areaMatrix.get(key4).isChecked()) 	                	
	                	 queue.add(new Coordinates(x1, c.getY())); 
	                 
	             }
	      }
	      
	      return count; 
	  }
	     
	 
	  /** 
	  * verify if coordinate has been checked already - if not, set checked to true 
	  * @param areaMatrix Map 
	  * @param Coordinates
	  * @param length
	  * @param width 
	  * @return boolean value representing whether coordinate c has been checked or not 
	  */ 
	  private static boolean isCoordinateUnChecked(Map<String,Coordinates> areaMatrix, Coordinates c,int length,int width) {	 
           //verify if the Coordinate c is not outside the boundaries of the areaMatrix
	       if (c.getX() < 0 || c.getY() < 0 || c.getX() >= length || c.getY() >= width) { 
	           return false; 
	       }	
	       String key = String.valueOf(c.getX())+","+String.valueOf(c.getY());
	       Coordinates coordinateToCheck = areaMatrix.get(key);	 
	       if (coordinateToCheck.isChecked()) { 
	           return false; 
	       } 	 
	      coordinateToCheck.setChecked(true);	 
	      return true; 
	  }  

} 



