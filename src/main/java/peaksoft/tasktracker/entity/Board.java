package peaksoft.tasktracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "boards")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(
            generator = "board_gen",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "board_gen",
            sequenceName = "board_seq",
            allocationSize = 1,
            initialValue = 6
    )
    private Long id;
    private String title;
    private String backGround;

    @OneToOne(
                    cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH},
                    mappedBy = "board")
    private Favorite favorite;

    @ManyToOne(
            cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
   private WorkSpace workSpace;

    @ManyToMany(
            cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
   private List<User>users;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "board")
   private List<Column>columns;


}
