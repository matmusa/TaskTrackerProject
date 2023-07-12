package peaksoft.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.tasktracker.entity.Board;

public interface BoardRepository extends JpaRepository<Board,Long> {

}
