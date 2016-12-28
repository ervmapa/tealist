package tealist;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Handles reading and writing for files containing tea information.
 * 
 * @author Thomas Ejnefjäll
 */
public class FileIO 
{	
	/**
	 * Constructs a FileIO object 
	 */
	public FileIO() { }
	/**
	 * Reads tea information from a text file. The text file must be in the 
	 * formatted as following: category;name;price;description[new line]
	 * and should be in UTF-8 if it contains any special characters.
	 * 
	 * @param fileName Name of the file
	 * @return A list of the tea in the file
	 * @throws IOException If there was a I/O error
	 */
	public List<Tea> readTextFile(String fileName) throws IOException
	{
		List<Tea> teaList = new ArrayList<Tea>();

		File file = new File(fileName);

		if(!file.exists() || !file.isFile())
		{
			throw new IOException("The file " + fileName +  " does not exist");
		}
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
		BufferedReader br = new BufferedReader(isr);

		String line;

		try
		{
			while((line = br.readLine()) != null)
			{
				Tea tea = new Tea();
				String[] teaLine = line.split(";");
				tea.category = teaLine[0];
				tea.name = teaLine[1];
				tea.price = Integer.valueOf(teaLine[2]);
				tea.description = teaLine[3];
				teaList.add(tea);

			}
			br.close();
		} catch (Exception e)
		{			
			throw new IOException("Input file (" + fileName + ") not correct format");
		}		
		return teaList;
	}
	/**
	 * Reads tea information from a xml file. The xml file must have elements 
	 * named tea containing sub elements with the tags category, name, price and
	 * description.
	 * 
	 * @param fileName Name of the file
	 * @return A list of the tea in the file
	 * @throws IOException If there was a I/O error
	 */
	public List<Tea> readXmlFile(String fileName) throws IOException
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
	/**
	 * Writes the tea information to a text file. The information will be saved
	 * to the file as following: category;name;price;description[new line]
	 * 
	 * @param teaList A list of the tea
	 * @param fileName Name of the file, if it is null the file will be written to 
	 *                 system.out
	 * @throws IOException If there was an I/O error
	 */
	public void writeTextFile(List<Tea> teaList, String fileName) throws IOException
	{	
		if(fileName == null) 
		{
			for(Tea tea : teaList)
			{
				System.out.println(tea.category + ";" + tea.name + ";" + tea.price + ";" + tea.description);
			}
		}
		else 
		{
			File file = new File(fileName);

			if(!file.exists())
			{
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);

			for(int i = 0; i < teaList.size(); i ++)  
			{
				Tea tea = teaList.get(i);
				bw.write(tea.category + ";" + tea.name + ";" + tea.price + ";" + tea.description);

				if(i + 1 != teaList.size())
				{
					bw.newLine();
				}
			}		
			bw.close();			
		}				
	}
	/**
	 * Writes the tea information to a xml file. The xml file will be saved with
	 * a root element named tealist and contains elements named tea with sub
	 * elements that has the tags category, name, price and description.
	 * 
	 * @param teaList A list of the tea
	 * @param fileName Name of the file, if it is null the file will be written to 
	 *                 system.out
	 * @throws Exception If there was an error while constructing or writing the file
	 */
	public void writeXmlFile(List<Tea> teaList, String fileName) throws Exception
	{	
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement(Tea.TEALIST);
		doc.appendChild(rootElement);

		for(Tea tea : teaList) {
			Element teaElement = doc.createElement(Tea.TEA);
			rootElement.appendChild(teaElement);

			Element categoryElement = doc.createElement(Tea.CATEGORY);
			categoryElement.appendChild(doc.createTextNode(tea.category));
			teaElement.appendChild(categoryElement);

			Element nameElement = doc.createElement(Tea.NAME);
			nameElement.appendChild(doc.createTextNode(tea.name));
			teaElement.appendChild(nameElement);

			Element priceElement = doc.createElement(Tea.PRICE);
			priceElement.appendChild(doc.createTextNode("" + tea.price));
			teaElement.appendChild(priceElement);

			Element descriptionElement = doc.createElement(Tea.DESCRIPTION);
			descriptionElement.appendChild(doc.createTextNode(tea.description));
			teaElement.appendChild(descriptionElement);
		}			

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result;

		if(fileName == null) {
			result = new StreamResult(System.out);
		}
		else {
			result = new StreamResult(new File(fileName));
		}

		transformer.transform(source, result);					
	}
}