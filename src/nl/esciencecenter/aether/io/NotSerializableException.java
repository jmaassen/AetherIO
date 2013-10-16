package nl.esciencecenter.aether.io;

class NotSerializableException extends java.io.NotSerializableException {

    private static final long serialVersionUID = 1L;

    public NotSerializableException() {
    }

    public NotSerializableException(String message) {
        super(message);
    }

    public NotSerializableException(Throwable cause) {
        super();
        initCause(cause);
    }

    public NotSerializableException(String message, Throwable cause) {
        super(message);
        initCause(cause);
    }
}
