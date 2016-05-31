/**
 * Create at 2008-11-21 by jiming.liu
 * $Id$
 */

package jm.lib.framework.exception;

/**
 * A NOTICE for need release the using resource, such as JDBC connection release, ThreadLocal set/clear
 * @author jiming.liu
 *
 */
public class MustReleaseException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 6847863091564907822L;

    public MustReleaseException() {
        super();
    }

    public MustReleaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public MustReleaseException(String message) {
        super(message);
    }

    public MustReleaseException(Throwable cause) {
        super(cause);
    }



}
