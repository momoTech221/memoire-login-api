package sn.uasz.api.uaszloginapi.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogResponseDto {
    public Long idUser;
    public String nom;
    public String prenom;
    public String login;
    public Boolean etatCompte;
    public List<RoleDto> roles;
    public String token;
}
