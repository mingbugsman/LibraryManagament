package Library.LibraryManager.BorrowManager;

import Library.Entities.Book.Book;
import Library.Entities.Member.Member;
import Library.ErrorManagement.BorrowedException.BorrowException;

public interface IBorrowStrategy {
    void borrowBook(Member member, Book book, double amount) throws BorrowException;

}
