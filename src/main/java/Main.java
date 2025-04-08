import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Operation addOperation = new Operation() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        };

        Operation subOperation = (a, b) -> a - b;
        Operation addOperationMethodReference = Integer::sum;

        AddOperation addOperation1 = new AddOperation();
        int result = addOperation1.apply(3, 5);

        System.out.println(result);
        System.out.println(subOperation.andThen(a -> a - 1).andThen(a -> a -2).apply(50, 25));
        System.out.println(addOperationMethodReference.apply(1, 2));

        List<String> numbers = List.of("9", "7", "3");
        int sum = numbers.stream().mapToInt(Integer::parseInt).reduce(0, Integer::sum);
        int sumMap = numbers.stream().map(Integer::valueOf).reduce(0, Integer::sum);
        OptionalDouble sumMap2 = numbers.stream().mapToDouble(a -> Integer.valueOf(a)).average();
        OptionalInt max = numbers.stream().mapToInt(Integer::parseInt).max();
        System.out.println(sumMap2.isPresent() ? sumMap2.getAsDouble() : "555");
        System.out.println(max.getAsInt());

        Map<String, Integer> dict = new HashMap<>();
        String chave = "abc";
        dict.put(chave, 4);
        int dictValue = dict.computeIfAbsent(chave, k -> 7);
        System.out.println(dictValue);
        dict.forEach((key, value) -> System.out.println(key + " -> " + value));

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("abc.json"))) {
            bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println("Falha pra ler o arquivo. Erro esperado");
        }
    }
}
