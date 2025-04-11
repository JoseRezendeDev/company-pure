import java.util.Objects;

public class Position {
    private int x;
    private int y;

    public Position() {

    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int compare(Position p) {
        int compareX = Integer.compare(this.x, p.x);

        if (compareX == 0) {
            return Integer.compare(this.y, p.y);
        }

        return compareX;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }

        Position position = (Position) object;
        return this.x == position.x && this.y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "{" + x + "," + y + "}";
    }
}
