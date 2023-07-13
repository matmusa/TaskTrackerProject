package peaksoft.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.tasktracker.dto.response.UserResponse;
import peaksoft.tasktracker.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User>getUserByEmail(String email);
}
