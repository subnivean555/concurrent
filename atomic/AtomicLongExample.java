package weilan.concurrent.atomic;

import lombok.extern.slf4j.Slf4j;
import weilan.concurrent.annoations.ThreadSafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

/**
 *  incrementAndGet()   先加再操作
 *  getAndIncrement()   先操作再加
 */

@Slf4j
@ThreadSafe
public class AtomicLongExample {
    private static int threadTotal = 200;
    private static int clientTotal = 5000;

    private static AtomicLong count = new AtomicLong(0);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int index = 0; index < clientTotal; index++){
            exec.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                }catch (Exception e){
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        exec.shutdown();
        log.info("count : "+count.get());
    }

    private static void add(){
        count.incrementAndGet();
    }
}
