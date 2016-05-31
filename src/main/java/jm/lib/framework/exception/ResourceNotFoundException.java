package jm.lib.framework.exception;

/**
 * 
 * @author Jiming
 *
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 730014469027797450L;
    
    private String resourceName;


    public ResourceNotFoundException(String resourceName) {
        super();
        this.resourceName = resourceName;
    }


    public ResourceNotFoundException(String resourceName, String message) {
        super(message);
        this.resourceName = resourceName;
    }

    
    public ResourceNotFoundException(String resourceName, Throwable cause) {
        super(cause);
        this.resourceName = resourceName;
    }

    public ResourceNotFoundException(String resourceName, String message, Throwable cause) {
        super(message, cause);
        this.resourceName = resourceName;
    }


    public String getResourceName() {
        return resourceName;
    }


    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    
    
}
