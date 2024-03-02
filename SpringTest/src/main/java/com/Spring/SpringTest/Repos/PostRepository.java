package com.Spring.SpringTest.Repos;

import com.Spring.SpringTest.models.Post;
import org.springframework.data.repository.CrudRepository;
//все функции в крудрепозитории есть получение и т.д
public interface PostRepository extends CrudRepository<Post,Long> {

}
