package ru.practicum.shareit.item.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.user.model.User;

import java.util.ArrayList;
import java.util.List;

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
    String name;
    String description;
    Boolean available;
    User owner;
    ItemRequest request;
    BookingDto lastBooking;
    BookingDto nextBooking;
    List<CommentDto> comments = new ArrayList<>();
}

//Разделять объекты, которые хранятся в базе данных и которые возвращаются пользователям,
// — хорошая практика. Например, вы можете не захотеть показывать пользователям владельца вещи
// (поле owner), а вместо этого возвращать только информацию о том, сколько раз вещь была в аренде.
// Чтобы это реализовать, нужно создать отдельную версию каждого класса, с которой будут работать
// пользователи, — DTO (Data Transfer Object).
