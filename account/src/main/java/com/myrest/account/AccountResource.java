package com.myrest.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import java.net.URI;
import java.util.Optional;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
public class AccountResource {
//    @Autowired
//    private AccountDaoService service;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/mysql/accounts")
    public Iterable<Account> retrieveAllUsers(){
        return accountRepository.findAll();
    }

    @GetMapping("/mysql/accounts/{id}")
    public Resource<Account> retrieveAccount(@PathVariable int id){
        Optional<Account> account = accountRepository.findById((long) id);
        if(!account.isPresent()){
            throw new AccountNotFound("id-"+ id);
        }

        Resource<Account> resource = new Resource<Account>(account.get());
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-accounts"));
        return resource;
    }

    @PostMapping("/mysql/accounts")
    public ResponseEntity<Object> createUser(@RequestBody Account account){
        Account savedAccount = accountRepository.save(account);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedAccount.getId()).toUri();
        return ResponseEntity.created(location).build();   //201 created, header: location in url
    }

    @DeleteMapping(path = "/mysql/accounts/{id}")
    public void deleteUser(@PathVariable int id){
        accountRepository.deleteById((long) id);
//        Account user = service.deleteById(id);
//        if(user==null){
//            throw new AccountNotFound("id-"+ id);
//        }
    }
}
