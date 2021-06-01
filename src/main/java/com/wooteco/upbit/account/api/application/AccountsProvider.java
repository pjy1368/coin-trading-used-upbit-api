package com.wooteco.upbit.account.api.application;

import static com.wooteco.upbit.account.api.Constants.SEVER_URL;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wooteco.upbit.account.api.InvalidIOException;
import com.wooteco.upbit.account.dto.AllAccountResponse;
import com.wooteco.upbit.account.dto.AllAccountResponses;
import com.wooteco.upbit.auth.infrastructure.JwtTokenProvider;
import java.io.IOException;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class AccountsProvider {

    private final JwtTokenProvider jwtTokenProvider;

    public AccountsProvider(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public AllAccountResponses getAllAccounts() {
        final String authenticationToken = "Bearer " + jwtTokenProvider.createToken();
        final CloseableHttpClient client = HttpClientBuilder.create().build();
        final HttpGet request = new HttpGet(SEVER_URL + "/v1/accounts");
        request.setHeader("Content-Type", "application/json");
        request.addHeader("Authorization", authenticationToken);
        return requestAllAccounts(client, request);
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
