
class ParkingGarageBQ {
    private int places;

    public ParkingGarageBQ(int places) {
        if(places < 0) {
            places = 0;
        }

        this.places = places;
    }

    public synchronized void enter() {
        while(places == 0) {
            try {
                wait();
            } catch(InterruptedException e) {}
        }
        places--;
    }

    public synchronized void leave() {
        places++;
        notify();
    }

    public synchronized int getPlaces() {
        return places;
    }
}


class CarBQ extends Thread {
    private ParkingGarageBQ parkingGarage;

    public CarBQ(String name, ParkingGarageBQ p) {
        super(name);
        this.parkingGarage = p;
        start();
    }

    private void tryingEnter() {
        System.out.printf("%s: trying to enter\n", getName());
    }


    private void justEntered() {
        System.out.printf("%s: just entered\n", getName());
    }

    private void aboutToLeave() {
        System.out.printf("%s:                                     about to leave\n", getName());
    }

    private void Left() {
        System.out.printf("%s:                                     have been left\n", getName());
    }

    public void run() {
        while(true) {
            try {
                sleep((int)(Math.random() * 10000));
            } catch(InterruptedException e) {}

            tryingEnter();
            parkingGarage.enter();
            justEntered();

            try {
                sleep((int)(Math.random() * 20000));
            } catch(InterruptedException e) {}

            aboutToLeave();
            parkingGarage.leave();
            Left();
        }
    }
}


public class ParkingBlockingQueue {
    public static void main(String[] args) {
        ParkingGarageBQ parkingGarage = new ParkingGarageBQ(7);
        
        for(int i = 1; i <= 10; i++) {
            CarBQ c = new CarBQ("Car " + i, parkingGarage);
        }
    }
}

