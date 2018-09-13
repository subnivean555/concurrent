package weilan.concurrent.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class CyclicBarrierExample {

    /**
     *  同步等待的线程数,
     */
    //private static CyclicBarrier barrier = new CyclicBarrier(5);

    /**
     *  当等待的线程数到达要求时, 优先执行 runnable 接口中的代码.
     */
    private static CyclicBarrier barrier = new CyclicBarrier(5,()->{
        log.info("call back is running");
    });

    public static void main(String[] args) throws Exception {

        ExecutorService exec = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++){
            final int threadNum = i;
            Thread.sleep(1000);
            exec.execute(()->{
                try {
                    race(threadNum);
                }catch (Exception e){
                    log.error("exception", e);
                }
            });
        }

        exec.shutdown();
    }

    private static void race(int threadNum) throws Exception {
        Thread.sleep(1000);
        log.info("{} is ready", threadNum);
        /**
         * 此时表示一个线程准备完成, 进入等待, 当它达到指定的数字就可以开始执行了
         */
        //barrier.await();

        /**
         * 也可以指定等待时间, 如果超过了等待时间, 就开始执行
         */
        try {
            barrier.await(2000, TimeUnit.MILLISECONDS);
        } catch (BrokenBarrierException e){
            log.warn("BrokenBarrierException", e);
        }

        log.info("{} is continue", threadNum);
    }
}
