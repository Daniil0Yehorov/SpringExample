package com.Spring.SpringTest.Repos;

import com.Spring.SpringTest.models.Post;
import org.springframework.data.repository.CrudRepository;
//all functions in the crudrepository are receiving, etc.
public interface PostRepository extends CrudRepository<Post,Long> {

}
