package name.xuebing;

/**
 * Store the output of the mapper.
 *
 * @param <K> key of the mapper output
 * @param <V> value of the mapper output
 */
public class Result<K, V> {
    private K key;
    private V value;

    public Result(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Result{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
