package peaksoft.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.tasktracker.entity.Column;

public interface ColumnRepository extends JpaRepository<Column,Long> {

}
