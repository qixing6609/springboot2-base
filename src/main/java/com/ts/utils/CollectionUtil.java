package com.ts.utils;


import java.util.*;

/**
 * 集合工具类
 * @author 
 *
 */
public class CollectionUtil {
	
	/**
	 * 判断对象是否为空
	 * @param object
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public static boolean isEmpty(Object object)
	{
		if(String.class.isInstance(object))
		{
			String string = (String)object;
			return string == null ? true : string.trim().length() == 0;
		}
		
		if(Collection.class.isInstance(object))
		{
			Collection<? extends Object> col = (Collection<? extends Object>)object;
			return col == null ? true : col.isEmpty();
		}
		
		if(Map.class.isInstance(object))
		{
			Map<? extends Object, ? extends Object> map = (Map<? extends Object, ? extends Object>)object;
			return map == null ? true : map.isEmpty();
		}
		
		if(Vector.class.isInstance(object))
		{
			Vector<? extends Object> vector = (Vector<? extends Object>)object;
			return vector == null ? true : vector.size() == 0;
		}
		
		if(Object[].class.isInstance(object))
		{
			Object[] obj = (Object[])object;
			return obj == null ? true : obj.length == 0;
		}
		
		return object == null;
	}
	
	/**
	 * 计算整型数组中元素和
	 * @param c
	 * @return
	 */
	public static int getCount(int[] c)
	{
		int count = 0;
		if(c != null)
		{
			for(int i = 0; i < c.length; i++)
			{
				count += c[i];
			}
		}
		return count;
	}
	//去掉list集合中的重复对象
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List removeDuplication(List list){
		//定义set
		Set set = new HashSet();
		set.addAll(list);
		list.clear();
		return new ArrayList(set);
	}

}
