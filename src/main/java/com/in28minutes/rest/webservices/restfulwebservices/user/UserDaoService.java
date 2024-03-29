package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {

    private static List<User>users = new ArrayList<>();

    private static int usersCount = 0;

    static
    {
        users.add(new User(++usersCount,"Bajrang", LocalDate.now().minusYears(23)) );
        users.add(new User(++usersCount,"Ram", LocalDate.now().minusYears(26)) );
        users.add(new User(++usersCount,"Siya", LocalDate.now().minusYears(25)) );

    }

    //get all user
    public List<User>findAll(){
        return users;
    }

    public User save(User user){
        user.setId(++usersCount);
        users.add(user);
        return user;

    }


    public User findOne(int id){
        Predicate<? super User> predicate = user -> user.getId() == (id);

        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public void deleteById(int id){
        Predicate<? super User> predicate = user -> user.getId() == (id);
        users.removeIf(predicate);
    }


}
