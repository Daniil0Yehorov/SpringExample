package com.Spring.SpringTest.Repos;
import com.Spring.SpringTest.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByLogin(String login);
}
