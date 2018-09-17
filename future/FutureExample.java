package weilan.concurrent.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class FutureExample {

    static class CallableTest implements Callable<String>{

        @Override
        public String call() throws Exception {
            log.info("do something in callable");
            Thread.sleep(3000);
            return "Done";
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService exec = Executors.newCachedThreadPool();
        Future<String> future = exec.submit(new CallableTest());

        log.info("do something in main");
        Thread.sleep(1000);
        String result = future.get();
        log.info("{}", result);
    }

}
