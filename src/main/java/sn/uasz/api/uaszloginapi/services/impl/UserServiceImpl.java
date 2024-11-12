package sn.uasz.api.uaszloginapi.services.impl;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sn.uasz.api.uaszloginapi.entities.UserDto;
import sn.uasz.api.uaszloginapi.execptions.AppException;
import sn.uasz.api.uaszloginapi.mappers.UserSpiMapper;
import sn.uasz.api.uaszloginapi.repositories.UserRepository;
import sn.uasz.api.uaszloginapi.services.UserService;
import sn.uasz.spi.authApi.model.Role;
import sn.uasz.spi.authApi.model.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Data
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository repository;
    @Autowired
    UserSpiMapper mapper;
    private final PasswordEncoder passwordEncoder;
    @Override
    public List<User> readAll() {
        List<UserDto> userDtos = repository.findAll();
        return mapper.mapListUserDtoToListUser(userDtos);
    }

    @Override
    public User search(Long id) {
        UserDto userDto = repository.findById(id).get();
        if (userDto == null){
            throw new AppException("Cette utilisateur n'existe pas", HttpStatus.NOT_FOUND);
        }
        return mapper.mapUserDtoToUser(userDto);
    }

    @Override
    public User getUserByEmail(String email) {
        UserDto userDto = repository.findByLogin(email);
        return mapper.mapUserDtoToUser(userDto);
    }

    @Override
    public String remove(Long id) {
        UserDto userDto = repository.findById(id).get();
        if (userDto == null){
            throw new AppException("Cette utilisateur n'existe pas", HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);

        return"Supression reussie !!!";
    }

    @Override
    public String update(Long id, User user) {
        UserDto existe = repository.findById(id).get();
        //UserDto newUserDto = mapper.mapUserToUserDto(user);
//        String resultat = null;

        if(existe == null){
            throw new AppException("Cette utilisateur n'existe pas", HttpStatus.NOT_FOUND);
        }


        if (user.getNom() != null)
            existe.setNom(user.getNom());
        if (user.getPrenom() != null)
            existe.setPrenom(user.getPrenom());
        if (user.getSexe() != null)
            existe.setSexe(user.getSexe());
        if (user.getAdresse() != null)
            existe.setAdresse(user.getAdresse());
//           if (existe.getLogin() != null) existe.setLogin(newUser.getLogin());
        if (user.getPassword() != null) existe.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getLieuNaissance() != null)
                existe.setLieuNaissance(user.getLieuNaissance());
        if (user.getDateNaissance() != null) existe.setDateNaissance(user.getDateNaissance());
        if (user.getTypePiece() != null) existe.setTypePiece(user.getTypePiece());
        if (user.getNumeroPiece() != null) existe.setNumeroPiece(user.getNumeroPiece());
        if (user.getProfession() != null) existe.setProfession(user.getProfession());
//          if (user.getFonction() != null) existe.setFonction(user.getFonction());
        if (user.getTelephone() != null) existe.setTelephone(user.getTelephone());
        existe.setDateModification(LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy-HH-mm-ss")));


            repository.save(existe);
//            resultat = "Modification éffectuée !!!";


        return "Modification éffectuée !!!";
    }

    @Override
    public String bloquer(Long id) {
        UserDto existe = repository.findById(id).get();
        if(existe == null){
            throw new AppException("Cette utilisateur n'existe pas", HttpStatus.NOT_FOUND);
        }
        existe.setEtatCompte(false);
        repository.save(existe);
        return "Cette utilisateur est temporairement supprimé !!!";
    }

    @Override
    public String debloquer(Long id) {
        UserDto existe = repository.findById(id).get();
        if(existe == null){
            throw new AppException("Cette utilisateur n'existe pas", HttpStatus.NOT_FOUND);
        }
        existe.setEtatCompte(true);
        repository.save(existe);
        return "Félicitation cette utilisateur est de nouveau actif !!!";
    }

    @Override
    public List<User> actifUsers() {
        List<UserDto> userDtos = repository.findAll();
        List<UserDto> list = new ArrayList<>();
        for(UserDto dto : userDtos){
            if(dto.etatCompte== true){
                list.add(dto);
            }
        }
        return mapper.mapListUserDtoToListUser(list);
    }

//    @Override
//    public List<User> actifUsers() {
//        List<UserDto> userDtos = repository.findAll();
//        List<User> list = new ArrayList<>();
//
//        for(UserDto dto : userDtos){
//            User user = mapper.mapUserDtoToUser(dto);
////            System.out.println(user);
//            if(user.getEtatCompte().equals(true) ){
//                roles=user.getRoles();
//                System.out.println(roles);
//                for (Role role: roles){
//
//                    if(!Objects.equals(role.getName(), "Admin")){
//                        list.add(user);
//                    }
//                }
//            }
//        }
//        return list;
//    }

    @Override
    public List<User> inactifUsers() {
        List<UserDto> userDtos = repository.findAll();
        List<UserDto> list = new ArrayList<>();
        for(UserDto dto : userDtos){
            if(dto.etatCompte== false){
                list.add(dto);
            }
        }
        return mapper.mapListUserDtoToListUser(list);
    }
}
