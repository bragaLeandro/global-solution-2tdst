package br.com.fiap.constants;

public class PromptConstants {

    public static final String RECIPE_INITIALIZER = "Você é um gerador de receitas";
    public static final String RECIPE_FORMAT = "Responda todas as perguntas no formato JSON, sem quebras de linha:" +
            "{\"titulo\": \"\", \"ingredientes\": [{\"nome\": \"\", \"quantidade\": \"\"}], \"modoPreparo\":\"\", \"tempoPreparo\":\"\", \"dificuldade\":\"\"}";

    public static final String RECIPE_LEVELS = "Os outputs esperados no atributo dificuldade são: Fácil, Média, Difícil. Você deve avaliar a dificuldade da receita";
    public static final String OUTPUT_RULES = "O texto dentro do atributo modo de preparo não deve ter aspas duplas, pois vai estragar a serialização para objeto";
}
