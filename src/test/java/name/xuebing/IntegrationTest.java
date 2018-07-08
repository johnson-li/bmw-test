package name.xuebing;

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IntegrationTest {
    public static void main(String[] args) throws Exception {
        new IntegrationTest().run();
    }

    void run() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("demo.txt").getFile());
        Master<String, String, Integer> master = new Master<>(10);
        byte[] bytes = Files.readAllBytes(file.toPath());
        String content = new String(bytes).trim();
        String[] inputs = content.split("[ \t.:,;?]+");
        List<String> inputList = Arrays.asList(inputs);
        inputList = inputList.stream().filter(str -> !str.equals("a") && !str.equals("an") && !str.equals("the")).collect(Collectors.toList());
        List<Result<String, Integer>> result = master.mapReduce(inputList, new WordCountMapOperation(), new WorkCountReduceOperation());
        System.out.println("-----------result-------------");
        System.out.println(result);
    }
}
