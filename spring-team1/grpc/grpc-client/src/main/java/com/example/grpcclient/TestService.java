package com.example.grpcclient;

import com.example.pb.svc.test.*;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {

    @GrpcClient("test")
    private TestGrpc.TestBlockingStub testStub;

    public String sendGreeting(String message) {
        GreetingReq request = GreetingReq.newBuilder()
                .setSome(message)
                .build();

        GreetingRes response = testStub.greeting(request);
        return response.getResult().getMessage();
    }
}