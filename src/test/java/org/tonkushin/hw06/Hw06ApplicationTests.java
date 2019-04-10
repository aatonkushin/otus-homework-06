package org.tonkushin.hw06;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.tonkushin.hw06.repository.author.AuthorRepositoryJpaImplTest;
import org.tonkushin.hw06.repository.book.BookRepositoryJpaImplTest;
import org.tonkushin.hw06.repository.bookcomment.BookCommentRepositoryJpaImplTest;
import org.tonkushin.hw06.repository.genre.GenreRepositoryJpaImpl;
import org.tonkushin.hw06.repository.genre.GenreRepositoryJpaImplTest;

@RunWith(Suite.class)
@SpringBootTest
@TestPropertySource("/test.properties")
@Suite.SuiteClasses({AuthorRepositoryJpaImplTest.class,
		GenreRepositoryJpaImplTest.class,
		BookRepositoryJpaImplTest.class,
		BookCommentRepositoryJpaImplTest.class})
public class Hw06ApplicationTests {

	@Test
	public void contextLoads() {
	}

}
