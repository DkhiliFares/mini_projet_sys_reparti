package imp;

import io.grpc.stub.StreamObserver;
import messaging.*;
import messaging.MessageOuterClass.Message;

import java.util.HashMap;
import java.util.Map;

public class MessagingServiceImpl extends MessagingServiceGrpc.MessagingServiceImplBase {
    private Map<String, StringBuilder> userMessages = new HashMap<>();

    @Override
    public void sendMessage(Message request, StreamObserver<Message> responseObserver) {
        String receiver = request.getReceiver();
        StringBuilder messages = userMessages.computeIfAbsent(receiver, k -> new StringBuilder());
        messages.append(request.getSender()).append(": ").append(request.getText()).append("\n");
        responseObserver.onNext(Message.newBuilder().setSender("Message sent").build());
        responseObserver.onCompleted();
    }

    @Override
    public void getMessagesForUser(Message request, StreamObserver<Message> responseObserver) {
        String receiver = request.getReceiver();
        StringBuilder messages = userMessages.getOrDefault(receiver, new StringBuilder());
        String[] messageLines = messages.toString().split("\n");
        for (String messageLine : messageLines) {
            if (!messageLine.isEmpty()) {
                String[] parts = messageLine.split(": ", 2);
                responseObserver.onNext(Message.newBuilder()
                        .setSender(parts[0])
                        .setText(parts[1])
                        .build());
            }
        }
        responseObserver.onCompleted();
    }
}

