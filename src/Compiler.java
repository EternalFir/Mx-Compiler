import Utility.errors.*;

public class Compiler {
    public static void main(String[] args) throws Exception {
        try {

        } catch (basicError error) {
            System.err.println(error.intoString());
            throw new RuntimeException();
        }

    }

}