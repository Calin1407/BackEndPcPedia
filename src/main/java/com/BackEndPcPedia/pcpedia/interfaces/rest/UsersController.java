package com.BackEndPcPedia.pcpedia.interfaces.rest;

import com.BackEndPcPedia.pcpedia.application.dto.UsersResponse;
import com.BackEndPcPedia.pcpedia.domain.model.commands.RegisterUserCommand;
import com.BackEndPcPedia.pcpedia.domain.model.commands.UpdatePasswordCommand;
import com.BackEndPcPedia.pcpedia.domain.model.commands.handler.RegisterUserHandler;
import com.BackEndPcPedia.pcpedia.domain.model.commands.handler.UpdatePasswordHandler;
import com.BackEndPcPedia.pcpedia.domain.model.queries.GetListUserQuery;
import com.BackEndPcPedia.pcpedia.domain.model.queries.GetUserByIdQuery;
import com.BackEndPcPedia.pcpedia.domain.model.queries.Handler.GetListUserHandler;
import com.BackEndPcPedia.pcpedia.domain.model.queries.Handler.GetUserByIdHandler;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller exposes endpoints to managing Users
 * This class interacts with UsersService to handle HTTP request
 * related to Users registration and data retrieval
 */
@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final RegisterUserHandler registerUserHandler;
    private final GetUserByIdHandler getUserByIdHandler;
    private final GetListUserHandler getListUserHandler;
    private final UpdatePasswordHandler updatePasswordHandler;

    public UsersController(
            RegisterUserHandler _registerUserHandler,
            GetUserByIdHandler _getUserByIdHandler,
            GetListUserHandler _getListUserHandler,
            UpdatePasswordHandler _updatePasswordHandler) {
        this.registerUserHandler = _registerUserHandler;
        this.getUserByIdHandler = _getUserByIdHandler;
        this.getListUserHandler = _getListUserHandler;
        this.updatePasswordHandler = _updatePasswordHandler;
    }

    /**
     * Using protocol HTTP(POST), Register user
     * @param cmd command to register user, this will request information associated with user
     * @return user register in database
     */
    @PostMapping
    public ResponseEntity<UsersResponse> register(@Valid @RequestBody RegisterUserCommand cmd){
        return ResponseEntity.ok(registerUserHandler.handle(cmd));
    }

    /**
     * Using protocol HTTP(GET), Search for a user using an ID
     * @param id User's id
     * @return user associated with the ID
     */
    @GetMapping("{id}")
    public ResponseEntity<UsersResponse> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(getUserByIdHandler.handle(new GetUserByIdQuery(id)));
    }

    /**
     * Using protocol HTTP(PUT) update user's password
     * @param id User's ID
     * @param cmd Input with ID, old password and new password
     * @return password update associated with the user
     */
    @PutMapping("/{id}/password")
    public ResponseEntity<UsersResponse> updatePassword(
            @PathVariable Long id,
            @Valid @RequestBody UpdatePasswordCommand cmd){
        var update=updatePasswordHandler.handle(
                new UpdatePasswordCommand(id, cmd.oldPassword(), cmd.newPassword()
                ));
        return ResponseEntity.ok(update);
    }

    /**
     * To solve Error 405, we implement this
     * Read a mapping, in this case only 10 elements max
     * And check this exist
     * @param page inferior limit
     * @param size superior limit
     * @return 10 users registered
     */
    @GetMapping
    public ResponseEntity<Iterable<UsersResponse>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return ResponseEntity.ok(getListUserHandler.handle(new GetListUserQuery(page, size)));
    }

}
