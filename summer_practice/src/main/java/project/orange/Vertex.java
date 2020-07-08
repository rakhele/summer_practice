package project.orange;

import java.util.Objects;

public class Vertex {
    private final char name;

    public Vertex(char name) {
        this.name = name;
    }
    public char getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return name == vertex.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
