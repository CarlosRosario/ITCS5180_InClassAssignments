// Assignment #1
// InClassOn.java
// Carlos Rosario

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.omg.CORBA.Environment;

import com.sun.javafx.collections.MappingChange.Map;

public class InClassOn {

	public static HashMap<String, ArrayList<String>> moviesGroupedByLetter;
	public static HashMap<Integer, Movie> movies; 
	public static ArrayList<Movie> moviesList;
	
	public static void main(String[] args) {
		movies = new HashMap<Integer, Movie>();
		moviesList = new ArrayList<Movie>();
		moviesGroupedByLetter = new HashMap<String, ArrayList<String>>();
		
		// read file into hash map
		readFileAtPath("topmovies.csv");
		
		// print movies in descending order by totalAmountForYear
		for (Entry<Integer, Movie> entry : movies.entrySet()){
			int key = entry.getKey();
			Movie tempMovie = entry.getValue();
			moviesList.add(tempMovie);
		}
		
		// Sort the movies list
		Collections.sort(moviesList, new Comparator<Movie>() {

			@Override
			public int compare(Movie o1, Movie o2) {
				
				if(o1.getTotalAmountForYear() >= o2.getTotalAmountForYear()){
					return -1;
				}
				else if(o2.getTotalAmountForYear() >= o1.getTotalAmountForYear()){
					return 1;
				}
				else {
					return 0;
				}
			}
		});
		
		// Print out movies list in descending order
		for (Movie m : moviesList){
			System.out.println(m.toString());
		}
		
		// Set up hashmap to print movies grouped by first letter
		for (Movie m: moviesList){
			String key = m.getMovieName().substring(0,1);
			if(moviesGroupedByLetter.containsKey(key)){
				moviesGroupedByLetter.get(key).add(m.getMovieName());
			}
			else {
				ArrayList<String> tempMoviesByLetter = new ArrayList<String>();
				tempMoviesByLetter.add(m.getMovieName());
				moviesGroupedByLetter.put(key, tempMoviesByLetter);
			}
		}
		
		System.out.println(System.lineSeparator());
		
		// Print out hashmap
		for (Entry<String, ArrayList<String>> entry : moviesGroupedByLetter.entrySet()){
			String key = entry.getKey();
			ArrayList<String> moviesByLetter = entry.getValue();
			System.out.print(key+": ");
			if(moviesByLetter.size() <= 1){
				System.out.print(moviesByLetter.get(0));
			}
			else{
				for (int i = 0; i < moviesByLetter.size(); i++){
					if(i >= moviesByLetter.size() - 1){
						System.out.print(moviesByLetter.get(i));
					}
					else {
						System.out.print(moviesByLetter.get(i) +", ");
					}
					
				}
			}
			System.out.println();
		}
	}
	

	public static void readFileAtPath(String filename) {
		// Lets make sure the file path is not empty or null
		if (filename == null || filename.isEmpty()) {
			System.out.println("Invalid File Path");
			return;
		}
		String filePath = System.getProperty("user.dir") + "/" + filename;
		BufferedReader inputStream = null;
		// We need a try catch block so we can handle any potential IO errors
		try {
			try {
				inputStream = new BufferedReader(new FileReader(filePath));
				String lineContent = null;
				// Loop will iterate over each line within the file.
				// It will stop when no new lines are found.
				String header = inputStream.readLine();
				while ((lineContent = inputStream.readLine()) != null) {
					//System.out.println("Found the line: " + lineContent);
					Movie movie = parseMovie(lineContent);
					movies.put(movie.getYear(), movie);
					
				}
			}
			// Make sure we close the buffered reader.
			finally {
				if (inputStream != null)
					inputStream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}// end of method

	public static Movie parseMovie(String movieRecord) { 
		
		Movie m; 
		String[] resultingTokens = movieRecord.split(",");
		int year = Integer.parseInt(resultingTokens[0]);
		String movieName = resultingTokens[1];
		double totalAmountForYear = Double.parseDouble(resultingTokens[2]);
		m = new Movie(year, movieName, totalAmountForYear);
		return m;
	}
	
}
