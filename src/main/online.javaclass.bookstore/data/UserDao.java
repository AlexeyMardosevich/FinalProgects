package data;

import data.entities.User;

import java.util.List;

public interface UserDao {

        User find (Long id);

        List <User> getAll ();

        User create (User user);

        User update (User user);

        boolean deleteById (Long Id);

}
