package com.wooteco.upbit.account.api.application;

import static com.wooteco.upbit.account.api.Constants.UPBIT_GET_ACCOUNTS_URL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wooteco.upbit.account.dto.AccountResponse;
import com.wooteco.upbit.auth.infrastructure.JwtTokenProvider;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

@AutoConfigureWebClient(registerRestTemplate = true)
@RestClientTest(AccountClient.class)
class AccountClientTest {

    private final List<AccountResponse> accountResponses = Arrays.asList(
        AccountResponse
            .builder()
            .currency("KRW")
            .balance("1000000.0")
            .locked("0.0")
            .avgBuyPrice("0")
            .avgBuyPriceModified(false)
            .unitCurrency("KRW")
            .build(),
        AccountResponse
            .builder()
            .currency("BTC")
            .balance("2.0")
            .locked("0.0")
            .avgBuyPrice("101000")
            .avgBuyPriceModified(false)
            .unitCurrency("KRW")
            .build()
    );

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AccountClient accountClient;

    @Autowired
    private MockRestServiceServer mockServer;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("업비트의 모든 계좌 정보를 가져온다.")
    @Test
    void getAccounts() throws JsonProcessingException {
        String expectedResult = objectMapper.writeValueAsString(accountResponses);
        mockServer.expect(requestTo(UPBIT_GET_ACCOUNTS_URL))
            .andRespond(withSuccess(expectedResult, MediaType.APPLICATION_JSON));

        List<AccountResponse> actual = accountClient.getAccounts();
        assertThat(actual).hasSize(accountResponses.size());
    }
}