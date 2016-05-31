package jm.lib.framework.context;

/**
 * Created by jiming.liu
 */
public class CacheSetting {
    /**
     * 获取一些生命周期短的相关的 cache 项的 ttl (18s)
     * @return
     */
    public static int getTinyTermTtlInSeconds() {
        return Math.max(1, (int)(Contexts.getConfigAttrs().attrAsInt("cache.Tinyterm.TTL", 18) * Contexts.getWeightLevelTwo()));
    }


    /**
     * 获取一些生命周期短的相关的 cache 项的 ttl (180s)
     * @return
     */
    public static int getShortTermTtlInSeconds() {
        return Math.max(1, (int)(Contexts.getConfigAttrs().attrAsInt("cache.Shortterm.TTL", 180) * Contexts.getWeightLevelTwo()));
    }


    /**
     * 获取一些生命周期中等的相关的 cache 项的 ttl (1800s)
     * @return
     */
    public static int getMediumTermTtlInSeconds() {
        return Math.max(1, Contexts.getConfigAttrs().attrAsInt("cache.Mediumterm.TTL", 1800));
    }


    /**
     * 获取一些生命周期较长的相关的 cache 项的 ttl (18000s, 5 hours)
     * @return
     */
    public static int getLongTermTtlInSeconds() {
        return Math.max(1, Contexts.getConfigAttrs().attrAsInt("cache.Longterm.TTL", 18000));
    }

    /**
     * 获取一些生命周期很长的相关的 cache 项的 ttl (172800s, two days)
     * @return
     */
    public static int getVeryLongTermTtlInSeconds() {
        return Math.max(1, Contexts.getConfigAttrs().attrAsInt("cache.VeryLongterm.TTL", 172800));
    }


}
