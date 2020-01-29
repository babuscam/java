package com.proiect.proiect.controllers;

import com.proiect.proiect.models.RegisterModel;
import com.proiect.proiect.services.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/account")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = "/Register", produces = "application/json", method= RequestMethod.POST)
    public void Register(@RequestBody RegisterModel model){
        accountService.Register(model);
    }
}
