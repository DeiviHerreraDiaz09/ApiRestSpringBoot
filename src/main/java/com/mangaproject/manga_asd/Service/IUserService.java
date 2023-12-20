package com.mangaproject.manga_asd.Service;

import com.mangaproject.manga_asd.Model.User;
import java.util.List;

public interface IUserService {

    User save(User usuario);

    List<User> findAll();

}
