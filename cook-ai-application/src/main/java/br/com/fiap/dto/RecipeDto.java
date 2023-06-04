package br.com.fiap.dto;

import br.com.fiap.entity.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDto {

    private String titulo;
    private List<IngredientDto> ingredientes;
    private String modoPreparo;
    private String tempoPreparo;
    private String dificuldade;
}
