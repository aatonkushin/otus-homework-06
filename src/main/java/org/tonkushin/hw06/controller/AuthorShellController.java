package org.tonkushin.hw06.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.tonkushin.hw06.model.Author;
import org.tonkushin.hw06.service.author.AuthorService;

import java.util.List;

@ShellComponent
public class AuthorShellController {

    private final AuthorService authorService;

    @Autowired
    public AuthorShellController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ShellMethod(value = "Выводит список всех авторов.", key={"get-authors", "ga"})
    public String getAuthors() {

        List<Author> items = authorService.getAll();

        StringBuilder sb = new StringBuilder();
        sb.append("Код\tИмя автора\n");
        for (Author item : items) {
            sb.append(item.getId()).append("\t")
                    .append(item.getName()).append("\n");
        }

        return sb.toString();
    }

    @ShellMethod(value = "Добавляет автора с именем name.", key = {"add-author", "aa"})
    public String addAuthor(String name) {
        authorService.insert(new Author(-1, name));
        return "Автор '"+name+"' добавлен";
    }

    @ShellMethod(value = "Удаляет автора с указанным кодом.", key={"delete-author", "da"})
    public String deleteAuthor(long id){
        authorService.deleteById(id);
        return "Автор удалён";
    }
}

