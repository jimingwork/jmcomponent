/*
 * StrUtil.java
 *
 * Created on 2004��10��9��, ����1:04
 */

package jm.lib.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.*;
import java.util.regex.Pattern;

import jm.lib.Global;
import jm.lib.common.entity.Idable;
import jm.lib.common.entity.Nameable;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;


/**
 *
 * @author Jiming Liu
 */
public class StrUtil {

	static final Logger logger = LoggerFactory.getLogger(StrUtil.class);


    static final char[] cArr = {
            '0','1','2','3','4'
            ,'5','6','7','8','9'
            ,'A','B','C','D','E','F','G'
            ,'H','I','J','K','L','M','N'
            ,'O','P','Q','R','S','T'
            ,'U','V','W','X','Y','Z'
            ,'a','b','c','d','e','f','g'
            ,'h','i','j','k','l','m','n'
            ,'o','p','q','r','s','t'
            ,'u','v','w','x','y','z'
            //, '_', '.'
    };
    public static final int MAX_RADIX = cArr.length;

    private static final int assessBufLen(int len){
        return Math.max(512, len + len / 8);
    }


    public static final String NUMBER = "0123456789";
    public static final String UPPERCASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWERCASE_CHARS = "abcdefghijklmnopqrstuvwxyz";
    public static final String NUMBER_AND_CHARS = NUMBER + UPPERCASE_CHARS + LOWERCASE_CHARS;
    
    public static final String VERIFY_CHARS = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefhjkmnprstuvwxyz2345678";
    public static final String DIGITS_CHARS = "0123456789";
    public static final String DIGITS_HEX_CHARS = "0123456789abcdef";
    public static final String OTHER_CHARS = " !#$%&'()*+,-./:;<=>?@[]^_`{|}~\\\"";
    static public final String PASSWORD_CHARS =
            UPPERCASE_CHARS + LOWERCASE_CHARS + DIGITS_CHARS
            + OTHER_CHARS.replaceAll("[ \\\\\"]", "");
    static public final String WHITESPACE_CHAR = " \r\n\t\f";




    /**
     * 这个常量是用来插入 oracle 的空字符串的,因为 oracle 认为空字符串等于 NULL
     */
    public static final String ONE_SPACE = " ";


    public static final String UTF8 = "UTF-8";

    public static final String concat(String ...values) {
        return append(values);
    }
    
    /**
	 *
	 * @param values
	 * @return
	 */
	public static final String append(String ... values) {
		StringBuilder sb = new StringBuilder();
		append(sb, values);
		return sb.toString();
	}

	public static void append(StringBuilder sb, CharSequence ... values) {
//		if (null == values)
//			return;
//		int len = values.length;
//		if (0 == len)
//			return;
//		for (int i = 0; i < len; i++) {
//			sb.append(values[i]);
//		}
		for(CharSequence s: values) {
			if(s!=null) sb.append(s);
		}
	}

    /**
	 *
	 * @param values
	 * @return
	 */
	public static final String append(Object ... values) {
		StringBuilder sb = new StringBuilder();
		append(sb, values);
		return sb.toString();
	}

	public static void append(StringBuilder sb, Object ... values) {
//		if (null == values)
//			return;
//		int len = values.length;
//		if (0 == len)
//			return;
//		for (int i = 0; i < len; i++) {
//			sb.append(values[i]);
//		}
		for(Object s: values) {
		    if(s!=null) sb.append(s);
		}
	}

    public static String join(char delimiter, byte ... values) {
        if(null == values) return "";
        int len = values.length;
        if(0 == len) return "";
        if(1 == len){
            return String.valueOf(values[0]);
        }
        StringBuilder sb = new StringBuilder(len*3);
        sb.append(values[0]);
        for(int i = 1; i < len; i++){
            sb.append(delimiter).append(values[i]);
        }
        return sb.toString();
    }

    public static String join(char delimiter, short ... values) {
        if(null == values) return "";
        int len = values.length;
        if(0 == len) return "";
        if(1 == len){
            return String.valueOf(values[0]);
        }
        StringBuilder sb = new StringBuilder(len*3);
        sb.append(values[0]);
        for(int i = 1; i < len; i++){
            sb.append(delimiter).append(values[i]);
        }
        return sb.toString();
    }

	public static String join(char delimiter, int ... values) {
        if(null == values) return "";
        int len = values.length;
        if(0 == len) return "";
        if(1 == len){
            return String.valueOf(values[0]);
        }
        StringBuilder sb = new StringBuilder(len*4);
        sb.append(values[0]);
        for(int i = 1; i < len; i++){
            sb.append(delimiter).append(values[i]);
        }
        return sb.toString();
	}

	public static String join(char delimiter, long ... values) {
        if(null == values) return "";
        int len = values.length;
        if(0 == len) return "";
        if(1 == len){
            return String.valueOf(values[0]);
        }
        StringBuilder sb = new StringBuilder(len*5);
        sb.append(values[0]);
        for(int i = 1; i < len; i++){
            sb.append(delimiter).append(values[i]);
        }
        return sb.toString();
	}

    /**
	 * Join an array of Strings together.
	 *
	 * @param delimiter
	 *            Token to place between Strings.
	 * @param values
	 *            Array of Strings to join.
	 * @return String presentation of joined Strings.
	 *
	 */
    public static String join(char delimiter, CharSequence ... values){
        if(null == values) return "";
        int len = values.length;
        if(0 == len) return "";
        if(1 == len){
            return toString(values[0]);
        }
        StringBuilder sb = new StringBuilder(assessBufLen(24 * values.length));
        sb.append(values[0]);
        for(int i = 1; i < len; i++){
            sb.append(delimiter).append(values[i]);
        }
        return sb.toString();
    }

    public static String join(char delimiter, Object ... values){
        if(null == values) return "";
        int len = values.length;
        if(0 == len) return "";
        if(1 == len){
            return noNull(values[0]);
        }
        StringBuilder sb = new StringBuilder(assessBufLen(24 * values.length));
        sb.append(values[0]);
        for(int i = 1; i < len; i++){
            sb.append(delimiter).append(values[i]);
        }
        return sb.toString();
    }

    /**
     * Join an array of Strings together.
     *
     * @param delimiter Token to place between Strings.
     * @param alues Array of Strings to join.
     * @return String presentation of joined Strings.
     *
     */
    public static String join(String delimiter, CharSequence ... values){
        if(null == values) return "";
        int len = values.length;
        if(0 == len) return "";
        if(1 == len){
            return toString(values[0]);
        }
        StringBuilder sb = new StringBuilder(assessBufLen(24 * len));
        sb.append(values[0]);
        for(int i = 1; i < len; i++){
            sb.append(delimiter).append(values[i]);
        }
        return sb.toString();
    }

    /**
     * Join an Iteration of Strings together.
     *
     * <h5>Example</h5>
     *
     * <pre>
     *   // get Iterator of Strings ("abc","def","123");
     *   Iterator i = getIterator();
     *   out.print( TextUtils.join(", ",i) );
     *   // prints: "abc, def, 123"
     * </pre>
     *
     * @param delimiter Token to place between Strings.
     * @param pieces Iteration of Strings to join.
     * @return String presentation of joined Strings.
     */
    public static String join(String delimiter, Iterator<?> pieces) {
        StringBuilder sb = new StringBuilder(512);

        if(pieces.hasNext()) sb.append(pieces.next().toString());
        while (pieces.hasNext()) {
            sb.append(delimiter);
            sb.append(pieces.next().toString());
        }

        return sb.toString();
    }


