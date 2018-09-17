package weilan.concurrent.commonunsafe;

import lombok.extern.slf4j.Slf4j;
import weilan.concurrent.annoations.NotThreadSafe;
import weilan.concurrent.annoations.ThreadSafe;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 *  同步容器不总是都是线程安全的, 使用时依然要注意场景
 */

@Slf4j
@ThreadSafe
public class VectorExample {

    private static Vector<Integer> vector = new Vector<>();

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
                    notsafeVectorOpearation();
                    semaphore.release();
                }catch (Exception e){
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        exec.shutdown();
        log.info("list size = {}", vector.size());
    }

    @ThreadSafe
    private static void add() {
        vector.add(1);
    }

    @NotThreadSafe
    private static void notsafeVectorOpearation(){
        while (true){
            for (int i = 0; i < 20; i++){
                vector.add(i);
            }

            Thread t1 = new Thread(){
                @Override
                public void run(){
                    for (int i = 0; i < vector.size(); i++){
                        vector.remove(i);
                    }
                }
            };

            Thread t2 = new Thread(){
                @Override
                public void run(){
                    for (int i = 0; i < vector.size(); i++){
                        vector.remove(i);
                    }
                }
            };

            t1.start();
            t2.start();
        }
    }
}
