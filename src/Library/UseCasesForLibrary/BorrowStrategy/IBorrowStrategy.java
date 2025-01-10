package Library.UseCasesForLibrary.BorrowStrategy;

import Library.Entities.Book.Book;
import Library.Entities.Member.Member;

public interface IBorrowStrategy {
    boolean borrowBook(Member member, Book book, double amount);
    boolean reserveBook(Member member, Book book, double amount);
}