    /**
     * Join a Collection of Strings together.
     *
     * @param delimiter Token to place between Strings.
     * @param pieces Collection of Strings to join.
     * @return String presentation of joined Strings.
     *
     * @see #join(String, java.util.Iterator)
     */
    public static final String join(String delimiter, Collection<?> pieces) {
        if(pieces == null) return "";
        return join(delimiter, pieces.iterator());
    }

    public static final String joinIdable(String delimiter, Collection<? extends Idable<?>> pieces) {
        if(pieces == null) return "";
        return joinIdable(delimiter, pieces.iterator());
    }

    public static String joinIdable(String delimiter, Iterator<? extends Idable<?>> pieces) {
        if(pieces == null) return "";
        StringBuilder sb = new StringBuilder();
        if(pieces.hasNext()) sb.append(pieces.next().getId().toString());
        while (pieces.hasNext()) {
            sb.append(delimiter);
            sb.append(pieces.next().getId().toString());
        }

        return sb.toString();
    }

    public static final String joinNameable(String delimiter, Iterable<? extends Nameable> pieces) {
        if(pieces == null) return "";
        return joinNameable(delimiter, pieces.iterator());
    }

    public static String joinNameable(String delimiter, Iterator<? extends Nameable> pieces) {
        StringBuilder sb = new StringBuilder();
        if(pieces.hasNext()) sb.append(pieces.next().getName());
        while (pieces.hasNext()) {
            sb.append(delimiter);
            sb.append(pieces.next().getName());
        }

        return sb.toString();
    }

    public static final String joinNameableForSql(Iterable<? extends Nameable> pieces) {
        if(pieces == null) return "";
        return joinNameableForSql(pieces.iterator());
    }

