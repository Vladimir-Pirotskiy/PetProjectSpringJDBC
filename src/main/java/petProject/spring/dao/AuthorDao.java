package petProject.spring.dao;

import petProject.spring.persistance.Author;

import java.util.List;

public interface AuthorDao {

	List<Author> findByName(String name);

}
