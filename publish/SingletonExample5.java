package weilan.concurrent.publish;

import weilan.concurrent.annoations.Recommend;
import weilan.concurrent.annoations.ThreadSafe;

/**
 *  使用 volatile 关键字来避免指令重排序,
 */

@ThreadSafe
@Recommend
public class SingletonExample5 {

    private SingletonExample5(){

    }

    private volatile static SingletonExample5 instance = null;

    public static SingletonExample5 getInstance(){
        if (instance == null){
            synchronized (SingletonExample5.class) {
                if (instance == null){
                    instance = new SingletonExample5();
                }
            }
        }
        return instance;
    }

}
