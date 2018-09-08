package weilan.concurrent.threadlocal;

public class RequestHolder {

    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    /**
     *  请求进入后端服务器,但并没有实际处理的时候调用
     * @param id
     */
    public static void add(Long id){
        requestHolder.set(id);
    }


    public static long getId(){
        return requestHolder.get();
    }

    /**
     *  在接口处理完之后释放内存
     */
    public static void remove(){
        requestHolder.remove();
    }
}
