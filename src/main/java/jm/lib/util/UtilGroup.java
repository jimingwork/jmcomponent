package jm.lib.util;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * Created by jiming.liu
 */
public class UtilGroup {

    public static final char SEPERATOR_LIST = ',';
    public static final String SEPERATOR_MAP_ENTRY = ":";
    public static final char SEPERATOR_MULTIVALUE = '|';

    private static final Splitter SPLITTER_ByCommaNoEmpty = Splitter.on(SEPERATOR_LIST).omitEmptyStrings().trimResults();
    public static List<String> splitToList(String s) {
        return SPLITTER_ByCommaNoEmpty.splitToList(s);
    }

    private static final Splitter SPLITTER_ByMULTIVALUE_NoEmpty = Splitter.on(SEPERATOR_MULTIVALUE).omitEmptyStrings().trimResults();
    private static final Splitter SPLITTER_ByColonNoEmpty = Splitter.on(':').omitEmptyStrings().trimResults();
    private static final Splitter SPLITTER_ByColonNoEmpty_limit2 = Splitter.on(':').omitEmptyStrings().limit(2).trimResults();
    private static final Splitter.MapSplitter MAPSPLITTER_ByCommaNoEmpty = Splitter.on(SEPERATOR_LIST)
            .trimResults().omitEmptyStrings()
            .withKeyValueSeparator(SPLITTER_ByColonNoEmpty_limit2);

    /**
     *
     * @param s
     * @return
     * @throws IllegalArgumentException if the specified sequence does not split
     *         into valid map entries, or if there are duplicate keys
     */
    public static Map<String, String> splitToMap(String s) {
        return MAPSPLITTER_ByCommaNoEmpty.split(s);
    }

    /**
     *
     * @param s
     * @return
     * @throws IllegalArgumentException if the specified sequence does not split
     *         into valid map entries, or if there are duplicate keys
     */
    public static Map<String, String> splitToLinkedHashMap(String s) {
        if(StringUtils.isBlank(s)) return Collections.EMPTY_MAP;
        LinkedHashMap<String, String> result = new LinkedHashMap<String, String>();
        Iterable<String> lines = SPLITTER_ByCommaNoEmpty.split(s);
        for (String line : lines) {
            List<String> kv = SPLITTER_ByColonNoEmpty_limit2.splitToList(line);
            if (kv.size() != 2) {
                throw new IllegalArgumentException("Chunk [" + line + "] is not a valid entry");
            }
            result.put(kv.get(0), kv.get(1));

        }
        return result;
    }


//    private static final Splitter SPLITTER_BySemicolonNoEmpty = Splitter.on(';').omitEmptyStrings().trimResults();

    private static final Splitter SPLITTER_ByOrNoEmpty = Splitter.on('|').omitEmptyStrings().trimResults();
    /**
     *
     * @param s
     * @return
     * @throws IllegalArgumentException if the specified sequence does not split
     *         into valid map entries, or if there are duplicate keys
     */
    public static ListMultimap<String, String> splitToListMultiMap(String s) {
        Map<String, String> split = splitToMap(s);
        if(MapUtils.isEmpty(split)) return ImmutableListMultimap.of();

        ListMultimap<String, String> result = ArrayListMultimap.create(split.size(), 3);
        for (Map.Entry<String, String> entry : split.entrySet()) {
            result.putAll(entry.getKey(), SPLITTER_ByOrNoEmpty.splitToList(entry.getValue()));
        }
        return result;
    }

    public static SetMultimap<String, String> splitToSetMultiMap(String s) {
        Map<String, String> split = splitToMap(s);
        if(MapUtils.isEmpty(split)) return ImmutableSetMultimap.of();

        SetMultimap<String, String> result = LinkedHashMultimap.create(split.size(), 3);
        for (Map.Entry<String, String> entry : split.entrySet()) {
            result.putAll(entry.getKey(), SPLITTER_ByOrNoEmpty.splitToList(entry.getValue()));
        }
        return result;
    }

    public static List<List<String>> splitToListList(String s) {
        List<String> lines = SPLITTER_ByCommaNoEmpty.splitToList(s);
        if(lines.isEmpty()) return Collections.EMPTY_LIST;
        ArrayList<List<String>> result = new ArrayList<List<String>>(lines.size());
        for (String line : lines) {
            result.add(SPLITTER_ByMULTIVALUE_NoEmpty.splitToList(line));
        }

        return result;
    }

    public static String join(Multimap<String,String> m) {
        if(null==m || m.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        Map<String, Collection<String>> maps = m.asMap();
        for (Map.Entry<String, Collection<String>> entry : maps.entrySet()) {
            sb.append(entry.getKey()).append(':');
            for (String s : entry.getValue()) {
                sb.append(s).append(SEPERATOR_MULTIVALUE);
            }
            sb.setCharAt(sb.length() - 1, ',');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }


    private static final Joiner JOINER_WithCommaSkipNulls = Joiner.on(SEPERATOR_LIST).skipNulls();
    private static final Joiner.MapJoiner MAPJOINER_WithComma = Joiner.on(SEPERATOR_LIST).withKeyValueSeparator(SEPERATOR_MAP_ENTRY);

    public static String join(Collection c) {
        if(CollectionUtils.isEmpty(c)) return "";
        return JOINER_WithCommaSkipNulls.join(c);
    }

    public static String join(Map m) {
        if(MapUtils.isEmpty(m)) return "";
        return MAPJOINER_WithComma.join(m);
    }
}
