package com.wooteco.upbit.account.ui;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wooteco.upbit.account.api.application.AccountClient;
import com.wooteco.upbit.account.dto.AccountResponse;
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
    public ResponseEntity<List<AccountResponse>> showAllAccounts() throws JsonProcessingException {
        return ResponseEntity.ok(accountClient.getAccounts());
    }
}
