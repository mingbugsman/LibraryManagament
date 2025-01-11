package Library.UseCasesForLibrary.UseCases_Book;


import Library.Entities.Book.Book;

import java.util.List;

public interface IBookRepository {
    void Add(Book book);
    void Remove(Book book);
    Book updateBook(Book updateBook);
    Book GetBookByID(String IdBook);

    boolean isExistedBook(String ID_member);
    List<Book> GetAllBooks();
    List<Book> searchBookByTitle(String title);
    List<Book> searchBookByAuthor(String Author);
}
