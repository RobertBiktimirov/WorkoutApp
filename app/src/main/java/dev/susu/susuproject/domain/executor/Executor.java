package dev.susu.susuproject.domain.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Executor {
    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void ioThread(Runnable runnable) {
        executorService.execute(runnable);
    }

    public static void shutdown() {
        executorService.shutdown();
    }
}
