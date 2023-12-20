package com.mangaproject.manga_asd.Service;

import java.util.List;
import com.mangaproject.manga_asd.Model.User;
import com.mangaproject.manga_asd.Repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class _UserServiceImpl implements IUserService{


    @Autowired
    private userRepository userrepository;


    @Override
    public User save(User user) {
        return userrepository.save(user);
    }


     @Override
    public List<User> findAll() {
        return (List<User>) userrepository.findAll();
    }
}
