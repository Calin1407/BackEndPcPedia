package com.BackEndPcPedia.pcpedia.interfaces.rest;

import com.BackEndPcPedia.pcpedia.application.UsersService;
import com.BackEndPcPedia.pcpedia.application.dto.UsersRequest;
import com.BackEndPcPedia.pcpedia.application.dto.UsersResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller exposes endpoints to managing Users
 * This class interacts with UsersService to handle HTTP request
 * related to Users registration and data retrieval
 */
@RestController
@RequestMapping
public class UsersController {
    /**
     * Service used to handle business logic for Users
     */
    private final UsersService usersService;

    /**
     * Dependency injection of UsersService
     * @param _service reference to UsersService
     */
    public UsersController(UsersService _service){
        this.usersService=_service;
    }

    /**
     * register a new user in the system
     * @param _req the user data received from the client
     * @return the created User as a {@link UsersResponse}
     */
    @PostMapping
    public ResponseEntity<UsersResponse> register(@Valid @RequestBody UsersRequest _req){
        var created = usersService.register(_req);
        return ResponseEntity.ok(created);
    }

    /**
     * Retrieves a user by its unique ID
     * @param id the User's ID
     * @return the user data as a {@link UsersResponse}
     */
    @GetMapping("/{id}")
    public ResponseEntity<UsersResponse> getById(@PathVariable Long id){
        var user = usersService.getById(id);
        return ResponseEntity.ok(user);
    }

}
