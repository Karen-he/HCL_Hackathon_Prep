package com.myrest.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
public class AccountResource {
    @Autowired
    private AccountDaoService service;

    @GetMapping("/accounts")
    public List<Account> retrieveAllUsers(){
        return service.findAll();
    }

    @GetMapping("/accounts/{id}")
    public Account retrieveAccount(@PathVariable int id){
        Account account = service.findOne(id);
        if(account==null){
            throw new AccountNotFound("id-"+ id);
        }
        return service.findOne(id);
    }

    @PostMapping("/accounts")
    public ResponseEntity<Object> createUser(@RequestBody Account account){
        Account savedAccount = service.save(account);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedAccount.getId()).toUri();
        return ResponseEntity.created(location).build();   //201 created, header: location in url
    }

    @DeleteMapping(path = "/accounts/{id}")
    public void deleteUser(@PathVariable int id){
        Account user = service.deleteById(id);
        if(user==null){
            throw new AccountNotFound("id-"+ id);
        }
    }
}