    public static String joinNameableForSql(Iterator<? extends Nameable> pieces) {
        if(pieces == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append('\'');
        if(pieces.hasNext()) sb.append(pieces.next().getName());
        while (pieces.hasNext()) {
            sb.append("','");
            sb.append(pieces.next().getName());
        }
        sb.append('\'');

        return sb.toString();
    }

    public static final String noNull(Object o) {
        return null == o ? "" : o.toString();
    }

    public static final String noNull(Object o, String o2) {
        return null == o ? (null==o2?"":o2) : o.toString();
    }

    public static final String noNull(Object o1, Object o2) {
        return null == o1 ? (null==o2?"":o2.toString()): o1.toString();
    }

    public static final String noNull(Object o1, Object o2, Object o3) {
        return null == o1 ? noNull(o2, o3) : o1.toString();
    }

    public static final String noNull(Object o1, Object o2, Object o3, Object o4) {
        return null == o1 ? noNull(o2, o3, o4) : o1.toString();
    }

    public static final String noEmpty(CharSequence s, String default_string) {
    	return isEmpty(s)?default_string:s.toString();
    }

    public static final String noEmpty(CharSequence s1, String s2, String s3) {
        return isEmpty(s1)?(isEmpty(s2)?s3:s2):s1.toString();
    }

    public static String noEmpty(String s1, String s2, String s3, String ... str) {
        String result = noEmpty(s1, s2, s3);
        if(isNotEmpty(result)) return result;
        if(null!=str && str.length>0) {
            for(String s:str) {
                if(isNotEmpty(s)) return s;
            }
        }

        return null;
    }

    public static final String noEmptyGreedy(String s, String default_string) {
    	return isEmptyGreedy(s)?default_string:s;
    }

    public static String noEmptyGreedy(String s1, String s2, String s3) {
        return isEmptyGreedy(s1)?(isEmptyGreedy(s2)?s3:s2):s1.toString();
    }

    /**
     *
     * @param cs
     * @return
     */
    public static boolean isVarName(CharSequence cs) {
        if(StringUtils.isBlank(cs)) {
            return false;
        } else {
            int sz = cs.length();
            char c = cs.charAt(0);
            if ('_' != c && !Character.isLetter(c)) {
                return false;
            }

            for(int i = 1; i < sz; ++i) {
                char ch = cs.charAt(i);
                if('_' != ch && !Character.isLetterOrDigit(ch)) {
                    return false;
                }
            }

            return true;
        }

    }

    /**
     * Check whether <code>s</code> is empty
     * something equal <code>""</code> or <code>null</code>.
     * @param s the <code>String</code> to check
     * @return a boolean indicating whether the string was empty (or null)
     */
    public static final boolean isEmpty(CharSequence s) {
        return (s == null) || s.length() == 0;
    }

    public static final boolean isNotEmpty(CharSequence s) {
    	return !isEmpty(s);
    }

    /**
     * Test whether <code>s</code> is empty, white space will not count
     * @param s
     * @return
     */
    public static boolean isEmptyGreedy(CharSequence s) {
        return StringUtils.isBlank(s);
    }

    /**
     * Test whether <code>s</code> is not empty, white space will not count
     * @param s
     * @return
     */
    public static final boolean isNotEmptyGreedy(CharSequence s) {
        return !isEmptyGreedy(s);
    }

    public static final boolean isAllEmpty(CharSequence s1, CharSequence s2) {
        return isEmpty(s1) && isEmpty(s2);
    }

    public static final boolean isAnyEmpty(CharSequence s1, CharSequence s2) {
        return isEmpty(s1) || isEmpty(s2);
    }

    /**
     *
     * @param s
     */
    public static void trim(CharSequence[] s){
        if(null == s) return;
        int len = s.length;
        for(int i = 0; i < len; i++){
            s[i] = trim(s[i]);
        }
    }


    /**
    *
    * remove empty elements from the array
    * <strong>NOTICE:</strong> the input arr will be used as buffer, if you want to
    * keep your copy, input a clone one.
    * //GOOD
    * @param arr
    * @return
    */
   public static String[] trimEmpty(String[] arr) {
       if(null == arr || arr.length == 0) return EmptyObjects.ARRAY_STRING;
       int nonEmptyIndex = -1;
       for (int i = 0; i < arr.length; i++) {
           if(StrUtil.isNotEmptyGreedy(arr[i])) {
               nonEmptyIndex++;
               if(nonEmptyIndex != i) {
                   arr[nonEmptyIndex] = arr[i];
               }
           }
       }
       String[] result;
       if(nonEmptyIndex == -1) {
           result = EmptyObjects.ARRAY_STRING;
       }else if(nonEmptyIndex == arr.length) {
           result = arr;
       } else {
           result = new String[nonEmptyIndex+1];
           System.arraycopy(arr, 0, result, 0, nonEmptyIndex+1);
       }
       return result;
   }




    /**
     *
     *
     */
    public static String trimNonLetter(String s) {
    	if(null == s) return "";
    	int len = s.length();
    	if(0 == len) return "";
    	int st = 0;
    	int off = len - 1;

    	for(; !Character.isLetter(s.charAt(st)); st++) {
    		;
    	}

    	for(; !Character.isLetter(s.charAt(off)); off--) {
    		;
    	}

    	return ((st > 0) || (off < len - 1)) ? s.substring(st, off+1) : s;

    }

    /**
     * Remove all <code>'\r'</code> from <code>s</code>
     * @param s
     * @return
     */
    public static final String trimCR(String s){
        return replace(s, "\r", null);
    }

    public static String trimCRLF(String s) {
        if(null==s) return s;
        if(s.length()==0) return s;
        char c = s.charAt(s.length()-1);
        if(c=='\r' || c == '\n') {
            int i = 0;
            for(i = s.length() -2; i>=0; i--) {
                c = s.charAt(i);
                if(c=='\r' || c == '\n') continue;
                break;
            }
            return s.substring(0, i+1);
        } else {
            return s;
        }
    }

    public static final String ltrim(CharSequence s) {
    	return trimHead(s);
    }

    public static String trimHead(CharSequence s) {
    	if(null==s) return null;
    	int len = s.length();
    	if(0 == len) return "";
    	if (Global.CUSTOMER_VERSION) { // Global.CUSTOMER_VERSION - BEGIN
            return Pattern.compile("^\\s+").matcher(s).replaceAll("");
        } else { // Global.CUSTOMER_VERSION - MIDDLE
            int i = 0;
            for (i = 0; i < len; i++) {
                //if(!Character.isWhitespace(s.charAt(i))) break;
                if(!Character.isWhitespace(s.charAt(i))) break;
            }
            return i==0?s.toString():(i==len)?"":s.subSequence(i, s.length()).toString();
        } // Global.CUSTOMER_VERSION - END
    }

    public static final String rtrim(CharSequence s) {
    	return trimTail(s);
    }

    public static String trimTail(CharSequence s) {
    	if(null==s) return null;
    	if(s.length() ==0) return "";
    	if (Global.CUSTOMER_VERSION) { // Global.CUSTOMER_VERSION - BEGIN
    	    return Pattern.compile("\\s+$").matcher(s).replaceAll("");
        } else { // Global.CUSTOMER_VERSION - MIDDLE
            int len = s.length();
            int i = len-1;
            for (; i >= 0; i--) {
                if(!Character.isWhitespace(s.charAt(i))) break;
            }
            return i<0?"":(i==len-1)?s.toString():s.subSequence(0, i+1).toString();
        } // Global.CUSTOMER_VERSION - END
    }

    public static String trim(CharSequence s) {
    	if(null==s) return "";
    	if(0 == s.length()) return "";
    	if(s instanceof String) return ((String) s).trim();

    	//OPTI:
    	String result = trimHead(s);
    	result = trimTail(result);
    	return result;
    }

    /**
     * Returns a string that has whitespace removed from
     * both ends of the String, as well as duplicate whitespace
     * removed from within the String.
     */
    public static String innerTrim(String s) {
        StringBuilder b = new StringBuilder(s);
        int index = 0;

        while ((b.length() != 0) && (b.charAt(0) == ' ')) {
            b.deleteCharAt(0);
        }

        while (index < b.length()) {
            if (Character.isWhitespace(b.charAt(index))) {
                if (((index + 1) < b.length()) && (Character.isWhitespace(b.charAt(index + 1)))) {
                    b.deleteCharAt(index + 1);
                    index--; // let's restart at this character!
                }
            }

            index++;
        }

        if (b.length() > 0) {
            int l = b.length() - 1;

            if (b.charAt(l) == ' ') {
                b.setLength(l);
            }
        }

        return b.toString();
    }


    /**
     * <p>Removes one newline from end of a String if it's there,
     * otherwise leave it alone.  A newline is &quot;<code>\n</code>&quot;,
     * &quot;<code>\r</code>&quot;, or &quot;<code>\r\n</code>&quot;.</p>
     *
     * <p>NOTE: This method changed in 2.0.
     * It now more closely matches Perl chomp.</p>
     *
     * <pre>
     * StringUtils.chomp(null)          = null
     * StringUtils.chomp("")            = ""
     * StringUtils.chomp("abc \r")      = "abc "
     * StringUtils.chomp("abc\n")       = "abc"
     * StringUtils.chomp("abc\r\n")     = "abc"
     * StringUtils.chomp("abc\r\n\r\n") = "abc\r\n"
     * StringUtils.chomp("abc\n\r")     = "abc\n"
     * StringUtils.chomp("abc\n\rabc")  = "abc\n\rabc"
     * StringUtils.chomp("\r")          = ""
     * StringUtils.chomp("\n")          = ""
     * StringUtils.chomp("\r\n")        = ""
     * </pre>
     *
     * @param str  the String to chomp a newline from, may be null
     * @return String without newline, <code>null</code> if null String input
     */
    public static String chomp(String str) {
        if (isEmpty(str)) {
            return str;
        }

        if (str.length() == 1) {
            char ch = str.charAt(0);
            if (ch == '\r' || ch == '\n') {
                return "";
            } else {
                return str;
            }
        }

        int lastIdx = str.length() - 1;
        char last = str.charAt(lastIdx);

        if (last == '\n') {
            if (str.charAt(lastIdx - 1) == '\r') {
                lastIdx--;
            }
            //} else if (last == '\r') {
            // why is this block empty?
            // just to skip incrementing the index?
        } else {
            lastIdx++;
        }
        return str.substring(0, lastIdx);
    }

    /**
     * <p>Removes <code>separator</code> from the end of
     * <code>str</code> if it's there, otherwise leave it alone.</p>
     *
     * <p>NOTE: This method changed in version 2.0.
     * It now more closely matches Perl chomp.
     * For the previous behavior, use {@link #substringBeforeLast(String, String)}.
     * This method uses {@link String#endsWith(String)}.</p>
     *
     * <pre>
     * StringUtils.chomp(null, *)         = null
     * StringUtils.chomp("", *)           = ""
     * StringUtils.chomp("foobar", "bar") = "foo"
     * StringUtils.chomp("foobar", "baz") = "foobar"
     * StringUtils.chomp("foo", "foo")    = ""
     * StringUtils.chomp("foo ", "foo")   = "foo "
     * StringUtils.chomp(" foo", "foo")   = " "
     * StringUtils.chomp("foo", "foooo")  = "foo"
     * StringUtils.chomp("foo", "")       = "foo"
     * StringUtils.chomp("foo", null)     = "foo"
     * </pre>
     *
     * @param str  the String to chomp from, may be null
     * @param separator  separator String, may be null
     * @return String without trailing separator, <code>null</code> if null String input
     */
    public static String chomp(String str, String separator) {
        if (isEmpty(str) || separator == null) {
            return str;
        }
        if (str.endsWith(separator)) {
            return str.substring(0, str.length() - separator.length());
        }
        return str;
    }



    /**
     * Escape chars that need slashes in front of them.
     * @param s the String to add escape characters to
     * @return the converted String
     */
    public static String slashes(String s) {
        s = noNull(s);

        int len = s.length();
        if(0 == len) return s;

        StringBuilder sb = new StringBuilder(assessBufLen(s.length()));
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '\\' || c == '\"' || c == '\'') {
                sb.append('\\');
            }
            sb.append(c);
        }

