package com.jayon.upbit.quote.api.client;

import com.jayon.upbit.common.UpbitApiPaths;
import com.jayon.upbit.quote.dto.MarketInfoResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class MarketClient {

    public static final String MARKET_PATH = UpbitApiPaths.BASE_SEVER_URL + "/market/all?isDetails=true";

    private final RestTemplate restTemplate;

    public List<MarketInfoResponse> getMarketInfos() {
        return restTemplate.exchange(MARKET_PATH, HttpMethod.GET, null,
            new ParameterizedTypeReference<List<MarketInfoResponse>>() {
            }).getBody();
    }
}
