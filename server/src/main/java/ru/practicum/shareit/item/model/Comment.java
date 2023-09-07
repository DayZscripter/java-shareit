package ru.practicum.shareit.item.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.user.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "comments", schema = "public")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(name = "text")
    String text;
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    Item item;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    User author;
    LocalDateTime created;


}