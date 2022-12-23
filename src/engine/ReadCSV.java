package engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadCSV {

	private String filePath;

	public ReadCSV(String filePath) {
		super();
		this.filePath = filePath;
	}

	public List<List<String>> readContent(String delim) throws IOException{
		String line = "";  
		BufferedReader br = null;
		List<List<String>> content = new ArrayList<>();
		
		//parsing a CSV file into BufferedReader class constructor  
		br = new BufferedReader(new FileReader(filePath));  
		while ((line = br.readLine()) != null)   //returns a Boolean value  
		{  
			String[] pierre = line.split(delim);    // use comma as separator  
			List<String> arrayLine = new ArrayList<>(Arrays.asList(pierre));
			content.add(arrayLine);
		}  
		
		if( br != null )
			br.close();
		
		return content;
	}
}
