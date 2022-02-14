package com.Projects.MedStore.repository;

import org.springframework.data.repository.CrudRepository;


import com.Projects.MedStore.Model.Product;


public interface ProductRepository extends CrudRepository<Product, String>  {

    



}
