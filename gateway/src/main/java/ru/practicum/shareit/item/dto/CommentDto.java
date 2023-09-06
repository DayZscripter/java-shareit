package ru.practicum.shareit.item.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentDto {
    long id;
    @NotBlank(message = "Текст комментария не может быть пустым")
    @Size(max = 512, message = "Текст комментария не может быть больше 512 символов")
    String text;
    long itemId;
    long authorId;
    String authorName;
    LocalDateTime created;
}



