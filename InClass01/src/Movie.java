// Assignment #1
// Movie.java
// Carlos Rosario

import java.util.Comparator;


public class Movie{

	private int year;
	private String movieName;
	private double totalAmountForYear;
	
	public Movie(int year, String movieName, double totalAmountForYear){
		this.year = year;
		this.movieName = movieName;
		this.totalAmountForYear = totalAmountForYear;
	}
	
	public int getYear(){
		return year;
	}
	
	public String getMovieName(){
		return movieName;
	}
	
	public double getTotalAmountForYear(){
		return totalAmountForYear;
	}
	
	public void setYear(int year){
		this.year = year;
	}
	
	public void setMovieName(String movieName){
		this.movieName = movieName;
	}
	
	public void setTotalAmountForYear(double totalAmountForYear){
		this.totalAmountForYear = totalAmountForYear;
	}
	
	public String toString(){
		return getYear() + " " + getMovieName() + " " + getTotalAmountForYear();
	}
}
