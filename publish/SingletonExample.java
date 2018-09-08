package weilan.concurrent.publish;


import weilan.concurrent.annoations.NotRecommend;
import weilan.concurrent.annoations.NotThreadSafe;

/**
 *  懒汉模式
 */

@NotRecommend
@NotThreadSafe
public class SingletonExample {

    private SingletonExample(){

    }

    private static SingletonExample instance = null;


    public static SingletonExample getInstance(){
        if (instance == null){
            instance = new SingletonExample();
        }
        return instance;
    }
}
