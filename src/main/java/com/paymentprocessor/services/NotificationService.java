package com.paymentprocessor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {

    public void sendNotification(String userName) {
        System.out.println("Notificação enviada para " + userName);
    }
}
