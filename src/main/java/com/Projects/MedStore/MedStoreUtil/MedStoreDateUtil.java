package com.Projects.MedStore.MedStoreUtil;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.sql.Date;




public class MedStoreDateUtil {
    
    //this method returns only sql date without from midnight
	public static Date getTodaysDateOnly() throws ParseException{
		java.util.Date dt=new java.util.Date();
			DateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date dateWithoutTime=formatter.parse(formatter.format(dt));
			return new Date(dateWithoutTime.getTime());
	}
}
