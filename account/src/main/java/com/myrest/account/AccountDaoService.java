package com.myrest.account;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class AccountDaoService {
    private static List<Account> accounts = new ArrayList<>();

    private static int accountCount = 3;

    static {
        accounts.add(new Account(1, "Adam", "first"));
        accounts.add(new Account(2, "Eve", "second"));
        accounts.add(new Account(3, "Jack", "third"));
    }

    public List<Account> findAll(){
        return accounts;
    }

    public Account save(Account account){
        if(account.getId()==null){
            account.setId(++accountCount);
        }
        accounts.add(account);
        return account;
    }

    public Account findOne(int id) {
        for (Account user : accounts) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public Account deleteById(int id){
        Iterator<Account> iterator = accounts.iterator();
        while(iterator.hasNext()){
            Account user = iterator.next();
            if(user.getId()==id){
                iterator.remove();
                return  user;
            }
        }
        return null;
    }
}
