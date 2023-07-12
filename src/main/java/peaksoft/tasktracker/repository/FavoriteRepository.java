package peaksoft.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.tasktracker.entity.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite,Long> {

}
