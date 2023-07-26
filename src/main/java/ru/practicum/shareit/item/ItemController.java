package ru.practicum.shareit.item;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.service.ItemService;

import javax.validation.Valid;
import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping("/items")
@Slf4j
@AllArgsConstructor
public class ItemController { //контроллер CRUD операций над вещами.
    ItemService itemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    //Добавление новой вещи. X-Sharer-User-Id это идентификатор юзера, который добавляет вещь
    public ItemDto addItem(@Valid @RequestBody ItemDto itemDto,
                           @RequestHeader("X-Sharer-User-Id") Long userId) { //id юзера-владельца добавившего вещь
        log.info("Добавлена вещь: {}", itemDto);
        return itemService.addItem(userId, itemDto);
    }

    @GetMapping("/{id}")
    public ItemDto getItemById(@PathVariable long id) { //Просмотр инфо о конкретной вещи по id
        return itemService.getItemByUserId(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    //Просмотр владельцем списка всех его вещей с указанием названия и описания для каждой.
    public List<ItemDto> getItemsByUserId(@RequestHeader("X-Sharer-User-Id") long userId) {
        return itemService.getItemsByUserId(userId);
    }

    @PatchMapping("/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    //Редактирование вещи владельцем.(Изменить можно название,описание и статус доступа к аренде)
    public ItemDto updateItem(@RequestHeader("X-Sharer-User-Id") long userId,
                              @PathVariable long itemId, @RequestBody ItemDto itemDto) {

        itemDto.setId(itemId);
        log.info("Обновление вещи id: {}", userId);
        return itemService.updateItem(userId, itemDto);
    }

    @GetMapping("/search")
    //Поиск вещи потенциальным арендатором
    public List<ItemDto> findByText(@RequestHeader("X-Sharer-User-Id") long userId,
                                    @RequestParam(value = "text") String text) {
        return itemService.searchText(text);
    }
}
