package Library.UseCasesForLibrary.UseCases_BorrowBookTransaction;

import Library.Entities.BookTransaction.BookTransaction;

import java.time.LocalDate;
import java.util.List;

// This Repository supports the system to query book borrowers
public interface IBookTransactionRepository {

    // add, remove, update
    void Add(BookTransaction addObj);
    void Remove(BookTransaction removeObj);
    void RemoveWhenBookisReturned(String memberID, String bookID);
    BookTransaction Update(BookTransaction updateObj);

    // check
    boolean isExistedBookTransaction(String ID_book, String ID_member);
    // get
    List<BookTransaction> getAll();
    BookTransaction getBookTransaction(String ID_member,String ID_book);
    List<BookTransaction> getByDuringTime(LocalDate startDate, LocalDate endDate);

}
