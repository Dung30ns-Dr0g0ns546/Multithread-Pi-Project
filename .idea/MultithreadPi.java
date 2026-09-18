import java.time.Duration;
import java.time.Instant;
import.java.util.ArrayList;
import.java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultithreadPi {
    private static final long totalPoints = 500_000_000;
    private static final int numThreads = 4;

    public static void main(String[] args) throw Exception {
        Instant start = Instant.now();

        ExecutorService es = Executors.newFixedThreadPool(numThreads);
        long pointsPerThread = totalPoints / numThreads;
        List<Future<Long>> futures = new ArrayList<>();
    }
}