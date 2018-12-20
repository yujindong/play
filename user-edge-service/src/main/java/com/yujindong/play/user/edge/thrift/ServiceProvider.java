package com.yujindong.play.user.edge.thrift;

import com.yujindong.play.message.api.MessageService;
import com.yujindong.play.user.api.UserService;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author yujindong
 */
@Component
public class ServiceProvider {

    @Value("${thrift.user.service.ip}")
    private String userServerIp;
    @Value("${thrift.user.service.port}")
    private int userServerPort;

    @Value("${thrift.message.service.ip}")
    private String messageServerIp;
    @Value(("${thrift.message.service.port}"))
    private int messageServerPort;

    private enum ServiceType {
        USER,
        MESSAGE
    }

    public UserService.Client getUserService() {
        return getService(userServerIp, userServerPort, ServiceType.USER);
    }

    public MessageService.Client getMessageService() {
        return getService(messageServerIp, messageServerPort, ServiceType.MESSAGE);
    }

    public <T> T getService(String ip, int port, ServiceType serviceType) {
        TSocket socket = new TSocket(ip, port, 3000);
        TTransport transport = new TFramedTransport(socket);
        try {
            transport.open();
        } catch (TTransportException e) {
            e.printStackTrace();
            return null;
        }
        TProtocol protocol = new TBinaryProtocol(transport);

        TServiceClient result = null;
        switch (serviceType) {
            case USER:
                result = new UserService.Client(protocol);
                break;
            case MESSAGE:
                result = new MessageService.Client(protocol);
                break;
        }
        return (T)result;
    }
}
