package jm.lib.framework.context;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import jm.lib.common.entity.ext.ConfigAttrsSupport;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Created by jiming.liu
 */
@ManagedResource(objectName = "global.Contexts", description = "", value = "")
public final class Contexts implements InitializingBean, ApplicationContextAware, ApplicationEventPublisherAware {

    public static final Set<Integer> ALLOWED_PAGE_SIZE = ImmutableSortedSet.of(2, 5, 10, 15, 20, 15, 100, 500);

    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher() {



        @Override
        public void setPathSeparator(String pathSeparator) { }

        @Override
        public void setCaseSensitive(boolean caseSensitive) { }

        @Override
        public void setTrimTokens(boolean trimTokens) { }

        @Override
        public void setCachePatterns(boolean cachePatterns) {
            super.setCachePatterns(true);
        }
    };

    public static final Random r = new Random();

    private static String configAttrsBeanName;

    private static CoreContext core;

    private static ApplicationContext applicationContext;
    private static WebApplicationContext webApplicationContext;
    private static ServletContext servletContext;

    private static ApplicationEventPublisher applicationEventPublisher;

    private static ConfigAttrsSupport configAttrs;


    private static Set<Integer> allowedPageSize;

//    @Autowired
//    private InfoEncryptClient infoEncryptClient;
//    @Autowired
//    private InfoDecryptClient infoDecryptClient;
//    @Autowired
//    private IdEncryptClient idEncryptClient;
//    @Autowired
//    private IdDecryptClient idDecryptClient;


    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public WebApplicationContext getWebApplicationContext() {
        return webApplicationContext;
    }

    public void setWebApplicationContext(WebApplicationContext webApplicationContext) {
        Contexts.webApplicationContext = webApplicationContext;
    }

    public ServletContext getServletContext() {
        return Contexts.servletContext;
    }

    public static String getConfigAttrsBeanName() {
        return configAttrsBeanName;
    }

    public void setConfigAttrsBeanName(String configAttrsBeanName) {
        Contexts.configAttrsBeanName = configAttrsBeanName;
    }

    public static <R> CoreContext<R> getCore(Class<R> clazz) {
        return Contexts.core;
    }

    public void setCore(CoreContext coreContext) {
        Contexts.core = coreContext;
    }

    public static ConfigAttrsSupport getConfigAttrs() {
        return configAttrs;
    }

    public static void setConfigAttrs(ConfigAttrsSupport configAttrs) {
        Contexts.configAttrs = configAttrs;
    }

    public static Set<Integer> getAllowedPageSize() {
        return Contexts.allowedPageSize;
    }

    public void setAllowedPageSize(Set<Integer> allowedPageSize) {
        Contexts.allowedPageSize = ImmutableSortedSet.copyOf(allowedPageSize);
    }


    /**
     * 用于系统优雅升级或降级，控制核心业务
     * @return
     */
    @ManagedAttribute(description = "global.Contexts.weight.level.one")
    public static float getWeightLevelOne() {
        return getConfigAttrs().attrAsFloat("context.weight.level.one", 1.0f);
    }


    /**
     * 用于系统优雅升级或降级，控制较低优先级业务
     * @return
     */
    @ManagedAttribute(description = "global.Contexts.weight.level.two")
    public static float getWeightLevelTwo() {
        return getConfigAttrs().attrAsFloat("context.weight.level.two", 1.0f);
    }


    /**
     * 用于系统优雅升级或降级，控制非必要业务
     * @return
     */
    @ManagedAttribute(description = "global.Contexts.weight.level.three")
    public static float getWeightLevelThree() {
        return getConfigAttrs().attrAsFloat("context.weight.level.three", 1.0f);
    }





    /**
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        if(webApplicationContext == null) return null;
        return webApplicationContext.getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        if(webApplicationContext == null) return null;
        return webApplicationContext.getBean(name, clazz);
    }
    /**
     * 获取所有这个类的实例
     *
     * @param clazz 注册的 bean类型
     * @param <T>
     * @return
     */
    public static <T> Map<String, T> getBeanList(Class<T> clazz) {
        if(webApplicationContext == null) return null;
        Map<String, T> beansOfType = webApplicationContext.getBeansOfType(clazz);
        if(MapUtils.isEmpty(beansOfType)) return Collections.EMPTY_MAP;
        return beansOfType;
    }

    /**
     * Invoked by a BeanFactory after it has set all bean properties supplied
     * (and satisfied BeanFactoryAware and ApplicationContextAware).
     * <p>This method allows the bean instance to perform initialization only
     * possible when all bean properties have been set and to throw an
     * exception in the event of misconfiguration.
     *
     * @throws Exception in the event of misconfiguration (such
     *                   as failure to set an essential property) or if initialization fails.
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        Contexts.core = Contexts.applicationContext.getBean(CoreContext.class);
        Assert.notNull(Contexts.core, "Contexts.coreContext is necessary.");

        if (StringUtils.isBlank(Contexts.configAttrsBeanName)) {
            Contexts.configAttrs = Contexts.applicationContext.getBean(ConfigAttrsSupport.class);
        } else {
            Contexts.configAttrs = Contexts.applicationContext.getBean(configAttrsBeanName, ConfigAttrsSupport.class);
        }
        if (null != webApplicationContext) {
            Contexts.servletContext = webApplicationContext.getServletContext();
        }

        Assert.notNull(Contexts.configAttrs, "Contexts.configAttrs is necessary.");

        if (null == Contexts.allowedPageSize) {
            Contexts.allowedPageSize = ALLOWED_PAGE_SIZE;
        }

        PATH_MATCHER.setCachePatterns(true);

    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Contexts.applicationContext = applicationContext;
        if (applicationContext instanceof WebApplicationContext) {
            Contexts.webApplicationContext = (WebApplicationContext) applicationContext;
        }
    }


    public static ApplicationEventPublisher getApplicationEventPublisher() {
        return applicationEventPublisher;
    }

    /**
     * Set the ApplicationEventPublisher that this object runs in.
     * <p>Invoked after population of normal bean properties but before an init
     * callback like InitializingBean's afterPropertiesSet or a custom init-method.
     * Invoked before ApplicationContextAware's setApplicationContext.
     *
     * @param applicationEventPublisher event publisher to be used by this object
     */
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        Contexts.applicationEventPublisher = applicationEventPublisher;
    }
}
