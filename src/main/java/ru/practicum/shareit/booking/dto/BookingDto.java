package ru.practicum.shareit.booking.dto;

import lombok.AccessLevel;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.booking.enums.BookingStatus;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;

/**
 * TODO Sprint add-bookings.
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingDto {
    Long id;               //уникальный идентификатор бронирования.
    LocalDateTime start;   //дата и время начала бронирования.
    LocalDateTime end;     //дата и время окончания бронирования.
    Item item;             //вещь, которую пользователь бронирует.
    User booker;           //пользователь, который осуществляет бронирование.
    BookingStatus status;  //статус бронирования. может принимать одно из нескольких значений.
}
