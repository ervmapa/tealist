package converter.readers;

import java.io.IOException;
import java.util.List;

import tealist.Tea;

/**
 * This interface should be implemented by something that 
 * wants to read tea information from a file
 * 
 * @author Mats Palm
 */
public interface TeaFileReader {
	
	/**
	 * This interface can be implemented by a class
	 * that needs to read a tea file.
	 * 
	 * @param fileName Name of the file
	 * @return A list of the tea in the file
	 * @throws Exception if there was an error while reading
	 */
	public List<Tea> readFile(String fileName) throws IOException;

}
