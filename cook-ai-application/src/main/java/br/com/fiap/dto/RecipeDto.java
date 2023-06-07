package br.com.fiap.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDto {

    @JsonProperty("titulo")
    private String title;

    @JsonProperty("ingredientes")
    private List<IngredientDto> ingredients;

    @JsonIgnore
    private List<String> ingredientNames;

    @JsonProperty("modoPreparo")
    private String preparationMethod;

    @JsonProperty("tempoPreparo")
    private String preparationTime;

    @JsonProperty("dificuldade")
    private String difficulty;
}
