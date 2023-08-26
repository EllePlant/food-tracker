package example.data;


/* A trivial exception class for the Movie List program.
 */
@SuppressWarnings("serial")
public class UserException extends Exception {

    public UserException() {
        super();
    }

    public UserException(String message) {
        super(message);
    }

}