package name.xuebing;

/**
 * Word count reduce operation
 */
public class WorkCountReduceOperation extends ReduceOperation<String, Integer> {
    @Override
    public Integer accumulate(Integer v1, Integer v2) {
        return v1 + v2;
    }
}
