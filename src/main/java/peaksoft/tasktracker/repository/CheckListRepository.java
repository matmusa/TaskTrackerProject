package peaksoft.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.tasktracker.entity.CheckList;

public interface CheckListRepository extends JpaRepository<CheckList,Long> {

}
