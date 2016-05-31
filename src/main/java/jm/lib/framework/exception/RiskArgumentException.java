package jm.lib.framework.exception;

import lombok.Data;

/**
 * Created by jiming.liu on 2016/4/21.
 */
@Data
public class RiskArgumentException extends IllegalArgumentException implements HumanException {

    private String humanMessage;


    /**
     * Constructs an <code>IllegalArgumentException</code> with no
     * detail message.
     */
    public RiskArgumentException() {
        super();
    }

    public RiskArgumentException(String humanMessage) {
        super(humanMessage);
        this.setHumanMessage(humanMessage);
    }

    /**
     * Constructs an <code>IllegalArgumentException</code> with the
     * specified detail message.
     *
     * @param s the detail message.
     */
    public RiskArgumentException(String humanMessage, String s) {
        super(s);
        this.setHumanMessage(humanMessage);
    }

    /**
     * Constructs a new exception with the specified detail message and
     * cause.
     * <p/>
     * <p>Note that the detail message associated with <code>cause</code> is
     * <i>not</i> automatically incorporated in this exception's detail
     * message.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link Throwable#getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link Throwable#getCause()} method).  (A <tt>null</tt> value
     *                is permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     * @since 1.5
     */
    public RiskArgumentException(String humanMessage, String message, Throwable cause) {
        super(message, cause);
        this.setHumanMessage(humanMessage);
    }

    /**
     * Constructs a new exception with the specified cause and a detail
     * message of <tt>(cause==null ? null : cause.toString())</tt> (which
     * typically contains the class and detail message of <tt>cause</tt>).
     * This constructor is useful for exceptions that are little more than
     * wrappers for other throwables (for example, {@link
     * java.security.PrivilegedActionException}).
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link Throwable#getCause()} method).  (A <tt>null</tt> value is
     *              permitted, and indicates that the cause is nonexistent or
     *              unknown.)
     * @since 1.5
     */
    public RiskArgumentException(String humanMessage, Throwable cause) {
        super(cause);
        this.setHumanMessage(humanMessage);
    }

}
