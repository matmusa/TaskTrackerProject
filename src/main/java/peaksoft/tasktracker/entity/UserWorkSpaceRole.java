package peaksoft.tasktracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.tasktracker.enums.Role;

@Entity
@Table(name = "userWorkSpaceRoles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserWorkSpaceRole {
    @Id
    @GeneratedValue(
            generator = "userWorkSpaceRole_gen",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "userWorkSpaceRole_gen",
            sequenceName = "userWorkSpaceRole_gen",
            allocationSize = 1,
            initialValue = 6)
    private Long id;
    @Enumerated(EnumType.STRING
    )
    private Role role;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    private User user;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    private WorkSpace workSpace;

    public UserWorkSpaceRole(Role role, User user, WorkSpace workSpace) {
        this.role = role;
        this.user = user;
        this.workSpace = workSpace;
    }
}
