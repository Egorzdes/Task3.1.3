package spring_rest.repository;

import org.springframework.data.repository.CrudRepository;
import spring_rest.model.*;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    void deleteById(Long id);

}

