package weilan.concurrent.publish;

import weilan.concurrent.annoations.NotRecommend;
import weilan.concurrent.annoations.ThreadSafe;

/**
 *  懒汉模式
 *  此种写法锁的粒度过大, 并不推荐使用
 */

@ThreadSafe
@NotRecommend
public class SingletonExample3 {

    private SingletonExample3(){

    }

    private static SingletonExample3 instance = null;


    public static synchronized SingletonExample3 getInstance(){
        if (instance == null){
            instance = new SingletonExample3();
        }
        return instance;
    }
}
