package com.codecool.SpringAPI.controller;

import com.codecool.SpringAPI.service.EmailService;
import com.codecool.SpringAPI.service.EmailServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@RestController
public class MyErrorController implements ErrorController {
    private final Logger log = LogManager.getLogger(MyErrorController.class);

    @Autowired
    private final EmailService emailService = new EmailServiceImpl();

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                log.warn("ERROR 404: We couldn't find that page");
                emailService.sendEmail("example@gmail.com", "Error404", "No page");
                return "ERROR 404 - Sorry, we couldn't find that page";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                log.warn("ERROR 500: Internal server error");
                emailService.sendEmail("example@gmail.com", "Error500", "Internal server error.");
                return "ERROR 500 - Internal server error";
            }
        }
        return "<h4>Sorry, something went wrong. Please try again or contact us: movierestapi@gmail.com</h4>";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
