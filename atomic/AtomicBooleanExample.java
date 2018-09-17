package weilan.concurrent.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class AtomicBooleanExample {

    private static int threadTotal = 200;
    private static int clientTotal = 5000;

    private static AtomicBoolean isHappened = new AtomicBoolean(false);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int index = 0; index < clientTotal; index++){
            exec.execute(()->{
                try {
                    semaphore.acquire();
                    test();
                    semaphore.release();
                }catch (Exception e){
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        exec.shutdown();
        log.info("isHappened : "+isHappened.get());
    }

    private static void test(){
        if (isHappened.compareAndSet(false, true)){
            log.info("execute");
        }
    }
}
