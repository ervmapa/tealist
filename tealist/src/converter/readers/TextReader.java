package converter.readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import tealist.Tea;

public class TextReader implements Reader {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Tea> readFile(String fileName) throws IOException {
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

}
