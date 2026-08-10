package service.impl;

import data.entities.User;
import data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import mapper.EntityDtoMapper;
import mapper.ServiceDtoMapper;
import service.exception.AppException;
import service.dto.UserDto;
import service.UserService;

import java.util.List;

@Log4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ServiceDtoMapper serviceDtoMapper;
    private final DigestServiceImpl digestService;

    @Override
    public UserDto login(String email, String password) {
        User user = userRepository.login(email, password);
        String hashed = digestService.hash(password);
        if (user == null || !user.getPassword().equals(hashed)) {
            throw new RuntimeException("Invalid login entrapment" + email);
        }
        return serviceDtoMapper.toDto(user);
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
                .map(this::toDto)
                .toList();
    }

    @Override
    public UserDto create(UserDto userDto) {
        log.debug("Service call, Create new user");
        //Валидация
        User user = serviceDtoMapper.toEntity(userDto);
        String originalPassword = userDto.getPassword();
        String hashed = digestService.hash(originalPassword);;
        user.setPassword(hashed);
        User created = userRepository.create(user);
        return serviceDtoMapper.toDto(created);
    }

    @Override
    public UserDto update(UserDto userDto) {
        User user = toEntity(userDto);
        User update = userRepository.update(user);
        return toDto(update);
    }

    @Override
    public boolean deleteById(Long id) {
        boolean delete = userRepository.deleteById(id);
        if (!delete) {
            throw new AppException("Couldn't delete user with id: " + id);
        }
        return delete;
    }

    private UserDto toDto(User entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setLastName(entity.getLastName());
        dto.setFirstName(entity.getFirstName());
        dto.setRole(entity.getRole());
        return dto;
    }

    private User toEntity(UserDto dto) {
        User entity = new User();
        entity.setId(dto.getId());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setLastName(dto.getLastName());
        entity.setFirstName(dto.getFirstName());
        entity.setRole(dto.getRole());
        return entity;
    }
}
