package peaksoft.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.tasktracker.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification,Long> {

}
