import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import builder.TrueFalseQuestionBuilder;
import director.QuestionDirector;
import domain.DifficultyLevel;
import domain.Question;
import domain.QuestionType;

class TrueFalseQuestionBuilderTest {

    private QuestionDirector director;
    private TrueFalseQuestionBuilder builder;

    @SuppressWarnings("unused")
    @BeforeEach
    void setUp() {
        director = new QuestionDirector();
        builder = new TrueFalseQuestionBuilder();
        director.setBuilder(builder);
    }

    @Test
    void testBuildValidTrueFalseQuestion() {
        director.construct("Enunciado", DifficultyLevel.BAJA, "Cat", "", "", "");
        builder.buildCorrectAnswer(true);
        
        Question q = builder.build();
        assertNotNull(q);
        assertEquals(QuestionType.TRUE_FALSE, q.getType());
        assertEquals("Verdadero", q.getExpectedAnswer());
    }

    @Test
    void testBuildWithoutStatementThrowsException() {
        director.construct(null, DifficultyLevel.BAJA, "Cat", "", "", "");
        builder.buildCorrectAnswer(true);
        
        Exception e = assertThrows(IllegalStateException.class, () -> builder.build());
        assertTrue(e.getMessage().contains("obligatorio"));
    }

    @Test
    void testBuildValidFalseAnswer() {
        director.construct("Enunciado", DifficultyLevel.BAJA, "Cat", "", "", "");
        builder.buildCorrectAnswer(false);
        
        Question q = builder.build();
        assertEquals("Falso", q.getExpectedAnswer());
    }
}
