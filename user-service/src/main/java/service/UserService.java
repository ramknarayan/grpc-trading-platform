package service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.user.StockTradeRequest;
import org.example.user.UserInformation;
import org.example.user.UserInformationRequest;
import org.example.user.UserServiceGrpc;
import service.handler.UserInformationRequestHandler;

@GrpcService
public class UserService extends UserServiceGrpc.UserServiceImplBase {
    private final UserInformationRequestHandler userRequestHandler;

    public UserService(UserInformationRequestHandler userRequestHandler) {
        this.userRequestHandler = userRequestHandler;
    }

    @Override
    public void getUserInformation(UserInformationRequest request, StreamObserver<UserInformation> responseObserver) {
        var userInformation = this.userRequestHandler.getUserInformation(request);
        responseObserver.onNext(userInformation);
        responseObserver.onCompleted();
    }

    @Override
    public void tradeStock(StockTradeRequest request, StreamObserver<org.example.user.StockTradeResponse> responseObserver) {

    }
}
