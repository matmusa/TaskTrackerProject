package peaksoft.tasktracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "workSpaces")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkSpace {
    @Id
    @GeneratedValue(
            generator = "workSpace_gen",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "workSpace_gen",
            sequenceName = "workSpace_seq",
            allocationSize = 1
    )
    private Long id;
    private String name;
    private Long adminId;
    @ManyToMany(
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH})
    List<User> users;
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "workSpace")
    private List<Board> boards;
    @OneToOne(cascade = CascadeType.ALL,
            mappedBy = "workSpace")
    private Favorite favorite;
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "workSpace")
    List<UserWorkSpaceRole> userWorkSpaceRoles;


}
