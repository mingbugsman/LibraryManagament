package Library.UseCasesForLibrary;

import Library.Entities.Book.Book;

import java.util.List;

public class ManagerBook {

    private final IBookRepository _bookRepository;
    public  ManagerBook(IBookRepository bookRepository) {
        this._bookRepository = bookRepository;
    }
    //get
    public Book getBook(String ID) {
        return _bookRepository.GetBookByID(ID);
    }
    public List<Book> getListBooks() {
        return _bookRepository.GetAllBooks();
    }

    public List<Book> searchBookByTitle(String title) {
        return _bookRepository.searchBookByAuthor(title);
    }
    // post
    public void AddNewBook(Book newBook) {
        _bookRepository.Add(newBook);
    }
    // delete
    public void RemoveBook(Book book) {
        _bookRepository.Remove(book);
    }
    // put
    public Book updateBook(Book updateBook) {
        return _bookRepository.updateBook(updateBook);
    }

}
