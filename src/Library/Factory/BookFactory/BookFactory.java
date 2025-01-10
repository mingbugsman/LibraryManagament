package Library.Factory.BookFactory;

import Library.Entities.Book.Book;

public interface BookFactory {
    Book createRegularBook(String IdBook, String Title, String Author, int Quantity);
    Book createDigitalBook(String IdBook, String Title, String Author, int Quantity, double RentalAccessFee);
    Book createLimitedBook(String IdBook, String Title, String Author, int Quantity, double SpecialRentalFee);
}
