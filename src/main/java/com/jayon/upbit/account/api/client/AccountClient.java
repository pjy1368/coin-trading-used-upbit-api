package com.jayon.upbit.account.api.client;

import static com.jayon.upbit.account.api.Constants.UPBIT_GET_ACCOUNTS_URL;

import com.jayon.upbit.account.dto.AccountResponse;
import com.jayon.upbit.auth.infrastructure.JwtTokenProvider;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class AccountClient {

    private final JwtTokenProvider jwtTokenProvider;
    private final RestTemplate restTemplate;

    public List<AccountResponse> getAccounts() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(jwtTokenProvider.createToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(UPBIT_GET_ACCOUNTS_URL, HttpMethod.GET, entity,
            new ParameterizedTypeReference<List<AccountResponse>>() {
            }).getBody();
    }
}
