package name.xuebing;

import java.util.*;

/**
 * @param <T> Input of data
 * @param <K> Key of map/reduce
 * @param <V> Value of map/reduce
 */
public class Master<T, K, V> {
    private Random random = new Random();
    // The number of workers
    private Worker<T, K, V>[] mappers;

    public Master(int mapperNumber) {
        if (mapperNumber <= 0) {
            throw new IllegalStateException("Mapper Number should be positive");
        }
        mappers = new Worker[mapperNumber];
    }

    public List<Result<K, V>> mapReduce(List<T> values, MapOperation<T, K, V> map, ReduceOperation<K, V> reduce) {
        // Create reducers and workers
        Reducer<K, V> reducer = new Reducer<>(reduce);
        for (int i = 0; i < mappers.length; i++) {
            mappers[i] = new Worker<>();
        }

        // Shuffle the data
        List<T>[] inputs = partition(values);

        // Dispatch input data to the workers
        for (int i = 0; i < mappers.length; i++) {
            Worker<T, K, V> worker = mappers[i];
            worker.submit(new Task<>(map, inputs[i]));
        }

        boolean async = true;
        // Start the workers
        for (Worker worker : mappers) {
            // Run in new threads
            if (async) {
                new Thread(worker).start();
            } else {
                // Run in current thread
                worker.execute();
            }

        }

        // Wait for the workers and perform the reduce
        int count = 0;
        Worker<T, K, V>[] waitingList = Arrays.copyOf(mappers, mappers.length);
        while (count < waitingList.length) {
            for (int i = 0; i < waitingList.length; i++) {
                Worker<T, K, V> worker = waitingList[i];
                if (worker != null) {
                    if (worker.isDone()) {
                        List<Result<K, V>> results = worker.getResults();
                        results.forEach(reducer::reduce);
                        waitingList[i] = null;
                        count++;
                    }
                }
            }
        }
        return reducer.getResults();
    }

    /**
     * Distribute input data into the workers
     *
     * @param input a list of input data
     * @return
     */
    List<T>[] partition(List<T> input) {
        // For partition affinity
        Map<T, Integer> map = new HashMap<>();
        List<T>[] result = new List[mappers.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = new ArrayList<>();
        }
        for (T t : input) {
            // If the key is not distributed before, assign a partition randomly
            if (!map.containsKey(t)) {
                map.put(t, random.nextInt(mappers.length));
            }
            result[map.get(t)].add(t);
        }
        return result;
    }
}
