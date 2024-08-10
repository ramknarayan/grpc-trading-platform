package org.example.user.tests.service.handler;


import jakarta.transaction.Transactional;
import org.example.common.Ticker;
import org.example.user.tests.exceptions.InsufficientBalanceException;
import org.example.user.tests.exceptions.InsufficientSharesException;
import org.example.user.tests.exceptions.UnknowUserException;
import org.example.user.tests.exceptions.UnknownTickerException;
import org.example.user.tests.repository.PortfolioItemRepository;
import org.example.user.tests.repository.UserRepository;
import org.example.user.tests.util.EntityMessageMapper;
import org.springframework.stereotype.Service;

@Service
public class StockTradeRequestHandler {
    private final UserRepository userRepository;
    private final PortfolioItemRepository portfolioItemRepository;

    public StockTradeRequestHandler(UserRepository userRepository, PortfolioItemRepository portfolioItemRepository) {
        this.userRepository = userRepository;
        this.portfolioItemRepository = portfolioItemRepository;
    }
    @Transactional
    public org.example.user.StockTradeResponse buyStock(org.example.user.StockTradeRequest request)
    {
            //validate
                this.validateTicker(request.getTicker());
                var user = this.userRepository.findById(request.getUserId())
                        .orElseThrow(()->new UnknowUserException(request.getUserId()));
                var totalPrice = request.getQuantity() * request.getPrice();
                this.validateUserBalance(user.getId(), user.getBalance(), totalPrice);
            // update
                user.setBalance(user.getBalance()-totalPrice);
                this.portfolioItemRepository.findByUserIdAndTicker(user.getId(),request.getTicker())
                        .ifPresentOrElse(
                                item -> item.setQuantity(item.getQuantity() + request.getQuantity()),
                                ()-> this.portfolioItemRepository.save(EntityMessageMapper.toPortfolioItem(request))
                        );

        return EntityMessageMapper.toStockTradeResponse(request,user.getBalance());
    }
    @Transactional
    public org.example.user.StockTradeResponse sellStock(org.example.user.StockTradeRequest request)
    {
        //validate
        this.validateTicker(request.getTicker());
        var user = this.userRepository.findById(request.getUserId())
                .orElseThrow(()->new UnknowUserException(request.getUserId()));
        var portfolioItem = this.portfolioItemRepository.findByUserIdAndTicker(user.getId(),request.getTicker())
                .filter(pi ->pi.getQuantity() > request.getQuantity())
                .orElseThrow(()-> new InsufficientSharesException(user.getId()));
        var totalPrice = request.getQuantity() * request.getPrice();
        user.setBalance(user.getBalance() + totalPrice);
        portfolioItem.setQuantity(portfolioItem.getQuantity()- request.getQuantity());
        return EntityMessageMapper.toStockTradeResponse(request, user.getBalance());

    }
    private void validateTicker(org.example.common.Ticker ticker){
        if(Ticker.UNKNOWN.equals(ticker)){
            throw new UnknownTickerException();
        }

    }
    private void validateUserBalance(Integer userId,Integer userBalance, Integer totalPrice){
        if(totalPrice>userBalance){
            throw new InsufficientBalanceException(userId);
        }


    }
}
