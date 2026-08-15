package online.javaclass.bookstore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import online.javaclass.bookstore.data.dto.PageableDto;
import online.javaclass.bookstore.service.dto.UserDto;
import online.javaclass.bookstore.data.entities.User;
import online.javaclass.bookstore.data.repository.UserRepository;
import online.javaclass.bookstore.mapper.ServiceDtoMapper;
import online.javaclass.bookstore.service.DigestService;
import online.javaclass.bookstore.service.UserService;
import online.javaclass.bookstore.service.exception.AppException;

import java.util.List;

import static online.javaclass.bookstore.mapper.ServiceDtoMapper.toDto;
import static online.javaclass.bookstore.mapper.ServiceDtoMapper.toEntity;

@Log4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final DigestService digestService;

    @Override
    public UserDto login(String email, String password) {
        User user = userRepository.findByEmail(email);
        String hashed = digestService.hash(password);

        if (user == null || !user.getPassword().equals(hashed)) {
            throw new RuntimeException("Invalid login entrapment" + email);
        }

        return toDto(user);
    }

    @Override
    public UserDto find(Long id) {
        User user = userRepository.find(id);

        if (user == null) {
            throw new AppException("Couldn't find user with id:" + id);
        }

        return toDto(user);
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.getAll()
                .stream()
                .map(ServiceDtoMapper::toDto)
                .toList();
    }

    @Override
    public List<UserDto> getAll(PageableDto pageableDto) {
        List<UserDto> bookDtoList = userRepository.getAll(pageableDto.getPageSize(), pageableDto.getOffset()).stream().
                map(ServiceDtoMapper::toDto).
                toList();

        int countAll = userRepository.countAll();
        int pages = countAll / pageableDto.getPageSize();

        if (countAll % pageableDto.getPageSize() != 0) {
            pages++;
        }

        pageableDto.setTotalItems(countAll);
        pageableDto.setTotalPages(pages);

        return bookDtoList;
    }

    @Override
    public UserDto create(UserDto userDto) {
        log.debug("Service call, Create new user");
        //Валидация
        User user = toEntity(userDto);
        String originalPassword = userDto.getPassword();
        String hashed = digestService.hash(originalPassword);
        user.setPassword(hashed);
        User created = userRepository.create(user);

        return toDto(created);
    }

    @Override
    public UserDto update(UserDto userDto) {
        User user = toEntity(userDto);
        User update = userRepository.update(user);

        return toDto(update);
    }

    @Override
    public boolean deleteById(Long id) {
        return userRepository.deleteById(id);
    }
}
