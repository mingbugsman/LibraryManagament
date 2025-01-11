package Library.UseCasesForLibrary.UseCases_BorrowBookTransaction;

import Library.Entities.BookTransaction.BookTransaction;

import java.time.LocalDate;
import java.util.List;

public class BorrowTransactionManagement {
    private final IBookTransactionRepository _bookTransactionRepository;
    public BorrowTransactionManagement(IBookTransactionRepository bookTransactionRepository) {
        _bookTransactionRepository = bookTransactionRepository;
    }
    public void Add(BookTransaction addObj) {
        _bookTransactionRepository.Add(addObj);
    }
    public void Remove(BookTransaction removeObj) {
        _bookTransactionRepository.Remove(removeObj);
    }
    public BookTransaction Update(BookTransaction updateObj) {
        return _bookTransactionRepository.Update(updateObj);
    }

    List<BookTransaction> getAll() {
        return _bookTransactionRepository.getAll();
    }

    public BookTransaction getBookTransaction(String ID_member) {
        return  _bookTransactionRepository.getBookTransaction(ID_member);
    }

    public List<BookTransaction> getByDuringTime(LocalDate startDate, LocalDate endDate) {
        return _bookTransactionRepository.getByDuringTime(startDate, endDate);
    }

}
