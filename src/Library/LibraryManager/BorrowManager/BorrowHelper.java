package Library.LibraryManager.BorrowManager;

import Library.Entities.Book.Book;
import Library.Entities.Member.Member;
import Library.ErrorManagement.BorrowException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class BorrowHelper {

    // Discount map for different types of members
    private static final Map<String, Double> DISCOUNT_MAP = Map.of(
            "student", 0.15,  // Students get a 15% discount
            "premium", 0.25   // Premium members get a 25% discount
    );

    /**
     * Checks if the member has enough balance to borrow a specific book.
     *
     * @param member The member who wants to borrow the book.
     * @param book   The book that the member wants to borrow.
     * @param amount The total amount of money the member has paid.
     * @return true if the member can borrow the book, throws BorrowException otherwise.
     * @throws BorrowException if the book is not available or the member has exceeded their borrowing limit.
     */
    public static boolean canBorrowBook(Member member, Book book, double amount) throws BorrowException {
        System.out.println("Member name: " + member.getNameMember() +
                " wants to borrow the book: " + book.getTitle() +
                "\nQuantity: " + book.getQuantity() +
                " | Available: " + book.isAvailable());

        if (!book.isAvailable()) {
            throw new BorrowException("The book is not available.");
        }

        if (member.getMaxBorrowLimit() <= member.getBorrowedBook().size()) {
            throw new BorrowException(
                    "You have reached the maximum borrow limit. \n" +
                            "Please return some books before borrowing more."
            );
        }

        return checkMemberBalanceForBook(member, book, amount);
    }

    /**
     * Calculates and returns the remaining balance after borrowing a single book.
     *
     * @param member The member borrowing the book.
     * @param book   The book being borrowed.
     * @param amount The total amount of money the member has.
     * @return The remaining balance after deducting the rental fee.
     */
    public static double calculateRemainingBalance(Member member, Book book, double amount) {
        double finalRentalFee = calculateDiscountedRentalFee(member, book.calculateRentalFee());
        return amount - finalRentalFee;
    }

    /**
     * Calculates and returns the remaining balance after borrowing multiple books.
     *
     * @param member The member borrowing the books.
     * @param books  The list of books being borrowed.
     * @param amount The total amount of money the member has.
     * @return The remaining balance after deducting the rental fees for all books.
     */
    public static double calculateRemainingBalanceForMultipleBooks(Member member, List<Book> books, double amount) {
        for (Book book : books) {
            double finalRentalFee = calculateDiscountedRentalFee(member, book.calculateRentalFee());
            amount -= finalRentalFee;
        }
        return amount;
    }

    /**
     * Determines the due date for borrowed books based on the type of member.
     *
     * @param member      The member borrowing the books.
     * @param borrowDate  The date the books are borrowed.
     * @return The due date for returning the books.
     */
    public static LocalDate calculateDueDate(Member member, LocalDate borrowDate) {
        return switch (member.geTypeMember()) {
            case "regular" -> borrowDate.plusDays(7);    // Regular members have 7 days
            case "student" -> borrowDate.plusDays(14);   // Students have 14 days
            case "premium" -> borrowDate.plusDays(28);   // Premium members have 28 days
            default -> throw new IllegalStateException("Unexpected member type: " + member.geTypeMember());
        };
    }

    /**
     * Updates the book's quantity and the member's borrowed books after a successful borrow.
     *
     * @param member The member borrowing the book.
     * @param book   The book being borrowed.
     */
    public static void updateBorrowingStatus(Member member, Book book) {
        member.setBorrowedBooks(book);
        book.setQuantity(book.getQuantity() - 1);

        if (book.getQuantity() == 0) {
            book.setAvailable(false);
        }
    }

    /**
     * Calculates the final rental fee after applying the member's discount.
     *
     * @param member    The member borrowing the book.
     * @param rentalFee The original rental fee of the book.
     * @return The discounted rental fee.
     */
    private static double calculateDiscountedRentalFee(Member member, double rentalFee) {
        return rentalFee * (1 - DISCOUNT_MAP.getOrDefault(member.geTypeMember(), 0.0));
    }

    /**
     * Checks if the member has enough balance to borrow a book.
     *
     * @param member The member borrowing the book.
     * @param book   The book being borrowed.
     * @param amount The total amount of money the member has.
     * @return true if the member has enough money to borrow the book, false otherwise.
     * @throws BorrowException if the member does not have enough money.
     */
    private static boolean checkMemberBalanceForBook(Member member, Book book, double amount) throws BorrowException {
        // Calculate the rental fee
        double rentalFee = book.calculateRentalFee();

        // Calculate the discounted final fee
        double finalRentalFee = calculateDiscountedRentalFee(member, rentalFee);

        // Check if the member has sufficient funds
        if (amount < finalRentalFee) {
            throw new BorrowException("Insufficient funds to borrow the book.");
        }

        System.out.println("You can borrow " + book.getTitle() +
                ". Your remaining balance is: " + (amount - finalRentalFee));
        return true;
    }
}
