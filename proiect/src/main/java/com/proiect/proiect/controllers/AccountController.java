package com.proiect.proiect.controllers;

import com.proiect.proiect.models.RegisterModel;
import com.proiect.proiect.services.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/account")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping()
    public void Register(@RequestBody RegisterModel model){
        accountService.Register(model);
    }
}
