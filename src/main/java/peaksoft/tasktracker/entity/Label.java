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
            allocationSize = 1,
            initialValue = 6
    )
    private Long id;
    private String labelName;
    private String color;

    @ManyToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    private List<Card>cards;

    public Label(String labelName, String color, List<Card> cards) {
        this.labelName = labelName;
        this.color = color;
        this.cards = cards;
    }

    public Label(String labelName, String color) {
        this.labelName = labelName;
        this.color = color;
    }
}
