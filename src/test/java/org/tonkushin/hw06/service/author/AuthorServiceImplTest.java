package org.tonkushin.hw06.service.author;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.tonkushin.hw06.model.Author;
import org.tonkushin.hw06.repository.author.AuthorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@TestPropertySource("/test.properties")
public class AuthorServiceImplTest {

    @Test
    public void insert() {
        Author author = getAuthor();

        AuthorRepository repository = Mockito.mock(AuthorRepository.class);
        Mockito.when(repository.save(author)).thenReturn(author);

        AuthorService service = new AuthorServiceImpl(repository);
        Assertions.assertThat(service.insert(author)).isEqualTo(author);
    }

    @Test
    public void getById() throws Exception {
        Author author = getAuthor();
        Optional<Author> opt = Optional.of(author);

        AuthorRepository repository = Mockito.mock(AuthorRepository.class);
        Mockito.when(repository.findById(1L)).thenReturn(opt);

        AuthorService service = new AuthorServiceImpl(repository);
        Assertions.assertThat(service.getById(1L)).isEqualTo(author);
    }

    @Test
    public void getAll() {
        List<Author> authors = new ArrayList<>(3);
        for (long i = 1; i <= 3; i++) {
            authors.add(getAuthor());
        }

        AuthorRepository repository = Mockito.mock(AuthorRepository.class);
        Mockito.when(repository.findAll()).thenReturn(authors);

        AuthorService service = new AuthorServiceImpl(repository);

        Assertions.assertThat(service.getAll().size()).isEqualTo(3L);
    }

    private Author getAuthor() {
        return new Author("Пушкин");
    }
}
