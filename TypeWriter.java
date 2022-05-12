import java.util.LinkedList;

public class TypeWriter {
    public LinkedList<Operation> operations;
    protected int typingSpeed;
    protected int deleteSpeed;

    public TypeWriter(int typingSpeed, int deleteSpeed){
        this.typingSpeed = typingSpeed;
        this.deleteSpeed = deleteSpeed;
        operations = new LinkedList<>();
    }//END CONSTRUCTOR

    public TypeWriter(int typingSpeed){
        this(typingSpeed, typingSpeed);
    }//END CONSTRUCTOR

    public TypeWriter(){
        this(50);
    }//END CONSTRUCTOR

    public void setTypingSpeed(int speed){
        this.typingSpeed = speed;
    }//END setTypingSpeed METHOD

    public void setDeleteSpeed(int speed){
        this.deleteSpeed = speed;
    }//END setDeleteSpeed

    public <T> TypeWriter write(T text){
        //Making sure the value passed in is a String
        String output = String.valueOf(text);

        //Creating a new write operation
        Write newWrite = new Write(output, typingSpeed);

        //Adding the operation to the operations LinkedList
        operations.add(newWrite);
        return this;
    }//END write METHOD

    public TypeWriter backspace(int amount){

        //Creating a new backspace operation
        Backspace newBackspace = new Backspace(amount, deleteSpeed);

        //Adding the operation to the operations LinkedList
        operations.add(newBackspace);
        return this;
    }//END backSpace METHOD

    public TypeWriter deleteAll(){

        //Creating a new deleteAll operation
        DeleteAll newDeleteAll = new DeleteAll();

        //Adding the operation to the operations LinkedList
        operations.add(newDeleteAll);
        return this;
    }//END deleteAll METHOD

    public TypeWriter nextLine(){
        NextLine nextLine = new NextLine();

        operations.add(nextLine);

        return this;
    }//END nextLine METHOD

    public TypeWriter pause(int amount){
        Pause newPause = new Pause(amount);

        operations.add(newPause);
        return this;
    }//END pause METHOD

    public void run(){

        System.out.print("\033[H\033[2J");

        for(Operation operation : operations){
            operation.run();
        }

        System.out.println("Finished operations...");
    }//END run METHOD


}//END TypeWriter OUTER-CLASS

abstract class Operation{
    protected final int speed;

    public Operation(int speed){
        this.speed = speed;
    }

    public Operation(){
        this(0);
    }

    public abstract void run();
}//END Operation ABSTRACT INNER-CLASS

class Write extends Operation{
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
}//END Write INNER-CLASS

class Backspace extends Operation{
    /**
     * <p>Creates a backspace operation to be ran at a later
     * time</p>
     *
     * @param speed The speed the operation will delete at
     */
    private int amount;

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
                Thread.sleep(this.speed);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }//END run METHOD
}//END Backspace INNER-CLASS

class DeleteAll extends Operation{
    /**String that clears the terminal when printed*/
    private final static String clearANSI = "\033[H\033[2J";

    /**
     * <p>Runs the deleteAll operation</p>
     */
    public void run(){
        try {
            System.out.println("\n");
            Thread.sleep(200);
            System.out.println("Deleting all text... Please wait...");
            Thread.sleep(2000);
            System.out.print(clearANSI);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }//END run METHOD
}//END DeleteAll INNER-CLASS

class NextLine extends Operation{

    public void run(){
        System.out.println();
    }//END run METHOD
}//END NextLine INNER-CLASS

class Pause extends Operation{

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
