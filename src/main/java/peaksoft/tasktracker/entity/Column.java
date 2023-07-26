package peaksoft.tasktracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "columns")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Column {
    @Id
    @GeneratedValue(
            generator = "column_gen",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "column_gen",
            sequenceName = "column_seq",
            allocationSize = 1,
            initialValue = 6
    )
    private Long id;
    private String title;
    private Boolean isArchive;

    @ManyToMany(
            cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    private List<User> users;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "column")
    private List<Card> cards;

    @ManyToOne(
            cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    private Board board;

}