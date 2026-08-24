package online.javaclass.bookstore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import online.javaclass.bookstore.data.dto.PageResponseDto;
import online.javaclass.bookstore.data.dto.PageableDto;
import online.javaclass.bookstore.data.entities.User;
import online.javaclass.bookstore.data.repository.UserRepository;
import online.javaclass.bookstore.mapper.ServiceDtoMapper;
import online.javaclass.bookstore.service.DigestService;
import online.javaclass.bookstore.service.UserService;
import online.javaclass.bookstore.service.dto.UserDto;
import online.javaclass.bookstore.service.exception.AppException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static online.javaclass.bookstore.mapper.ServiceDtoMapper.toDto;
import static online.javaclass.bookstore.mapper.ServiceDtoMapper.toEntity;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final DigestService digestService;

    @Override
    public UserDto login(String email, String password) {
        User user = userRepository.findByEmail(email);
        String hashed = digestService.hash(password);
        if (user == null || !user.getPassword().equals(hashed)) {
            log.warn("Login failed for email: {}", email);
            throw new AppException("Invalid email or password" + email);
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
                .collect(Collectors.toList());
    }

    @Override
    public PageResponseDto<UserDto> getAll(PageableDto pageableDto) {
        List<UserDto> users = userRepository
                .getAll(pageableDto.getPageSize(), pageableDto.getOffset())
                .stream()
                .map(ServiceDtoMapper::toDto)
                .collect(Collectors.toList());
        int totalItems = userRepository.countAll();
        return new PageResponseDto<>(users, pageableDto.getPage(), pageableDto.getPageSize(), totalItems);
    }

    @Override
    @Transactional
    public UserDto create(UserDto userDto) {
        if (userDto == null) {
            throw new AppException("User must not be null");
        }
        if (userDto.getEmail() == null || userDto.getEmail().isBlank()) {
            throw new AppException("Email must not be blank");
        }
        if (userDto.getPassword() == null || userDto.getPassword().isBlank()) {
            throw new AppException("Password must not be blank");
        }
        if (userRepository.findByEmail(userDto.getEmail()) != null) {
            throw new AppException("User with email " + userDto.getEmail() + " already exists");
        }
        User user = toEntity(userDto);
        user.setPassword(digestService.hash(userDto.getPassword()));
        User createdUser = userRepository.create(user);
        return toDto(createdUser);
    }

    @Override
    @Transactional
    public UserDto update(UserDto userDto) {
        if (userDto == null || userDto.getId() == null) {
            throw new AppException("User id must not be null");
        }
        User user = userRepository.find(userDto.getId());
        if (user == null) {
            throw new AppException("Couldn't find user with id: " + userDto.getId());
        }
        user.setEmail(userDto.getEmail());
        user.setRole(User.Role.valueOf(userDto.getRole()));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        User updatedUser = userRepository.update(user);
        return toDto(updatedUser);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        if (id == null) {
            throw new AppException("User id must not be null");
        }
        if (!userRepository.deleteById(id)) {
            throw new AppException("Couldn't find user with id: " + id);
        }
        return true;
    }
}
