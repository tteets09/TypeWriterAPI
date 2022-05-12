package Operations;

public class Backspace extends Operation{

    private int amount;
    /**
     * <p>Creates a new backspace operation to be ran at
     * later time</p>
     *
     * @param speed The speed the backspace operation will run
     */
    public Backspace(int amount, int speed){
        super(speed);
        this.amount = amount;
    }//END CONSTRUCTOR

    /**
     * <p>Runs the backspace operation</p>
     */
    public void run(){

        for(int i = 0; i < amount; i++){
            try {
                System.out.print("\b \b");
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }//END run METHOD
}//END Backspace CLASS
