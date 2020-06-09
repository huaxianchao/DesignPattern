package createType.singleton;

/*** 单例设计模式-饿汉式，在静态代码块中new instance
 *  1：private static final 修饰instance
 *  2：在静态代码块中完成instance的初始化
 *  注意：instance要定义在静态代码块之前
 *  优点：
 *  缺点：
 *  todo:此方式 instance具体在什么时候初始化未确定，需要细节了解
 * @author: xianchao.hua
 * @create: 2020-06-09 9:53
 **/
public class Singleton_v6 {

    private static final Singleton_v6 instance;

    static {
        instance = new Singleton_v6();
    }

    public static Singleton_v6 getInstance() {
        return instance;
    }

}
