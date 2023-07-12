package peaksoft.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.tasktracker.entity.Card;

public interface CardRepository extends JpaRepository<Card,Long> {

}
