package Operations;

public class DeleteAll extends Operation{
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
}//END DeleteAll CLASS