        return sb.toString();
    }

    /**
     * Escape chars that need slashes in front of them.
     * @param s the String to add escape characters to
     * @return the converted String
     */
    public static String slashesForJS(String s) {
        s = noNull(s);

        int len = s.length();
        if(0 == len) return s;

        StringBuilder sb = new StringBuilder(assessBufLen(s.length()));

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '\\' || c == '\"' || c == '\'') {
                sb.append("\\\\");
            }

            sb.append(c);
        }

        return sb.toString();
    }

    /**
     *
     * @param str
     * @param size expected string length
     * @param padChar
     * @return
     */
    public static String leftPad(String str, int size, char padChar) {
        if (null == str) return repeat(padChar, size);

        int pads = size - str.length();
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        StringBuilder sb = new StringBuilder(size);
        repeat(padChar, pads, sb);
        sb.append(str);
        return sb.toString();
    }

    /**
     *
     * @param str
     * @param size expected string length
     * @param padChar
     * @return
     */
    public static String rightPad(String str, int size, char padChar) {
        if (null == str) return repeat(padChar, size);

        int pads = size - str.length();
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        StringBuilder sb = new StringBuilder(size);
        sb.append(str);
        repeat(padChar, pads, sb);
        return sb.toString();
    }

    /**
     *
     * @param c
     * @param times
     * @return
     */
    public static final String repeat(char c, int times){
        if (Global.CUSTOMER_VERSION) { // Global.CUSTOMER_VERSION - BEGIN
            StringBuilder sb = new StringBuilder(times);
            repeat(c, times, sb);
            return sb.toString();
        } else { // Global.CUSTOMER_VERSION - MIDDLE
            char[] buf = new char[times];
            Arrays.fill(buf, c);
            return new String(buf);
        } // Global.CUSTOMER_VERSION - END
    }

    /**
     *
     * @param c
     * @param times
     * @param buf
     */
    public static void repeat(char c, int times, StringBuilder buf){
        for (int i = 0; i < times; i++) {
            buf.append(c);
        }
    }

    /**
     *
     * @param s
     * @param times
     * @return
     */
    public static String repeat(String s, int times){
        StringBuilder sb = new StringBuilder(s.length() * times);
        for ( int i = 0; i < times; i++ ) sb.append( s );
        return sb.toString();
    }


    /**
     *
     * @param s
     * @param times
     * @param seperator
     * @return
     */
    public static String repeat(String s, int times, String seperator){
        StringBuilder sb = new StringBuilder((s.length() + seperator.length()) * times);
        if(times>0) sb.append(s);
        for ( int i = 1; i < times; i++ ) {
            sb.append(seperator).append(s);
        }
        return sb.toString();
    }


    /**
     * Replace all occurances of a string within another string.
     *
     * @see #replace(String text, String repl, String with, int max)
     * @param text  text to search and replace in
     * @param repl  String to search for
     * @param with  String to replace with
     * @return the text with any replacements processed
     */
    public static final String replace(String text, String repl, String with) {
        return replace(text, repl, with, -1);
    }

    /**
     * Replace a string with another string inside a larger string,
     * for the first <code>max</code> values of the search string.  A
     * <code>null</code> reference is passed to this method is a
     * no-op.
     * @param text text to search and replace in
     * @param repl String to search for
     * @param with String to replace with
     * @param max maximum number of values to replace, or
     * <code>-1</code> if no maximum
     * @return the text with any replacements processed
     */
    public static String replace(String text, String repl, String with, int max) {
        if (isEmpty(text)) return "";
        if(isEmpty(repl)) return text;
        if(null == with) with = "";
        // REFACTOR: if null == with , do not use "", just skip append

        int pos = 0, end = 0;
        pos = text.indexOf(repl, end);
        if(pos < 0) return text;
        int len = repl.length();
        StringBuilder buf = new StringBuilder(text.length() + len<with.length()?(text.length()>>3):0);
        do{
            buf.append(text.substring(end, pos)).append(with);
            end = pos + len;
            if (--max == 0) break;
        }while ((pos = text.indexOf(repl, end)) != -1);
        buf.append(text.substring(end));
        return buf.toString();
    }

    static Pattern patternWhiteSpace = Pattern.compile("\\s+");
    public static String removeWhiteSpace(String s) {
        if (isEmpty(s)) return "";
        // OPTI:
        if(Global.CUSTOMER_VERSION) {// Global.CUSTOMER_VERSION - BEGIN
            return patternWhiteSpace.matcher(s).replaceAll("");
        } else { // Global.CUSTOMER_VERSION - MIDDLE
            return patternWhiteSpace.matcher(s).replaceAll("");
        } // Global.CUSTOMER_VERSION - END
    }
    
    static Pattern patternEmptyLine = Pattern.compile("(?m)\\s*\\r{0,1}\\n");
    public static String removeBlankLine(String s) {
        if (isEmpty(s)) return "";
        return patternEmptyLine.matcher(s).replaceAll("\n").trim();
    }
    
    


    /**
     * Split <code>str</code> with <code>','</code> and will trim each substring, and remote empyt string
     * @param str
     * @return
     */
    public static final String[] split(String str) {
        return split(str, ",", -1, true, true);
    }

    /**
     * call <code>split(String str, String seperator, -1, true, true)</code>
     * and <code>limit=-1</code>
     * @param str
     * @param seperator
     *
     * @return
     */
    public static final String[] split(String str, String seperator) {
        return split(str, seperator, -1, true, true);
    }

    /**
     *
     * @param str
     * @param seperator
     * @param trim whether trim the result strings
     * @return
     */
    public static final String[] split(String str, String seperator, boolean trim) {
        return split(str, seperator, -1, trim, true);
    }

    /**
     *
     * @param str
     * @param seperator
     * @param limit
     * @return
     */
    public static final String[] split(String str, String seperator, int limit) {
        return split(str, seperator, limit, true, true);
    }

    /**
     * Splits the provided text into a list, based on a given separator.
     * The separator is not included in the returned String array.
     * The maximum number of splits to perfom can be controlled.
     * A null separator will cause parsing to be on whitespace.
     *
     * <p>This is useful for quickly splitting a string directly into
     * an array of tokens, instead of an enumeration of tokens (as
     * <code>StringTokenizer</code> does).
     *
     * @param str The string to parse.
     * @param separator Characters used as the delimiters. If
     * <code>null</code>, splits on whitespace.
     * @param limit The maximum number of elements to include in the
     * @param trim whether trim the result strings
     * list.  A zero or negative value implies no limit.
     * @return an array of parsed Strings
     */
    public static final String[] split(String str, String separator, int limit, boolean trim) {
        return split(str, separator, limit, trim, true);
    }

    public static final String[] split(String str, String separator, boolean trim, boolean noempty) {
        return split(str, separator, -1, trim, noempty);
    }

    /**
     * Splits the provided text into a list, based on a given separator.
     * The separator is not included in the returned String array.
     * The maximum number of splits to perfom can be controlled.
     * A null separator will cause parsing to be on whitespace.
     *
     * <p>This is useful for quickly splitting a string directly into
     * an array of tokens, instead of an enumeration of tokens (as
     * <code>StringTokenizer</code> does).
     *
     * @param str The string to parse.
     * @param separator Characters used as the delimiters. If
     * <code>null</code>, return the input str without split.
     * @param limit The maximum number of elements to include in the
     * list.  A zero or negative value implies no limit.
     * @param trim whether trim the result strings
     * @param noempty do not put empty string in the array
     * @return an array of parsed Strings
     */
    public static String[] split(String str, String separator, int limit, boolean trim, boolean noempty) {
        if (isEmpty(str)) {
            return EmptyObjects.ARRAY_STRING;
        }
        if (isEmpty(separator)) {
            return new String[] {str};
        }
        List<String> result = new ArrayList<String>();

        if (Global.CUSTOMER_VERSION) { // Global.CUSTOMER_VERSION - BEGIN
            String[] values = str.split(separator, limit);
            for(int i=0; i<values.length; i++) {
                if(trim) values[i] = values[i].trim();
                if(!noempty || values[i].length()>0) {
                    result.add(values[i]);
                }
            }
        } else { // Global.CUSTOMER_VERSION - MIDDLE
            int pos = 0;
            int delPos = 0;
            int separator_len = separator.length();
            while ((delPos = str.indexOf(separator, pos)) != -1 && --limit!=0) {
                String r = str.substring(pos, delPos);
                if(trim) r = trim(r);
                if((!noempty) || (trim?isNotEmpty(r):isNotEmptyGreedy(r))) result.add(r);
                pos = delPos + separator_len;
            }
            if (str.length() > 0 && pos <= str.length()) {
                // Add rest of String, but not in case of empty input.
                String r = str.substring(pos);
                if(trim) r = trim(r);
                if((!noempty) || (trim?isNotEmpty(r):isNotEmptyGreedy(r))) result.add(r);
            }
        } // Global.CUSTOMER_VERSION - END
        String[] ss = new String[result.size()];
        result.toArray(ss);
        return ss;

    }
    
    private static final Splitter generatSplitter = Splitter.onPattern("[,;(\r?\n)]+")
        .trimResults()
        .omitEmptyStrings()
    ;
    /**
     * Split ",;" and new line (\r?\n), 
     * Trim empty leading and trailing space.
     * Remote empty string 
     * @param s
     * @return
     */
    public static final String[] splitGeneral(String s) {
        List<String> split_list = splitGeneralToList(s);
        String[] result = new String[split_list.size()];
        split_list.toArray(result);
        return result;
    }
    
    /**
     * Split ",;" and new line (\r?\n), 
     * Trim empty leading and trailing space.
     * Remote empty string 
     * @param s
     * @return
     */
    public static final List<String> splitGeneralToList(String s) {
        if (isEmptyGreedy(s)) {
            return Collections.emptyList();
        }
        
        Iterator<String> split = generatSplitter.split(s).iterator();
        ArrayList<String> result = Lists.newArrayList(split);
        return result;        
    }

    public static List<Integer> splitToIntegerList(String s) {
        return toIntegerList(splitGeneralToList(s));
    }

    public static List<Integer> toIntegerList(List<String> stringList) {
        if(CollectionUtils.isEmpty(stringList)) return Collections.EMPTY_LIST;
        ArrayList<Integer> result = new ArrayList<Integer>(stringList.size());
        for (String s : stringList) {
            result.add(Integer.valueOf(s));
        }
        return result;
    }

    public static final Set<String> splitGeneralToSet(String s) {
        if (isEmptyGreedy(s)) {
            return Collections.emptySet();
        }

        List<String> split = generatSplitter.splitToList(s);
        if (CollectionUtils.isEmpty(split)) {
            return Collections.EMPTY_SET;
        }
        Set<String> result = new LinkedHashSet<String>(split);
        return result;
    }

    public static Set<Integer> splitToIntegerSet(String s) {
        return toIntegerList(splitGeneralToSet(s));
    }

    public static Set<Integer> toIntegerList(Collection<String> stringList) {
        if(CollectionUtils.isEmpty(stringList)) return Collections.EMPTY_SET;
        Set<Integer> result = new LinkedHashSet<Integer>(stringList.size());
        for (String s : stringList) {
            result.add(Integer.valueOf(s));
        }
        return result;
    }



    /**
     * Capitalize a <code>String</code>, changing the first letter to
     * upper case as per {@link Character#toLowerCase(char)}.
     * No other letters are changed.
     * @param s the String to capitalize, may be null
     * @return the capitalized String, <code>null</code> if null
     */
    public static final String capitalize(String s) {
        return changeFirstCharacterCase(s, true);
    }

    /**
     * same as capitalize
     * @param s
     * @return
     */
    public static final String cap(String s) {
        return changeFirstCharacterCase(s, true);
    }

    /**
     * Uncapitalize a <code>String</code>, changing the first letter to
     * lower case as per {@link Character#toLowerCase(char)}.
     * No other letters are changed.
     * @param s the String to uncapitalize, may be null
     * @return the uncapitalized String, <code>null</code> if null
     */
    public static final String uncapitalize(String s) {
        return changeFirstCharacterCase(s, false);
    }
    
    /**
     * same as uncapitalize
     * @param s
     * @return
     */
    public static final String uncap(String s) {
        return changeFirstCharacterCase(s, false);
    }
    private static String changeFirstCharacterCase(String s, boolean capitalize) {
        if (isEmpty(s)) {
            return s;
        }
        boolean isCapitalized = Character.isUpperCase(s.charAt(0));
        if(capitalize){
            if(isCapitalized) return s;
            StringBuilder sb = new StringBuilder(s);
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
            return sb.toString();
        }else if(isCapitalized){
            StringBuilder sb = new StringBuilder(s);
            sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
            return sb.toString();
        }else{
            return s;
        }

    }

    /**
     * Get the String that is nested in between two instances of the
     * same String. If str is null, will return null
     *
     * @param str  the string containing nested-string
     * @param tag  the string before and after nested-string
     * @return the string that was nested, or null
     * @throws NullPointerException if tag is null
     */
    public static final String getNestedString(String str, String tag) {
        return getNestedString(str, tag, tag);
    }

    /**
     * Get the string that is nested in between two strings.
     *
     * @param str  the string containing nested-string
     * @param open  the string before nested-string
     * @param close  the string after nested-string
     * @return the string that was nested, or null
     * @throws NullPointerException if open or close  is null
     */
    public static String getNestedString(String str, String open, String close) {
        if (isEmpty(str)) {
            return "";
        }
        int start = str.indexOf(open);
        if (start != -1) {
            int end = str.indexOf(close, start + open.length());
            if (end != -1) {
                return str.substring(start + open.length(), end);
            }
        }
        return "";
    }

    /**
     *
     * @param str
     * @param open
     * @param close
     * @return
     */
    public static String[] getNestedStrings(String str, String open, String close) {
        throw new RuntimeException("Not implemented!");
    }

    /**
     * How many times is the substring in the larger string.
     * Null returns 0.
     *
     * @param str  the string to check
     * @param sub  the substring to count
     * @return the number of occurances, 0 if the string is null
     * @throws NullPointerException if sub is null
     */
    public static final int countMatches(String str, String sub) {
        return countMatches(str, sub, -1);
    }

    /**
     * How many times is the substring in the larger string.
     * Null returns 0.
     *
     * @param str  the string to check
     * @param sub  the substring to count
     * @param limit not over this count
     * @return the number of occurances, 0 if the string is null
     * @throws NullPointerException if sub is null
     */
    public static int countMatches(String str, String sub, int limit) {
        if (isEmpty(str) || isEmpty(sub)) {
            return 0;
        }
        int count = 0;
        int idx = 0;
        while ((limit--!=0) && (idx = str.indexOf(sub, idx)) != -1) {
            count++;
            idx += sub.length();
        }
        return count;
    }

    //TODO: need refactor
    /**
     *
     * @param s
     * @return
     */
    public static String decodeURL1(String s){
        int pos = s.indexOf('%');
        if(pos < 0) return s;
        int len = s.length() - 1;
        StringBuilder sb = new StringBuilder(len);
        sb.append(s, 0, pos);
        int i = pos;
        for(; i < len; i++){
            char a = s.charAt(i);
            if(a == '%'){
                char a1 = s.charAt(i+1);
                switch(a1){
                    case '1':
                        i++;
                        sb.append('&');
                        break;
                    case '2':
                        i++;
                        sb.append('=');
                        break;
                    case '%':
                        i++;
                        sb.append('%');
                        break;
                    default:
                        sb.append('%');
                }
            }else{
                sb.append(a);
            }
        }
        for(;i<len+1; i++){
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    /**
     * replace '<b>&</b>' to '<b>%1</b>', '<b>=</b>' to '<b>%2</b>', '<b>%</b>' to '<b>%%</b>'
     * @param s
     * @return
     */
    public static String encodeURL1(String s){
        int len = s.length();
        int pos = -1;
        SearchChar:
            for(int i = 0; i < len; i++){
                switch(s.charAt(i)){
                    case '&':
                    case '=':
                    case '%':
                        pos = i;
                        break SearchChar;
                    default:
                        break;
                }
            }


            if(pos == -1) return s;
            StringBuilder sb = new StringBuilder(len + len>>3);
            sb.append(s.subSequence(0, pos));
            for(int i = pos; i < len; i++){
                char a = s.charAt(i);
                switch(a){
                    case '&':
                        sb.append(s.substring(pos, i));
                        pos = i+1;
                        sb.append("%1");
                        break;
                    case '=':
                        sb.append(s.substring(pos, i));
                        pos = i+1;
                        sb.append("%2");
                        break;
                    case '%':
                        sb.append(s.substring(pos, i));
                        pos = i+1;
                        sb.append("%%");
                        break;
                    default:
                        break;
                }
            }
            sb.append(s.substring(pos));
            return sb.toString();
    }


    // Misc
    //--------------------------------------------------------------------------

    /**
     * Checks if the String contains only certain chars.
     *
     * @param s the String to check
     * @param valid an array of valid chars
     * @return true if it only contains valid chars and is non-null
     */
    public static boolean containsOnly(String s, char ... valid) {
        if (Global.CUSTOMER_VERSION) { // Global.CUSTOMER_VERSION - BEGIN
            if (s == null || valid == null) {
                return false;
            }

            int len = s.length();
            int validSize = valid.length;

            for (int i = 0; i < len; i++) {
                boolean contains = false;
                char c = s.charAt(i);
                for (int j = 0; j < validSize; j++) {
                    if (valid[j] == c) {
                        contains = true;
                        break;
                    }
                }
                if (!contains) {
                    return false;
                }
            }

            return true;
        } else { // Global.CUSTOMER_VERSION - MIDDLE
            return  StringUtils.containsOnly(s, valid);
        } // Global.CUSTOMER_VERSION - END
    }



    /**
     * used for condition where need second hash code of a string
     * @param str
     * @return
     */
    static public int hash(String str) {
		if (isEmpty(str)) return 0;
		int len = str.length();
		int result = 0x238F13A9 * len;

		for (int i = 0; i < len; i++) {
			result += (str.codePointAt(i) << (i * 5 % 23));
		}
		result &= 0x7FFFFFFF;
		return result;
	}

    static public long hashLong(String s) {
		if (isEmpty(s)) return 0;
		int len = s.length();
		long result = 0x45543238F13A9L * len;

		for (int i = 0; i < len; i++) {
			result += (((long)s.codePointAt(i)) << (i * 5 % 47));
		}
		result &= 0x7FFFFFFFFFFFFFFFL;
		return result;
    }


    /**
     *
     * @param sb
     * @param len
     * @param radix
     * @exception IllegalArgumentException <code>sb</code> is null
     *                                     <code>len</code> less than 1
     *                                     <code>radix</code> not in qualified range
     */
//    public static void appendRandomChars(StringBuilder sb, int len, int radix){
//        if(null == sb || len <= 0 || radix <=0 || radix > CharUtil.MAX_RADIX){
//            throw new IllegalArgumentException(
//                    MessageFormat.format("Argument({0}, {1}, {2}) error!", sb, len, radix)
//                    );
//        }
//        byte[] bytes = new byte[len];
//        GlobalUtil.r.nextBytes(bytes);
//        for(int i = 0; i < len; i++){
//            sb.append(CharUtil.cArr[(bytes[i]&0x7F)%radix]);
//        }
//    }

    /**
     * <code>null==o?null:o.toString();</code>
     * @param o
     * @return
     */
    public final static String toString(Object o) {
        return null==o?null:o.toString();
    }

    /**
     * Returns a string representation of the first argument in the
     * radix specified by the second argument.
     * <p>
     * If the radix is smaller than <code>Character.MIN_RADIX</code>
     * or larger than <code>MAX_RADIX(62)</code>, then the radix
     * <code>MAX_RADIX</code> is used instead.
     * <p>
     * The remaining characters of the result represent the magnitude
     * of the first argument. If the magnitude is zero, it is
     * represented by a single zero character <code>'0'</code>
     * (<code>'&#92;u0030'</code>); otherwise, the first character of
     * the representation of the magnitude will not be the zero
     * character.  The following ASCII characters are used as digits:
     * <blockquote><pre>
     *   0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ
     * </pre></blockquote>
     * These are <code>'&#92;u0030'</code> through
     * <code>'&#92;u0039'</code> and <code>'&#92;u0061'</code> through
     * <code>'&#92;u007a'</code>. If <code>radix</code> is
     * <var>N</var>, then the first <var>N</var> of these characters
     * are used as radix-<var>N</var> digits in the order shown. Thus,
     * the digits for hexadecimal (radix 16) are
     * <code>0123456789abcdef</code>. If uppercase letters are
     * desired, the {@link java.lang.String#toUpperCase()} method may
     * be called on the result:
     * <blockquote><pre>
     * Long.toString(n, 16).toUpperCase()
     * </pre></blockquote>
     *
     * @param   i       a <code>long</code>to be converted to a string.
     * @param   radix   the radix to use in the string representation.
     * @return  a string representation of the argument in the specified radix.
     *      <b>NOTE:</b> No negative sign will be returned.
     * @see     java.lang.Character#MIN_RADIX
     */
    public static String toString(long i, int radix) {
        if (radix < Character.MIN_RADIX || radix > MAX_RADIX)
            radix = MAX_RADIX;
        char[] buf = new char[65];
        int charPos = 64;
        boolean negative = (i < 0);

        if (!negative) {i = -i;}

        while (i <= -radix) {
            buf[charPos--] = cArr[(int)(-(i % radix))];
            i = i / radix;
        }
        buf[charPos] = cArr[(int)(-i)];

        return new String(buf, charPos, (65 - charPos));
    }

    /**
     *
     * @see StrUtil.verifyCheckSum
     * @param values
     * @param key
     * @return
     */
//    public static String getCheckSum(String key, String charset, String ... values){
//        if(null == values || null == key) return "";
//        int len = values.length;
//        MessageDigest md5 = null;
//		try {
//			md5 = MessageDigest.getInstance("MD5");
//		} catch (NoSuchAlgorithmException e) {
//			logger.error("should never be here which can not get MD5 instance");
//			throw new RuntimeException("should never be here which can not get MD5 instance");
//		}
//        try {
//            md5.update(key.getBytes(charset));
//            for(int i = 0; i < len; i++){
//                md5.update(values[i].getBytes(charset));
//            }
//        } catch (UnsupportedEncodingException e) {
//            String message = "Charset " + charset + " is not supported";
//            logger.error(message);
//            throw new RuntimeException(message, e);
//        }
//        return CodeUtil.byte2String(md5.digest());
//    }

    /**
     *
     * @see StrUtil.getCheckSum
     * @param values
     * @param key
     * @param checkSum
     * @return
     */
//    public static final boolean verifyCheckSum(String checkSum, String key, String charset, String ... values){
//        if(null == values || null == key || null == checkSum) return false;
//        return checkSum.equals(getCheckSum(key, charset, values));
//    }


    /**
     * Truncate the string to <code>len</code> in byte, one Chinese character
     * eqauls two byte
     * @param s
     * @param len
     * @return
     */
    public static final String cutString(String s, int len){
        if (len < 1 || isEmpty(s))
			return "";
        int sLen = s.length();
        if(len >= sLen * 2) return s;
        int i = 0;
        for(; i < sLen && len > 0; i++){
            len -= s.charAt(i) > 255? 2: 1;
        }
        return s.substring(0, i);
    }

    /**
     * get string from tail
     * @param s
     * @param length the length of the result string
     * @return
     */
    public static String tail(String s, int length) {
    	if(null == s) return "";
    	int len = s.length();
    	if(len < length || length < 1) return s;

    	return s.substring(len - length);
    }

    /**
     * tail(s, sep, false);
     * @param s
     * @param sep
     * @return
     */
    public static final String tail(String s, String sep) {
    	return tail(s, sep, false);
    }
    /**
     * get substring after <code>sep</code>
     * @param s
     * @param sep
     * @param included include <code>sep</code> in result
     * @return
     */
    public static String tail(String s, String sep, boolean included) {
    	if(null == s) return "";
    	int len = s.length();
    	if(len == 0 || isEmpty(sep)) return s;

    	int idx = s.lastIndexOf(sep);
    	if(-1 == idx) return s;
    	return s.substring(idx + (included?0:sep.length()));
    }

    /**
     * head(s, sep, false)
     * @param s
     * @param sep
     * @return
     */
    public static final String head(String s, String sep) {
    	return head(s, sep, false);
    }

    /**
     * get substring before <code>sep</code>
     * @param s
     * @param sep
     * @param included include <code>sep</code> in result
     * @return
     */
    public static String head(String s, String sep, boolean included) {
    	if(null == s) return "";
    	int len = s.length();
    	if(len == 0 || isEmpty(sep)) return s;

    	int idx = s.indexOf(sep);
    	if(-1 == idx) return s;
    	return s.substring(0, idx + (included?sep.length():0));

    }

    /**
     * 将页面上的汉字转换为unicode
     *
     * @param inStr 要转换的中文字符串
     *
     * @return unicodeStr 转换后的unicode字符串
     */
    public static String GBToUnicode(String inStr) {
        // OPTIMIZE:
        //将要转换的字符串拆成字符数组
        char[] myBuffer = inStr.toCharArray();

        //要返回的unicode字符串
        StringBuilder sb = new StringBuilder();

        //循环取出每个字符的unicode
        for (int i = 0; i < inStr.length(); i ++) {
            //得到汉字内码
            short s = (short) myBuffer[i];
            int j = new Integer("" + s).intValue();
            // int j = (int)j;   // perhaps I should use this line

            String unicode = Integer.toHexString(j);

            //拼接unicode字符串
            if(unicode.indexOf("ffff") != -1) {
                unicode = unicode.substring( 0, unicode.indexOf("ffff") ) +
                    unicode.substring( unicode.indexOf("ffff") + 4 );
            }

            sb.append("&#x").append("&#x").append(';');
        }

        return sb.toString();
    }

    /**
     * 将得到的汉字参数从Utf-8转换为unicode字符串
     *
     * @param s 要转换的汉字参数从Utf-8字符串
     *
     * @return unicodeStr 转换后的unicode字符串
     */
    public static String utfToUnicode( String s ) throws Exception {

        //得到UTF-8编码的字符串
        s = new String(s.getBytes("ISO-8859-1"), StrUtil.UTF8);

        StringBuilder sb = new StringBuilder();
        //要返回的字符串

        //得到数组
        char[] charArray = s.toCharArray();

        //拼接unicode字符串
        for(int i = 0; i < charArray.length; i ++) {
          sb.append("&#x").append(Integer.toHexString(charArray[i])).append(';');
        }

        return sb.toString();
    }


	public static String strConvert(String s, String encode1, String encode2) {
		if(isEmpty(s)) return s;
		String result = s;
		try {
			result = new String(s.getBytes(encode2), encode1);
		} catch (UnsupportedEncodingException e) {
			logger.error("StrUtil.strConvert("+s+","+encode1+"," + encode2+") error!", e);
		}
		return result;
	}




	/**
	 *
	 * @param str
	 * @return
	 */
	public static String getPropertyName(String str) {
		String[] items = StrUtil.split(str.toLowerCase(), "_");
		StringBuilder sb = new StringBuilder();
		for (String s : items) {
			sb.append(StrUtil.capitalize(s));
		}
		sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
		return sb.toString();
	}

	/**
	 * Convert a bean property name to db name
	 * @param s
	 * @return
	 */
	public static String toDbName(String s) {
		int len = s.length();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<len; i++) {
			char c = s.charAt(i);
			if(Character.isUpperCase(c)) {
				sb.append('_').append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

    /**
     * @deprecated
     * Using <code>cutString</code> instead
     * To truncate the string <code>s</code> length <code>length</code>.
     * One chinese character's length will be considered as 2
     * @param s the String
     * @param length the left string length
     * @return the truncated String
     */
//    public static String truncate(String s, int length) {
//        if (length < 1 || isEmpty(s))
//			return "";
//        int len = s.length();
//        StringBuilder sb = new StringBuilder();
//        for(int i = 0; i < len && length > 0; i++){
//            char c = s.charAt(i);
//            if(c < 128){
//                sb.append(c);
//                length--;
//            }else{
//                if(length>1){
//                    sb.append(c);
//                }
//                length -= 2;
//            }
//        }
//        return sb.toString();
//    }

//    public static final String encodeXML(CharSequence s) {
//    	return XmlUtil.encode(s);
//    }

    public static String encodeJSON(CharSequence s) {
        if(isEmpty(s)) return "";
        int len = s.length();
        //OPTI: Don't use StringBuilder if no special char in [s]
        StringBuilder sb = new StringBuilder(len+len/8);
        for(int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if(c=='\"') sb.append("\\\"");
            else if(c=='\'') sb.append("\\'");
            else if(c=='\\') sb.append("\\\\");
            //else if(c=='/')  sb.append("\\/");
            else if(c=='\b') sb.append("\\b");
            else if(c=='\f') sb.append("\\r");
            else if(c=='\n') sb.append("\\n");
            else if(c=='\r') sb.append("\\r");
            else if(c=='\t') sb.append("\\t");
            else sb.append(c);
        }
        return sb.toString();
    	/*
        if(null == s || 0 == s.length()) return "";
        int len = s.length();
        StringBuilder sb = new StringBuilder(len+len/8);
        for(int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if(c=='\"') sb.append("\\\\\\\"");
            else if(c=='\'') sb.append("\\\\\\\'");
            else if(c=='\\') sb.append("\\\\");
            //else if(c=='/') sb.append("\\\\\\/");
            else if(c=='\b') sb.append("\\\\b");
            else if(c=='\f') sb.append("\\\\r");
            else if(c=='\n') sb.append("\\\\n");
            else if(c=='\r') sb.append("\\\\r");
            else if(c=='\t') sb.append("\\\\t");
            else sb.append(c);
        }
        return sb.toString();
        */
    }

    /**
     * encode the string to javascript string
     * @param s
     * @return
     */
    public static String encodeJsString(CharSequence s) {
        if(null == s || 0 == s.length()) return "";
        int len = s.length();
        StringBuilder sb = new StringBuilder(len+len/8);
        for(int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if(c=='\"') sb.append("\\\"");
            else if(c=='\'') sb.append("\\'");
            else if(c=='\\') sb.append("\\\\");
            else if(c=='/') sb.append("\\/");
            else if(c=='\b') sb.append("\\b");
            else if(c=='\f') sb.append("\\r");
            else if(c=='\n') sb.append("\\n");
            else if(c=='\r') sb.append("\\r");
            else if(c=='\t') sb.append("\\t");
            else sb.append(c);
        }
        return sb.toString();
    }


    public static String[] toStringArray(byte ... arr) {
        if(null==arr||arr.length==0) return EmptyObjects.ARRAY_STRING;
        int len = arr.length;
        String[] result = new String[len];
        for(int i=0; i<len; i++) {
            result[i] = String.valueOf(arr[i]);
        }
        return result;
    }

    public static String[] toStringArray(short ... arr) {
        if(null==arr||arr.length==0) return EmptyObjects.ARRAY_STRING;
        int len = arr.length;
        String[] result = new String[len];
        for(int i=0; i<len; i++) {
            result[i] = String.valueOf(arr[i]);
        }
        return result;
    }

    public static String[] toStringArray(int ... arr) {
    	if(null==arr||arr.length==0) return EmptyObjects.ARRAY_STRING;
    	int len = arr.length;
    	String[] result = new String[len];
    	for(int i=0; i<len; i++) {
    		result[i] = String.valueOf(arr[i]);
		}
    	return result;
    }

    public static String[] toStringArray(long ... arr) {
    	if(null==arr||arr.length==0) return EmptyObjects.ARRAY_STRING;
    	int len = arr.length;
    	String[] result = new String[len];
    	for(int i=0; i<len; i++) {
    		result[i] = String.valueOf(arr[i]);
		}
    	return result;
    }

    public static String[] toStringArray(Object ... arr) {
        if(null==arr||arr.length==0) return EmptyObjects.ARRAY_STRING;
        int len = arr.length;
        String[] result = new String[len];
        for(int i=0; i<len; i++) {
            result[i] = noNull(arr[i]);
        }
        return result;
    }

    public static String[] toStringArray(Collection<?> c) {
        if(null==c||c.isEmpty()) return EmptyObjects.ARRAY_STRING;
        String[] result = new String[c.size()];
        Iterator<?> it = c.iterator();
        for(int i=0; i<result.length&&it.hasNext(); i++) {
            result[i] = noNull(it.next());
        }
        return result;
    }

    public static String parseBacklash(String s) {
        if(s.indexOf('\\')==-1) return s;
        int len = s.length();
        char c;
        StringBuilder sb = new StringBuilder(len);
        for(int i = 0; i < len; i++) {
            c = s.charAt(i);
            if(c != '\\') {
                sb.append(c);
                continue;
            }
            if(++i < len) {
                c = s.charAt(i);
                switch(c) {
                case 'b':
                    sb.append('\b');
                    break;
                case 'f':
                    sb.append('\f');
                    break;
                case 'n':
                    sb.append('\n');
                    break;
                case 'r':
                    sb.append('\r');
                    break;
                case 't':
                    sb.append('\t');
                    break;
                default:
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

//    public static final int indexOfWhitespace(String s) {
//    	return indexOf(s, CharUtil.WHITESPACE_CHARS, 0);
//    }

    /**
     *
     * @param s
     * @param chars
     * @return
     */
    public static final int indexOf(String s, char ... chars) {
    	return indexOf(s, chars, 0);
    }

    /**
     *
     * @param s
     * @param chars
     * @param fromIndex
     * @return
     */
    public static int indexOf(String s, char[] chars, int fromIndex) {
    	if(isEmpty(s) || null==chars||chars.length==0||fromIndex>s.length()) return -1;
    	if(fromIndex<0) fromIndex = 0;
    	int len = s.length();
    	for(int i=fromIndex; i<len; i++) {
    		char c = s.charAt(i);
    		for(int j=0; j<chars.length; j++) {
				if(c == chars[j]) return i;
			}
		}
    	return -1;
    }

    /**
     *
     * @param s
     * @param chars
     * @return
     */
    public static final int lastIndexOf(String s, char ... chars) {
    	if(isEmpty(s)) return -1;
    	return lastIndexOf(s, chars, s.length() - 1);
    }

    /**
     *
     * @param s
     * @param chars
     * @param fromIndex
     * @return
     */
    public static int lastIndexOf(String s, char[] chars, int fromIndex) {
    	if(isEmpty(s) || null==chars||chars.length==0||fromIndex<0) return -1;
    	int len = s.length();
    	int i = fromIndex>=len?len-1:fromIndex;
    	for(; i>=0; i--) {
    		char c = s.charAt(i);
    		for(int j=0; j<chars.length; j++) {
				if(c == chars[j]) return i;
			}
		}
    	return -1;
    }
    
    /**
     * Whether <code>s</code> partially contains <code>regex</code>
     * @param s
     * @param regex
     * @return
     */
    public static boolean contains(String s, String regex) {
        // OPTI: 
        return s.matches(".*?" + regex + ".*?");
    }


    /**
     * test whether arr contains str
     * @param arr
     * @param str
     * @return
     */
    public static boolean containsIgnoreCase(String[] arr, String str) {
        if(null==str || null==arr|| arr.length == 0) return false;
        for(String s: arr) {
            if(s.equalsIgnoreCase(str)) return true;
        }
        return false;
    }

    /**
     * split comma separated string into list
     * @param s
     * @return
     */
    public static List<String> toList(String s) {
        return Arrays.asList(split(s));
        
    }
    
	/**
	 * "a,b,c" to ",a,b,c,", and remove all whitespace
	 * @param s
	 * @return
	 */
	public static String toMultiValueString(String s) {
		if(isEmpty(s)) return "";
		s = s.trim();
        if(isEmpty(s)) return "";
		if(!s.startsWith(",") || !s.endsWith(",") || hasWhitespaceChar(s)) {
			StringBuilder sb = new StringBuilder(s.length()+2);
			if(s.charAt(0) != ',') {
			    sb.append(',');
			}
			char c;
			int len = s.length();
			for(int i=0; i<len; i++) {
				c = s.charAt(i);
				if(!Character.isWhitespace(c)) sb.append(c);
			}
			if(sb.length()==1) return "";
			if(sb.charAt(sb.length()-1) != ',') sb.append(',');
			int pos = 0;
			while((pos = sb.indexOf(",,", pos)) > -1) {
				sb.deleteCharAt(pos);
			}
			return sb.toString();
		}

		return s;
	}

    /**
     * The string contains whitespace chars
     * @param s
     * @return
     */
    public static boolean hasWhitespaceChar(CharSequence s) {
        if(null == s) return false;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isWhitespace(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }

	public static String toMultiValueString(String[] s) {
		if(null == s || s.length ==0) return "";
		StringBuilder sb = new StringBuilder();
		sb.append(',');
		for(String str: s) {
		    str = StrUtil.trim(str);
			if(isNotEmpty(str)) sb.append(str).append(',');
		}
		int pos = 0;
		while((pos = sb.indexOf(",,", pos)) > -1) {
			sb.deleteCharAt(pos);
		}
		return sb.toString();
	}

	public static boolean hasLength(CharSequence s) {
	    return org.springframework.util.StringUtils.hasLength(s);
	}
	
	public static boolean hasText(CharSequence s) {
	    return org.springframework.util.StringUtils.hasText(s);
	}

	/**
	 * <ul>
	 * <li>trim start and end empty string
	 * <li>remove '\r'
	 * </ul>
	 * @param s
	 * @return
	 */
	public final static String prepareBlockProcess(String s) {
	    String str = s.trim();
	    str = replace(str, "\r", "");
	    return str;
	}

    /**
     * <ul>
     * <li>trim start and end empty string
     * <li>remove '\r'
     * <li>split to line by '\n'
     * <li>trim empty lines
     * <li>trim each line
     * </ul>
     * @param s
     * @return
     */
    public final static String[] prepareToArray(String s) {
        if(isEmpty(s)) return EmptyObjects.ARRAY_STRING;
        String[] split = s.split("(\\r?\\n)+");
        trim(split);
        split = trimEmpty(split);
        return split;
    }

    private static Pattern identifier = Pattern.compile("^[0-9a-zA-Z][0-9a-zA-Z_\\-\\.]*$");
    private static Pattern normalize = Pattern.compile("[^0-9a-zA-Z_\\-\\.]");

    public static boolean isIdentifier(String s) {
        return s != null && !s.isEmpty() && identifier.matcher(s).find();
    }


//    private static Set<Character> ImmutableSet.copyOf(ArrayUtils.toObject(("._-" + NUMBER + UPPERCASE_CHARS + LOWERCASE_CHARS).toCharArray()));
    public static String normalizeToIdentifier(String s) {
        if(StringUtils.isBlank(s)) return "";
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '.' || c == '_' || c == '-' || (c > 47 && c < 58) || (c>64 && c<91)|| (c>96 && c<123)) {
                chars[i] = c;
            } else {
                chars[i] = '_';
            }
        }
        return new String(chars);
    }

}


