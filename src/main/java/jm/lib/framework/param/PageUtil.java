package jm.lib.framework.param;

import com.google.common.collect.ImmutableSet;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by jiming.liu on 2015/5/8.
 * 分页相关的帮助方法，要注意的一点是，我们的 page 都是以 1 为 base 的，spring 的 page base 都是 0
 */
public class PageUtil {

    public static final Set<Integer> ALLOWED_PAGE_SIZE = ImmutableSet.of(2, 5, 10, 15, 20, 15, 100, 500);

    public static final int PAGE_SIZE = 10;


    /**
     * 使用 PAGE_SIZE 作为 pageSize 构造 Pageable
     *
     * @param page
     * @return
     */
    public static Pageable buildPageRequest(int page) {
        return new PageRequest(Math.max(0, page - 1), PAGE_SIZE);
    }

    /**
     * 构造 Pageable
     *
     * @param page
     * @param pageSize
     * @return
     */
    public static Pageable buildPageRequest(int page, int pageSize) {
        return new PageRequest(Math.max(0, page - 1), pageSize);
    }

    /**
     * 构造 Pageable，按照 field 倒排
     * @param page
     * @param pageSize
     * @param field
     * @return
     */
    public static Pageable buildPageRequestDesc(int page, int pageSize, String ... field) {
        return buildPageRequestDesc(page, pageSize, Arrays.asList(field));
    }

    /**
     * 构造 Pageable，按照 field 倒排
     * @param page
     * @param pageSize
     * @param field
     * @return
     */
    public static Pageable buildPageRequestDesc(int page, int pageSize, Collection<String> field) {
        return buildPageRequestByOrder(page, pageSize, OrderBuilder.desc(field));
    }

    /**
     * 构造 Pageable，按照 field 正排序
     * @param page
     * @param pageSize
     * @param field
     * @return
     */
    public static Pageable buildPageRequestAsc(int page, int pageSize, String ... field) {
        return buildPageRequestAsc(page, pageSize, Arrays.asList(field));
    }

    /**
     * 构造 Pageable，按照 field 正排序
     * @param page
     * @param pageSize
     * @param field
     * @return
     */
    public static Pageable buildPageRequestAsc(int page, int pageSize, Collection<String> field) {
        return buildPageRequestByOrder(page, pageSize, OrderBuilder.asc(field));
    }


    /**
     *
     * @param page
     * @param pageSize
     * @param orders
     * @return
     */
    public static Pageable buildPageRequest(int page, int pageSize, Sort.Order ... orders) {
        return buildPageRequestByOrder(page, pageSize, Arrays.asList(orders));
    }

    public static Pageable buildPageRequestByOrder(int page, int pageSize, Collection<Sort.Order> orders) {
        if(page < 1) page = 1;
        if(CollectionUtils.isEmpty(orders)) return buildPageRequest(page, pageSize);
        List<Sort.Order> orderList = new ArrayList<Sort.Order>(orders.size());
        for (Sort.Order order : orders) {
            orderList.add(order);
        }

        return new PageRequest(page - 1, pageSize, new Sort(orderList));
    }

//    public static Pageable buildPageRequest(int page, int pageSize, String field, Sort.Direction direction) {
//        if(direction == Sort.Direction.DESC) {
//            return buildPageRequestDesc(page, pageSize, field);
//        } else {
//            return buildPageRequestAsc(page, pageSize, field);
//        }
//    }
//
//    public static Pageable buildPageRequest(int page, int pageSize, String field, Sort.Direction direction, String field2, Sort.Direction direction2) {
//        List<Sort.Order> orderList = new ArrayList<Sort.Order>(2);
//        orderList.add(new Sort.Order(direction, field));
//        orderList.add(new Sort.Order(direction2, field2));
//        return buildPageRequestByOrder(page, pageSize, orderList);
//    }
//
//    public static Pageable buildPageRequest(int page, int pageSize, String field, Sort.Direction direction
//            , String field2, Sort.Direction direction2
//            , String field3, Sort.Direction direction3
//    ) {
//        List<Sort.Order> orderList = new ArrayList<Sort.Order>(2);
//        orderList.add(new Sort.Order(direction, field));
//        orderList.add(new Sort.Order(direction2, field2));
//        orderList.add(new Sort.Order(direction3, field3));
//        return buildPageRequestByOrder(page, pageSize, orderList);
//    }

    public static Pageable buildPageRequest(HttpServletRequest request) {
        // JMTODO:
        throw new RuntimeException("Not implement yet! Please contact ");
    }

    /**
     *
     * @param page
     * @param pageSize
     * @param sortFields 如果是以 - 开头的表示 desc
     * @return
     */
    public static Pageable buildPageRequest(int page, int pageSize, Collection<String> sortFields) {
        if(CollectionUtils.isEmpty(sortFields)) return buildPageRequest(page, pageSize);
        List<Sort.Order> orders = new ArrayList<Sort.Order>(sortFields.size());
        for (String field : sortFields) {
            if(field.startsWith("-")) {
                orders.add(OrderBuilder.desc(field.replaceAll("-", "")));
            } else {
                orders.add(OrderBuilder.asc(field));
            }
        }
        return buildPageRequestByOrder(page, pageSize, orders);
    }

    public static class OrderBuilder {
        public static Sort.Order desc(String field) {
            return new Sort.Order(Sort.Direction.DESC, field);
        }

        public static List<Sort.Order> desc(String... field) {
            if (field == null) return null;
            return desc(Arrays.asList(field));
        }

        public static List<Sort.Order> desc(Collection<String> field) {
            if (CollectionUtils.isEmpty(field)) return Collections.EMPTY_LIST;
            List<Sort.Order> result = new ArrayList<Sort.Order>(field.size());
            for (String s : field) {
                result.add(new Sort.Order(Sort.Direction.DESC, s));
            }
            return result;
        }

        public static Sort.Order asc(String field) {
            return new Sort.Order(Sort.Direction.ASC, field);
        }

        public static List<Sort.Order> asc(String... field) {
            if (field == null) return null;
            return asc(Arrays.asList(field));
        }

        public static List<Sort.Order> asc(Collection<String> field) {
            if (CollectionUtils.isEmpty(field)) return Collections.EMPTY_LIST;
            List<Sort.Order> result = new ArrayList<Sort.Order>(field.size());
            for (String s : field) {
                result.add(new Sort.Order(Sort.Direction.ASC, s));
            }
            return result;
        }

    }

}



