package org.tonkushin.hw06.repository.author;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.tonkushin.hw06.model.Author;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(AuthorRepositoryJpaImpl.class)
@TestPropertySource("/test.properties")
public class AuthorRepositoryJpaImplTest {

    @Autowired
    private AuthorRepositoryJpa repository;
    @Test
    public void insert() {
        System.out.println("Проверяем, что после вставки новой записи, её код больше 0, а наименование совпадает с оригинальной записью...");
        Author originalAuthor = getAuthor();
        Author savedAuthor = repository.insert(originalAuthor);
        Assertions.assertThat(savedAuthor.getId()).isGreaterThan(-1);
        Assertions.assertThat(savedAuthor.getName()).isEqualTo(originalAuthor.getName());
        System.out.println("OK");
    }

    @Test
    public void getById() {
        System.out.println("Проверяем, что после вставки новой записи, мы находим её по коду...");
        Author authorToInsert = getAuthor();
        Author savedAuthor = repository.insert(authorToInsert);
        Author author = repository.getById(savedAuthor.getId());
        Assertions.assertThat(author.getName()).isEqualTo(savedAuthor.getName());
        System.out.println("ОК");
    }

    @Test
    public void getAll() {
        System.out.println("Проверяем, что после вставки трёх записей, получаемый список состоит из трёх элементов...");
        for (int i = 0; i < 3; i++) {
            repository.insert(getAuthor());
        }
        List<Author> items = repository.getAll();
        Assertions.assertThat(items.size()).isEqualTo(3);

        System.out.println("ОК");
    }

    @Test
    public void deleteById() {
        System.out.println("Проверяем, что после вставки новой записи, мы находим её по коду и удаляем её");
        Author authorToInsert = getAuthor();
        Author savedAuthor = repository.insert(authorToInsert);
        Assertions.assertThat(repository.getAll().size()).isEqualTo(1);
        repository.deleteById(savedAuthor.getId());
        Assertions.assertThat(repository.getAll().size()).isEqualTo(0);
        System.out.println("ОК");
    }

    private Author getAuthor(){
        return new Author("Пушкин");
    }
}
