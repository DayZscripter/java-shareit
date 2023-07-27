package ru.practicum.shareit.item.dto;

import lombok.experimental.UtilityClass;
import ru.practicum.shareit.item.model.Item;

@UtilityClass
public class ItemMapper {
    // Mapper-класс и метод преобразования объекта модели в DTO-объект
    public static ItemDto toItemDto(Item item) { // Item -> в объект ItemDto
        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.isAvailable())
                .build();
    }

    public static Item toItem(ItemDto itemDto) { //  ItemDto -> в объект Item для сохр. в db
        return Item.builder()
                .id(itemDto.getId())
                .name(itemDto.getName())
                .description(itemDto.getDescription())
                .available(itemDto.getAvailable() == null ? false : itemDto.getAvailable())
                .build();
    }
}

//Mapper-классы помогут преобразовывать объекты модели в DTO-объекты и обратно.
// Для базовых сущностей Item и User создан Mapper-класс и
// метод преобразования объекта модели в DTO-объект.

//В дальнейшем можно будет добавлять в них методы, которые нужны для обратного преобразования
// типов, — например, toItem().