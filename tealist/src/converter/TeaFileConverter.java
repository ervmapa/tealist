package converter;

import converter.readers.TeaFileReader;
import converter.readers.TextReader;
import converter.readers.XmlReader;
import converter.writers.HtmlWriter;
import converter.writers.TeaFileWriter;
import converter.writers.TextWriter;
import converter.writers.XmlWriter;

/**
 * A TeaFileConverter can convert between different tea file formats
 * To do that, a converter needs a reader object and a 
 * writer object.
 */
public class TeaFileConverter {
	
	private TeaFileReader reader; // composition
	private TeaFileWriter writer; // composition
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
	 * Convert between two Tea formats
	 * 
	 * @return if conversion was successful or not
	 */
	public boolean convert() {
		try {
			reader = getReader();
			writer = getWriter();
			writer.writeFile(reader.readFile(inFileName), outFileName);
		}
		catch (Exception e) {
			System.err.println("A fault occured during conversion: " + e);
			return false;
		}
		return true;
	}

	/**
	 * Create a TeaFileReader object 
	 * @return a reader object
	 * @throws Exception if the input file was unknown
	 */
	public TeaFileReader getReader() throws Exception {
		
		if(FileFormats.TEXT.equals(inFileFormat))
		{
			reader = new TextReader();
		}
		else if(FileFormats.XML.equals(inFileFormat))
		{
			reader = new XmlReader();
		}
		else {
			throw new Exception("Unknown input file format: " + inFileFormat);
		}
		return reader;
	}

	/**
	 * Create a TeaFileWriter object 
	 * @return a writer object
	 * @throws Exception if the output file was unknown
	 */
	public TeaFileWriter getWriter() throws Exception {
		
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
