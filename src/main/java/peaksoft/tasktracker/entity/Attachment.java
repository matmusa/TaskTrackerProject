package peaksoft.tasktracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity
@Table(name = "attachments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Attachment {
    @Id
    @GeneratedValue(
            generator = "attachment_gen",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "attachment_gen",
            sequenceName = "attachment_seq",
            allocationSize = 1,
            initialValue = 6
    )
    private Long id;
    private String documentLink;
    private ZonedDateTime createdAt;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    private Card card;


}
