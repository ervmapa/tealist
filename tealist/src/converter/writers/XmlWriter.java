package converter.writers;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;



import org.w3c.dom.Document;
import org.w3c.dom.Element;

import tealist.Tea;

/**
 * Xmlwriter can write a tea list as XML
 */
public class XmlWriter implements TeaFileWriter {
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void writeFile(List<Tea> teaList, String fileName) throws Exception {
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
