package createType.singleton;

/*** 单例设计模式-懒汉式-getInstance方法加锁
 *  1:私有化构造方法
 *  2：private static修饰本类类型变量instance
 *  3：在getInstance()上加synchronized
 *  优点：解决了v2版本的多线程场景下的问题
 *  缺点：在整个方法加锁，且该锁只在外部第一次请求getInstance()起到实际效果，造成性能浪费
 * @author: xianchao.hua
 * @create: 2020-06-08 12:51
 **/
public class Singleton_v3 {

    private static Singleton_v3 instance;

    private Singleton_v3() {
    }

    public static synchronized Singleton_v3 getInstance() {
        if (instance == null) {
            instance = new Singleton_v3();
        }
        return instance;
    }
}
