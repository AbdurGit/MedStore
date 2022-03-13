package com.Projects.MedStore.service;

import java.util.Optional;

import com.Projects.MedStore.Model.Notification;
import com.Projects.MedStore.repository.NotificationRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class NotificationService {
    @Autowired  
NotificationRepository notificationRepository;  
	
public Iterable<Notification> getAllNotification() {
    return notificationRepository.findAll();
}

public void deleteNotification(String notificationId) {
    notificationRepository.deleteById(notificationId);
}

public void muteNotification(String notificationId) {
    Optional<Notification> notif=notificationRepository.findById(notificationId);
    if(notif.isPresent()==true){
       Notification notification= notif.get();
       notification.setMuted(true);
       notificationRepository.save(notification);

    }

    
        


}
public Optional<Notification> getNotificationById(String notificationId) {
        
    return notificationRepository.findById(notificationId);
}

public void saveNotification( Notification notification) {
    notificationRepository.save(notification);
}


	
}
