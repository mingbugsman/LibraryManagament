package Library.Factory.BookFactory;

import Library.Entities.Book.Book;

public class BookFactoryProducer {
    public static Book createBook(String bookType, String IdBook, String Title, String Author, int Quantity,double ExtraFee) {

        return switch (bookType) {
            case "digital" -> new LibraryBookFactory().createDigitalBook(IdBook,Title, Author,Quantity,ExtraFee);
            case "limited" -> new LibraryBookFactory().createLimitedBook(IdBook, Title,Author,Quantity,ExtraFee);
            case "regular" -> new LibraryBookFactory().createRegularBook(IdBook, Title, Author, Quantity);
            default -> throw new IllegalArgumentException("Not found this type book " + bookType);
        };
    }
}
