package weilan.concurrent.atomic;

import lombok.extern.slf4j.Slf4j;
import weilan.concurrent.annoations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;


@Slf4j
@ThreadSafe
public class AtomicReferenceExample {

    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {

        count.compareAndSet(0, 2);
        count.compareAndSet(0, 1);
        count.compareAndSet(1, 3);
        count.compareAndSet(2, 4);
        count.compareAndSet(3, 6);

        log.info("count : {}", count.get());
    }
}
