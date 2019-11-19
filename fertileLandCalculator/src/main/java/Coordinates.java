package com.fertileLandCalculator;

public class Coordinates {

      
	     private Integer x; 
	     private Integer y; 
	     private boolean isBarren; 
	     private boolean checked = false;
	     private String text="-";
	 
	     public Coordinates() { 
	          
	     } 
	      
	     public Coordinates(Integer x, Integer y) { 
	         this.x = x; 
	         this.y = y;	
	     } 
	      
	     public Integer getX() { 
	         return x; 
	     } 
	 
	 
	     public void setX(Integer x) { 
	         this.x = x; 
	     } 
	 
	 
	     public Integer getY() { 
	         return y; 
	     } 
	 
	 
	     public void setY(Integer y) { 
	         this.y = y; 
	     } 
	 
	 
	     public boolean isIsBarren() { 
	         return isBarren;
	     } 
	 
	 
	     public void setIsBarren(boolean isBarren) { 
	         this.isBarren = isBarren; 
	         if(isBarren) { 
	               text ="*"; 
	           }

	     } 	 
	 
	     public boolean isChecked() { 
	         return checked;	         
	     } 
	 
	 
	     public void setChecked(boolean checked) { 
	         this.checked = checked; 
	       
	     }

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		} 
	     
	     
	      
	      
	 } 



