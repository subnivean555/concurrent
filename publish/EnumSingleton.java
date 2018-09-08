package weilan.concurrent.publish;

import weilan.concurrent.annoations.Recommend;
import weilan.concurrent.annoations.ThreadSafe;

@ThreadSafe
@Recommend
public class EnumSingleton {

    private EnumSingleton(){ }

    public static EnumSingleton getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton{
        INSTANCE;

        private EnumSingleton singleton;

        /**
         *  JVM 会保证 该方法绝对只调用一次
         */
        Singleton(){
            singleton = new EnumSingleton();
        }

        public EnumSingleton getInstance(){
            return singleton;
        }
    }
}
