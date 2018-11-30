package com.ts.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class PredicateUtils {

	/**
	 * 
	 * filter去重<BR>
	 * 方法名：distinctByKey<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2018年10月17日-下午2:00:49 <BR>
	 * @param keyExtractor
	 * @return Predicate<T><BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
	    Map<Object, Boolean> seen = new ConcurrentHashMap<>();
	    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	  }
}
