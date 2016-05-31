package jm.lib.constants;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.AntPathMatcher;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

/**
 * Constant for string process
 * @author jiming liu
 * 
 */
public class StringConstants {

	public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher() {

		@Override
		public void setPathSeparator(String pathSeparator) {
		}
		
	};
	
	@Deprecated
	public static final String SPLIT_LEVEL_FIRST = "||";
    @Deprecated
	public static final String SPLIT_LEVEL_SECOND = "::";
    @Deprecated
	public static final String SPLIT_LEVEL_THIRD = "=";
	
	
	public static final String SPLIT_REGION = ":";
	
	
	public static final List<String> BASIC_TYPE_LIST;
	public static final Set<Class<?>> BASIC_TYPE_SET;
	public static final Map<String, Class<?>> BASIC_TYPE_MAP;
	
	
	static {
		HashMap<String, Class<?>> class_map = new HashMap<String, Class<?>>();
		class_map.put("string", String.class);
		class_map.put("String", String.class);
		class_map.put("java.lang.String", String.class);
	
		class_map.put("bool", Boolean.class);
		class_map.put("boolean", Boolean.class);
		class_map.put("Boolean", Boolean.class);
		class_map.put("java.lang.Boolean", Boolean.class);
		class_map.put("char", Character.class);
		class_map.put("Character", Character.class);
		class_map.put("java.lang.Character", Character.class);
		class_map.put("byte", Byte.class);
		class_map.put("Byte", Byte.class);
		class_map.put("java.lang.Byte", Byte.class);
		class_map.put("short", Short.class);
		class_map.put("Short", Short.class);
		class_map.put("java.lang.Short", Short.class);
		class_map.put("int", Integer.class);
		class_map.put("Integer", Integer.class);
		class_map.put("java.lang.Integer", Integer.class);
		class_map.put("long", Long.class);
		class_map.put("Long", Long.class);
		class_map.put("java.lang.Long", Long.class);
		class_map.put("float", Float.class);
		class_map.put("Float", Float.class);
		class_map.put("java.lang.Float", Float.class);
		class_map.put("double", Double.class);
		class_map.put("Double", Double.class);
		class_map.put("java.lang.Double", Double.class);
		
		class_map.put("date", Date.class);
		class_map.put("Date", Date.class);
		class_map.put("java.sql.Date", Date.class);
		
		class_map.put("timestamp", Timestamp.class);
		class_map.put("Timestamp", Timestamp.class);
		class_map.put("java.sql.Timestamp", Timestamp.class);
		
		BASIC_TYPE_LIST = ImmutableList.copyOf(new String[] {"Boolean", "Character"
				, "Byte", "Short", "Integer", "Long", "Float", "Double", "String"
				, "Date", "Timestamp"});
		BASIC_TYPE_MAP = ImmutableMap.copyOf(class_map);
		BASIC_TYPE_SET = ImmutableSet.copyOf(BASIC_TYPE_MAP.values());
	}
}
