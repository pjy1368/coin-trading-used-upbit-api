package com.wooteco.upbit.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AllAccountResponse {

    private String currency;
    private String balance;
    private String locked;
    private String avgBuyPrice;
    private boolean avgBuyPriceModified;
    private String unitCurrency;
}
