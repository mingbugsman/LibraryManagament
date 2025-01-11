package Library.LibraryManager.BorrowManager;

import Library.Entities.Book.Book;
import Library.Entities.Member.Member;
import Library.ErrorManagement.BorrowedException.BorrowException;

public class BorrowManager {
    public void borrowBook(IBorrowStrategy borrowStrategy,Member member, Book book, double amount) throws BorrowException {
        borrowStrategy.borrowBook(member, book, amount);
    }

}
