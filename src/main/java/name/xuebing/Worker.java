package name.xuebing;

import com.google.common.util.concurrent.SettableFuture;

import java.util.ArrayList;
import java.util.List;

/**
 * Async mapper, contains a list of tasks. The tasks are executed synchronized in a thread.
 */
public class Worker<T, K, V> implements Runnable {
    private List<Task<T, K, V>> taskList = new ArrayList<>();
    private List<Result<K, V>> results = new ArrayList<>();
    private SettableFuture<List<Result<K, V>>> future = SettableFuture.create();

    public List<Result<K, V>> getResults() {
        return results;
    }

    public void submit(Task<T, K, V> task) {
        taskList.add(task);
    }

    public boolean isDone() {
        return future.isDone();
    }

    @Override
    public void run() {
        execute();
    }

    public void execute() {
        for (Task<T, K, V> task : taskList) {
            List<Result<K, V>> result = task.run();
            results.addAll(result);
        }
        future.set(results);
    }
}
