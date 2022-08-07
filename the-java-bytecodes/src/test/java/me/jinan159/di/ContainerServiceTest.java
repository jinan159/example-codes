package me.jinan159.di;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ContainerServiceTest {

	@Test
	void getObjectTest() {
		BookRepository bookRepository = ContainerService.getObject(BookRepository.class);
		assertNotNull(bookRepository);
	}

	@Test
	void getObject_BookService() {
		BookService bookService = ContainerService.getObject(BookService.class);
		assertNotNull(bookService);
		assertNotNull(bookService.bookRepository);
	}
}
