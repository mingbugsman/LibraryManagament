package Library.Entities.Book;

import java.time.LocalDate;
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
        return "regular";
    }

    @Override
    public LocalDate DueReturnBook(LocalDate BorrowedDate) {
        return null;
    }
}
