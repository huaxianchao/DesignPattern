package createType.singleton;

/*** 单例设计模式-懒汉式
 *  1：私有化构造方法
 *  2：private static修饰本类类型对象，暂时不初始化
 *  3：getInstance中判断instance是否为空，若为空则初始化并返回，不为空直接返回
 *  优点：使用lazy-init解决了v1版本中的缺点
 *  缺点：多线程情况下，会有不同线程获取到的instance对象不一致的情况
 * @author: xianchao.hua
 * @create: 2020-06-08 12:44
 **/
public class Singleton_v2 {

    private static Singleton_v2 instance;

    private Singleton_v2() {
    }

    public static Singleton_v2 getInstance() throws InterruptedException {
        if (instance == null) {
            //模拟多线程同时进入到此处
            Thread.sleep(1);
            instance = new Singleton_v2();
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
                        System.out.println(Singleton_v2.getInstance().hashCode());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

    }
}
