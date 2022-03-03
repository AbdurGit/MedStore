package com.Projects.MedStore.service;

import com.Projects.MedStore.Model.User;
import com.Projects.MedStore.repository.UserCrudRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {

    @Autowired
    UserCrudRepository repo;

    public void saveUser(User user) {
      repo.save(user);
    }

}
