import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SNAKE_CASE;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Main {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.setPropertyNamingStrategy(SNAKE_CASE);

        objectMapper.readValue(new File("src/main/resources/something.json"), new TypeReference<List<Main>>() {});

        BufferedReader bufferedReader = new BufferedReader(new FileReader("something.csv"));
        bufferedReader.readLine();

        Map<String, Customer> map = new TreeMap<>();
        String randomKey = "abc";
        map.computeIfAbsent(randomKey, abc -> new Customer(abc));

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        AtomicInteger i = new AtomicInteger(0);
        while (i.getAndIncrement() < 10) {
            executorService.submit(() -> System.out.println("i = " + i.get() + " and name = " + Thread.currentThread().getName()));
            System.out.println(i.get());
        }

        Callable<Integer> task1 = () -> {
            System.out.println("print task 1");
            return 9;
        } ;

        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("print completable future");
            return 3;
        });

        Future<Integer> task1Future = executorService.submit(task1);

        System.out.println(task1Future.get());

        System.out.println("5555");
        System.out.println(completableFuture.get());
        System.out.println("7777");

        Customer customer = new Customer("jose");
        customer.setCounter(0);
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                customer.setCounter(i);
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        thread1.start();
        thread2.start();

        System.out.println("before first thread: " + customer.getCounter());
        thread1.join();
        System.out.println("before second thread: " + customer.getCounter());
        thread2.join();
        System.out.println("after all: " + customer.getCounter());

    }
}
