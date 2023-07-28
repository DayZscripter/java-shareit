package ru.practicum.shareit.item.model;

import lombok.AccessLevel;
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
public class Item {
    long id;             //уникальный идентификатор вещи
    String name;         //краткое название вещи
    String description;  //развёрнутое описание вещи
    boolean available;   //статус о том, доступна или нет вещь для аренды
    User owner;          //владелец вещи
    ItemRequest request; //если вещь была создана по запросу другого пользователя, то в этом поле.
                         // будет храниться ссылка на соответствующий запрос.
}
