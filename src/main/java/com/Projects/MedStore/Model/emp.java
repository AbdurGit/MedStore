package com.Projects.MedStore.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class emp {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    
    private String name;
    
    
}
