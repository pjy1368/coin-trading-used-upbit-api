package com.wooteco.upbit.account.ui;

import com.wooteco.upbit.account.api.AccountsProvider;
import com.wooteco.upbit.account.dto.AllAccountResponse;
import com.wooteco.upbit.account.dto.AllAccountResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController {

    private final AccountsProvider accountsProvider;

    public AccountsController(AccountsProvider accountsProvider) {
        this.accountsProvider = accountsProvider;
    }

    @GetMapping("/accounts")
    public ResponseEntity<AllAccountResponses> showAllAccounts() {
        return ResponseEntity.ok(accountsProvider.getAllAccounts());
    }
}
