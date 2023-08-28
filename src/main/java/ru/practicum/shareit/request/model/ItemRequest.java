package ru.practicum.shareit.request.model;

import lombok.AccessLevel;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import ru.practicum.shareit.user.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * TODO Sprint add-item-requests.
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "requests", schema = "public")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemRequest {       //класс, отвечающий за запрос вещи.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;                     //уникальный идентификатор запроса.
    @Column(name = "description")
    final String description;    //текст запроса, содержащий описание требуемой вещи.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requester_id", nullable = false)
    User requestor;              //пользователь, создавший запрос.
    @CreationTimestamp
    @Column(name = "created")
    LocalDateTime created;       // дата и время создания запроса.
}
