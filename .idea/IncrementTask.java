public class IncrementTask implements Runnable {
    private int currentThreads = 1;

    public int getNextThreadCount() {
        int result = currentThreads;
        currentThreads *= 2;
        return result;
    }

    public boolean hasNext() {
        return currentThreads <= 16;
    }
}
