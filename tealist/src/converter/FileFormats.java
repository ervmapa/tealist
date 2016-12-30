package converter;

/**
 * Enumeration for supported file formats.
 * 
 * @author Thomas Ejnefjäll
 */
public enum FileFormats {
	TEXT("text"), XML("xml"), HTML("html");
	
	private String fileFormat;
	
	/**
	 * Private constructor only for the enumeration itself
	 * 
	 * @param fileFormat Name of the file format
	 */
	private FileFormats(String fileFormat) {
		this.fileFormat = fileFormat;
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