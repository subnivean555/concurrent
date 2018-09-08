package weilan.concurrent.immutable;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import weilan.concurrent.annoations.ThreadSafe;

import java.util.Collections;
import java.util.Map;

/**
 *  Unmodifiable 的实现是, 将原本容器中的值拷贝进新创建的 Unmodifiable 容器中,
 *  然后将该容器的修改方法直接改为 抛出异常
 */

@Slf4j
@ThreadSafe
public class UnmodifiableExample {

    private static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(8, 9);
        map.put(4, 5);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        map.put(1, 3);
        log.info("{}", map.get(1));
    }
}
