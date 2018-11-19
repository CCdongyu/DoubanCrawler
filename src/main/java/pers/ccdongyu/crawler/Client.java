package pers.ccdongyu.crawler;

public class Client {

  public static void main(String[] args) {

    if (args.length != 2) {
      System.out.println(args.length);
      System.out.println("Usage: java -jar crawler.jar book.xlsx result.xlsx");
      return;
    }
    WorkbookUtil.writeWorkbook(args[1],
        WorkbookUtil.newFinalWorkbook(CrawlerUtil.getRecords(WorkbookUtil.getISBNs(args[0]))));
  }
}
