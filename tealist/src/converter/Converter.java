package converter;

import converter.readers.Reader;
import converter.readers.TextReader;
import converter.readers.XmlReader;
import converter.savers.Saver;
import converter.savers.TextSaver;
import converter.savers.XmlSaver;

public class Converter {
	
	private Reader reader;
	private Saver saver;
	private String inFileName;
	private String inFileFormat;
	private String outFileFormat;
	private String outFileName;
		
	
	public Converter(String inFileName, String inFileFormat, String outFileFormat, String outFileName) {
		
		this.inFileName = inFileName;
		this.inFileFormat = inFileFormat;
		this.outFileFormat = outFileFormat;
		this.outFileName = outFileName;
	}
	
	public boolean convert() {
		try {
			reader = getReader();
			saver = getSaver();
			saver.writeFile(reader.readFile(inFileName), outFileName);
		}
		catch (Exception e) {
			System.out.println("A fault occured during conversion: " +e);
			return false;
		}
		return true;
	}

	
	public Reader getReader() throws Exception {
		
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

	public Saver getSaver() throws Exception {
		
		if(FileFormats.TEXT.equals(outFileFormat))
		{
			saver = new TextSaver();
		}
		else if(FileFormats.XML.equals(outFileFormat))
		{
			saver = new XmlSaver();
		}
		else {
			throw new Exception("Unknown output file format: " + outFileFormat);
		}
		return saver;
	}
}
