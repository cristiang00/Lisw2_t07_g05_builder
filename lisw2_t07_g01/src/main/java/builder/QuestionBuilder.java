package builder;

import domain.DifficultyLevel;
import domain.Question;

public abstract class QuestionBuilder {
    protected Question question;

    public Question getQuestion() {
        return question;
    }

    public void reset() {
        question = new Question();
    }

    public abstract void buildStatement(String statement);
    public abstract void buildDifficulty(DifficultyLevel difficulty);
    public abstract void buildCategory(String category);
    public abstract void buildAuthor(String author);
    public abstract void buildJustification(String justification);
    public abstract void buildSource(String source);

    public abstract Question build();
}
