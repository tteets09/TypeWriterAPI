import java.util.LinkedList;

/**
 * <p>This class represents a typewriter and can do many
 * operations such as write to the console, delete a
 * specified amount of characters, and delete all the text. You
 * are also allowed to specify when the typewriter goes to the
 * next line and pause it for a certain amount of time too.</p>
 *
 * @author Tony Teets
 * @since May 12, 2022
 * @version 1.0
 */
public class TypeWriter {
    /**
     * A list of the operations that will be done when the
     * run method is called
     */
    public LinkedList<Operation> operations;

    /**
     * The speed at which the typewriter will type (in
     * milliseconds)
     */
    protected int typingSpeed;

    /**
     * The speed at which the typewriter will delete characters
     * (in milliseconds)
     */
    protected int deleteSpeed;

    /**
     * <p>Creates a new typewriter object with the specified typing speed
     * and the specified delete speed</p>
     *
     * @param typingSpeed The speed at which the typewriter will
     *                    type (in milliseconds)
     * @param deleteSpeed The speed at which the typewriter will
     *                    delete characters (in milliseconds)
     */
    public TypeWriter(int typingSpeed, int deleteSpeed){
        this.typingSpeed = typingSpeed;
        this.deleteSpeed = deleteSpeed;
        operations = new LinkedList<>();
    }//END CONSTRUCTOR

    /**
     * <p>Creates a new typewriter object with the specified speed at which
     * it will type and delete characters</p>
     *
     * @param speed The speed at which the typewriter will type
     *              and delete characters (in milliseconds)
     */
    public TypeWriter(int speed){
        this(speed, speed);
    }//END CONSTRUCTOR

    /**
     * <p>Creates a typewriter object that has a set typing speed and
     * deleting speed of 100 milliseconds</p>
     */
    public TypeWriter(){
        this(100);
    }//END CONSTRUCTOR

    /**
     * <p>Sets the typing speed (in milliseconds)</p>
     *
     * @param speed The new speed (in milliseconds)
     */
    public void setTypingSpeed(int speed){
        this.typingSpeed = speed;
    }//END setTypingSpeed METHOD

    /**
     * <p>Sets the deleting speed (in milliseconds)</p>
     *
     * @param speed the new speed (in milliseconds)
     */
    public void setDeleteSpeed(int speed){
        this.deleteSpeed = speed;
    }//END setDeleteSpeed

    /**
     *  <p>Writes the text specified out to the terminal when the run
     *  method is called</p>
     *
     * @param <T> A generic type that will be changed into a string
     * @param text The text that will be written to the terminal
     * @return The TypeWriter in the current state
     */
    public <T> TypeWriter write(T text){
        //Making sure the value passed in is a String
        String output = String.valueOf(text);

        //Creating a new write operation
        Write newWrite = new Write(output, typingSpeed);

        //Adding the operation to the operations LinkedList
        operations.add(newWrite);
        return this;
    }//END write METHOD

    /**
     * <p>Deletes the specified amount of characters in the temrinal
     * when the run method is called</p>
     *
     * @param amount The amount of characters to be removed
     * @return THE TypeWriter in the current state
     */
    public TypeWriter backspace(int amount){

        //Creating a new backspace operation
        Backspace newBackspace = new Backspace(amount, deleteSpeed);

        //Adding the operation to the operations LinkedList
        operations.add(newBackspace);
        return this;
    }//END backSpace METHOD

    /**
     * <p>Deletes all of the text in terminal when the run method
     * is called</p>
     *
     * @return The TypeWriter in its current state
     */
    public TypeWriter deleteAll(){

        //Creating a new deleteAll operation
        DeleteAll newDeleteAll = new DeleteAll();

        //Adding the operation to the operations LinkedList
        operations.add(newDeleteAll);
        return this;
    }//END deleteAll METHOD

    /**
     * <p>Moves the typewriter to the next line when the run method
     * is called</p>
     *
     * @return The TypeWriter in its current state
     */
    public TypeWriter nextLine(){
        NextLine nextLine = new NextLine();

        operations.add(nextLine);

        return this;
    }//END nextLine METHOD

    /**
     * <p>Pauses the typewriter for a specified amount of time
     * when the run method is called</p>
     *
     * @param amount The amount of time to pause for
     * @return The TypeWriter in its current state
     */
    public TypeWriter pause(int amount){
        Pause newPause = new Pause(amount);

        operations.add(newPause);
        return this;
    }//END pause METHOD

    /**
     * <p>Will run all of the operations of the TypeWriter when
     * called</p>
     */
    public void run(){
        //Clear the terminal
        System.out.print("\033[H\033[2J");

        //Runs all the operations
        for(Operation operation : operations){
            operation.run();
        }
    }//END run METHOD
}//END TypeWriter OUTER-CLASS

/**
 * <p>This class represents an operation that the
 * typewriter is allowed to do.</p>
 *
 * @author Tony Teets
 * @since May 12, 2022
 */
abstract class Operation{
    /** The speed at which the operation will run */
    protected final int speed;

    /**
     * <p>If the operation as a speed at which it will run
     * then this constructor will be used</p>
     *
     * @param speed The speed at which the operation is ran
     */
    public Operation(int speed){
        this.speed = speed;
    }//END CONSTRUCTOR

    /**
     * <p>If the operation does not have a speed at which it will
     * run then this constructor will be used.</p>
     */
    public Operation(){
        this(0);
    }//END CONSTRUCTOR

    /**
     * <p>Runs the operation</p>
     */
    public abstract void run();
}//END Operation ABSTRACT INNER-CLASS

/**
 * <p>The write operation writes out the specified text
 * out to the terminal</p>
 *
 * @author Tony Teets
 * @since May 12, 2022
 */
class Write extends Operation{

    /** The text that is being printed out by the typewriter*/
    String text;

    /**
     * <p>Creates a write operation to be ran at a later
     * time</p>
     *
     * @param text The text to be used in the operation
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

/**
 * <p>The Backspace operation does a backspace for the
 * amount of times specified.</p>
 *
 * @author Tony Teets
 * @since May 12, 2022
 */
class Backspace extends Operation{
    /**
     * The amount of characters to be deleted
     */
    private int amount;

    /**
     * <p>Creates a backspace operation to be ran at a
     * later time</p>
     * @param amount The amount of characters to delete
     * @param speed The speed at which the characters are deleted
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
                Thread.sleep(this.speed);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }//END run METHOD
}//END Backspace INNER-CLASS

/**
 * <p>The DeleteAll operation deletes all of the text
 * that has been typed out into the terminal.</p>
 *
 * @author Tony Teets
 * @since May 12, 2022
 */
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

/**
 * <p>The NextLine operation moves the typewriter to the
 * next line.</p>
 *
 * @author Tony Teets
 * @since May 12, 2022
 */
class NextLine extends Operation{

    /**
     * <p>Runs the nextLine operation</p>
     */
    public void run(){
        System.out.println();
    }//END run METHOD
}//END NextLine INNER-CLASS

/**
 * <p>The Pause operation pauses the typewriter for
 * the amount of time that is specified.</p>
 *
 * @author Tony Teets
 * @since May 12, 2022
 */
class Pause extends Operation{

    /**
     * <p>Pauses the typewriter for the amount
     * of time specified</p>
     *
     * @param amount The amount of time to pause for
     */
    public Pause(int amount) {
        super(amount);
    }//END CONSTRUCTOR

    /**
     * <p>Runs the pause operation</p>
     */
    @Override
    public void run() {
        try {
            Thread.sleep(this.speed);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }//END run METHOD
}//END Pause CLASS
