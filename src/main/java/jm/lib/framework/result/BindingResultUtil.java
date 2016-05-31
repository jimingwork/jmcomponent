package jm.lib.framework.result;

import org.apache.commons.collections.MapUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.Map;

/**
 * Created by jiming.liu on 2015/4/24.
 */
public class BindingResultUtil {


    public static String toHtmlMessage(BindingResult binding, Map<String, String> fieldNameMap) {
        StringBuilder sb = new StringBuilder();
        if(!binding.hasErrors()) {
            return "操作成功";
        }
        sb.append("<div class=''>");
        if(binding.hasGlobalErrors()) {
            sb.append("<ul class=''>");
            for (ObjectError oe : binding.getGlobalErrors()) {
                sb.append("<li class=''>").append(oe.getDefaultMessage()).append("</li>");
            }
            sb.append("</ul>");
        }
        if(binding.hasFieldErrors()) {
            sb.append("<ul class=''>");
            for (FieldError fe : binding.getFieldErrors()) {
                sb.append("<li class=''>").append(MapUtils.getString(fieldNameMap, fe.getField(), fe.getField()))
                        .append(fe.getDefaultMessage()).append("</li>");
            }
            sb.append("</ul>");
        }

        sb.append("</div>");
        return sb.toString();
    }


    public static String toHumanMessage(BindingResult binding, Map<String, String> fieldNameMap) {
        StringBuilder sb = new StringBuilder();
        if(!binding.hasErrors()) {
            return "操作成功";
        }
        if(binding.hasGlobalErrors()) {
            for (ObjectError oe : binding.getGlobalErrors()) {
                sb.append(oe.getDefaultMessage()).append('\n');
            }
        }
        sb.append('\n');
        if(binding.hasFieldErrors()) {
            for (FieldError fe : binding.getFieldErrors()) {
                sb.append(MapUtils.getString(fieldNameMap, fe.getField(), fe.getField()))
                        .append(fe.getDefaultMessage()).append('\n');
            }
        }

        return sb.toString();
    }
}
