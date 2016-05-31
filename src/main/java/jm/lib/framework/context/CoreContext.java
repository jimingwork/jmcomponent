package jm.lib.framework.context;


/**
 *
 * @param <R> ResourceFactory type
 */
@Deprecated  // not sure
public interface CoreContext<R> {

    /**
     * host name of the server. code: InetAddress.getLocalHost().getHostName()
     * @return
     */
    String getBoxName();


    R getResourceFactory();

//    S getServiceFactory();


}
