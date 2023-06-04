package br.com.fiap.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class TestController {
    private final static Logger logger = LoggerFactory.getLogger(TestController.class);


    @GetMapping
    public String getTest() {
        logger.info("Calling (GET) /home...");
        return "Funcionando...";
    }
}
