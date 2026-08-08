package data.repository;

import data.entities.User;

public interface UserRepository extends AbstractRepository<Long, User>{
    User login (String email, String password);

}
