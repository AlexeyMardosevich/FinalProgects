package online.javaclass.bookstore.service;

import online.javaclass.bookstore.service.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService extends AbstractService<Long, UserDto> {
    UserDto login (String email, String password);

    @Override
    Page<UserDto> getAll(Pageable pageable);
}
