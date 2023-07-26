package peaksoft.tasktracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity
@Table(name = "estimations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Estimation {
    @Id
    @GeneratedValue(
            generator = "estimation_gen",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "estimation_gen",
            sequenceName = "estimation_seq",
            allocationSize = 1,
            initialValue = 6
    )
    private Long id;
    private String reminder;
    private ZonedDateTime startDate;
    private ZonedDateTime dueDate;
    private ZonedDateTime time;

    @OneToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    private Card card;

    @OneToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
   private Notification notification;

}
