package ru.alexandrov.backend.services;

import org.springframework.stereotype.Component;

@Component
public interface EmailSenderService {

    void sendEmail(int purchaseId);
}
