package ru.practicum.shareit.user.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * TODO Sprint add-controllers.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    long id;      //уникальный идентификатор пользователя
    String name;  //имя или логин пользователя
    String email; //адрес электронной почты
}
