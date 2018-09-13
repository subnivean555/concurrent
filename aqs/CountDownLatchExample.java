package weilan.concurrent.aqs;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Slf4j
public class CountDownLatchExample {

    private final static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService exec = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++){
            final int threadNum = i;

            exec.execute(()->{
                try {
                    test(threadNum);
                }catch (Exception e){
                    log.error("exception", e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        /**
         *  await方法中可以传入两个参数, 第一个参数为时间, 第二个参数为时间单位,
         *  设置的效果为 : 执行指定时间后后不管是否执行完毕,都会停止等待
         */
        countDownLatch.await();
        //countDownLatch.await(1, TimeUnit.SECONDS);
        log.info("finish");
        exec.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        Thread.sleep(100);
        log.info("{}", threadNum);
        Thread.sleep(100);
    }

}
