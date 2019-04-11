package org.tonkushin.hw06.service.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tonkushin.hw06.model.Author;
import org.tonkushin.hw06.repository.author.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository repository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository repository) {
        this.repository = repository;
    }


    @Override
    public Author insert(Author item) {
        return repository.save(item);
    }

    @Override
    public Author getById(long id) throws Exception {
        Optional<Author> opt = repository.findById(id);

        if (opt.isPresent()) {
            return opt.get();
        }

        throw new Exception("Author not found");
    }

    @Override
    public List<Author> getAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
