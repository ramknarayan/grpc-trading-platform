package org.example.user.tests;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.example.user.UserInformationRequest;
import org.example.user.UserServiceGrpc;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
        "grpc.server.port=-1",
        "grpc.server.in-process-name=integration-test",
        "grpc.client.user-service.address=in-process:integration-test"
})

public class UserServiceTest {

    @GrpcClient("User-service")
    private UserServiceGrpc.UserServiceBlockingStub  stub;

    @Test
    public void UserInformationTest(){
        var request = UserInformationRequest.newBuilder()
                .setUserId(1)
                .build();
        var response = this.stub.getUserInformation(request);

    }
}
