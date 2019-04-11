package org.tonkushin.hw06.service.genre;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.tonkushin.hw06.model.Genre;
import org.tonkushin.hw06.repository.genre.GenreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@TestPropertySource("/test.properties")
public class GenreServiceImplTest {

    @Test
    public void insert() {
        Genre genre = getGenre();

        GenreRepository repository = Mockito.mock(GenreRepository.class);
        Mockito.when(repository.save(genre)).thenReturn(genre);

        GenreService service = new GenreServiceImpl(repository);
        Assertions.assertThat(service.insert(genre)).isEqualTo(genre);
    }

    @Test
    public void getById() throws Exception {
        Genre genre = getGenre();
        Optional<Genre> opt = Optional.of(genre);

        GenreRepository repository = Mockito.mock(GenreRepository.class);
        Mockito.when(repository.findById(1L)).thenReturn(opt);

        GenreService service = new GenreServiceImpl(repository);
        Assertions.assertThat(service.getById(1L)).isEqualTo(genre);
    }

    @Test
    public void getAll() {
        List<Genre> genres = new ArrayList<>(3);
        for (long i = 1; i <= 3; i++) {
            genres.add(getGenre());
        }

        GenreRepository repository = Mockito.mock(GenreRepository.class);
        Mockito.when(repository.findAll()).thenReturn(genres);

        GenreService service = new GenreServiceImpl(repository);

        Assertions.assertThat(service.getAll().size()).isEqualTo(3L);
    }

    private Genre getGenre() {
        return new Genre("Драма");
    }
}
