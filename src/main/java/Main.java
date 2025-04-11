import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.function.BiFunction;

public class Main {
    public static void main(String[] args) {
//        Position p1 = new Position(1, 1);
//        Position p2 = new Position(1, 2);
//        Position p3 = new Position(2, 1);
//        Position p4 = new Position(1, 1);

        BiFunction<Position, Position, Boolean> checkEquality = (a, b) -> {
            System.out.println(a + " and " + b + " are " + (a.equals(b) ? "equals" : "not equals"));
            return a.equals(b);
        };

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);

        Train train1;
        Train train2;
        try {
            train1 = objectMapper.readValue(new File("src/main/resources/positions.json"), Train.class);
            train2 = objectMapper.readValue(new File("src/main/resources/positions2.json"), Train.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (Objects.nonNull(train1) && Objects.nonNull(train2)) {
            System.out.println(train1.equals(train2));

            if (Objects.nonNull(train1.getPositions()) && Objects.nonNull(train2.getPositions())) {
                for (int i = 0; i < train1.getPositions().size(); i++) {
                    checkEquality.apply(train1.getPositions().get(i), train2.getPositions().get(i));
                }
            }
        }
    }
}
