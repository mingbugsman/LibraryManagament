package Library.UseCasesForLibrary;


import Library.Entities.Book.Book;

import java.util.List;

public interface IBookRepository {
    void Add(Book book);
    void Remove(Book book);
    Book updateBook(Book updateBook);
    Book GetBookByID(String IdBook);

    List<Book> GetAllBooks();
    List<Book> searchBookByTitle(String title);
    List<Book> searchBookByAuthor(String Author);
}
