package com.example.beefsenseapi.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.beefsenseapi.dtos.RoleDTO;
import com.example.beefsenseapi.entities.Role;
import com.example.beefsenseapi.serviceinterfaces.IRoleService;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService myService;

    // Add an item to table
    @PostMapping
    public void registrar(@RequestBody RoleDTO dto) {
        ModelMapper m = new ModelMapper();
        Role myItem = m.map(dto, Role.class);
        myService.insert(myItem);
    }

    // Delete an item by ID on table
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id")Long id) {
        myService.delete(id);
    }

    // Retrieve an items by ID from table
    @GetMapping("/{id}")
    public RoleDTO listarId(@PathVariable("id")Long id) {
        ModelMapper m = new ModelMapper();
        RoleDTO myItem = m.map(myService.listId(id), RoleDTO.class);
        return myItem;
    }

    // Retrieve all items from table
    @GetMapping
    public List<RoleDTO> listar() {
        return myService.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, RoleDTO.class);
        }).collect(Collectors.toList());
    }

    // (Exclusive to controller) Modify values on table
    @PutMapping
    public void modificar(@RequestBody RoleDTO dto) {
        ModelMapper m = new ModelMapper();
        Role d = m.map(dto, Role.class);
        myService.insert(d);
    }

    @GetMapping("/user/{username}")
    public List<RoleDTO> getRolesForUser(@PathVariable("username") String username) {
        return myService.findRolesByUsername(username).stream().map(role -> {
            ModelMapper m = new ModelMapper();
            return m.map(role, RoleDTO.class);
        }).collect(Collectors.toList());
    }
}