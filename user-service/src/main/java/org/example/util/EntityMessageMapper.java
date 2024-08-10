package org.example.user.tests.util;

import org.example.user.tests.entitiy.PortfolioItem;
import org.example.user.tests.entitiy.User;

import java.util.List;

public class EntityMessageMapper {
    public static org.example.user.UserInformation toUserInformation(User user, List<PortfolioItem> items){
        var holidsings = items.stream()
                .map(i-> org.example.user.Holding.newBuilder().setTicker(i.getTicker()).setQuantity(i.getQuantity()).build())
                .toList();
        return org.example.user.UserInformation.newBuilder()
                .setUserId(user.getId())
                .setName(user.getName())
                .setBalance(user.getBalance())
                .addAllHoldings(holidsings)
                .build();
    }

    public static PortfolioItem toPortfolioItem(org.example.user.StockTradeRequest request){
        var item = new PortfolioItem();
        item.setUserId(request.getUserId());
        item.setTicker(request.getTicker());
        item.setQuantity(request.getQuantity());
        return item;
    }

    public static org.example.user.StockTradeResponse toStockTradeResponse(org.example.user.StockTradeRequest request,int balance){
        return org.example.user.StockTradeResponse.newBuilder()
                .setUserId(request.getUserId())
                .setPrice(request.getPrice())
                .setTicker(request.getTicker())
                .setQuantity(request.getQuantity())
                .setAction(request.getAction())
                .setTotalPrice(request.getPrice() * request.getQuantity())
                .setBalance(balance)
                .build();
    }
}
