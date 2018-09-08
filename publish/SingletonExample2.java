package weilan.concurrent.publish;

import weilan.concurrent.annoations.NotRecommend;
import weilan.concurrent.annoations.ThreadSafe;

/**
 *  饿汉模式
 *  单例实例在类装载使用时创建
 *  性能问题, 会造成资源的浪费.
 */

@ThreadSafe
@NotRecommend
public class SingletonExample2 {

    private SingletonExample2(){

    }

    private static SingletonExample2 instance = new SingletonExample2();

    public static SingletonExample2 getInstance() {
        return instance;
    }
}
