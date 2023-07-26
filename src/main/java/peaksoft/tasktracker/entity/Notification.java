package peaksoft.tasktracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.tasktracker.enums.NotificationType;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(
            generator = "notification_gen",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "notification_gen",
            sequenceName = "notification_seq",
            allocationSize = 1,
            initialValue = 6
    )
    private Long id;
    private String  text;
    private String image;
    private ZonedDateTime createdDate;
    private Boolean isRead;
    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @ManyToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    private List<User>users;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    private Card card;

    @OneToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH},
    mappedBy = "notification")
    private Estimation estimation;


}
