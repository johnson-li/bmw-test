package name.xuebing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Define the mapper task, wrap the task the input data
 *
 * @param <T> Input of data
 * @param <K> Key of map/reduce
 * @param <V> Value of map/reduce
 */
public class Task<T, K, V> {

    private MapOperation<T, K, V> mapOperation;
    private List<T> input;

    public Task(MapOperation<T, K, V> mapOperation, List<T> input) {
        this.mapOperation = mapOperation;
        this.input = input;
    }

    public List<Result<K, V>> run() {
        Map<K, V> map = mapOperation.map(input);
        List<Result<K, V>> results = new ArrayList<>();
        for (K key : map.keySet()) {
            results.add(new Result<>(key, map.get(key)));
        }
        return results;
    }
}
