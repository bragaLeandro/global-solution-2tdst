package br.com.fiap.dto;

import br.com.fiap.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeCreationDto {

    @JsonProperty("ingredientes")
    private String ingredients;
    @JsonProperty("dificuldade")
    private String difficulty;
    @JsonProperty("tempoMaximo")
    private String preparationTime;
    @JsonIgnore
    private User user;
}
