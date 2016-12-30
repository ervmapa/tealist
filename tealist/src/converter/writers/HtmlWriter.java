package converter.writers;
import java.util.List;
import tealist.Tea;

/**
 * HtmlWriter can write a tea list as asn HTML page
 */
public class HtmlWriter implements TeaFileWriter {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeFile(List<Tea> teaList, String fileName) throws Exception {
		{
			if (fileName == null) {
				System.out.println("<!DOCTYPE html>\n<html>\n<body>");

				for (Tea tea : teaList) {
					System.out.println("<h2>" + tea.name + "</h2>");
					System.out.println("<ul>");
					System.out.println("<li>" + tea.category + "</li>");				
					System.out.println("<li>" + tea.price + "</li>");
					System.out.println("<li>" + tea.description + "</li>");
					System.out.println("</ul>");  
				}
				System.out.println("</body></html>");

			} else { // this is just "proof of concept" so writing to file is not supported yet :)
				System.err.println("HTML filetype to file is not supported");
			}
		}
	}
}