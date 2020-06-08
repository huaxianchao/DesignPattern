package createType.singleton;

/*** 单例设计模式-懒汉式-getInstance部分加锁
 *  1:私有化构造方法
 *  2:privae static 修饰本类类型变量instance
 *  3：在getInstance方法中判断，若instance为空，则对该class上锁，否则直接return
 *  测试结果证明：
 *      该线程虽然对比v3版本在性能上有了提升，但在多线程的情况下不能保证安全
 * @author: xianchao.hua
 * @create: 2020-06-08 12:58
 **/
public class Singleton_v4 {

    private static Singleton_v4 instance;

    private Singleton_v4() {
    }

    public static Singleton_v4 getInstance() throws InterruptedException {
        if (instance == null) {
            //模拟多线程同时进入到此处
            Thread.sleep(1);
            synchronized (Singleton_v4.class) {
                instance = new Singleton_v4();
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
                        System.out.println(Singleton_v4.getInstance().hashCode());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

    }
}
