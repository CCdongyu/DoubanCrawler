import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

/**
 * @author ccdongyu
 * @version 1.0
 * This class is used to pull the book information from the website with the interface of
 * "http://book.douban.com/isbn/xxx"
 */
class CrawlerUtil {

  private static final String URL_PREFIX = "http://book.douban.com/isbn/";

  static List<BookRecord> getRecords(List<String> isbns) {
    List<BookRecord> records = new ArrayList<>();
    for (String isbn : isbns) {
      Document doc = null;
      try {
        doc = Jsoup.connect(URL_PREFIX + isbn).get();
      } catch (IOException e) {
        e.printStackTrace();
      }
      String bookName = doc.select("h1 span").get(0).text();
      String author = doc.select("#info a").get(0).text();
      String price = null;
      List<Node> nodes = doc.select("#info").get(0).childNodes();
      boolean flag = false;
      for (Node node : nodes) {
        if (flag) {
          price = node.toString();
          break;
        }
        if (node.toString().contains("定价")) {
          flag = true;
        }
      }
      records.add(new BookRecord.Builder().isbn(isbn).bookName(bookName).author(author)
          .price(price)
          .newInstance());
    }
    return records;
  }
}
