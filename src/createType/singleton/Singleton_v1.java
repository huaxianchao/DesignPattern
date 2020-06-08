package createType.singleton;

/*** 单例设计模式-饿汉式
 *  1：私有化构造方法
 *  2：私有化本类对象instance,静态初始化
 *  3:提供 public static的 getInstance()获取instance-因为构造方法被private，
 *      外界无法通过构造方法创建对象，所以要用private修饰
 *  优点：static修饰的变量在类被加载时就被加载并初始化，而JVM又保证了每个类只会被加载一次
 *      所以是线程安全的
 *  缺点：在类启动时就完成了变量的初始化，此时并未实际用到该对象，造成程序启动时间/加载任务的负担
 * @author: xianchao.hua
 * @create: 2020-06-08 12:36
 **/
public class Singleton_v1 {

    private static final Singleton_v1 instart_v1 = new Singleton_v1();


    private Singleton_v1() {

    }

    public static Singleton_v1 getInstance() {
        return instart_v1;
    }

}
