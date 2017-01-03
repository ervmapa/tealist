package converter;

import converter.readers.TeaFileReader;
import converter.readers.TextReader;
import converter.readers.XmlReader;
import converter.writers.TeaFileWriter;
import converter.writers.TextWriter;
import converter.writers.XmlWriter;
/**
 * Enumeration for supported file formats.
 * 
 * The enum also has logic also ("factory methods") to return TeaFileReaders and TeaFileWriter
 * for a specified file format.
 * 
 * @author Thomas Ejnefjäll updated by Mats Palm
 */
public enum FileFormats {
	TEXT("text", new TextReader(), new TextWriter(), "text - Text (txt) file where fields are separated with ;"), 
	XML("xml", new XmlReader(), new XmlWriter(), "xml - Xml (xml) file");
	
	private String fileFormat;
	private TeaFileReader reader;
	private TeaFileWriter writer;
	private String description;
	
	/**
	 * Private constructor only for the enumeration itself
	 * 
	 * @param fileFormat Name of the file format
	 * @param reader A Writer object associated with the file format
	 * @param writer A Writer object associated with the file format
	 * @param description The description associated with the file format
	 */
	private FileFormats(String fileFormat, TeaFileReader reader, TeaFileWriter writer, String description) {
		this.fileFormat = fileFormat;
		this.reader = reader;
		this.writer = writer;
		this.description = description;
	}
	
	/**
	 * Iterate through the file formats that matches the 
	 * input file format. Return a reader object for the
	 * specific input file format
	 * 
	 * @param fileFormat Name of the input file format
	 * @return The reader for the specified file format
	 */
	public static TeaFileReader getReader(String fileFormat) {
		for(FileFormats ff : FileFormats.values()) {
			if(ff.equals(fileFormat)) {
				return ff.reader;
			}
		}
		// Default. Should never happen :)
		return new TextReader();
	}

	/**
	 * Iterate through the file formats that matches the 
	 * input file format. Return a writer object for the
	 * specific output file format

	 * @param fileFormat Name of the output file format
	 * @return The writer for the specified file format
	 */
	public static TeaFileWriter getWriter(String fileFormat) {
		
		for(FileFormats ff : FileFormats.values()) {
			if(ff.equals(fileFormat)) {
				return ff.writer;
			}
		}
		// Default. Should never happen :)
		return new TextWriter();
	}
	
	/**
	 * Iterate through the file formats and build a 
	 * string to show a complete description for all
	 * file formats
	 * @return Description string for all file formats
	 */
	public static String getSupportedformats() {
		String description = "";
		for(FileFormats ff : FileFormats.values()) {
			description += ff.description +"\n";
			}
		return description;
	}	
	
	/**
	 * Checks if the current file format is equal to another
	 * 
	 * @param fileFormat A string containing the other file format
	 * @return true it they are the same file format
	 */
	public boolean equals(String fileFormat) {
		return this.fileFormat.equalsIgnoreCase(fileFormat);
	}
	/**
	 * Checks if the current file format is equal to another
	 * 
	 * @param fileFormat The other file format
	 * @return true it they are the same file format
	 */
	public boolean equals(FileFormats fileFormat) {
		return this.equals(fileFormat.fileFormat);
	}
	/**
	 * Checks if the string contains a file format that is part of the enumeration
	 * 
	 * @param fileFormat a string containing the file format
	 * @return true if the file format is part of the enumeration
	 */
	public static boolean isValid(String fileFormat) {
		for(FileFormats ff : FileFormats.values()) {
			if(ff.equals(fileFormat)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Checks if the file format is part of the enumeration
	 * 
	 * @param fileFormat the file format we want to check
	 * @return true if the file format is part of the enumeration
	 */
	public static boolean isValid(FileFormats fileFormat) {
		return FileFormats.isValid(fileFormat.toString());
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return this.fileFormat;
	}
}