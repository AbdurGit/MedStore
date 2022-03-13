package com.Projects.MedStore.controller;


import javax.websocket.server.PathParam;


import com.Projects.MedStore.service.NotificationService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

@Autowired
NotificationService notificationService;


@DeleteMapping("/deleteNotification/{notificationId}")
    public String deleteNotification(@PathVariable String notificationId
    )  throws Exception
    {
       // System.out.println(notificationId);
        notificationService.deleteNotification(notificationId);
       return notificationId;
    }

    @PostMapping("/muteNotification/{notificationId}")
    public String muteNotification(@PathVariable String notificationId
    )  throws Exception
    {
       // System.out.println(notificationId);
        notificationService.muteNotification(notificationId);
       return notificationId;
    }

    




    
}
