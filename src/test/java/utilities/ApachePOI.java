package utilities;

import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ApachePOI {
	public Object[][] getData(String path, String fileName, String sheetName) throws Exception {
		String filePath = path + fileName + ".xlsx";
		FileInputStream file = new FileInputStream(filePath);
		XSSFWorkbook workBook = new XSSFWorkbook(file);
		XSSFSheet sheet = workBook.getSheet(sheetName);
		int rowLen = sheet.getLastRowNum();
		int colLen = sheet.getRow(0).getLastCellNum();

		Object[][] formData = new Object[rowLen][colLen];
		for (int i = 0; i < rowLen; i++) {
			XSSFRow row = sheet.getRow(i + 1);
			for (int j = 0; j < colLen; j++) {
				XSSFCell cell = row.getCell(j);
				formData[i][j] = cell.toString();
			}
		}
		workBook.close();
		file.close();
		return formData;
	}
}
