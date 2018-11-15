public class Client {

  public static void main(String[] args) {
    WorkbookUtil.writeWorkbook(
        WorkbookUtil.newFinalWorkbook(CrawlerUtil.getRecords(WorkbookUtil.getISBNs())));
  }
}
