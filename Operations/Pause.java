package Operations;

public class Pause extends Operation{

    public Pause(int amount) {
        super(amount);
    }//END CONSTRUCTOR

    @Override
    public void run() {
        try {
            Thread.sleep(this.speed);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }//END run METHOD
}//END Pause CLASS
