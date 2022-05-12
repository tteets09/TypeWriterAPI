package Operations;

public abstract class Operation {
    int speed;

    /**
     * <p>If the operation has a speed that it is ran at then
     * it will use this constructor.</p>
     *
     * @param speed The speed the operation is ran at
     */
    public Operation(int speed){
        this.speed = speed;
    }//END CONSTRUCTOR

    /**
     * If the operation does not have speed then
     * this constructor will be used.
     */
    public Operation(){
        this(0);
    }//END CONSTRUCTOR

    /**
     * <p>This method will run the operation</p>
     */
    public abstract void run();
}//END Operation ABSTRACT CLASS
