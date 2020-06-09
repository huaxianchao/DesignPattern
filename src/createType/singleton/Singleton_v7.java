package createType.singleton;

/*** 单例设计模式-懒汉式
 *  1：私有化构造方法
 *  2：定义静态内部类，在其内部使用private static final(可选) 定义instance
 *  3：getInstance方法中返回静态内部类.instance
 * 优点：外部类加载时不需要立即加载内部类，而内部类不被加载则不会初始化instance,不占用内存
 *      当外部第一次调用getInstance方法时才会静态内部类并初始化instance，有JVM保证了线程的安全
 *      保证了instance的唯一性，使用lay-init（延迟加载/实例化）减小了程序启动的负担
 * @author: xianchao.hua
 * @create: 2020-06-09 10:01
 **/
public class Singleton_v7 {

    private Singleton_v7() {
    }

    /***
     * 静态内部类，在其中完成instance的定义和静态初始化
     */
    private static class SingletonHolder {
        private static final Singleton_v7 instance = new Singleton_v7();
    }

    public static Singleton_v7 getInstance() {
        return SingletonHolder.instance;
    }
}
