package Factory;

import java.util.Queue;
import java.util.concurrent.*;

public class ThreadPool implements Executor {
  //  private ExecutorService executorService = Executors.newFixedThreadPool()
    private final BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
    private volatile boolean isRunning = true;

    public ThreadPool(int nThreads) {
        for (int i = 0; i < nThreads; i++) {
            new Thread(new TaskWorker()).start();
        }
    }

    @Override
    public void execute(Runnable command) {
        if (isRunning) {
            workQueue.offer(command);
        }
    }

    public void shutdown() {
        isRunning = false;
    }

    private final class TaskWorker implements Runnable {

        @Override
        public void run() {
            while (isRunning) {
                Runnable nextTask = workQueue.poll();
                if (nextTask != null) {
                    nextTask.run();
                }
            }
        }
    }
}
