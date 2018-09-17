package weilan.concurrent.pool;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ScheduledExecutorServiceExample {

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

        /**
         * 延迟 3 秒执行
         */
        executorService.schedule(new Runnable() {
            @Override
            public void run() {
                log.info("schedual run");
            }
        }, 3, TimeUnit.SECONDS);

        /**
         * 延迟一秒, 之后每 3 秒执行一次
         */
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.info("schedual run");
            }
        },1 , 2, TimeUnit.SECONDS);

        executorService.shutdown();
    }

}
