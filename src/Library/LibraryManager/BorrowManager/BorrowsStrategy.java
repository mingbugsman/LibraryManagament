
package Library.LibraryManager.BorrowManager;

import Library.Entities.Book.Book;
import Library.Entities.BookTransaction.BookTransaction;
import Library.Entities.Member.Member;
import Library.ErrorManagement.BorrowedException.BorrowException;
import Library.UseCasesForLibrary.UseCases_Book.ManagerBook;
import Library.UseCasesForLibrary.UseCases_BorrowBookTransaction.BorrowTransactionManagement;
import Library.UseCasesForLibrary.UseCases_Member.ManagerMember;

import java.time.LocalDate;

public class BorrowsStrategy implements IBorrowStrategy {
    private final BorrowTransactionManagement borrowTransactionManagement;
    private final ManagerBook managerBook;
    private final ManagerMember managerMember;

    private BorrowsStrategy(BorrowTransactionManagement borrowTransactionManagement,
                            ManagerBook managerBook, ManagerMember managerMember
    ) {
        this.borrowTransactionManagement = borrowTransactionManagement;
        this.managerBook = managerBook;
        this.managerMember = managerMember;
    }

    public static BorrowsStrategy createBorrowsStrategy(BorrowTransactionManagement borrowTransactionManagement,
                                                        ManagerBook managerBook, ManagerMember managerMember
    ) {
        return new BorrowsStrategy(borrowTransactionManagement, managerBook, managerMember);
    }

    public void borrowBook(Member member, Book book, double amount) throws BorrowException {

        String ID_member = member.getId_user();
        String ID_book = book.getIdBook();

        if (managerMember.isExistedMember(ID_member) && managerBook.isExistedBook(ID_book) && UtilBorrowedBook.hasEnoughBalanceForBook(member, book, amount)) {
            LocalDate borrowDate = LocalDate.now();
            LocalDate dueDate = UtilBorrowedBook.BorrowDayStrategy(member,borrowDate);
            BookTransaction newBookTransaction = new BookTransaction(ID_member,ID_book, borrowDate, dueDate);
            borrowTransactionManagement.Add(newBookTransaction);
            updateBookAndMemberAfterBorrow(member, book);
        }
    }


    private static void updateBookAndMemberAfterBorrow(Member member, Book book) {
        member.setBorrowedBooks(book);
        book.setQuantity(book.getQuantity() - 1);

        if (book.getQuantity() == 0) {
            book.setAvailable(false);
        }
    }
}
