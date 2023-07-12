package peaksoft.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.tasktracker.entity.Estimation;

public interface EstimationRepository extends JpaRepository<Estimation,Long> {

}
