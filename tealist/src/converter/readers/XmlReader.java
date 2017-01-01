package converter.readers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import tealist.Tea;

/**
 * XmlReader can read a XML input file
 * 
 * @author Mats Palm
 */
public class XmlReader implements TeaFileReader {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Tea> readFile(String fileName) throws IOException {
		{		
			List<Tea> teaList = new ArrayList<Tea>();

			File xmlFile = new File(fileName);
			if(!xmlFile.exists() || !xmlFile.isFile())
			{
				throw new IOException("The file " + fileName +  " does not exist");
			}

			try
			{
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(xmlFile);
				doc.getDocumentElement().normalize();

				NodeList nodeList = doc.getElementsByTagName(Tea.TEA);


				for(int i = 0; i < nodeList.getLength(); i++) {

					Tea tea = new Tea();
					Node node = nodeList.item(i);
					if(node.getNodeType() == Node.ELEMENT_NODE) {

						Element element = (Element) node;

						tea.category = getTagValue(Tea.CATEGORY, element);
						tea.name = getTagValue(Tea.NAME, element);
						tea.price = Integer.valueOf(getTagValue(Tea.PRICE, element));
						tea.description = getTagValue(Tea.DESCRIPTION, element);	 
					}
					teaList.add(tea);
				}
			}
			catch(Exception e) {
				throw new IOException("Input file (" + fileName + ") not correct format");
			}
			return teaList;
		}
	}

		/**
		 * Gets a value from an element.
		 *  
		 * @param tag The tag we are looking for
		 * @param element The current element
		 * @return The value of the tag
		 */
		private String getTagValue(String tag, Element element) {
			NodeList nlList = element.getElementsByTagName(tag).item(0).getChildNodes();
			Node nValue = (Node) nlList.item(0);

			return nValue.getNodeValue();
		}
		
}
