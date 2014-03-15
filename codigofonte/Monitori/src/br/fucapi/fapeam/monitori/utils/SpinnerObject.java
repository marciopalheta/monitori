package br.fucapi.fapeam.monitori.utils;

public class SpinnerObject {

		private long databaseId;
	    private String databaseValue;
	    private int imageResourse;
	    	    
	    
		public SpinnerObject ( long databaseId , String databaseValue ) {
	        this.databaseId = databaseId;
	        this.databaseValue = databaseValue;
	    }
		
		public SpinnerObject ( String databaseValue , int imageResourse) {	        
	        this.databaseValue = databaseValue;
	        this.imageResourse = imageResourse;
	    }
		
		public SpinnerObject ( long databaseId , String databaseValue , int imageResourse) {
	        this.databaseId = databaseId;
	        this.databaseValue = databaseValue;
	        this.imageResourse = imageResourse;
	    }
		
	    public long getId () {
	        return databaseId;
	    }

	    public String getValue () {
	        return databaseValue;
	    }

	    @Override
	    public String toString () {
	        return databaseValue;
	    }
	    
	    public int getImageResourse() {
			return imageResourse;
		}

		public void setImageResourse(int imageResourse) {
			this.imageResourse = imageResourse;
		}				
		
		
	}