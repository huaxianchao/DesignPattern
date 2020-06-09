package createType.singleton;

/*** 单例设计模式-懒汉式-DCL：双重锁检测
 * ===该写法是一种完美写法，保证了线程安全，且效率也比直接在getInstance()方法上加锁好，
 * ===但该方法涉及到volitile，需要深入学习
 * 1：私有化构造方法
 * 2：private static volatile修饰instance --volatile实现禁止JVM指令重排序，
 *      不加volatile也会有多线程安全的问题，但是不太好模拟，即DCL失效问题
 * 优缺点暂时不做分析
 * @author: xianchao.hua
 * @create: 2020-06-08 13:16
 **/
public class Singleton_v5 {

    private static volatile Singleton_v5 instance;

    private Singleton_v5() {
    }

    public static Singleton_v5 getInstance() throws InterruptedException {
        //第一次判断为了避免不必要的加锁
        if (instance == null) {
            //配合测试方法模拟多线程同时进入此处
            Thread.sleep(10);
            //加锁
            synchronized (Singleton_v5.class) {
                //第二次判断+instance定义时用volatile修饰，避免DCL失效问题
                if (instance == null) {
                    //new一个对象在JVM中包含以下三个步骤
                    //  1.在堆内存开辟内存空间。
                    //  2.在堆内存中实例化SingleTon里面的各个参数。
                    //  3.把对象指向堆内存空间。
                    //  由于jvm存在乱序执行功能，所以可能在2还没执行时就先执行了3，
                    // 如果此时再被切换到线程B上，由于执行了3，INSTANCE 已经非空了，
                    // 会被直接拿出来用，这样的话，就会出现异常。这个就是著名的DCL失效问题。
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
