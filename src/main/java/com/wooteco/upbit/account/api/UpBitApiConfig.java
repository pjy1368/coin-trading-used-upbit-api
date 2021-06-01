package com.wooteco.upbit.account.api;

import com.wooteco.upbit.account.api.application.AccountsProvider;
import com.wooteco.upbit.auth.infrastructure.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpBitApiConfig {

    private final JwtTokenProvider jwtTokenProvider;

    public UpBitApiConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public AccountsProvider accountsProvider() {
        return new AccountsProvider(jwtTokenProvider);
    }
}
