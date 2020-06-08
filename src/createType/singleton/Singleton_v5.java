package createType.singleton;

/*** 单例设计模式-懒汉式-DCL：双重锁检测
 * ===该写法是一种完美写法，保证了线程安全，且效率也比直接在getInstance()方法上加锁好，
 * ===但该方法涉及到volitile，需要深入学习
 * 1：私有化构造方法
 * 2：private static volatile修饰instance --volatile实现禁止JVM指令重排序，
 *      不加volatile也会有多线程安全的问题，但是不太好模拟
 * 优缺点暂时不做分析
 * @author: xianchao.hua
 * @create: 2020-06-08 13:16
 **/
public class Singleton_v5 {

    private static volatile Singleton_v5 instance;

    private Singleton_v5() {
    }

    public static Singleton_v5 getInstance() throws InterruptedException {
        if (instance == null) {
            Thread.sleep(10);
            synchronized (Singleton_v5.class) {
                Thread.sleep(10);
                if (instance == null) {
                    instance = new Singleton_v5();
                }
            }
        }
        return instance;
    }

    /**
     * 测试方法,通过模拟测试该单例在多线程情况下是否安全
     *
     * @Author: huaxianchao
     * @Date: 2020/6/8 13:08
     * @Param:
     * @Return:
     */
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Singleton_v5.getInstance().hashCode());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
