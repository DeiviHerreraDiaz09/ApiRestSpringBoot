package com.mangaproject.manga_asd.Repository;

import com.mangaproject.manga_asd.Model.User;
import org.springframework.data.repository.CrudRepository;

public interface userRepository extends CrudRepository<User, Integer> {

    User findByEmailAndPassword(String email, String password);

    User findByEmail(String email);

}
