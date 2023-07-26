package peaksoft.tasktracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "checkLists")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckList {
    @Id
    @GeneratedValue(
            generator = "checkList_gen",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "checkList_gen",
            sequenceName = "checkList_seq",
            allocationSize = 1,
            initialValue = 6
    )
    private Long id;
    private String description;
    private int percent;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "checkList")
    private List<Item>items;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.MERGE})
    private Card card;


}
