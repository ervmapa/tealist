package converter.savers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import tealist.Tea;

public class TextSaver implements Saver {


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeFile(List<Tea> teaList, String fileName) throws Exception {
		{
			if (fileName == null) {
				for (Tea tea : teaList) {
					System.out.println(tea.category + ";" + tea.name + ";" + tea.price + ";" + tea.description);
				}
			} else {
				File file = new File(fileName);

				if (!file.exists()) {
					file.createNewFile();
				}
				FileOutputStream fos = new FileOutputStream(file);
				OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
				BufferedWriter bw = new BufferedWriter(osw);

				for (int i = 0; i < teaList.size(); i++) {
					Tea tea = teaList.get(i);
					bw.write(tea.category + ";" + tea.name + ";" + tea.price + ";" + tea.description);

					if (i + 1 != teaList.size()) {
						bw.newLine();
					}
				}
				bw.close();
			}
		}

	}

}
