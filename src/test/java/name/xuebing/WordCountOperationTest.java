package name.xuebing;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordCountOperationTest {

    @Test
    public void testMap() {
        WordCountMapOperation operation = new WordCountMapOperation();
        List<String> input = new ArrayList<>();
        Assert.assertEquals(0, operation.map(input).size());
        input.add("a");
        Assert.assertEquals(1, (long) operation.map(input).get("a"));
        input.add("b");
        Assert.assertEquals(1, (long) operation.map(input).get("a"));
        Assert.assertEquals(1, (long) operation.map(input).get("b"));
        input.add("a");
        Assert.assertEquals(2, (long) operation.map(input).get("a"));
    }

    @Test
    public void testReduce() {
        WorkCountReduceOperation operation = new WorkCountReduceOperation();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int d1 = random.nextInt();
            int d2 = random.nextInt();
            Assert.assertEquals(d1 + d2, (long) operation.accumulate(d1, d2));
        }
    }
}
