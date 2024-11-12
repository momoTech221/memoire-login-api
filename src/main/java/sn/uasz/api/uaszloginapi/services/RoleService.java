package sn.uasz.api.uaszloginapi.services;

import sn.uasz.spi.authApi.RolesApi;
import sn.uasz.spi.authApi.model.Role;

import java.util.List;

public interface RoleService {
    public List<Role> readAll();
    public String add(Role role);
    public Role search(Long id);
    public String remove(Long id);
    public String update(Long id, Role role);
}
