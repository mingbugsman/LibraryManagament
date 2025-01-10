package Library.Entities.Book;

import java.util.Date;

public class NormalBook extends Book{
    public NormalBook(String IdBook, String Title, String Author, int Quantity) {
        super(IdBook, Title, Author, Quantity);

    }

    @Override
    public double calculateRentalFee() {
        return 0;
    }

    @Override
    public String getTypeBook() {
        return "Regular";
    }
}
