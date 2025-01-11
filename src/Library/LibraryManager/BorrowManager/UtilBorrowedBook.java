package Library.LibraryManager.BorrowManager;

import Library.Entities.Book.Book;
import Library.Entities.Member.Member;
import Library.ErrorManagement.BorrowedException.BorrowException;

import java.time.LocalDate;
import java.util.Map;

public class UtilBorrowedBook {

    private static final Map<String, Double> DISCOUNT_MAP = Map.of(
            "student", 0.15,
            "premium", 0.25
    );


    /**
     * Checks if the user has enough money to borrow or reserve a book.
     * @param member The member who wants to borrow the book.
     * @param book The book to be borrowed .
     * @param amount The amount of money the user has paid.
     * @return true if the user has enough money to borrow the book, false otherwise.
     */

    public static boolean hasEnoughBalanceForBook(Member member, Book book, double amount) throws BorrowException {
        System.out.println("Member name : " + member.getNameMember() + " want to borrow book name is : " + book.getTitle() + "\n" +
            "Quantity is : "+ book.getQuantity() + " with available : " + book.isAvailable());
        if (!book.isAvailable()) {

            throw new BorrowException("The book is not available.");
        }

        if (member.getMaxBorrowLimit() <= member.getBorrowedBook().size()) {
            throw new BorrowException
                    ("Your book borrowing times are maximized. \n" +
                            "Please return the book if you want to borrow it again."
                    );
        }
        return checkIfUserCanBorrowOrReserve(member, book, amount);
    }

    private static boolean checkIfUserCanBorrowOrReserve(Member member, Book book, double amount) {
        // calc fee
        double rentalFee = book.calculateRentalFee();
        // calc final fee
        double finalRentalFee = calculateFinalRentalFee(member, rentalFee);

        // checking member money with final fee

        if (amount < finalRentalFee) {
            System.out.println("You don't have enough money to borrow the book.");
            return false;
        }

        System.out.println("You can borrow " + book.getTitle() + " book. Your change is " + (amount - finalRentalFee));
        return true;
    }

    private static double calculateFinalRentalFee(Member member, double rentalFee) {
        return rentalFee * (1 - DISCOUNT_MAP.getOrDefault(member.geTypeMember(), 0.0));
    }

    public static LocalDate BorrowDayStrategy(Member member, LocalDate borrowDate) {
        return switch (member.geTypeMember()) {
            case "regular" ->  borrowDate.plusDays(7);
            case "student" -> borrowDate.plusDays(14);
            case "premium" -> borrowDate.plusDays(28);
            default -> throw new IllegalStateException("Unexpected value: " + member.geTypeMember());
        };
    }
}
