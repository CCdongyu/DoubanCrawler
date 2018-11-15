/**
 * @author ccdongyu
 * @version 1.0
 * This class is used to save the temporary information of the book information
 */
public class BookRecord {

  private BookRecord(Builder builder) {
    this.isbn = builder.isbn;
    this.bookName = builder.bookName;
    this.author = builder.author;
    this.price = builder.price;
  }

  private String isbn;
  private String bookName;
  private String author;
  private String price;

  String getISBN() {
    return isbn;
  }

  String getBookName() {
    return bookName;
  }

  String getAuthor() {
    return author;
  }

  String getPrice() {
    return price;
  }

  static class Builder {
    private String isbn = "unknown ISBN";
    private String bookName = "unknown bookName";
    private String author = "unknown author";
    private String price = "unknown price";

    Builder isbn(String isbn) {
      this.isbn = isbn;
      return this;
    }

    Builder bookName(String bookName) {
      this.bookName = bookName;
      return this;
    }

    Builder author(String author) {
      this.author = author;
      return this;
    }

    Builder price(String price) {
      this.price = price;
      return this;
    }

    BookRecord newInstance() {
      return new BookRecord(this);
    }

  }

  @Override
  public String toString() {
    return isbn + bookName + author + price;
  }
}
