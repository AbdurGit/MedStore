package com.Projects.MedStore;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.UUID;
import java.sql.Date;

import com.Projects.MedStore.Model.Notification;
import com.Projects.MedStore.Model.Product;
import com.Projects.MedStore.repository.NotificationRepository;
import com.Projects.MedStore.repository.ProductRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
@Autowired
ProductRepository productRepository;

@Autowired
NotificationRepository notificationRepository;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	

	//@Scheduled(fixedRate = 1000*60*12) //run after each 12 hours
	@Scheduled(fixedRate = 1000*60)
	public void reportCurrentTime() throws ParseException {
		// log.info("The time is now {}", dateFormat.format(new Date()));
		
		Iterable<Product> pdtList=productRepository.findAll();

		for (Product pdt:pdtList){
			// java.util.Date dt=new java.util.Date();
			// DateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
			// java.util.Date dateWithoutTime=formatter.parse(formatter.format(dt));
			// Date today=new Date(dateWithoutTime.getTime());
			Date today=getTodaysDateOnly();
			long millis=pdt.getExpDate().getTime()-today.getTime();
			long dayDiff=millis/(1000*60*60*24);

			if(dayDiff>60){
				continue;
			}else{
				Optional<Notification> notif=notificationRepository.findById(pdt.getId());

				if(notif.isPresent()==false){
					Notification notification=new Notification();
					notification.setId(pdt.getId());
					notification.setBatch(pdt.getBatch());
					notification.setExpDate(pdt.getExpDate());
					int diff=(int)dayDiff;
					notification.setExpiringIn(diff);
					notification.setPdtName(pdt.getProductName());
					notification.setType("Expiry");
					notification.setMuted(false);
	
					notificationRepository.save(notification);
				}else{
					Notification notification=notif.get();				
					int diff=(int)dayDiff;
					notification.setExpiringIn(diff);
					notificationRepository.save(notification);
				}
				


			}



		}

	}
//this method returns only sql date without from midnight
	public Date getTodaysDateOnly() throws ParseException{
		java.util.Date dt=new java.util.Date();
			DateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date dateWithoutTime=formatter.parse(formatter.format(dt));
			return new Date(dateWithoutTime.getTime());
	}
}
