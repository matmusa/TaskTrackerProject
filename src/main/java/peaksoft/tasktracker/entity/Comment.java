package peaksoft.tasktracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity
@Table(name = "comments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(
            generator = "comment_gen",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "comment_gen",
            sequenceName = "comment_seq",
            allocationSize = 1,
            initialValue = 6
    )
    private Long id;
    private String comment;
    private ZonedDateTime createdDate;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    private Card card;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    private User user;

    public Comment(String comment, ZonedDateTime createdDate, Card card, User user) {
        this.comment = comment;
        this.createdDate = createdDate;
        this.card = card;
        this.user = user;
    }
}
