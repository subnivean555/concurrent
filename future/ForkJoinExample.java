package weilan.concurrent.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

@Slf4j
public class ForkJoinExample extends RecursiveTask<Integer> {

    public static final int threshold = 5;
    private int start;
    private int end;

    public ForkJoinExample(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end - start) <= threshold;
        if (canCompute){
            for (int i = start; i <= end; i++){
                sum += i;
            }
        } else {
            int mid = (start + end) / 2;
            ForkJoinExample leftTask = new ForkJoinExample(start, mid);
            ForkJoinExample rightTask = new ForkJoinExample(mid + 1, end);

            leftTask.fork();
            rightTask.fork();

            int leftResult = leftTask.join();
            int rightResult = rightTask.join();

            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();

        ForkJoinExample task = new ForkJoinExample(1, 100);

        Future<Integer> res = pool.submit(task);
        try {
            log.info("result : {}", res.get());
        } catch (Exception e){
            log.error("exception", e);
        }
    }
}
