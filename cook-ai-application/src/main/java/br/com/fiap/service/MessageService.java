package br.com.fiap.service;

import br.com.fiap.controller.AuthController;
import br.com.fiap.dto.RecipeDto;
import br.com.fiap.entity.Message;
import br.com.fiap.entity.User;
import br.com.fiap.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final static Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final GptService gptService;
    private final MessageRepository messageRepository;
    private final RecipeService recipeService;

    @Autowired
    public MessageService(MessageRepository messageRepository, GptService gptService, RecipeService recipeService) {
        this.messageRepository = messageRepository;
        this.gptService = gptService;
        this.recipeService = recipeService;
    }

}
