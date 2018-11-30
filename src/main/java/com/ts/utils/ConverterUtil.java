package com.ts.utils;


import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;

public class ConverterUtil {

//	public static void convertMap2Bean(Map<String, Object> map, Object obj) {
//		if (map == null || obj == null) {
//			return;
//		}
//		try {
//			BeanUtils.populate(obj, map);
//		} catch (Exception e) {
//			System.out.println("transMap2Bean2 Error " + e);
//		}
//	}

	/**
	 * 将 Map对象转化为JavaBean
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param map
	 * @param T
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	public static <T> T convertMap2Bean(Map<String,Object> map, Class<T> T)
			throws Exception
	{
		if (map == null || map.size() == 0)
		{
			return null;
		}
		BeanInfo beanInfo = Introspector.getBeanInfo(T);
		T bean = (T)T.newInstance();
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0, n = propertyDescriptors.length; i < n; i++)
		{
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			//            String upperPropertyName = propertyName.toUpperCase();
			if (map.containsKey(propertyName))
			{
				Object value = map.get(propertyName);
				//这个方法不会报参数类型不匹配的错误。
				BeanUtils.copyProperty(bean, propertyName, value);
			}
		}
		return bean;
	}

	/**
	 * 将一个 JavaBean 对象转化为一个 Map
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param bean
	 * @return
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @see [类、类#方法、类#成员]
	 */
	public static Map<String, Object> convertBean2Map(Object bean)
			throws IntrospectionException, IllegalAccessException, InvocationTargetException
	{
		Class<? extends Object> type = bean.getClass();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);

		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++)
		{
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (!propertyName.equals("class"))
			{
				Method readMethod = descriptor.getReadMethod();
				Object result = readMethod.invoke(bean, new Object[0]);
				if (result != null)
				{
					returnMap.put(propertyName, result);
				}
				else
				{
					returnMap.put(propertyName, null);
				}
			}
		}
		return returnMap;
	}

	/**
	 * 将 List<Map>对象转化为List<JavaBean>
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param listMap
	 * @param T
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	public static <T> List<T> convertListMap2ListBean(List<Map<String, Object>> listMap, Class<T> T)
			throws Exception
	{
		List<T> beanList = new ArrayList<T>();
		if (listMap != null && !listMap.isEmpty())
		{
			for (int i = 0, n = listMap.size(); i < n; i++)
			{
				Map<String, Object> map = listMap.get(i);
				T bean = convertMap2Bean(map, T);
				beanList.add(bean);
			}
			return beanList;
		}
		else
		{
			return null;
		}
	}

	/**
	 * 将 List<JavaBean>对象转化为List<Map>
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param beanList
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	public static <T> List<Map<String, Object>> convertListBean2ListMap(List<T> beanList, Class<T> T)
			throws Exception
	{
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		for (int i = 0, n = beanList.size(); i < n; i++)
		{
			Object bean = beanList.get(i);
			Map<String,Object> map = convertBean2Map(bean);
			mapList.add(map);
		}
		return mapList;
	}

}
