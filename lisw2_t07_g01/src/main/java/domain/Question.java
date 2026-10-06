package domain;

import java.util.List;

public class Question {
    private String statement;
    private DifficultyLevel difficulty;
    private String category;
    private String author;
    private String justification;
    private String source;
    private QuestionType type;
    
    // Atributos específicos según tipo
    private List<Option> options;
    private String expectedAnswer;

    public String getStatement() { return statement; }
    public void setStatement(String statement) { this.statement = statement; }

    public DifficultyLevel getDifficulty() { return difficulty; }
    public void setDifficulty(DifficultyLevel difficulty) { this.difficulty = difficulty; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getJustification() { return justification; }
    public void setJustification(String justification) { this.justification = justification; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public QuestionType getType() { return type; }
    public void setType(QuestionType type) { this.type = type; }

    public List<Option> getOptions() { return options; }
    public void setOptions(List<Option> options) { this.options = options; }

    public String getExpectedAnswer() { return expectedAnswer; }
    public void setExpectedAnswer(String expectedAnswer) { this.expectedAnswer = expectedAnswer; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Enunciado: ").append(statement).append("\n");
        sb.append("Tipo: ").append(type).append("\n");
        sb.append("Dificultad: ").append(difficulty).append("\n");
        sb.append("Categoría: ").append(category).append("\n");
        
        if (author != null && !author.isEmpty()) {
            sb.append("Autor: ").append(author).append("\n");
        }
        
        if (type == QuestionType.MULTIPLE_CHOICE && options != null) {
            sb.append("Opciones:\n");
            for (Option opt : options) {
                sb.append("  ").append(opt.toString()).append("\n");
            }
        } else if (type == QuestionType.TRUE_FALSE) {
            sb.append("Respuesta correcta: ").append(expectedAnswer).append("\n");
        } else if (type == QuestionType.OPEN) {
            sb.append("Respuesta esperada: ").append(expectedAnswer).append("\n");
        }
        
        if (justification != null && !justification.isEmpty()) {
            sb.append("Justificación: ").append(justification).append("\n");
        }
        if (source != null && !source.isEmpty()) {
            sb.append("Fuente: ").append(source).append("\n");
        }
        return sb.toString();
    }
}
