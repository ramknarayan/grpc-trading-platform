package org.example.user.tests.service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.user.tests.service.handler.StockTradeRequestHandler;
import org.example.user.tests.service.handler.UserInformationRequestHandler;
import org.example.user.StockTradeRequest;
import org.example.user.UserInformation;
import org.example.user.UserInformationRequest;
import org.example.user.UserServiceGrpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class UserService extends UserServiceGrpc.UserServiceImplBase {
    private final UserInformationRequestHandler userRequestHandler;
    private final StockTradeRequestHandler tradeRequestHandler;
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    public UserService(UserInformationRequestHandler userRequestHandler, StockTradeRequestHandler tradeRequestHandler) {
        this.userRequestHandler = userRequestHandler;
        this.tradeRequestHandler = tradeRequestHandler;
    }

    @Override
    public void getUserInformation(UserInformationRequest request, StreamObserver<UserInformation> responseObserver) {
        log.info("User Information for id {}",request.getUserId());
        var userInformation = this.userRequestHandler.getUserInformation(request);
        responseObserver.onNext(userInformation);
        responseObserver.onCompleted();
    }

    @Override
    public void tradeStock(StockTradeRequest request, StreamObserver<org.example.user.StockTradeResponse> responseObserver) {
        log.info("{}",request.getUserId());
        var response = org.example.user.TradeAction.SELL.equals(request.getAction())?
                            this.tradeRequestHandler.sellStock(request):
                            this.tradeRequestHandler.buyStock(request);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
