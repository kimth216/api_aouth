package com.springboot.api.account.web;

import com.springboot.api.account.domain.Account;
import com.springboot.api.account.domain.AccountDto;
import com.springboot.api.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by kth on 2020-07-29
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AccountController {

  private final AccountService accountService;

  @PostMapping("/account")
  @ResponseStatus(HttpStatus.CREATED)
  public void createAccount(@RequestBody @Valid final AccountDto.CreateAccount createAccount,
                            final BindingResult bindingResult) throws BindException {
    if (bindingResult.hasErrors()) {
      throw new BindException(bindingResult);
    }
    accountService.createAccount(createAccount);
  }

}
