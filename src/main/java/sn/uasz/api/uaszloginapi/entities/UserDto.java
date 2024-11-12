package sn.uasz.api.uaszloginapi.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "users")
public class UserDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long idUser;
    @Size(max = 50)
    public String prenom;
    @Size(max = 20)
    public String nom;
   // @Column(nullable = true)
    @Size(max = 50)
    public String login;
   // @Column(nullable = true)
    @Size(max = 100)
    public String password;
    public String sexe;
    public String adresse;
    public String profession;
    @Column(name = "lieu_naissance", nullable = true)
    public String lieuNaissance;
    @Column(name = "date_naissance")
    public String dateNaissance;
    @Column(name = "date_creation")
    public String DateCreation;
    public  Boolean etatCompte;
    @Column(name = "date_modification")
    public String DateModification;
    @Column(name = "type_piece")
    public String typePiece;
    @Column(name = "numero_piece")
    public String numeroPiece;
//    public String fonction;
   // @Column(nullable = true)
    @Size(max = 15)
    public String telephone;
    //fetch = FetchType.LAZY permet de gerer automatiquement la suppression en cascade
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "id_user")},
            inverseJoinColumns = {@JoinColumn(name = "id_role")}
    )
    public List<RoleDto> roles = new ArrayList<>();
}
