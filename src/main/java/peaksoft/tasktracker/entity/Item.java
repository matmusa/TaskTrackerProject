package peaksoft.tasktracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(
            generator = "item_gen",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "item_gen",
            sequenceName = "item_seq",
            allocationSize = 1
    )
    private Long id;
    private String title;
    private Boolean isDone;

    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    private CheckList checkList;
}
