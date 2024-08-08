package service.handler;

import org.example.exceptions.UnknowUserException;
import org.example.repository.PortfolioItemRepository;
import org.example.repository.UserRepository;
import org.example.util.EntityMessageMapper;
import org.springframework.stereotype.Service;

@Service
public class UserInformationRequestHandler {
    private final UserRepository userRepository;
    private final PortfolioItemRepository portfolioItemRepository;

    public UserInformationRequestHandler(UserRepository userRepository, PortfolioItemRepository portfolioItemRepository) {
        this.userRepository = userRepository;
        this.portfolioItemRepository = portfolioItemRepository;
    }

    public org.example.user.UserInformation getUserInformation(org.example.user.UserInformationRequest request){
        var user = this.userRepository.findById(request.getUserId())
                .orElseThrow(()-> new UnknowUserException(request.getUserId()));
        var porfoliItems = this.portfolioItemRepository.findAllByUserId(request.getUserId());
        return EntityMessageMapper.toUserInformation(user,porfoliItems);

    }
}
