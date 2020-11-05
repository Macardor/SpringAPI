package com.codecool.SpringAPI.controller;

import com.codecool.SpringAPI.service.EmailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@RestController
@Component
public class MyErrorController implements ErrorController {

    @Autowired
    private final EmailService emailService = new EmailService();

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                emailService.sendEmail("example@gmail.com", "Error404", "No such page");
                return "<h4>ERROR 404 - Sorry, we couldn't find that page</h4>";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                emailService.sendEmail("example@gmail.com", "Error500", "Internal server error.");
                return "<h4>ERROR 500 - Internal server error</h4>";
            }
        }
        return "<h4>Sorry, something went wrong. Please try again or contact us: movierestapi@gmail.com</h4>";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
