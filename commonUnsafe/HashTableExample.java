package weilan.concurrent.commonUnsafe;

import lombok.extern.slf4j.Slf4j;
import weilan.concurrent.annoations.ThreadSafe;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@ThreadSafe
public class HashTableExample {

    private static int threadNum = 200;
    private static int clientNum = 5000;
    private static Map<Integer, Integer> table = new Hashtable<>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadNum);
        final CountDownLatch countDownLatch = new CountDownLatch(clientNum);
        for (int i = 0; i < clientNum; i++){
            final  int threadNum = i;
            exec.execute(()->{
                try{
                    semaphore.acquire();
                    hashtableAdd(threadNum);
                    semaphore.release();
                }catch (Exception e){
                    System.out.println(e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        exec.shutdown();
        log.info("size : " + table.size());
    }

    public static void hashtableAdd(int threadNum){
        table.put(threadNum, threadNum);
    }

}
