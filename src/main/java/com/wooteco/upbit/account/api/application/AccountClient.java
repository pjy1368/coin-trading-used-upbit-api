package com.wooteco.upbit.account.api.application;

import static com.wooteco.upbit.account.api.Constants.UPBIT_GET_ACCOUNTS_URL;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wooteco.upbit.account.dto.AccountResponse;
import com.wooteco.upbit.auth.infrastructure.JwtTokenProvider;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
    private final ObjectMapper objectMapper;

    public List<AccountResponse> getAccounts() throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(jwtTokenProvider.createToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        String response = restTemplate.exchange(UPBIT_GET_ACCOUNTS_URL, HttpMethod.GET, entity, String.class).getBody();
        return objectMapper.readValue(response, new TypeReference<>() {
        });
    }
}
