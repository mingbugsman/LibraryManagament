package Library.Entities.Book;

public abstract class Book {
    private String IdBook;
    private String Title;
    private String Author;
    private int Quantity;

    public Book(String IdBook, String Title, String Author, int Quantity) {
        this.IdBook = IdBook;
        this.Title = Title;
        this.Author = Author;
        this.Quantity = Quantity;
    }
    // ID book

    public void setIdBook(String idBook) {
        IdBook = idBook;
    }

    public String getIdBook() {
        return IdBook;
    }

    //Author

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    // Title //

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    // Quantity //

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public abstract double calculateRentalFee();
    public abstract String getTypeBook();

    public void DescriptionBook() {
        System.out.println(
                "Name : "  + getTitle() + "\n" +
                        "Author : " + getAuthor() + "\n" +
                        "Quantity : " + getQuantity() + "\n" +
                        "Rental Free : " + calculateRentalFee() + "\n" +
                        "Type book : " + getTypeBook()
        );
    }
}
