import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Shop {

    private static final long BUY = 3000;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    List<Car> cars = new ArrayList<>();

    public Car sellCar() {
        Car car = null;
        try {
            System.out.format("%s зашёл в автосалон!\n", Thread.currentThread().getName());
            lock.lock();
            while (cars.isEmpty()) {
                System.out.println("Машин нет!");
                condition.await();
            }
            Thread.sleep(BUY);
            System.out.format("%s уехал на новом автомобиле!\n", Thread.currentThread().getName());
            car = cars.remove(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return car;
    }

    public void addCar(Car car) {
        cars.add(car);
        condition.signal();
    }
}