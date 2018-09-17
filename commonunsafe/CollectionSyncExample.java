package weilan.concurrent.commonunsafe;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import weilan.concurrent.annoations.ThreadSafe;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@ThreadSafe
public class CollectionSyncExample {


    private static List<Integer> list = Collections.synchronizedList(Lists.newArrayList());

    private static int threadTotal = 200;
    private static int clientTotal = 5000;

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
        log.info("list size = {}", list.size());
    }

    private static void add() {
        list.add(1);
    }


}
