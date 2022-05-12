import Operations.*;

import java.util.LinkedList;

public class TypeWriter {
    public static LinkedList<Operation> operations;
    private static int typingSpeed;
    private static int deleteSpeed;

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


}//END TypeWriter CLASS
