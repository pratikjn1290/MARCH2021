package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	private Workbook book;
	private Sheet sName;
	public static final String path = "";

	public void getData(String sheetName) {

		Object[][] data = null;

		try {
			FileInputStream fis = new FileInputStream(path);
			book = WorkbookFactory.create(fis);
			sName = book.getSheet(sheetName);

			data = new Object[sName.getLastRowNum()][sName.getRow(0).getLastCellNum()];

			for (int i = 0; i < sName.getLastRowNum(); i++) {
				for (int j = 0; j < sName.getRow(0).getLastCellNum(); i++) {

					data[i][j] = sName.getRow(i + 1).getCell(j).toString();

				}
			}

		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
