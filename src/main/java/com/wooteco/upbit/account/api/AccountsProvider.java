package com.wooteco.upbit.account.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wooteco.upbit.account.dto.AllAccountResponse;
import com.wooteco.upbit.account.dto.AllAccountResponses;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AccountsProvider {

    private static final String serverUrl = "https://api.upbit.com";
    @Value("${security.access-key}")
    private String accessKey;
    @Value("${security.secret-key}")
    private String secretKey;

    public AllAccountResponses getAllAccounts() {
        final String jwtToken = decideJwtToken();
        final String authenticationToken = "Bearer " + jwtToken;
        final CloseableHttpClient client = HttpClientBuilder.create().build();
        final HttpGet request = new HttpGet(serverUrl + "/v1/accounts");
        request.setHeader("Content-Type", "application/json");
        request.addHeader("Authorization", authenticationToken);
        return requestAllAccounts(client, request);
    }

    private String decideJwtToken() {
        final Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
            .withClaim("access_key", accessKey)
            .withClaim("nonce", UUID.randomUUID().toString())
            .sign(algorithm);
    }

    private AllAccountResponses requestAllAccounts(CloseableHttpClient client, HttpGet request) {
        try {
            final CloseableHttpResponse response = client.execute(request);
            final HttpEntity entity = response.getEntity();
            final String result = EntityUtils.toString(entity, "UTF-8");
            final ObjectMapper mapper = new ObjectMapper();
            final List<AllAccountResponse> allAccountResponses = mapper
                .readValue(result, mapper.getTypeFactory().constructCollectionType(List.class, AllAccountResponse.class));
            return new AllAccountResponses(allAccountResponses);
        } catch (IOException e) {
            e.printStackTrace();
            throw new InvalidIOException("라이브러리 예외가 발생하였습니다.");
        }
    }
}
