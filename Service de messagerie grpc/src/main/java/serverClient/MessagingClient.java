package serverClient;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import messaging.*;
import messaging.MessageOuterClass.Message;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MessagingClient {
    private final ManagedChannel channel;
    private final MessagingServiceGrpc.MessagingServiceStub stub;

    public MessagingClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        stub = MessagingServiceGrpc.newStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void sendMessage(String sender, String receiver, String text) {
        Message message = Message.newBuilder()
                .setSender(sender)
                .setReceiver(receiver)
                .setText(text)
                .build();

        stub.sendMessage(message, new StreamObserver<Message>() {
            @Override
            public void onNext(Message value) {
                System.out.println("Message sent: " + value.getSender());
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error sending message: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                // Not needed for this example
            }
        });
    }

    public void getMessagesForUser(String user) {
        Message request = Message.newBuilder()
                .setReceiver(user)
                .build();

        stub.getMessagesForUser(request, new StreamObserver<Message>() {
            @Override
            public void onNext(Message value) {
                System.out.println(value.getSender() + ": " + value.getText());
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error getting messages: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                // Not needed for this example
            }
        });
    }

    public static void main(String[] args) throws InterruptedException {
        MessagingClient client = new MessagingClient("localhost", 50051);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter sender: ");
        String sender = scanner.nextLine();
        System.out.print("Enter receiver: ");
        String receiver = scanner.nextLine();
        System.out.print("Enter message: ");
        String text = scanner.nextLine();

        client.sendMessage(sender, receiver, text);

        // Wait a bit before getting messages (just for demonstration purposes)
        Thread.sleep(1000);

        client.getMessagesForUser(receiver);
        client.shutdown();
        
    }
}
