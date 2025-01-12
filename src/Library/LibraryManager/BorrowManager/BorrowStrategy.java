package Library.LibraryManager.BorrowManager;

import Library.Entities.Book.Book;
import Library.Entities.BookTransaction.BookTransaction;
import Library.Entities.Member.Member;
import Library.ErrorManagement.BorrowException;
import Library.UseCasesForLibrary.UseCases_Book.ManagerBook;
import Library.UseCasesForLibrary.UseCases_BorrowBookTransaction.BorrowTransactionManagement;
import Library.UseCasesForLibrary.UseCases_Member.ManagerMember;

import java.time.LocalDate;
import java.util.List;

public class BorrowStrategy implements IBorrowStrategy {
    private final BorrowTransactionManagement transactionManager;
    private final ManagerBook bookManager;
    private final ManagerMember memberManager;

    private BorrowStrategy(BorrowTransactionManagement transactionManager,
                           ManagerBook bookManager,
                           ManagerMember memberManager) {
        this.transactionManager = transactionManager;
        this.bookManager = bookManager;
        this.memberManager = memberManager;
    }

    /**
     * Factory method to create an instance of BorrowStrategy.
     *
     * @param transactionManager The transaction management instance.
     * @param bookManager        The book management instance.
     * @param memberManager      The member management instance.
     * @return A new instance of BorrowStrategy.
     */
    public static BorrowStrategy createBorrowStrategy(BorrowTransactionManagement transactionManager,
                                                      ManagerBook bookManager,
                                                      ManagerMember memberManager) {
        return new BorrowStrategy(transactionManager, bookManager, memberManager);
    }

    /**
     * Checks whether a member is eligible to borrow a specific book.
     *
     * @param member The member attempting to borrow the book.
     * @param book   The book the member wants to borrow.
     * @param amount The amount of money the member has.
     * @return true if the member can borrow the book, false otherwise.
     * @throws BorrowException if the book is not available, the member exceeds their borrow limit,
     *                         or they do not have enough funds.
     */
    private boolean canBorrowBook(Member member, Book book, double amount) throws BorrowException {
        String memberId = member.getId_user();
        String bookId = book.getIdBook();

        return memberManager.isExistedMember(memberId)
                && bookManager.isExistedBook(bookId)
                && BorrowHelper.canBorrowBook(member, book, amount);
    }

    /**
     * Allows a member to borrow multiple books as long as they do not exceed the borrow limit.
     *
     * @param member The member borrowing the books.
     * @param books  A list of books the member wants to borrow.
     * @param amount The amount of money the member has.
     * @throws BorrowException if the member exceeds their borrow limit or other conditions are not met.
     */
    public void borrowMultipleBooks(Member member, List<Book> books, double amount) throws BorrowException {
        String memberId = member.getId_user();

        // Validate if the member exists
        if (!memberManager.isExistedMember(memberId)) {
            throw new BorrowException("Member does not exist.");
        }

        // Check if the borrow request exceeds the member's maximum borrow limit
        if ((member.getBorrowedBook().size() + books.size()) > member.getMaxBorrowLimit()) {
            throw new BorrowException("Borrow request exceeds the maximum borrow limit.");
        }

        // Process each book in the list
        for (Book book : books) {
            borrowBook(member, book, amount);
        }
    }

    /**
     * Allows a member to borrow a single book.
     *
     * @param member The member borrowing the book.
     * @param book   The book being borrowed.
     * @param amount The amount of money the member has.
     * @throws BorrowException if the book is not available, the member exceeds their borrow limit,
     *                         or they do not have enough funds.
     */
    @Override
    public void borrowBook(Member member, Book book, double amount) throws BorrowException {
        if (canBorrowBook(member, book, amount)) {
            LocalDate borrowDate = LocalDate.now();
            LocalDate dueDate = BorrowHelper.calculateDueDate(member, borrowDate);

            // Create a new transaction for the borrowed book
            BookTransaction newTransaction = new BookTransaction(
                    member.getId_user(), book.getIdBook(), borrowDate, dueDate
            );
            transactionManager.Add(newTransaction);

            // Update book and member details after borrowing
            BorrowHelper.updateBorrowingStatus(member, book);
        }
    }

    /**
     * Calculates the remaining balance after borrowing a single book.
     *
     * @param member The member borrowing the book.
     * @param book   The book being borrowed.
     * @param amount The amount of money the member has.
     * @return The remaining balance after deducting the rental fee.
     */
    @Override
    public double calculateRemainingBalance(Member member, Book book, double amount) {
        return BorrowHelper.calculateRemainingBalance(member, book, amount);
    }

    /**
     * Calculates the remaining balance after borrowing multiple books.
     *
     * @param member The member borrowing the books.
     * @param books  A list of books being borrowed.
     * @param amount The amount of money the member has.
     * @return The remaining balance after deducting the rental fees for all books.
     */
    @Override
    public double calculateRemainingBalance(Member member, List<Book> books, double amount) {
        return BorrowHelper.calculateRemainingBalanceForMultipleBooks(member, books, amount);
    }
}
