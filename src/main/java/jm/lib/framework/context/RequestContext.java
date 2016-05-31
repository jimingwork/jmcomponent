package jm.lib.framework.context;

import lombok.Data;

/**
 * Created by jiming.liu on 2016/4/15.
 */
@Data
public class RequestContext {

    private static ThreadLocal<RequestContext> context = new ThreadLocal<RequestContext>() {
        @Override
        protected RequestContext initialValue() {
            return new RequestContext();
        }
    };

    private Integer userId;
    private String userName;
    private String userDisplayName;
//    private String userAgent;
    /**
     * This session is not Servlet http session, it's a distributed session.
     */
    private String sessionId;



    public static Integer getUserId() {
        return context.get().getUserId();
    }

    public static void setUserId(Integer userId) {
        context.get().setUserId(userId);
    }

    public static String getUserName() {
        return context.get().getUserName();
    }

    public static void setUserName(String userName) {
        context.get().setUserName(userName);
    }

    public static String getUserDisplayName() {
        return context.get().getUserDisplayName();
    }

    public static void setUserDispalyName(String userName) {
        context.get().setUserDisplayName(userName);
    }

    public static String getSessionId() {
        return context.get().getSessionId();
    }

    public static void setSessionId(String sessionId) {
        context.get().setSessionId(sessionId);
    }





}
