package com.example.grpc_server;

import com.example.pb.svc.test.*;
import com.example.pb.unit.common.ReturnMsg;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class TestService extends TestGrpc.TestImplBase{

    @Override
    public void greeting(GreetingReq request, StreamObserver<GreetingRes> responseObserver) {
        String received = request.getSome();
        System.out.println("Received: " + received);

        // 응답 메시지 생성
        ReturnMsg returnMsg = ReturnMsg.newBuilder()
                .setMessage("Hello, " + received)
                .setCode(200)
                .build();

        GreetingRes response = GreetingRes.newBuilder()
                .setResult(returnMsg)
                .build();

        // 클라이언트로 응답 전송
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
