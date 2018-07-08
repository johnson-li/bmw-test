package name.xuebing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Word count map operation
 */
public class WordCountMapOperation extends MapOperation<String, String, Integer> {

    @Override
    public Map<String, Integer> map(List<String> inputs) {
        Map<String, Integer> map = new HashMap<>();
        List<Result<String, Integer>> results = new ArrayList<>();
        for (String input : inputs) {
            map.putIfAbsent(input, 0);
            map.put(input, map.get(input) + 1);
        }
        return map;
    }
}
