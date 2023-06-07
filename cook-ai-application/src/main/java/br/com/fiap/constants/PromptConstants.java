package br.com.fiap.constants;

public class PromptConstants {

    public static final String RECIPE_INITIALIZER = "Você é um gerador de receitas";
    public static final String RECIPE_FORMAT = "Responda todas as perguntas exclusivamente no formato JSON, sem quebras de linha:" +
            "{\"titulo\": \"\", \"ingredientes\": [{\"nome\": \"\", \"quantidade\": \"\"}], \"modoPreparo\":\"\", \"tempoPreparo\":\"\", \"dificuldade\":\"\"}";

    public static final String RECIPE_LEVELS = "Os outputs esperados no atributo dificuldade são: Fácil, Média, Difícil. Você deve avaliar a dificuldade da receita";

    public static final String OUTPUT_EXAMPLE = "Exemplo de como deve ser o output: {\"titulo\": \"Arroz com macarrão e alecrim\",\"ingredientes\": [{\"nome\": \"arroz\",\"quantidade\": \"1xícara\"},{\"nome\": \"macarrão\",\"quantidade\": \"100g\"},{\"nome\": \"alecrim\",\"quantidade\": \"2 colheres de chá\"},{\"nome\": \"sal\",\"quantidade\": \"a gosto\"}],\"modoPreparo\": \"1. Cozinhe o arroz normalmente.\\n2. Coloque o macarrão para cozinhar em outra panela.\\n3. Quando o macarrão estiver pronto, escorra a água.\\n4. Em seguida, misture o macarrão com o arroz cozido.\\n5. Acrescente o alecrim e o sal a gosto.\\n6. Mexa bem para incorporar todos os ingredientes.\\n7. Deixe descansar por 5 minutos antes de servir.\",\"tempoPreparo\": \"60 minutos\",\"dificuldade\": \"Fácil\"}";
    public static final String RECIPE_CREATOR = "Crie uma receita SOMENTE com: ";
    public static final String LINE_SEPARATOR = "No atributo modoPreparo, os passos devem vir sempre com uma quebra de linha entre um passo e outro";

    public static final String OUTPUT_RULES = "Você deve retornar somente o JSON, Nenhum texto dos atributos deve ter caracteres que possam estragar o formato JSON, por exemplo aspas duplas, pois vai estragar a serialização para objeto";
    public static final String MAX_TIME = "A receita pode ter o tempo máximo de: ";
    public static final String RECIPE_DIFFICULTY = "A dificuldade dessa receita deve ser:";



}
