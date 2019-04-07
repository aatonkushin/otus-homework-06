package org.tonkushin.hw06.repository.genre;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.tonkushin.hw06.model.Genre;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(GenreRepositoryJpaImpl.class)
@TestPropertySource("/test.properties")
public class GenreRepositoryJpaImplTest {

    @Autowired
    private GenreRepositoryJpa repository;

    @Test
    public void insert() {
        System.out.println("Проверяем, что после вставки новой записи, её код больше 0, а наименование совпадает с оригинальной записью...");
        Genre originalGenre = getGenre();
        Genre savedGenre = repository.insert(originalGenre);
        Assertions.assertThat(savedGenre.getId()).isGreaterThan(-1);
        Assertions.assertThat(savedGenre.getName()).isEqualTo(originalGenre.getName());
        System.out.println("OK");
    }

    @Test
    public void getById() {
        System.out.println("Проверяем, что после вставки новой записи, мы находим её по коду...");
        Genre GenreToInsert = getGenre();
        Genre savedGenre = repository.insert(GenreToInsert);
        Genre Genre = repository.getById(savedGenre.getId());
        Assertions.assertThat(Genre.getName()).isEqualTo(savedGenre.getName());
        System.out.println("ОК");
    }

    @Test
    public void getAll() {
        System.out.println("Проверяем, что после вставки трёх записей, получаемый список состоит из трёх элементов...");
        for (int i = 0; i < 3; i++) {
            Genre g = getGenre();
            g.setName(g.getName()+i);
            repository.insert(g);
        }
        List<Genre> items = repository.getAll();
        Assertions.assertThat(items.size()).isEqualTo(3);

        System.out.println("ОК");
    }

    @Test
    public void deleteById() {
        System.out.println("Проверяем, что после вставки новой записи, мы находим её по коду и удаляем её");
        Genre GenreToInsert = getGenre();
        Genre savedGenre = repository.insert(GenreToInsert);
        Assertions.assertThat(repository.getAll().size()).isEqualTo(1);
        repository.deleteById(savedGenre.getId());
        Assertions.assertThat(repository.getAll().size()).isEqualTo(0);
        System.out.println("ОК");
    }

    private Genre getGenre() {
        return new Genre("Комедия");
    }
}
