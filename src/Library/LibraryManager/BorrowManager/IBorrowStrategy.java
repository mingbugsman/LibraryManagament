package Library.LibraryManager.BorrowManager;

import Library.Entities.Book.Book;
import Library.Entities.Member.Member;
import Library.ErrorManagement.BorrowException;

import java.util.List;

public interface IBorrowStrategy {
    void borrowBook(Member member, Book book, double amount) throws BorrowException;
    void borrowMultipleBooks(Member member, List<Book> books, double amount) throws BorrowException;
    double calculateRemainingBalance(Member member, Book book, double amount);
    double calculateRemainingBalance(Member member, List<Book> book, double amount);

}
