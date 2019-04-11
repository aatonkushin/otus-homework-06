package org.tonkushin.hw06.service.genre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tonkushin.hw06.model.Genre;
import org.tonkushin.hw06.repository.genre.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository repository;

    @Autowired
    public GenreServiceImpl(GenreRepository repository) {
        this.repository = repository;
    }

    @Override
    public Genre insert(Genre item) {
        return repository.save(item);
    }

    @Override
    public Genre getById(long id) throws Exception {
        Optional<Genre> opt = repository.findById(id);

        if (opt.isPresent())
            return opt.get();
        else
            throw new Exception("Genre not found");
    }

    @Override
    public List<Genre> getAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
