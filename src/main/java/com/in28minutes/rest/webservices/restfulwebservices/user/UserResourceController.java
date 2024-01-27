package com.in28minutes.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@RestController
public class UserResourceController {

    private UserDaoService service;

    public UserResourceController(UserDaoService service){
        this.service=service;
    }

    //get all users
    @GetMapping("/users")
    public List<User>retrieveAllUsers(){
        return service.findAll();

    }

    //get specific user
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id){
        User user = service.findOne(id);

        if(user == null)
            throw new UserNotFoundException("id" + id );

        EntityModel<User> entityModel = EntityModel.of(user);

        WebMvcLinkBuilder link =
                linkTo(methodOn(this.getClass()).retrieveAllUsers());

        entityModel.add(link.withRel("all-users"));

        //HATEOAS

        return entityModel;

    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = service.save(user);
        //location - /users/4 => /users /{id}, users.getID
        URI location = ServletUriComponentsBuilder
                       .fromCurrentRequest().path("/{id}")
                       .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();


    }

    //get specific user
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
         service.deleteById(id);

    }


}
