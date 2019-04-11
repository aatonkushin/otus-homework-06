package org.tonkushin.hw06;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.tonkushin.hw06.service.author.AuthorServiceImplTest;
import org.tonkushin.hw06.service.book.BookServiceImplTest;
import org.tonkushin.hw06.service.genre.GenreServiceImplTest;

@RunWith(Suite.class)
@SpringBootTest
@TestPropertySource("/test.properties")
@Suite.SuiteClasses({AuthorServiceImplTest.class, GenreServiceImplTest.class, BookServiceImplTest.class})
public class Hw06ApplicationTests {

    @Test
    public void contextLoads() {
    }

}
