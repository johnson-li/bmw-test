package name.xuebing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reducer<K, V> {
    private Map<K, V> map = new HashMap<>();
    private ReduceOperation<K, V> reduceOperation;

    public Reducer(ReduceOperation<K, V> reduceOperation) {
        this.reduceOperation = reduceOperation;
    }

    /**
     * Perform the reduce
     *
     * @param result the output from the mapper
     */
    public void reduce(Result<K, V> result) {
        if (!map.containsKey(result.getKey())) {
            map.put(result.getKey(), result.getValue());
        } else {
            map.put(result.getKey(), reduceOperation.accumulate(map.get(result.getKey()), result.getValue()));
        }
    }

    /**
     * Fetch the result of the reduce procedure
     *
     * @return the output of reduce
     */
    public List<Result<K, V>> getResults() {
        List<Result<K, V>> results = new ArrayList<>();
        for (K key : map.keySet()) {
            results.add(new Result<>(key, map.get(key)));
        }
        return results;
    }
}
