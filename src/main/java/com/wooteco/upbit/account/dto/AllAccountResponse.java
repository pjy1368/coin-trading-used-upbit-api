package com.wooteco.upbit.account.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AllAccountResponse {

    private String currency;
    private String balance;
    private String locked;
    private String avgBuyPrice;
    private boolean avgBuyPriceModified;
    private String unitCurrency;
}
