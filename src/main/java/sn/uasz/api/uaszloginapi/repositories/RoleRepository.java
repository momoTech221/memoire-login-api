package sn.uasz.api.uaszloginapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.uasz.api.uaszloginapi.entities.RoleDto;

@Repository
public interface RoleRepository extends JpaRepository<RoleDto,Long> {
    RoleDto findByName(String name);
}
