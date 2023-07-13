package peaksoft.tasktracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "labels")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Label {
    @Id
    @GeneratedValue(
            generator = "label_gen",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "label_gen",
            sequenceName = "label_seq",
            allocationSize = 1
    )
    private Long id;
    private String labelName;
    private String color;

    @ManyToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    private List<Card>cards;
}
