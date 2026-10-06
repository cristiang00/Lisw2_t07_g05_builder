package builder;

import domain.DifficultyLevel;
import domain.Option;
import domain.Question;
import domain.QuestionType;
import java.util.List;

public class MultipleChoiceQuestionBuilder extends QuestionBuilder {

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

    public void buildOptions(List<Option> options) {
        question.setOptions(options);
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
        
        List<Option> options = question.getOptions();
        if (options == null || options.size() < 2) {
            throw new IllegalStateException("Una pregunta de selección múltiple debe tener al menos 2 opciones.");
        }
        
        long correctCount = options.stream().filter(Option::isCorrect).count();
        if (correctCount != 1) {
            throw new IllegalStateException("Debe haber exactamente una respuesta correcta.");
        }
        
        question.setType(QuestionType.MULTIPLE_CHOICE);
        return question;
    }
}
