package com.fertileLandCalculator;

import java.util.ArrayList;
import java.util.List;

public class FertileLandCalcMain {

	  public static void main(String[] args) throws Exception{ 
	  	  String[] inputCoordinates_1 = {"0 292 399 307"};
		 // String[] inputCoordinates_1 = {"0 2 6 4"}; 
		  String[] inputCoordinates_2 = {"48 192 351 207", "48 392 351 407", "120 52 135 547", "260 52 275 547"};
	      List<String[]> inputList = new ArrayList();
	      inputList.add(inputCoordinates_1);
  		  inputList.add(inputCoordinates_2);
	      	        
	      int length = 400;
	      int width = 600;

	      FertileLandCalculator fcalc = new FertileLandCalculator();
  	      for(String[] inputCoordinates:inputList) {
		    	 System.out.println("Calculating fertile land area...");
		         String fertileLandArea = fcalc.getFertileLandArea(inputCoordinates,length,width);
		         StringBuilder str = new StringBuilder();
		         str.append("{");
		         for(int s=0;s<inputCoordinates.length;s++) {
		          	 str.append("\"");
		           	 str.append(inputCoordinates[s]);
		           	 str.append("\"");
		           	 if(s!=inputCoordinates.length-1)
		           	    str.append(",");
		         }
		         str.append("}");	 
		         System.out.println("Input :"+str+"  Fertile Land Area : "+fertileLandArea);
	      }
      }

}
