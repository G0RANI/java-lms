package nextstep.courses.exception.exception;

public class IncorrectDateException extends IllegalArgumentException {
    private static final long serialVersionUID = 1L;

    public IncorrectDateException(String message) {
        super(message);
    }
}
