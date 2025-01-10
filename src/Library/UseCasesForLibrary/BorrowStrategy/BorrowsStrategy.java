
package Library.UseCasesForLibrary.BorrowStrategy;

import Library.Entities.Book.Book;
import Library.Entities.Member.Member;

public class BorrowsStrategy implements IBorrowStrategy {

    @Override
    public boolean borrowBook(Member member, Book book, double amount) {
        if (UtilBorrowedBook.checkBookAvailabilityAndBalance(member, book, amount, false)) {
            updateBookAndMemberAfterBorrow(member, book);
            return true;
        }
        return false;
    }

    @Override
    public boolean reserveBook(Member member, Book book, double amount) {
        if (UtilBorrowedBook.checkBookAvailabilityAndBalance(member, book, amount, true)) {
            // Handle reserve logic (e.g., put book on hold, etc.)
            return true;
        }
        return false;
    }

    private void updateBookAndMemberAfterBorrow(Member member, Book book) {
        member.setBorrowedBooks(book);
        member.setTimesBorrowed(member.getTimesBorrowed() - 1);
        book.setQuantity(book.getQuantity() - 1);

        if (book.getQuantity() == 0) {
            book.setAvailable(false);
        }
    }
}
