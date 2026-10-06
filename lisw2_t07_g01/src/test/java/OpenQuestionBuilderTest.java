import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import builder.OpenQuestionBuilder;
import builder.TrueFalseQuestionBuilder;
import director.QuestionDirector;
import domain.DifficultyLevel;
import domain.Question;
import domain.QuestionType;

class OpenQuestionBuilderTest {

    private QuestionDirector director;
    private OpenQuestionBuilder builder;

    
    @SuppressWarnings("unused")
    @BeforeEach
    void setUp() {
        director = new QuestionDirector();
        builder = new OpenQuestionBuilder();
        director.setBuilder(builder);
    }

    @Test
    void testBuildValidOpenQuestion() {
        director.construct("Enunciado", DifficultyLevel.ALTA, "Cat", "", "", "");
        builder.buildExpectedAnswer("Respuesta esperada");
        
        Question q = builder.build();
        assertNotNull(q);
        assertEquals(QuestionType.OPEN, q.getType());
        assertEquals("Respuesta esperada", q.getExpectedAnswer());
    }

    @Test
    void testBuildWithoutStatementThrowsException() {
        director.construct("   ", DifficultyLevel.ALTA, "Cat", "", "", "");
        builder.buildExpectedAnswer("Respuesta");
        
        Exception e = assertThrows(IllegalStateException.class, () -> builder.build());
        assertTrue(e.getMessage().contains("obligatorio"));
    }

    @Test
    void testBuildWithoutExpectedAnswerThrowsException() {
        director.construct("Enunciado", DifficultyLevel.ALTA, "Cat", "", "", "");
        // No expected answer provided
        
        Exception e = assertThrows(IllegalStateException.class, () -> builder.build());
        assertTrue(e.getMessage().contains("obligatoria en preguntas abiertas"));
    }

    @Test
    void testDirectorBuildsDifferentQuestionTypes() {
        // Usa el builder actual
        director.construct("Abierta", DifficultyLevel.ALTA, "Cat", "", "", "");
        builder.buildExpectedAnswer("Resp");
        Question q1 = builder.build();
        
        // Cambia el builder
        TrueFalseQuestionBuilder tfBuilder = new TrueFalseQuestionBuilder();
        director.setBuilder(tfBuilder);
        director.construct("TF", DifficultyLevel.BAJA, "Cat", "", "", "");
        tfBuilder.buildCorrectAnswer(true);
        Question q2 = tfBuilder.build();
        
        assertEquals(QuestionType.OPEN, q1.getType());
        assertEquals(QuestionType.TRUE_FALSE, q2.getType());
        assertNotEquals(q1.getType(), q2.getType());
    }

    @Test
    void testResetClearsBuilder() {
        director.construct("Enunciado 1", DifficultyLevel.ALTA, "Cat", "", "", "");
        builder.buildExpectedAnswer("R1");
        Question q1 = builder.build();
        
        // Nueva construcción resetea
        director.construct("Enunciado 2", DifficultyLevel.BAJA, "Cat2", "", "", "");
        builder.buildExpectedAnswer("R2");
        Question q2 = builder.build();
        
        assertNotEquals(q1.getStatement(), q2.getStatement());
        assertEquals("Enunciado 1", q1.getStatement());
        assertEquals("Enunciado 2", q2.getStatement());
    }
}
