package converter.readers;

import java.io.IOException;
import java.util.List;

import tealist.Tea;

public interface Reader {
	
	/**
	 * Converts a text to a list
	 * 
	 * @param fileName Name of the file
	 * @return A list of the tea in the file
	 * @throws Exception if there was an error while converting
	 */
	public List<Tea> readFile(String fileName) throws IOException;

}
