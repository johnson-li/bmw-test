package name.xuebing;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MasterTest {

    @Test
    public void testPartition() {
        List<String> data = new ArrayList<>();
        Master<String, String, Integer> master = new Master<>(2);
        Assert.assertEquals(master.partition(data).length, 2);
        for (int i = 0; i < 100; i++) {
            data.add(String.valueOf(i));
        }
        List<String>[] partitions = master.partition(data);
        int count = 0;
        for (List<String> partition : partitions) {
            count += partition.size();
        }
        Assert.assertEquals(100, count);
        for (int i = 0; i < 100; i++) {
            data.add(String.valueOf(i));
        }
        partitions = master.partition(data);
        count = 0;
        for (List<String> partition : partitions) {
            count += partition.size();
        }
        Assert.assertEquals(200, count);
        System.out.println(data);
        for (List<String> partition : partitions) {
            System.out.println(partition);
            Assert.assertEquals(0, partition.size() % 2);
        }
    }
}
