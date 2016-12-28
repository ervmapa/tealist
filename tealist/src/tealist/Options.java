package tealist;

/**
 * Enumeration for options regarding tealist operations.
 * 
 * @author Thomas Ejnefjäll
 * @version 2011-10-24
 */
public enum Options {
	FROM_FILE_FORMAT("-f"), TO_FILE_FORMAT("-t"), INPUT_FILE("-i"), OUTPUT_FILE("-o"), LIST_FILE_FORMATS("-l"), HELP("-h"), ERROR("!error");

	private String value;
	
	/**
	 * Private constructor only for the enumeration itself.
	 * 
	 * @param value Value of the option
	 */
	private Options(String value) {
		this.value = value;
	}	
	/**
	 * Checks if current option is equal to another option.
	 * 
	 * @param option A string containing the other option
	 * @return true it they are the same option
	 */
	public boolean equals(String option) {
		return value.equalsIgnoreCase(option);
	}
	/**
	 * Checks if current option is equal to another option.
	 * 
	 * @param option The other option
	 * @return true it they are the same option
	 */
	public boolean equals(Options option) {
		return this.equals(option.value);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return this.value;
	}
}