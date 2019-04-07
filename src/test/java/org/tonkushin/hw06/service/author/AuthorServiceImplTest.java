package org.tonkushin.hw06.service.author;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.tonkushin.hw06.model.Author;
import org.tonkushin.hw06.repository.author.AuthorRepositoryJpa;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@TestPropertySource("/test.properties")
public class AuthorServiceImplTest {

    @Test
    public void insert() {
        Author author = getAuthor();

        AuthorRepositoryJpa repository = Mockito.mock(AuthorRepositoryJpa.class);
        Mockito.when(repository.insert(author)).thenReturn(author);

        AuthorService service = new AuthorServiceImpl(repository);
        Assertions.assertThat(service.insert(author)).isEqualTo(author);
    }

    @Test
    public void getById() {
        Author author = getAuthor();

        AuthorRepositoryJpa repository = Mockito.mock(AuthorRepositoryJpa.class);
        Mockito.when(repository.getById(1L)).thenReturn(author);

        AuthorService service = new AuthorServiceImpl(repository);
        Assertions.assertThat(service.getById(1L)).isEqualTo(author);
    }

    @Test
    public void getAll() {
        List<Author> authors = new ArrayList<>(3);
        for (long i=1; i<=3; i++){
            authors.add(getAuthor());
        }

        AuthorRepositoryJpa repository = Mockito.mock(AuthorRepositoryJpa.class);
        Mockito.when(repository.getAll()).thenReturn(authors);

        AuthorService service = new AuthorServiceImpl(repository);

        Assertions.assertThat(service.getAll().size()).isEqualTo(3L);
    }

    private Author getAuthor(){
        return new Author("Пушкин");
    }
}
