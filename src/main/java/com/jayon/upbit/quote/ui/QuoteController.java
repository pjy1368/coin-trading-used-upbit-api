package com.jayon.upbit.quote.ui;

import com.jayon.upbit.quote.api.client.MarketClient;
import com.jayon.upbit.quote.dto.MarketInfoResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class QuoteController {

    private final MarketClient marketClient;

    @GetMapping("/api/v1/quote/market-info")
    public ResponseEntity<List<MarketInfoResponse>> showAllMarketInfos() {
        return ResponseEntity.ok(marketClient.getMarketInfos());
    }

}
