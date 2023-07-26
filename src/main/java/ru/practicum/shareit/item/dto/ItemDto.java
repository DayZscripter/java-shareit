package ru.practicum.shareit.item.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.user.model.User;

/**
 * TODO Sprint add-controllers.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemDto {
    long id;
    //@NotBlank(message = "Имя не должно быть пустым")
    String name;
    //@NotBlank(message = "Описание не должно быть пустым")
    String description;
    //@NotNull(message = "Доступность должна быть указана явно")
    Boolean available;    //Boolean класс обертка boolean. может быть true, false, NULL.
    User owner;
    ItemRequest request;
}

//Разделять объекты, которые хранятся в базе данных и которые возвращаются пользователям,
// — хорошая практика. Например, вы можете не захотеть показывать пользователям владельца вещи
// (поле owner), а вместо этого возвращать только информацию о том, сколько раз вещь была в аренде.
// Чтобы это реализовать, нужно создать отдельную версию каждого класса, с которой будут работать
// пользователи, — DTO (Data Transfer Object).
