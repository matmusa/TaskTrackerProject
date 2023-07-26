package peaksoft.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.tasktracker.entity.Label;

public interface LabelRepository extends JpaRepository<Label,Long> {


}
