package name.xuebing;

import java.util.List;
import java.util.Map;

public abstract class MapOperation<T, K, V> {
    public abstract Map<K, V> map(List<T> inputs);
}
