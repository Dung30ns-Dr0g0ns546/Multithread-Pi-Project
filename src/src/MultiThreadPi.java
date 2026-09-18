import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultiThreadPi {
    private static final long totalPoints = 10_000_000_000L;
    private static final int numThreads = 4;

    public static void main(String[] args) throws Exception {
        Instant start = Instant.now();

        ExecutorService es = Executors.newFixedThreadPool(numThreads);
        long pointsPerThread = totalPoints / numThreads;
        List<Future<Long>> futures = new ArrayList<>();

        for (int i = 0; i < numThreads; i++) {
            Future<Long> future = es.submit(() -> {

                long insideCircle = 0;

                for (long j = 0; j < pointsPerThread; j++) {

                    double x = ThreadLocalRandom.current().nextDouble(-1.0, 1.0);
                    double y = ThreadLocalRandom.current().nextDouble(-1.0, 1.0);

                    double distance = x * x + y * y;

                    if (distance <= 1.0) {
                        insideCircle++;
                    }
                }

                return insideCircle;
            });
            futures.add(future);
        }
        long totalInsideCircle = 0;

        for (Future<Long> future : futures) {
            totalInsideCircle += future.get();
        }

        double pi = 4.0 * totalInsideCircle / totalPoints;

        es.shutdown();

        Instant end = Instant.now();
        long runtime = Duration.between(start,end).toMillis();

        System.out.println("Total points: " + totalPoints);
        System.out.println("Threads: " + numThreads);
        System.out.println("Points inside circle: " + totalInsideCircle);
        System.out.println("Estimated Pi: " + pi);
        System.out.println("Time: " + Duration.between(start, end).toMillis() + " ms");
    }
}
