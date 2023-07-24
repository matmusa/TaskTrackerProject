package peaksoft.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import peaksoft.tasktracker.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User>getUserByEmail(String email);

    boolean existsByEmail(String email);

   List<User> getAllByEmail(String email);
    Optional<User>findUserByEmail(String email);

    boolean existsUserByEmail(String email);
}
