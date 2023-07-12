package peaksoft.tasktracker.entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.*;
import peaksoft.tasktracker.enums.Role;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(
            generator = "user_gen",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "user_gen",
            sequenceName = "user_seq",
            allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String image;
    @ManyToMany(
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH},
            mappedBy = "users")
    private List<WorkSpace> workSpaces;
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "user")
    private List<Favorite> favorites;
    @ManyToMany (
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH},
            mappedBy = "users")
    private List<Column>columns;
    @ManyToMany(
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH},
            mappedBy = "users")
    private List<Card>cards;
    @ManyToMany(
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH},
            mappedBy = "users")
    private List<Notification>notifications;
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "user")
    private List<Comment>comments;
    @ManyToMany(
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH},
            mappedBy = "users")
    private List<Board>boards;
    private Role role;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<UserWorkSpaceRole>userWorkSpaceRoles;





















}
