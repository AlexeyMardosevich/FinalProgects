package data.repository;

import data.entities.User;
import service.dto.UserDto;

public interface UserRepository extends AbstractRepository<Long, User>{
    User login (String email, String password);
    User findByEmail(String email);

}
