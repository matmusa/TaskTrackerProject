package peaksoft.tasktracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "favorites")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Favorite {
    @Id
    @GeneratedValue(
            generator = "favorites_gen",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "favorites_gen",
            sequenceName = "favorites_seq",
            allocationSize = 1
    )
    private Long id;
    @OneToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    private Board board;
    @ManyToOne (cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    private User user;
    @OneToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    private WorkSpace workSpace;

}
