package org.tonkushin.hw06.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.tonkushin.hw06.model.Book;
import org.tonkushin.hw06.model.BookComment;
import org.tonkushin.hw06.service.book.BookService;
import org.tonkushin.hw06.service.bookcomment.BookCommentService;

import java.util.List;

@ShellComponent
public class BookShellController {
    private final BookService service;
    private final BookCommentService bookCommentService;

    @Autowired
    public BookShellController(BookService service, BookCommentService bookCommentService) {
        this.service = service;
        this.bookCommentService = bookCommentService;
    }

    @ShellMethod(value = "Выводит список всех книг", key = {"get-books", "gb"})
    public String getBooks() {
        List<Book> items = service.getAll();

        StringBuilder sb = new StringBuilder();
        sb.append("Код\tНазвание книги\tАвтор\tЖанр\n");
        for (Book item : items) {
            sb.append(item.getId()).append("\t")
                    .append(item.getName()).append("\t")
                    .append(item.getAuthor().getName()).append("\t")
                    .append(item.getGenre().getName()).append("\n");
        }

        return sb.toString();
    }

    @ShellMethod(value = "Добавляет книгу с названием name, автором с кодом authorId и жанром с кодом genreId", key = {"add-book", "ab"})
    public String addBook(String name, long authorId, long genreId) {
        service.insert(name, authorId, genreId);
        return "Книга с названием '" + name + "' добавлена";
    }

    @ShellMethod(value = "Добавляет комментарий к книге", key = {"add-book-comment", "abc"})
    public String addBookComment(long bookId, String commentText) {
        bookCommentService.insert(bookId, commentText);
        return "Комментарий добавлен";
    }

    @ShellMethod(value = "Выводит все комментарии к книге", key = {"get-book-comments", "gbc"})
    public String getBookComments(long bookId) {
        Book b = service.getById(bookId);

        StringBuilder sb = new StringBuilder();
        sb.append("Название: ").append(b.getName()).append("\n");
        sb.append("Автор: ").append(b.getAuthor().getName()).append("\n");
        sb.append("Жанр: ").append(b.getGenre().getName()).append("\n");
        sb.append("Коментарии:\n");

        for (BookComment bc : b.getComments()) {
            sb.append(bc.getId()).append("\t").append(bc.getCommentText()).append("\n");
        }

        return sb.toString();
    }

    @ShellMethod(value = "Удаляет книгу с указанным кодом.", key = {"delete-book", "db"})
    public String deleteBook(long id) {
        service.deleteById(id);
        return "Книга удалена";
    }
}
