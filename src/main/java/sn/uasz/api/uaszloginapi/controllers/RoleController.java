package sn.uasz.api.uaszloginapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.uasz.api.uaszloginapi.services.RoleService;
import sn.uasz.spi.authApi.RolesApi;
import sn.uasz.spi.authApi.model.Role;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
public class RoleController implements RolesApi {

    @Autowired
    RoleService service;

    @Override
    @GetMapping("/roles/read")
    public ResponseEntity<List<Role>> listRole() {
        List<Role> list = service.readAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/roles/delete/{id}")
    public ResponseEntity<String> removeRole(@PathVariable Long id) {
        String deletedRole = service.remove(id);
        return new ResponseEntity<>(deletedRole,HttpStatus.OK);
    }

    @Override
    @PostMapping("/roles/create")
    public ResponseEntity<String> saveRole(@Valid Role role) {
        String newRole = service.add(role);
        return new ResponseEntity<>(newRole,HttpStatus.OK);
    }

    @Override
    @GetMapping("/roles/search/{id}")
    public ResponseEntity<Role> searchRole(@PathVariable Long id) {
        Role role = service.search(id);
        return new ResponseEntity<>(role,HttpStatus.OK);
    }

    @Override
    @PatchMapping("/roles/update/{id}")
    public ResponseEntity<String> updateRole(@PathVariable Long id, @Valid Role role) {
        String newRole = service.update(id, role);
        return new ResponseEntity<>(newRole,HttpStatus.OK);
    }
}
