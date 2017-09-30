package com.guttv.util;

/**   
 * @Description: TODO
 * @author: koukaiqiang
 * @date:   2017年6月19日 下午6:49:44   
 *     
 */
public class DataThreadLocalUtil {
	
	private static ThreadLocal<Object> threadLocal = new ThreadLocal<>();
	
	public static void setData(Object data){
		threadLocal.set(data);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getData(){
		return (T)threadLocal.get();
	}

	/**   
	 * @Description: TODO
	 * @author: koukaiqiang   
	 * @version: 1.1
	 * @date:   2017年6月21日 下午5:12:39
	 *  
	 */  
	public static void removeData() {
		threadLocal.remove();
	}

}
