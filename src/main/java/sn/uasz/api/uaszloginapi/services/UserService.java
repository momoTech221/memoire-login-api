package sn.uasz.api.uaszloginapi.services;

import sn.uasz.spi.authApi.model.User;

import java.util.List;

public interface UserService {
    public List<User> readAll();
    public User search(Long id);
    public User getUserByEmail(String email);
    public String remove(Long id);
    public String update(Long id, User user);
    public String bloquer(Long id);
    public String debloquer(Long id);
    public  List<User> actifUsers();
    public  List<User> inactifUsers();

}
