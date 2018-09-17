package weilan.concurrent.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j
public class FutureTaskExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("do something in callable");
                Thread.sleep(3000);
                return "Done";
            }
        });

        new Thread(futureTask).start();
        log.info("do something in main");
        Thread.sleep(1000);
        log.info("{}",futureTask.get());
    }

}
