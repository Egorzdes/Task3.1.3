package spring_rest.repository;

import org.springframework.data.repository.CrudRepository;
import spring_rest.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
}
