package converter;

import converter.readers.TeaFileReader;
import converter.writers.TeaFileWriter;

/**
 * A TeaFileConverter can convert between different tea file formats
 * 
 * @author Mats Palm
 */
public class TeaFileConverter implements Converter {
	
	private ConverterFactory converterFactory;
	private String inFileName;
	private String inFileFormat;
	private String outFileFormat;
	private String outFileName;
		
	/**
	 * Constructor for TeaFileConverter
	 * 
	 * @param inFileName Input file name
	 * @param inFileName Input file format
	 * @param outFileFormat Output file format
	 * @param outFileName Output file name
	 */
	public TeaFileConverter(String inFileName, String inFileFormat, String outFileFormat, String outFileName) {
		
		this.inFileName = inFileName;
		this.inFileFormat = inFileFormat;
		this.outFileFormat = outFileFormat;
		this.outFileName = outFileName;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean convert() {
		try {
			converterFactory = new ConverterFactory();
			TeaFileReader reader = converterFactory.getReader(inFileFormat);
			TeaFileWriter writer = converterFactory.getWriter(outFileFormat);
			writer.writeFile(reader.readFile(inFileName), outFileName);
			
		}
		catch (Exception e) {
			System.err.println("A fault occured during conversion: " + e);
			return false;
		}
		return true;
	}
}
