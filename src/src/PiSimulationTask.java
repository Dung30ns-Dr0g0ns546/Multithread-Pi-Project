import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class PiSimulationTask implements Callable<Long> {

    private final long pointsToGenerate;

    public PiSimulationTask(long pointsToGenerate) {
        this.pointsToGenerate = pointsToGenerate;
    }

    @Override
    public Long call() {

        long insideCircle = 0;

        for (long i = 0; i < pointsToGenerate; i++) {

            double x = ThreadLocalRandom.current()
                    .nextDouble(-1.0, 1.0);

            double y = ThreadLocalRandom.current()
                    .nextDouble(-1.0, 1.0);

            double distance = x * x + y * y;

            if (distance <= 1.0) {
                insideCircle++;
            }
        }

        return insideCircle;
    }
}

