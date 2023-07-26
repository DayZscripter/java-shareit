package ru.practicum.shareit.request.model;

import lombok.AccessLevel;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;

/**
 * TODO Sprint add-item-requests.
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemRequest {       //класс, отвечающий за запрос вещи.
    Long id;                     //уникальный идентификатор запроса.
    final String description;    //текст запроса, содержащий описание требуемой вещи.
    User requestor;              //пользователь, создавший запрос.
    LocalDateTime created;       // дата и время создания запроса.
}
