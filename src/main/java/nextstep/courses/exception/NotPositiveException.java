package nextstep.courses.exception;

public class NotPositiveException extends IllegalArgumentException {
    private static final long serialVersionUID = 1L;

    public NotPositiveException(String message) {
        super(message);
    }
}
