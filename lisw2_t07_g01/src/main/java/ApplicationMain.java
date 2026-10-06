import builder.MultipleChoiceQuestionBuilder;
import builder.OpenQuestionBuilder;
import builder.TrueFalseQuestionBuilder;
import director.QuestionDirector;
import domain.DifficultyLevel;
import domain.Option;
import domain.Question;

import java.util.Arrays;

public class ApplicationMain {
    public static void main(String[] args) {
        QuestionDirector director = new QuestionDirector();

        // 1. Pregunta de Selección Múltiple
        MultipleChoiceQuestionBuilder mcBuilder = new MultipleChoiceQuestionBuilder();
        director.setBuilder(mcBuilder);
        director.construct(
            "¿Cuál es el principio SOLID de responsabilidad única?",
            DifficultyLevel.MEDIA,
            "Ingeniería de Software",
            "Prof. Pantoja",
            "El principio dice que una clase debe tener una sola razón para cambiar.",
            "Clean Code"
        );
        mcBuilder.buildOptions(Arrays.asList(
            new Option("A", "Cada clase debe tener una sola responsabilidad", true),
            new Option("B", "Las clases deben ser abiertas para extensión", false),
            new Option("C", "Las interfaces deben ser específicas", false),
            new Option("D", "Depender de abstracciones", false)
        ));
        Question mcQuestion = mcBuilder.build();
        System.out.println("=== Pregunta de Selección Múltiple ===");
        System.out.println(mcQuestion);


        // 2. Pregunta Verdadero/Falso
        TrueFalseQuestionBuilder tfBuilder = new TrueFalseQuestionBuilder();
        director.setBuilder(tfBuilder);
        director.construct(
            "El patrón Builder es un patrón estructural.",
            DifficultyLevel.BAJA,
            "Patrones de Diseño",
            "Prof. Pantoja",
            "Builder es un patrón creacional, no estructural.",
            "Refactoring Guru"
        );
        tfBuilder.buildCorrectAnswer(false); // La afirmación es Falsa
        Question tfQuestion = tfBuilder.build();
        System.out.println("=== Pregunta Verdadero/Falso ===");
        System.out.println(tfQuestion);


        // 3. Pregunta Abierta
        OpenQuestionBuilder openBuilder = new OpenQuestionBuilder();
        director.setBuilder(openBuilder);
        director.construct(
            "Explique el principio de inversión de dependencias.",
            DifficultyLevel.ALTA,
            "Ingeniería de Software",
            "",
            "",
            ""
        );
        openBuilder.buildExpectedAnswer("Los módulos de alto nivel no deben depender de los módulos de bajo nivel. Ambos deben depender de abstracciones.");
        Question openQuestion = openBuilder.build();
        System.out.println("=== Pregunta Abierta ===");
        System.out.println(openQuestion);
    }
}
