package com.jayon.upbit.quote.api.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayon.upbit.quote.dto.MarketInfoResponse;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

@AutoConfigureWebClient(registerRestTemplate = true)
@RestClientTest(MarketClient.class)
class MarketClientTest {

    private final List<MarketInfoResponse> marketInfoResponses = Arrays.asList(
        MarketInfoResponse.builder()
            .market("KRW_BTC")
            .koreanName("비트코인")
            .englishName("Bitcoin")
            .marketWarning("NONE")
            .build(),
        MarketInfoResponse.builder()
            .market("KRW_ETH")
            .koreanName("이더리움")
            .englishName("Ethereum")
            .marketWarning("CAUTION")
            .build()
    );

    @Autowired
    private MarketClient marketClient;

    @Autowired
    private MockRestServiceServer mockServer;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("업비트의 모든 마켓 정보를 가져온다.")
    @Test
    void getMarketInfos() throws JsonProcessingException {
        String expectedResult = objectMapper.writeValueAsString(marketInfoResponses);
        mockServer.expect(requestTo(MarketClient.MARKET_PATH))
            .andRespond(withSuccess(expectedResult, MediaType.APPLICATION_JSON));

        List<MarketInfoResponse> actual = marketClient.getMarketInfos();
        assertThat(actual).hasSize(marketInfoResponses.size());
        assertThat(actual.get(0).getMarket()).isEqualTo(marketInfoResponses.get(0).getMarket());
    }
}