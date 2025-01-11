package Library.Entities.Book;

import java.time.LocalDate;

public abstract class Book {
    private final String IdBook;
    private String Title;
    private String Author;
    private int Quantity;
    private boolean isAvailable; // check if quantity > 0
    public Book(String IdBook, String Title, String Author, int Quantity) {
        this.IdBook = IdBook;
        this.Title = Title;
        this.Author = Author;
        this.Quantity = Quantity;
        this.isAvailable = true;
    }
    // ID book
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

    // isAvailable
    public boolean isAvailable() {
        return this.getQuantity() > 0;
    }
    public void setAvailable(boolean available) {
        isAvailable = available;
    }


    // abstract //
    public abstract double calculateRentalFee();
    public abstract String getTypeBook();

    // description
    public void DescriptionBook() {
        System.out.println(
                "Name : "  + getTitle() + "\n" +
                        "Author : " + getAuthor() + "\n" +
                        "Quantity : " + getQuantity() + "\n" +
                        "Rental Free : " + calculateRentalFee() + "\n" +
                        "Type book : " + getTypeBook() + "\n" +
                        "Available : " + isAvailable() + "\n" +
                        "Quantity : " + getQuantity() + "\n"
        );
    }
}
