import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Train {
    private List<Position> positions;

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Train train = (Train) o;

        if (Objects.isNull(this.positions) || Objects.isNull(train.positions)) {
            return false;
        }

        if (positions.size() != train.positions.size()) {
            return false;
        }

        List<Position> thisOrderedPositions = positions.stream().sorted((p1, p2) -> p1.compare(p2)).toList();
        List<Position> trainOrderedPositions = train.positions.stream().sorted(Position::compare).toList();

        return Arrays.equals(thisOrderedPositions.toArray(), trainOrderedPositions.toArray());
    }
}
