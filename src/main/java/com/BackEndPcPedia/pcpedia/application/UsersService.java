package com.BackEndPcPedia.pcpedia.application;

import com.BackEndPcPedia.pcpedia.application.dto.UsersRequest;
import com.BackEndPcPedia.pcpedia.application.dto.UsersResponse;
import com.BackEndPcPedia.pcpedia.domain.model.aggregates.Users;
import com.BackEndPcPedia.pcpedia.infrastructure.persistence.jpa.UsersRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    /**
     * Reference to read, save and delete own User's Database
     */
    private final UsersRepository repo;

    /**
     * Injection of dependency
     * @param _repo is UsersRepository data
     */
    public UsersService(UsersRepository _repo){
        this.repo = _repo;
    }

    /**
     * Convert request to aggregate Users and save that
     * @param req User's request
     * @return response of request saved
     */
    @Transactional
    public UsersResponse register(UsersRequest req){

        if(repo.findByEmailValue(req.email()).isPresent()){
            throw new IllegalStateException("Email existent");
        }

        var role = req.role()==null? Users.Role.CLIENT: Users.Role.valueOf(req.role());
        var user = Users.register(
                req.name(),
                req.email(),
                Users.PasswordHash.of(req.password()),
                req.phoneNumber(),
                req.address(),
                role
        );
        var saved = repo.save(user);
        return toResponse(saved);
    }

    /**
     * Loaded User's data associated with an id
     * @param id User's id
     * @return response associated with User's id
     */
    @Transactional(readOnly = true)
    public UsersResponse getById(Long id){
        var u = repo.findById(id).orElseThrow(()->
                new IllegalArgumentException("User not found"));
        return toResponse(u);
    }

    /**
     * Response of 'u' User's data (OUT)
     * @param u User's aggregate
     * @return User's response associated with 'u'
     */
    private UsersResponse toResponse(Users u){
        return new UsersResponse(
                u.getId(),
                u.getName(),
                u.getEmail(),
                u.getPhoneNumber(),
                u.getAddress(),
                u.getRole().name()
        );
    }

    /**
     * Give a role to User's
     * @param _role role to associate with the User
     * @return User's role value
     */
    private Users.Role toRole(String _role){
        if(_role==null||_role.isBlank()) return Users.Role.CLIENT;
        return Users.Role.valueOf(_role.trim().toUpperCase());
    }

    /**
     * Give all users in database
     * @return list about all User registered
     */
    @Transactional(readOnly = true)
    public List<UsersResponse> getAll(){
        return repo.findAll().stream()
                .map(this::toResponse).toList();
    }
}
