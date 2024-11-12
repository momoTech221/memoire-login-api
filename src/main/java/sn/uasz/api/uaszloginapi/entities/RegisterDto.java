package sn.uasz.api.uaszloginapi.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RegisterDto {
    public String prenom;
    public String nom;
    public String login;
    public String password;
    public String sexe;
    public String adresse;
    public String profession;
    public String lieuNaissance;
    public Date dateNaissance;
    public String typePiece;
    public String numeroPiece;
    public String telephone;
    public  String roleName;
}
