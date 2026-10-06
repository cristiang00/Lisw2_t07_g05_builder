package domain;

public class Option {
    private String label;
    private String text;
    private boolean isCorrect;

    public Option(String label, String text, boolean isCorrect) {
        this.label = label;
        this.text = text;
        this.isCorrect = isCorrect;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    @Override
    public String toString() {
        return label + ") " + text + (isCorrect ? " (Correcta)" : "");
    }
}
