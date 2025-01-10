package Library.Entities.Book;

import java.time.LocalDate;

public class LimitedBook extends Book{
    private final double SpecialRentalFee;

    public LimitedBook(String IdBook, String Title, String Author, int Quantity, double SpecialRentalFee) {
        super(IdBook, Title, Author, Quantity);
        this.SpecialRentalFee = SpecialRentalFee;
    }

    @Override
    public double calculateRentalFee() {
        return this.SpecialRentalFee;
    }

    @Override
    public String getTypeBook() {
        return "limited";
    }

    @Override
    public LocalDate DueReturnBook(LocalDate BorrowedDate) {
        return null;
    }
}
