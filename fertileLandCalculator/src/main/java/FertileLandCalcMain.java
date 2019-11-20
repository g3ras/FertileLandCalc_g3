package com.fertileLandCalculator;

import java.util.Scanner;

public class FertileLandCalcMain {

	  public static void main(String[] args) throws Exception{
		int length = 400;
	    int width = 600;
	    FertileLandCalculator fcalc = new FertileLandCalculator();
  		Scanner sc = new Scanner(System.in);
        System.out.println("EnterInput:");
        while(sc.hasNextLine()) {
        	String str = sc.nextLine();
        	str = str.replaceAll("\\{", "");
        	str = str.replaceAll("\\}", "");
        	str = str.replaceAll("\"", "");
        	String[] input = str.split(",");
        	String[] inputArr = new String[input.length];
        	for(int i=0;i<input.length;i++) {
        		inputArr[i] = input[i].trim();
        	}	
        	System.out.println("Calculating fertile land area...");
	        String fertileLandArea = fcalc.getFertileLandArea(inputArr,length,width);
	        if(fertileLandArea!="")
	          System.out.println("Input :"+str+"  Fertile Land Area : "+fertileLandArea);
        	}
        }
  }


