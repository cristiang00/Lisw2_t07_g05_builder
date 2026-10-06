package builder;

import domain.DifficultyLevel;
import domain.Question;
import domain.QuestionType;

public class TrueFalseQuestionBuilder extends QuestionBuilder {

    @Override
    public void buildStatement(String statement) {
        question.setStatement(statement);
    }

    @Override
    public void buildDifficulty(DifficultyLevel difficulty) {
        question.setDifficulty(difficulty);
    }

    @Override
    public void buildCategory(String category) {
        question.setCategory(category);
    }

    @Override
    public void buildAuthor(String author) {
        question.setAuthor(author);
    }

    @Override
    public void buildJustification(String justification) {
        question.setJustification(justification);
    }

    @Override
    public void buildSource(String source) {
        question.setSource(source);
    }

    public void buildCorrectAnswer(boolean isTrue) {
        question.setExpectedAnswer(isTrue ? "Verdadero" : "Falso");
    }

    @Override
    public Question build() {
        if (question.getStatement() == null || question.getStatement().trim().isEmpty()) {
            throw new IllegalStateException("El enunciado es obligatorio.");
        }
        if (question.getDifficulty() == null) {
            throw new IllegalStateException("La dificultad es obligatoria.");
        }
        if (question.getCategory() == null || question.getCategory().trim().isEmpty()) {
            throw new IllegalStateException("La categoría es obligatoria.");
        }
        if (question.getExpectedAnswer() == null) {
            throw new IllegalStateException("La respuesta esperada (Verdadero/Falso) es obligatoria.");
        }
        
        question.setType(QuestionType.TRUE_FALSE);
        return question;
    }
}
