package weilan.concurrent.publish;

import lombok.extern.slf4j.Slf4j;
import weilan.concurrent.annoations.NotThreadSafe;
import java.util.Arrays;

/**
 *  此时发布对象是线程不安全的, 通过 get 方法获取到 states 的对象可以被修改,
 *  我们在使用时无法确认它是 我们需要的值 还是已经被修改过的值
 */

@Slf4j
@NotThreadSafe
public class UnsafePublish {

    private String[] states = {"a", "b", "c"};

    public String[] getStates() {
        return states;
    }

    public static void main(String[] args) {

        UnsafePublish publish = new UnsafePublish();
        log.info("{}", Arrays.toString(publish.getStates()));

        publish.getStates()[0] = "d";
        log.info("{}", Arrays.toString(publish.getStates()));

    }
}
