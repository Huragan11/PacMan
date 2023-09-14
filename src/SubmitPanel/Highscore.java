package SubmitPanel;

import java.io.Serializable;

public class Highscore implements Serializable {
    private static final long serialVersionUID = 1L; // ensure compatibility between different versions of the class when deserializing.

    private String name;
    private String score;

    public Highscore(String name, String score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public String getScore() {
        return score;
    }
}
