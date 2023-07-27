package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.ConflictException;
import ru.practicum.shareit.exception.ObjectNotFoundException;
import ru.practicum.shareit.user.dao.UserDao;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.dto.UserMapper;
import ru.practicum.shareit.user.model.User;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public UserDto addUser(UserDto userDto) {      //добавляем user в приложение.
        User user = UserMapper.toUser(userDto);    //конвертируем user в dto
        validateUser(user);                        //проверка email и user
        if (emailIsExist(user)) {
            throw new ConflictException("Пользователь с такой почтой уже существует!");
        }
        log.info("Добавлен новый пользователь; {}", user.getName());
        //конвертируем в dto объект мапером и добавляем нового user в HashMap и возвращаем dto объект
        // с информацией по добавленному пользователю.
        return UserMapper.toUserDto(userDao.addUser(user));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userDao.getAllUsers().stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User newUser = UserMapper.toUser(userDto);
        User user = userDao.getUserById(newUser.getId());

        if (user == null) {
            throw new ValidationException("Пользователь не существует!");
        }

        if (newUser.getEmail() == null) {

            newUser.setEmail(user.getEmail());
        }
        if (newUser.getName() == null) {
            newUser.setName(user.getName());
        }

        validateUser(newUser);
        if (emailIsExist(newUser)) {
            throw new ConflictException("Такой email уже существует");
        }
        log.info("Данные пользователя обновлены: {}", newUser.getName());
        return UserMapper.toUserDto(userDao.updateUser(newUser));
    }

    @Override
    public void deleteUser(long id) {
        if (!isUserExists(id)) {
            throw new ObjectNotFoundException("Такого пользователя не существует!");
        }
        userDao.deleteUser(id);

    }

    @Override
    public UserDto getUserById(long id) {
        if (!isUserExists(id)) {
            throw new ObjectNotFoundException("Такого пользователя не существует!");
        }
        User user = userDao.getUserById(id);
        validateUser(user);
        return UserMapper.toUserDto(user);
    }

    private void validateUser(User user) {
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new ValidationException("Поле email не может быть пустым!");
        }
        if (!user.getEmail().contains("@")) {
            throw new ValidationException("Поле email должно содержать символ @");
        }
        if (user.getName() == null || user.getName().isEmpty() || user.getName().isBlank()) {
            throw new ObjectNotFoundException("Пользователь не найден");
        }
    }

    public boolean isUserExists(long userId) {
        boolean isExist = false;
        List<User> userList = userDao.getAllUsers();
        for (User user : userList) {
            if (Objects.equals(user.getId(), userId)) {
                isExist = true;
            }
        }
        return isExist;

    }

    private boolean emailIsExist(User user) {
        String email = user.getEmail();
        List<User> userList = userDao.getAllUsers();
        for (User user1 : userList) {
            if (user1.getEmail().contains(email)) {                   //чек на совпадения в почте.
                if (!Objects.equals(user1.getId(), user.getId())) {   //чек на  совпадения в юзерах.
                    log.info("Пользователь с таким email уже существует!");
                    return true;
                }

            }
        }
        return false;
    }

}