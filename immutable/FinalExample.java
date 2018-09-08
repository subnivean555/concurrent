package weilan.concurrent.immutable;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import weilan.concurrent.annoations.NotThreadSafe;

import java.util.Map;

@Slf4j
@NotThreadSafe
public class FinalExample {

    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(8, 9);
        map.put(4, 5);
    }

    public static void main(String[] args) {
        map.put(1, 3);
        log.info("{}", map.get(1));
    }

}
