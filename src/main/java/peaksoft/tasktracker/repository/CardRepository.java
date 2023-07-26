package peaksoft.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.tasktracker.entity.Card;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card,Long> {


}
