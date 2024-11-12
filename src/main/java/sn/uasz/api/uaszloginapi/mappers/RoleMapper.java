package sn.uasz.api.uaszloginapi.mappers;

import org.mapstruct.Mapper;
import sn.uasz.api.uaszloginapi.entities.RoleDto;
import sn.uasz.spi.authApi.model.Role;

import java.util.List;

@Mapper(componentModel = "spring", implementationPackage = "<PACKAGE_NAME>.generated")
public interface RoleMapper {
    Role mapRoleDtoToRole(RoleDto role);
    RoleDto mapRoleToRoleDto(Role role);
    List<Role> mapListRoleDtoToRole(List<RoleDto> list);
}
