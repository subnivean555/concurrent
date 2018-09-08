package weilan.concurrent.publish;

import weilan.concurrent.annoations.NotRecommend;
import weilan.concurrent.annoations.ThreadSafe;

/**
 *  在代码中注意 静态域与变量定义的顺序,
 */

@ThreadSafe
@NotRecommend
public class SingletonExample6 {

    private SingletonExample6(){ }

    private static SingletonExample6 instance = null;

    static {
        instance = new SingletonExample6();
    }

    public static SingletonExample6 getInstance(){
        return instance;
    }
}
