package peaksoft.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.tasktracker.entity.UserWorkSpaceRole;

public interface UserWorkSpaceRoleRepository extends JpaRepository<UserWorkSpaceRole,Long> {

}
