package service.handler;


import org.example.common.Ticker;
import org.example.exceptions.InsufficientBalanceException;
import org.example.exceptions.UnknowUserException;
import org.example.exceptions.UnknownTickerException;
import org.example.repository.PortfolioItemRepository;
import org.example.repository.UserRepository;
import org.example.util.EntityMessageMapper;
import org.springframework.stereotype.Service;

@Service
public class StockTradeRequestHandler {
    private final UserRepository userRepository;
    private final PortfolioItemRepository portfolioItemRepository;

    public StockTradeRequestHandler(UserRepository userRepository, PortfolioItemRepository portfolioItemRepository) {
        this.userRepository = userRepository;
        this.portfolioItemRepository = portfolioItemRepository;
    }

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

        return  null;
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
