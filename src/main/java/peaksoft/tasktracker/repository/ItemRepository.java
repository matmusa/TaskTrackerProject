package peaksoft.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.tasktracker.entity.Item;

public interface ItemRepository extends JpaRepository<Item,Long> {

}
