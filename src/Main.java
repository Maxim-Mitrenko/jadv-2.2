public class Main {

    private static final int CAR_COUNT_PROGRAM_WORK = 10;
    private static final long WAIT = 1500;

    public static void main(String[] args) {
        final Manufacturer manufacturer = new Manufacturer("Toyota");
        final Shop shop = new Shop();
        for (int i = 1; i <= CAR_COUNT_PROGRAM_WORK; i++) {
            new Thread(null, shop::sellCar, String.format("Покупатель %d", i)).start();
            new Thread(null, () -> manufacturer.sendToShop(shop), manufacturer.toString()).start();
            try {
                Thread.sleep(WAIT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}