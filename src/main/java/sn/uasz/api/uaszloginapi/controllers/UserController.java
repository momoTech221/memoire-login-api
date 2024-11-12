package sn.uasz.api.uaszloginapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.uasz.api.uaszloginapi.config.UserAuthenticationProvider;
import sn.uasz.api.uaszloginapi.repositories.UserRepository;
import sn.uasz.api.uaszloginapi.services.UserLogService;
import sn.uasz.api.uaszloginapi.services.UserService;
import sn.uasz.spi.authApi.SignUpsApi;
import sn.uasz.spi.authApi.UsersApi;
import sn.uasz.spi.authApi.model.Log;
import sn.uasz.spi.authApi.model.LogResponse;
import sn.uasz.spi.authApi.model.User;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserController implements UsersApi, SignUpsApi {
    private final UserAuthenticationProvider userAuthenticationProvider;
    @Autowired
    UserService service;
    @Autowired
    private final UserLogService logService;

    @Override
    @GetMapping("/read/actifs")
    public ResponseEntity<List<User>> actifUser() {
        List<User> list = service.actifUsers();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/getUserByEmail/{mail}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String mail) {
        User list = service.getUserByEmail(mail);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @Override
    @PatchMapping("/bloquer/{id}")
    public ResponseEntity<String> bloquerUser(@PathVariable Long id) {
        String s = service.bloquer(id);
        return new ResponseEntity<>(s,HttpStatus.OK);
    }

    @Override
    @PatchMapping("/debloquer/{id}")
    public ResponseEntity<String> debloquerUser(@PathVariable Long id) {
        String s = service.debloquer(id);
        return new ResponseEntity<>(s,HttpStatus.OK);
    }

    @Override
    @GetMapping("/read")
    public ResponseEntity<List<User>> listUser() {
        List<User> list = service.readAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @Override
    @PostMapping("/login")
    public ResponseEntity<LogResponse> login(@RequestBody @Valid Log log) {
        LogResponse userDto = logService.login(log);
        userDto.setToken(userAuthenticationProvider.createToken(log));
        return ResponseEntity.ok(userDto);
    }

    @Override
    @GetMapping("/read/inactifs")
    public ResponseEntity<List<User>> lowUser() {
        List<User> list = service.inactifUsers();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid User user) {
        String createdUser = logService.register(user);
        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> removeUser(@PathVariable Long id) {
        String deletedUser = service.remove(id);
        return new ResponseEntity<>(deletedUser,HttpStatus.OK);
    }

    @Override
    @GetMapping("/search/{id}")
    public ResponseEntity<User> searchUser(@PathVariable Long id) {
        User user = service.search(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @Override
    @PatchMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @Valid User user) {
        String newUser = service.update(id, user);
        return new ResponseEntity<>(newUser,HttpStatus.OK);
    }

    @PatchMapping("/setPassword/{id}")
    public ResponseEntity<String> setPassword(@PathVariable Long id, @Valid User user) {
        String newUser = service.update(id, user);
        return new ResponseEntity<>(newUser,HttpStatus.OK);
    }
    @Override
    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody @Valid User user) {
        String createdUser = logService.register(user);
        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }
}
