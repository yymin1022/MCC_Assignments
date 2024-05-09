
class ParkingGarageS {
    private int places;

    public ParkingGarageS(int places) {
        if(places < 0){
            places = 0;
        }

        this.places = places;
    }

    public synchronized void enter() {
        while (places == 0) {
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


class CarS extends Thread {
    private ParkingGarageS parkingGarage;

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


public class ParkingSemaphore {
    public static void main(String[] args) {
        ParkingGarageS parkingGarage = new ParkingGarageS(7);
        for(int i = 1; i <= 10; i++) {
            CarS c = new CarS("Car " + i, parkingGarage);
        }
    }
}

