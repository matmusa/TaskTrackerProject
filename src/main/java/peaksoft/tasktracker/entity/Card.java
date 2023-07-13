package peaksoft.tasktracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "cards")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(
            generator = "card_gen",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "card_gen",
            sequenceName = "card_seq",
            allocationSize = 1
    )
    private Long id;
    private String title;
    private String description;
    private Boolean isArchive;

    @ManyToMany(
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH})
    List<User> users;

    @ManyToMany(
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH},
    mappedBy = "cards")
    List<Label> labels;

    @OneToMany(
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH},
            mappedBy = "card"
    )
    private List<Notification> notifications;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "card")
    private List<Attachment> attachments;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "card")
    private List<Comment> comments;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "card")
    private List<CheckList> checkLists;

    @OneToOne(cascade = CascadeType.ALL,
            mappedBy = "card")
    private Estimation estimation;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    private Column column;

}
