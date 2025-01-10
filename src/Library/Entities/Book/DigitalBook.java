package Library.Entities.Book;

import java.time.LocalDate;

public class DigitalBook extends Book{
    private final double RentalFeePerAccess;
    public DigitalBook(String IdBook, String Title, String Author, int Quantity, double RentalFeePerAccess) {
        super(IdBook, Title, Author, Quantity);
        this.RentalFeePerAccess = RentalFeePerAccess;
    }

    @Override
    public double calculateRentalFee() {
        return this.RentalFeePerAccess;
    }

    @Override
    public String getTypeBook() {
        return "digital";
    }

    @Override
    public LocalDate DueReturnBook(LocalDate BorrowedDate) {
        return null;
    }
}
