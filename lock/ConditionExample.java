package weilan.concurrent.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ConditionExample {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(()->{
            try {
                lock.lock();
                log.info("wait signal");    //1
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("get signal");         //4
            lock.unlock();
        }).start();

        new Thread(()->{
            lock.lock();
            log.info("get lock");           //2
            try{
                Thread.sleep(3000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            condition.signalAll();
            log.info("send signal ");       //3
            lock.unlock();
        }).start();

    }
}
