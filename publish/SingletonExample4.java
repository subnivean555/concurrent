package weilan.concurrent.publish;

import weilan.concurrent.annoations.NotRecommend;
import weilan.concurrent.annoations.NotThreadSafe;

/**
 *    CPU 在创建对象时分为三步操作
 *      1. memory = allocate()  分配对象的内存空间
 *      2. ctorInstance()       初始化对象
 *      3. instance = memory    设置 instance 指向刚分配的内存
 *
 *    在 JVM 和 CPU 的优化下, 指令重排序会导致对象未能初始化
 */

@NotThreadSafe
@NotRecommend
public class SingletonExample4 {

    private SingletonExample4(){

    }

    private static SingletonExample4 instance = null;


    public static SingletonExample4 getInstance(){
        if (instance == null){
            synchronized (SingletonExample4.class) {
                if (instance == null){
                    instance = new SingletonExample4();
                }
            }
        }
        return instance;
    }
}
