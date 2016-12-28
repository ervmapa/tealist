package tealist;

import java.io.IOException;
import java.util.List;

public interface Saver {

	/**
	 * Writes the tea information to a file. 
	 * 
	 * @param teaList A list of the tea
	 * @param fileName Name of the file, if it is null the file will be written to 
	 *                 system.out
	 * @throws IOException If there was an I/O error
	 */
	public void writeFile(List<Tea> teaList, String fileName) throws Exception;

}
