package converter;

import converter.readers.TeaFileReader;
import converter.writers.TeaFileWriter;
import converter.FileFormats;

/**
 * A TeaFileConverter can convert between different tea file formats
 * 
 * @author Mats Palm
 */
public abstract class TeaFileConverter  {
	
	/**
	 * Do the conversion
	 * 
	 * @param inFileName Input file name
	 * @param inFileName Input file format
	 * @param outFileFormat Output file format
	 * @param outFileName Output file name
	 * @return If the conversion was succesfull or not
	 */ 	 
	public static boolean convert(String inFileName, String inFileFormat, String outFileFormat, String outFileName) {
		
		TeaFileReader reader = FileFormats.getReader(inFileFormat);
		TeaFileWriter writer = FileFormats.getWriter(outFileFormat);
			
		try {
			writer.writeFile(reader.readFile(inFileName), outFileName);
		} catch (Exception e) {
			System.err.println("A fault occured during conversion: " + e);
			return false;
		}
		return true;
	}
}
