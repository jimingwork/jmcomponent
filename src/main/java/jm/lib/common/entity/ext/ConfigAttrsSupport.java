package jm.lib.common.entity.ext;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.SetMultimap;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by jiming.liu
 */
public interface ConfigAttrsSupport {
    String attr(String key, String default_value);
    boolean attrAsBool(String key, boolean default_value);
    int attrAsInt(String key, int default_value);
    long attrAsLong(String key, long default_value);
    float attrAsFloat(String key, float default_value);

    Set<Integer> attrAsSetInt(String key, Set<Integer> default_value);
    Set<String> attrAsSet(String key, Set<String> default_value);

    List<Integer> attrAsListInt(String key, List<Integer> default_value);
    List<String> attrAsList(String key, List<String> default_value);

    Map<String, String> attrAsMap(String key, Map<String, String> default_value);
    SetMultimap<String, String> attrAsSetMultimap(String key, SetMultimap<String, String> default_value);
    ListMultimap<String, String> attrAsListMultimap(String key, ListMultimap<String, String> default_value);
    List<List<String>> attrAsListList(String key, List<List<String>> default_value);

    void addAttrs(Map<String, String> attrs, long lastUpdateTime);


    void reset(Collection<String> keys);
    void resetAll();


//    <T> T attrAsObject(String key, Class<T> clazz, T default_value);
    // TODO: later, ${SpEL} and #{SpEL}
//    <T> T attrAsExpr(String key, T default_value);
//    <T> T attrAsExpr(String key, Object params, T default_value);

}
