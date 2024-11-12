package sn.uasz.api.uaszloginapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sn.uasz.api.uaszloginapi.entities.UserDto;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserDto, Long> {
    @Query(value = "SELECT * FROM users u WHERE u.login = ?1",nativeQuery = true)
    UserDto findByLogin(String login);
}
