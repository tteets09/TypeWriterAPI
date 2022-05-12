package Operations;

public class Write extends Operation{

    String text;
    /**
     * <p>Creates a write operation to be ran at a later
     * time</p>
     *
     * @param speed The speed the operation will write at
     */
    public Write(String text, int speed){
        super(speed);
        this.text = text;
    }//END CONSTRUCTOR

    /**
     * <p>Runs the write operation</p>
     */
    @Override
    public void run() {
        char[] textCharacters = text.toCharArray();

        for(char character : textCharacters){
            try {
                System.out.print(character);
                Thread.sleep(this.speed);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }//END run METHOD
}//END Write CLASS
