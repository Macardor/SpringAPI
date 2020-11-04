package com.codecool.SpringAPI.service;

public interface EmailService {
    void sendEmail(String to, String subject, String text);
}
