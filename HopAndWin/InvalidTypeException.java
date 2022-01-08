package HopAndWin;

public class InvalidTypeException extends RuntimeException {

    public InvalidTypeException(String message) {
        super(message);
    }
}

class InvalidInteger extends InvalidTypeException {
    public InvalidInteger(String message) {
        super(message);
    }
}

class DividebyZeroException extends RuntimeException {

    public DividebyZeroException(String message) {
        super(message);
    }
}
