package pers.ccdongyu.crawler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author ccdongyu
 * @version 1.0
 *
 * This class is used to Manipulate the Excel file, including extracting the ISBN information from
 * the specified excel file, creating a new Workbook with the BookRecords and writing the final
 * result into a new Excel file.
 */
class WorkbookUtil {


  static List<String> getISBNs(String inFileName) {
    List<String> list = new ArrayList<>();
    try (Workbook workbook = WorkbookFactory.create(new File(inFileName));) {
      Sheet sheet = workbook.getSheetAt(0);
      int i = 0;
      int startReadIndex = 2;
      for (Row row : sheet) {
        if (i++ < startReadIndex) {
          continue;
        }
        String ISBN = row.getCell(0).getStringCellValue().trim();
        if (!ISBN.equals("")) {
          list.add(ISBN);
        }
      }
    } catch (IOException | InvalidFormatException e) {
      e.printStackTrace();
    }
    return list;
  }

  static Workbook newFinalWorkbook(List<BookRecord> bookRecords) {

    Workbook workbook = new XSSFWorkbook();
    Sheet sheet = workbook.createSheet("购书清单");
    Row titleRow = sheet.createRow(0);
    titleRow.createCell(0).setCellValue("ISBN");
    titleRow.createCell(1).setCellValue("书名");
    titleRow.createCell(2).setCellValue("作者");
    titleRow.createCell(3).setCellValue("原价");
    int i = 2;
    for (BookRecord record : bookRecords) {
      Row row = sheet.createRow(i++);
      row.createCell(0).setCellValue(record.getISBN());
      row.createCell(1).setCellValue(record.getBookName());
      row.createCell(2).setCellValue(record.getAuthor());
      row.createCell(3).setCellValue(record.getPrice());
    }
    return workbook;
  }


  static void writeWorkbook(String outFileName, Workbook workbook) {
    if (workbook == null) {
      return;
    }
    File file = new File(outFileName);
    file.delete();
    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    try (FileOutputStream outputStream = new FileOutputStream(file);) {
      workbook.write(outputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
