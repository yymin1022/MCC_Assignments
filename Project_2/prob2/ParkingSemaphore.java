import java.util.concurrent.Semaphore;

class ParkingGarageS {
    private final Semaphore places;

    public ParkingGarageS(int places) {
        if(places < 0) {
            places = 0;
        }

        this.places = new Semaphore(places);
    }

    public void enter() {
        try {
            places.acquire();
        } catch(InterruptedException e) {
            System.err.printf("Err: %s\n", e);
        }
    }

    public void leave() {
        places.release();
    }

    public int getPlaces() {
        return places.availablePermits();
    }
}


class CarS extends Thread {
    private final ParkingGarageS parkingGarage;

    public CarS(String name, ParkingGarageS p) {
        super(name);
        this.parkingGarage = p;
        start();
    }

    private void tryingEnter() {
        System.out.println(getName()+": trying to enter");
    }

    private void justEntered() {
        System.out.println(getName()+": just entered");
    }

    private void aboutToLeave() {
        System.out.println(getName()+":                                     about to leave");
    }

    private void Left() {
        System.out.println(getName()+":                                     have been left");
    }

    public void run() {
        while(true) {
            try {
                sleep((int)(Math.random() * 10000));
            } catch(InterruptedException e) {
                System.err.printf("Err: %s\n", e);
            }

            tryingEnter();
            parkingGarage.enter();
            justEntered();

            try {
                sleep((int)(Math.random() * 20000));
            } catch(InterruptedException e) {
                System.err.printf("Err: %s\n", e);
            }

            aboutToLeave();
            parkingGarage.leave();
            Left();
        }
    }
}

public class ParkingSemaphore {
    public static void main(String[] args) {
        ParkingGarageS parkingGarage = new ParkingGarageS(7);

        for(int i = 1; i <= 10; i++) {
            CarS c = new CarS("Car " + i, parkingGarage);
        }
    }
}

