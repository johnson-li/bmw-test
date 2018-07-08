package name.xuebing;

public abstract class ReduceOperation<K, V> {
    /**
     * Combine the results of the same key
     *
     * @return accumulated result
     */
    public abstract V accumulate(V v1, V v2);
}
