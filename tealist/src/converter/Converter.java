package converter;

/**
 * This interface should be implemented by something that 
 * can convert tea information from one file format to another
 * 
 * @author Mats Palm
 */
public interface Converter {
	
	/**
	 * Convert between two Tea formats
	 * 
	 * @return if conversion was successful or not
	 */
	public boolean convert();
}
