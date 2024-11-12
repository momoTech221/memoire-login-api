package sn.uasz.api.uaszloginapi.services.impl;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.uasz.api.uaszloginapi.entities.RoleDto;
import sn.uasz.api.uaszloginapi.mappers.RoleMapper;
import sn.uasz.api.uaszloginapi.repositories.RoleRepository;
import sn.uasz.api.uaszloginapi.services.RoleService;
import sn.uasz.spi.authApi.model.Role;

import java.util.List;

@Service
@Data
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository repository;
    @Autowired
    RoleMapper mapper;
    @Override
    public List<Role> readAll() {
        List<RoleDto> list = repository.findAll();
        return mapper.mapListRoleDtoToRole(list);
    }

    @Override
    public String add(Role role) {
        RoleDto role1 = mapper.mapRoleToRoleDto(role);
        repository.save(role1);
        return "Insertion reussie !!!";
    }

    @Override
    public Role search(Long id) {
        RoleDto role = repository.findById(id).get();
        return mapper.mapRoleDtoToRole(role);
    }

    @Override
    public String remove(Long id) {
        repository.deleteById(id);
        return "supression reussie !!!";
    }

    @Override
    public String update(Long id, Role role) {
        RoleDto existe = repository.findById(id).get();
        RoleDto role1 = mapper.mapRoleToRoleDto(role) ;
        String resultat = null;

        if (existe != null){
            if (role.getName() != null) existe.setName(role1.getName());

            repository.save(existe);
            resultat = "Modification éffectuée avec succé !!!";
        }

        return resultat;
    }
}
