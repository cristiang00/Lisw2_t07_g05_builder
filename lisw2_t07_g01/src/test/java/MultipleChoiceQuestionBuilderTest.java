import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import builder.MultipleChoiceQuestionBuilder;
import director.QuestionDirector;
import domain.DifficultyLevel;
import domain.Option;
import domain.Question;
import domain.QuestionType;

class MultipleChoiceQuestionBuilderTest {

    private QuestionDirector director;
    private MultipleChoiceQuestionBuilder builder;

    @SuppressWarnings("unused")
    @BeforeEach
    void setUp() {
        director = new QuestionDirector();
        builder = new MultipleChoiceQuestionBuilder();
        director.setBuilder(builder);
    }

    @Test
    void testBuildValidMultipleChoiceQuestion() {
        director.construct("Enunciado", DifficultyLevel.MEDIA, "Cat", "Aut", "Just", "Src");
        builder.buildOptions(Arrays.asList(
                new Option("A", "Op 1", true),
                new Option("B", "Op 2", false)));

        Question q = builder.build();
        assertNotNull(q);
        assertEquals(QuestionType.MULTIPLE_CHOICE, q.getType());
        assertEquals("Enunciado", q.getStatement());
        assertEquals(2, q.getOptions().size());
    }

    @Test
    void testBuildWithoutStatementThrowsException() {
        director.construct("", DifficultyLevel.MEDIA, "Cat", "Aut", "Just", "Src");
        builder.buildOptions(Arrays.asList(
                new Option("A", "Op 1", true),
                new Option("B", "Op 2", false)));

        Exception e = assertThrows(IllegalStateException.class, () -> builder.build());
        assertTrue(e.getMessage().contains("obligatorio"));
    }

    @Test
    void testBuildWithoutOptionsThrowsException() {
        director.construct("Enunciado", DifficultyLevel.MEDIA, "Cat", "Aut", "Just", "Src");
        // No options added

        Exception e = assertThrows(IllegalStateException.class, () -> builder.build());
        assertTrue(e.getMessage().contains("2 opciones"));
    }

    @Test
    void testBuildWithoutCorrectAnswerThrowsException() {
        director.construct("Enunciado", DifficultyLevel.MEDIA, "Cat", "Aut", "Just", "Src");
        builder.buildOptions(Arrays.asList(
                new Option("A", "Op 1", false),
                new Option("B", "Op 2", false)));

        Exception e = assertThrows(IllegalStateException.class, () -> builder.build());
        assertTrue(e.getMessage().contains("exactamente una respuesta correcta"));
    }
}
