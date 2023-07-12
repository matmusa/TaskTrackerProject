package peaksoft.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.tasktracker.entity.WorkSpace;

public interface WorkSpaceRepository extends JpaRepository<WorkSpace,Long> {

}
