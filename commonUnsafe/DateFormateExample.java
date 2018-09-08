package weilan.concurrent.commonUnsafe;

import lombok.extern.slf4j.Slf4j;
import weilan.concurrent.annoations.NotRecommend;
import weilan.concurrent.annoations.NotThreadSafe;
import weilan.concurrent.annoations.ThreadSafe;

import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 *  SimpleDateFormat 不是一个线程安全的类, 当其被多线程使用时, 会抛出异常
 */

@Slf4j
@NotThreadSafe
@NotRecommend
public class DateFormateExample {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

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
                    //format();
                    safeFormat();
                    semaphore.release();
                }catch (Exception e){
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        exec.shutdown();

    }

    private static void format() {
        try {
            simpleDateFormat.parse("20180908");
        } catch (Exception e){
            log.error("parse excetion", e);
        }
    }

    /**
     * 使用线程封闭的写法来保证线程安全
     */
    @ThreadSafe
    private static void safeFormat(){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            sdf.parse("20180908");
        } catch (Exception e){
            log.error("parse excetion", e);
        }
    }

}
