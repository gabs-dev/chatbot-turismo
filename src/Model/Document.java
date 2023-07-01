package Model;

import java.util.List;

public class Document {

    private String name;
    private List<String> lines;
    private int size;

    public Document() {

    }

    public Document(String name, List<String> lines) {
        this.name = name;
        this.lines = lines;
        calculateSize();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public int getSize() {
        if (this.size == 0) calculateSize();

        return this.size;
    }

    private void calculateSize() {
        this.size = 0;

        for (String line : this.lines)
            this.size += line.split(" ").length;
    }
}
