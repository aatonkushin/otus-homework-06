package org.tonkushin.hw06.service.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tonkushin.hw06.model.Author;
import org.tonkushin.hw06.repository.author.AuthorRepositoryJpa;
import org.tonkushin.hw06.repository.author.AuthorRepositoryJpaImpl;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepositoryJpa repository;

    @Autowired
    public AuthorServiceImpl(AuthorRepositoryJpa repository) {
        this.repository = repository;
    }


    @Override
    public Author insert(Author item) {
        return repository.insert(item);
    }

    @Override
    public Author getById(long id) {
        return repository.getById(id);
    }

    @Override
    public List<Author> getAll() {
        return repository.getAll();
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
