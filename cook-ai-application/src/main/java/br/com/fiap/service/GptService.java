package br.com.fiap.service;

import br.com.fiap.constants.PromptConstants;
import br.com.fiap.dto.RecipeCreationDto;
import br.com.fiap.dto.RecipeDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@Service
public class GptService {

    private final OpenAiService service = new OpenAiService("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", Duration.ofSeconds(60));

    public RecipeDto sendMessageGpt(RecipeCreationDto recipeDto) {
        List<ChatMessage> messages = Arrays.asList(
                new ChatMessage(ChatMessageRole.SYSTEM.value(), PromptConstants.RECIPE_INITIALIZER),
                new ChatMessage(ChatMessageRole.USER.value(), PromptConstants.RECIPE_FORMAT),
                new ChatMessage(ChatMessageRole.USER.value(), PromptConstants.OUTPUT_RULES),
                new ChatMessage(ChatMessageRole.USER.value(), PromptConstants.OUTPUT_EXAMPLE),
                new ChatMessage(ChatMessageRole.USER.value(), PromptConstants.RECIPE_LEVELS),
                new ChatMessage(ChatMessageRole.USER.value(), PromptConstants.LINE_SEPARATOR),
                new ChatMessage(ChatMessageRole.USER.value(), PromptConstants.RECIPE_CREATOR + recipeDto.getIngredients()),
                new ChatMessage(ChatMessageRole.USER.value(), PromptConstants.RECIPE_DIFFICULTY + recipeDto.getDifficulty()),
                new ChatMessage(ChatMessageRole.USER.value(), PromptConstants.MAX_TIME + recipeDto.getPreparationTime())
                );

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                .builder()
                .model("gpt-3.5-turbo")
                .messages(messages)
                .n(1)
                .maxTokens(650)
                .build();

        String recipe = this.replaceLineSeparator(service.createChatCompletion(chatCompletionRequest).getChoices().get(0).getMessage().getContent());

        return this.jsonToRecipe(recipe);
    }

    public RecipeDto jsonToRecipe(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        RecipeDto recipe = null;
        try {
            recipe = objectMapper.readValue(json, RecipeDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recipe;
    }
    public String replaceLineSeparator(String text) {
        return text.replace(System.lineSeparator(), "");
    }

}
