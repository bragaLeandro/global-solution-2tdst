package br.com.fiap.dto;

import br.com.fiap.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeCreationDto {

    @JsonProperty("ingredientes")
    @NotEmpty(message = "ingredients cannot be empty")
    @NotNull(message = "ingredients cannot be null")
    private String ingredients;

    @NotEmpty(message = "difficulty cannot be empty")
    @NotNull(message = "difficulty cannot be null")
    @JsonProperty("dificuldade")
    private String difficulty;

    @NotNull(message = "preparationTime cannot be empty")
    @NotNull(message = "preparationTime cannot be null")
    @JsonProperty("tempoMaximo")
    private String preparationTime;

    @JsonIgnore
    private User user;
}
