package com.Projects.MedStore.repository;
import com.Projects.MedStore.Model.Notification;

import org.springframework.data.repository.CrudRepository;

public interface NotificationRepository extends CrudRepository<Notification, String>{
    
}
