package Library.UseCasesForLibrary.BorrowStrategy;

import Library.Entities.Book.Book;
import Library.Entities.Member.Member;
import java.util.Map;

public class UtilBorrowedBook {
    private static final Map<String, Double> DISCOUNT_MAP = Map.of(
            "student", 0.15,
            "premium", 0.25
    );

    public static boolean checkBookAvailabilityAndBalance(Member member, Book book, double amount, boolean isReserve) {

        if (!book.isAvailable()) {
            System.out.println("The book is not available.");
            return false;
        }

        if (member.getTimesBorrowed() == 0) {
            System.out.println("Your book borrowing times are maximized. Please return the book if you want to borrow it again.");
            return false;
        }

        if (isReserve && !member.getReserveBook()) {
            System.out.println("You don't have permission to reserve this book. It's only for premium members.");
            return false;
        }

        // calc
        double rentalFee = book.calculateRentalFee();
        double finalRentalFee = calculateFinalRentalFee(member, rentalFee);

        if (amount < finalRentalFee) {
            System.out.println("You don't have enough money to borrow or reserve the book.");
            return false;
        }

        System.out.println("You can borrow or reserve the book. Your change is " + (amount - finalRentalFee));
        return true;
    }

    private static double calculateFinalRentalFee(Member member, double rentalFee) {
        return rentalFee * (1 - DISCOUNT_MAP.getOrDefault(member.getRole(), 0.0));
    }
}
