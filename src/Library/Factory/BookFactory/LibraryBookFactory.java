package Library.Factory.BookFactory;

import Library.Entities.Book.Book;
import Library.Entities.Book.DigitalBook;
import Library.Entities.Book.LimitedBook;
import Library.Entities.Book.NormalBook;

public class LibraryBookFactory implements BookFactory {

    @Override
    public Book createRegularBook(String IdBook, String Title, String Author, int Quantity) {
        return new NormalBook(IdBook, Title, Author, Quantity);
    }

    @Override
    public Book createDigitalBook(String IdBook, String Title, String Author, int Quantity, double RentalPerAccessFee) {
        return new DigitalBook(IdBook, Title, Author, Quantity, RentalPerAccessFee);
    }

    @Override
    public Book createLimitedBook(String IdBook, String Title, String Author, int Quantity, double SpecialRentalFee) {
        return new LimitedBook(IdBook,Title,Author,Quantity,SpecialRentalFee);
    }
}
