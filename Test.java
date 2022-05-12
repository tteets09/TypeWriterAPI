public class Test {
    public static void main(String[] args) {
        TypeWriter typeWriter = new TypeWriter(100, 100);

        typeWriter.write("Something is happening omg")
                .backspace(26)
                .write("Sorry I was meaning for thes...")
                .backspace(5)
                .write("ws...")
                .backspace(5)
                .write("ds.")
                .backspace(3)
                .write("is...")
                .pause(1000)
                .nextLine()
                .write("Wow this is really cool isn't it?")
                .pause(300)
                .nextLine()
                .write("I am over it...")
                .deleteAll()
                .run();
    }
}//END Main CLASS