package com.example.beefsenseapi.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.beefsenseapi.dtos.UsersDTO;
import com.example.beefsenseapi.entities.Users;
import com.example.beefsenseapi.security.WebSecurityConfig;
import com.example.beefsenseapi.serviceinterfaces.IUsersService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private IUsersService myService;

    // Add an item to table
    @PostMapping
    public void registrar(@RequestBody UsersDTO dto) {
        ModelMapper m = new ModelMapper();
        Users myItem = m.map(dto, Users.class);
        myService.insertAndAssignRole(myItem, "OPERARIO");
    }

    // Delete an item by ID on table
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        myService.delete(id);
    }

    // Retrieve an items by ID from table
    @GetMapping("/{id}")
    public UsersDTO listarId(@PathVariable("id") Long id) {
        ModelMapper m = new ModelMapper();
        UsersDTO myItem = m.map(myService.listId(id), UsersDTO.class);
        return myItem;
    }

    // Retrieve all items from table
    @GetMapping
    public List<UsersDTO> listar() {
        return myService.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, UsersDTO.class);
        }).collect(Collectors.toList());
    }

    // (Exclusive to controller) Modify values on table
    @PutMapping
    public void modificar(@RequestBody UsersDTO dto) {
        ModelMapper m = new ModelMapper();
        Users d = m.map(dto, Users.class);
        // Encriptar la contraseña del usuario antes de modificarla
        d.setPassword(WebSecurityConfig.passwordEncoder().encode(d.getPassword()));
        myService.insert(d);
    }

    @GetMapping("/buscar/{username}")
    public UsersDTO buscarPorUsername(@PathVariable("username") String username) {
        Users user = myService.findByUsername(username);
        if (user != null) {
            ModelMapper m = new ModelMapper();
            return m.map(user, UsersDTO.class);
        } else {
            // Manejar el caso en el que el usuario no existe
            return null;
        }
    }

    @PatchMapping("/{id}")
    public void patchUser(@PathVariable("id") Long id, @RequestBody UsersDTO dto) {
        myService.patchUser(id, dto);
    }
}
