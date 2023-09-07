package ru.practicum.shareit.request;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.validation.ValidationGroups;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import static ru.practicum.shareit.util.Constants.header;

@Controller
@RequestMapping(path = "/requests")
@RequiredArgsConstructor
@Slf4j
@Validated
public class ItemRequestController {
    private final ItemRequestClient requestClient;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Validated(ValidationGroups.Create.class)
    public ResponseEntity<Object> addRequest(@RequestHeader(header) Long userId,
                                             @Valid @RequestBody ItemRequestDto itemRequestDto) {
        log.info("Добавлен новый запрос на бронирование: {}", itemRequestDto);
        return requestClient.addItemRequest(userId, itemRequestDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getItemsByUserId(@RequestHeader(header) Long userId) {
        log.info("Получен список запросов текущего пользователя " +
                "с id {}.", userId);
        return requestClient.getItemsRequests(userId);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> returnAll(@RequestHeader(header) Long userId,
                                            @RequestParam(defaultValue = "0") @Min(0) Integer from,
                                            @RequestParam(defaultValue = "20") @Min(1) @Max(100) Integer size) {
        var result = requestClient.getAllRequests(userId, from, size);
        log.info("Получен список запросов пользователя с id {}, созданных другими пользователями, " +
                ", from = {}, size = {}.", userId, from, size);
        return result;
    }

    @GetMapping("/{requestId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> get(@RequestHeader(header) Long userId,
                                      @PathVariable long requestId) {
        log.info("Получен запрос с id {}, userId {}.", requestId, userId);
        return requestClient.getRequestById(userId, requestId);
    }
}
