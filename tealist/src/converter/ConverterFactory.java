package converter;

import converter.readers.TeaFileReader;
import converter.readers.TextReader;
import converter.readers.XmlReader;
import converter.writers.HtmlWriter;
import converter.writers.TeaFileWriter;
import converter.writers.TextWriter;
import converter.writers.XmlWriter;

/**
 * A Factory that returns readers and writers for different tea file formats
 * 
 * @author Mats Palm
 */
public class ConverterFactory {

	private TeaFileReader reader;
	private TeaFileWriter writer;
	
	/**
	 * Provide a reader for the specified input file format
	 * 
	 * @param The input file format
	 * @return A reader object for the input file format
	 */
	public TeaFileReader getReader(String inFileFormat) throws Exception {

		if (FileFormats.TEXT.equals(inFileFormat)) {
			reader = new TextReader();
		} else if (FileFormats.XML.equals(inFileFormat)) {
			reader = new XmlReader();
		} else {
			throw new Exception("Unknown input file format: " + inFileFormat);
		}
		return reader;
	}

	/**
	 * Provide a writer for the specified output file format
	 * 
	 * @param The output file format
	 * @return A writer object for the input file format
	 */
	public TeaFileWriter getWriter(String outFileFormat) throws Exception {
		
		if(FileFormats.TEXT.equals(outFileFormat))
		{
			writer = new TextWriter();
		}
		else if(FileFormats.XML.equals(outFileFormat))
		{
			writer = new XmlWriter();
		}
		else if(FileFormats.HTML.equals(outFileFormat))
		{
			writer = new HtmlWriter();
		}
		else {
			throw new Exception("Unknown output file format: " + outFileFormat);
		}
		return writer;
	}
	
}
