package director;

import builder.QuestionBuilder;
import domain.DifficultyLevel;

public class QuestionDirector {
    private QuestionBuilder builder;

    public void setBuilder(QuestionBuilder builder) {
        this.builder = builder;
    }

    public void construct(String statement, DifficultyLevel difficulty, 
                          String category, String author, 
                          String justification, String source) {
        builder.reset();
        builder.buildStatement(statement);
        builder.buildDifficulty(difficulty);
        builder.buildCategory(category);
        builder.buildAuthor(author);
        builder.buildJustification(justification);
        builder.buildSource(source);
    }
}
