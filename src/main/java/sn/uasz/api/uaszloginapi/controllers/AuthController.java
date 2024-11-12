//package sn.uasz.api.uaszloginapi.controllers;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import sn.uasz.api.uaszloginapi.config.UserAuthenticationProvider;
//import sn.uasz.api.uaszloginapi.dtos.CredentialsDto;
//import sn.uasz.api.uaszloginapi.dtos.SignUpDto;
//import sn.uasz.api.uaszloginapi.entities.UserDto;
//import sn.uasz.api.uaszloginapi.mappers.UserSpiMapper;
//import sn.uasz.api.uaszloginapi.services.UserLogService;
//import sn.uasz.spi.authApi.SignUpsApi;
//import sn.uasz.spi.authApi.model.Log;
//import sn.uasz.spi.authApi.model.SignUp;
//import sn.uasz.spi.authApi.model.User;
//
//@Slf4j
//@RequiredArgsConstructor
//@RestController
//@CrossOrigin(origins = "http://localhost:4200")
//public class AuthController implements SignUpsApi {
//    private final UserAuthenticationProvider userAuthenticationProvider;
//    @Autowired
//    private final UserLogService logService;
//
//    @Autowired
//    private  UserSpiMapper mapper ;
//
//    @PostMapping("/users/login")
//    public ResponseEntity<User> login(@RequestBody @Valid Log log) {
//        User userDto = logService.login(log);
//
////        UserDto userDto1 = new UserDto();
////        userDto1.setNom(userDto.getNom());
////        userDto1.setPrenom(userDto.getPrenom());
////        userDto1.setToken(userDto.getToken());
//
//        userDto.setToken(userAuthenticationProvider.createToken(log));
//        return ResponseEntity.ok(userDto);
//    }
//    @PostMapping("/users/register")
//    public ResponseEntity<String> register(@RequestBody @Valid User user) {
//        String createdUser = logService.register(user);
////        System.out.println("-------------------"+user.roleName());
////        log.info("-----log log info--------------"+user.roleName());
//        return new ResponseEntity<>(createdUser, HttpStatus.OK);
//    }
//
//
//
//
////    @PostMapping("/test")
////    public ResponseEntity<SignUp> test(@RequestBody SignUp signUp){
////        SignUp signUp1 = signUp;
////        return  new ResponseEntity<>(signUp1,HttpStatus.OK);
////    }
//}