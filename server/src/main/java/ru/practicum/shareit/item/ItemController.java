package ru.practicum.shareit.item;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemItemRequestDto;
import ru.practicum.shareit.item.service.ItemService;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

import static ru.practicum.shareit.utill.Constants.header;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping("/items")
@Slf4j
@AllArgsConstructor
public class ItemController {
    ItemService itemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto addItem(@Valid @RequestBody ItemItemRequestDto itemDto, @RequestHeader(header) long userId) {
        log.info("Добавлена вещь: {}", itemDto);
        return itemService.addItem(userId, itemDto);
    }

    @PatchMapping("/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public ItemDto updateItem(@RequestHeader(header) Long userId,
                              @PathVariable long itemId,
                              @RequestBody ItemItemRequestDto itemDto) {

        itemDto.setId(itemId);
        log.info("Обновление вещи id: {}", userId);
        return itemService.updateItem(userId, itemDto);
    }

    @GetMapping("/{itemId}")
    public ItemDto getItemById(@RequestHeader(header) Long userId, @PathVariable long itemId) {
        return itemService.getItem(itemId, userId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ItemDto> getItemsByUserId(@RequestHeader(header) Long userId,
                                          @RequestParam(defaultValue = "0") @Min(0) Integer from,
                                          @RequestParam(defaultValue = "20") @Min(1) @Max(100) Integer size) {
        return itemService.getItemsByUserId(userId, from, size);
    }

    @GetMapping("/search")
    public List<ItemDto> seachText(
            @RequestParam(value = "text") String text,
            @RequestParam(defaultValue = "0") @Min(0) Integer from,
            @RequestParam(defaultValue = "20") @Min(1) @Max(100) Integer size) {
        return itemService.searchText(text, from, size);
    }

    @PostMapping("/{itemId}/comment")
    @ResponseStatus(HttpStatus.OK)
    public CommentDto addComment(@RequestHeader(header) Long userId,
                                 @RequestBody CommentDto commentDto,
                                 @PathVariable long itemId) {

        String text = commentDto.getText();
        if (text.isEmpty()) {
            throw new ValidationException("Поле text не может быть пустым!");
        }
        commentDto.setText(text);
        return itemService.addComment(userId, itemId, commentDto);
    }


}
