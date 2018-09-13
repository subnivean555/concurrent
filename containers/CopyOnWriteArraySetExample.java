package weilan.concurrent.containers;

import lombok.extern.slf4j.Slf4j;
import weilan.concurrent.annoations.ThreadSafe;

import java.util.concurrent.*;


@Slf4j
@ThreadSafe
public class CopyOnWriteArraySetExample {

    private static CopyOnWriteArraySet<Integer> set = new CopyOnWriteArraySet<>();
    private static int threadTotal = 200;
    private static int clientTotal = 5000;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int index = 0; index < clientTotal; index++){
            final int i = index;
            exec.execute(()->{
                try {
                    semaphore.acquire();
                    setadd(i);
                    semaphore.release();
                }catch (Exception e){
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        exec.shutdown();
        log.info("set size = {}", set.size());
    }

    private static void setadd(int i) {
        set.add(i);
    }
}
