package weilan.concurrent.atomicDemo;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 *  AtomicReferenceFieldUpdate 更新指定类中的指定字段, 该字段必须使用有 volatile 且非 static 修饰
 *
 */

@Slf4j
public class AtomicReferenceFieldUpdaterExample {

    private static AtomicIntegerFieldUpdater<AtomicReferenceFieldUpdaterExample> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicReferenceFieldUpdaterExample.class, "count");

    @Getter
    public volatile int count = 100;

    private static AtomicReferenceFieldUpdaterExample updaterExample = new AtomicReferenceFieldUpdaterExample();

    public static void main(String[] args) {

        if (updater.compareAndSet(updaterExample, 100, 120)){
            log.info("update success 1 : {}", updaterExample.getCount());
        }

        if (updater.compareAndSet(updaterExample, 100, 120)){
            log.info("update success 2 : {}", updaterExample.getCount());
        } else {
            log.info("update failed : {}", updaterExample.getCount());
        }
    }
}
