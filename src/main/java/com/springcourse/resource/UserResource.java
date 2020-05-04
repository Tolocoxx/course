package com.springcourse.resource;

import com.springcourse.domain.Request;
import com.springcourse.domain.User;
import com.springcourse.dto.UserLogindto;
import com.springcourse.dto.UserUpdateRoleDTO;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
import com.springcourse.service.RequestService;
import com.springcourse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "users")
public class UserResource {
    @Autowired
    private UserService userService;

    @Autowired
    private RequestService requestService;

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user){
        User createdUser = userService.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable(name = "id") Long id, @RequestBody User user){
        user.setId(id);
        User updateduser = userService.update(user);

        return ResponseEntity.ok(updateduser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable(name = "id") Long id){
        User user = userService.getById(id);

        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<PageModel<User>> listAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size){

        PageRequestModel pr = new PageRequestModel(page, size);
        PageModel<User> pm = userService.listAllOnLazyMode(pr);

        return ResponseEntity.ok(pm);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserLogindto user){
        User loggedUser = userService.login(user.getEmail(), user.getPassword());

        return ResponseEntity.ok(loggedUser);
    }

    @GetMapping("/{id}/requests")
    public ResponseEntity<PageModel<Request>> listAllRequestsById(
            @PathVariable(name = "id") Long id,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "page", defaultValue = "0") int page){

        PageRequestModel pr = new PageRequestModel(page,size);
        PageModel<Request> pm = requestService.listAllByOwnerIdOnLazyModel(id, pr);

        return ResponseEntity.ok(pm);
    }

    @PutMapping("/role/{id}")
    public ResponseEntity<?> updateRole(@PathVariable(value = "id") Long id, @RequestBody UserUpdateRoleDTO userdto){
        User user = new User();
        user.setId(id);
        user.setRole(userdto.getRole());

        userService.updateRole(user);

        return ResponseEntity.ok().build();

    }
}
