package com.jayon.upbit.account.ui;

import com.jayon.upbit.account.api.client.AccountClient;
import com.jayon.upbit.account.dto.AccountResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AccountController {

    private final AccountClient accountClient;

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountResponse>> showAllAccounts() {
        return ResponseEntity.ok(accountClient.getAccounts());
    }
}
