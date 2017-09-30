package com.guttv.util;

/**   
 * @Description: TODO
 * @author: koukaiqiang
 * @date:   2017年6月20日 下午5:11:46   
 *     
 */
public class SqlUtil {
	
 	public static final String WHERE = " where ";
 	
 	public static final String AND = " and ";
 	
 	public static final String GROUP_BY = " group by ";
 	
 	public static final String ORDER_BY = " order by ";
 	
 	public static final String LIMIT = " limit ";

	public static String addWhereCondition(String sql, String whereCondition, boolean isCount){
		int i;
		StringBuilder sb = new StringBuilder();
		
		if (isCount) {
			i = sql.indexOf(" from ");
			sb.append(sql.substring(0, i)).append(" ( select dddd.* from ( ").append(sql.substring(i))
			.append(" ) dddd ").append(WHERE).append(whereCondition).append(" ) ddddd");
		}else{
			if ((i = sql.indexOf(ORDER_BY)) != -1 || (i = sql.indexOf(LIMIT)) != -1) {
				
				sb.append("select dddd.* from ( ").append(sql.substring(0, i)).append(" ) dddd")
				.append(WHERE).append(whereCondition).append(sql.substring(i));
				
			}else{
				sb.append("select dddd.* from ( ").append(sql).append("  ) dddd ").append(WHERE).append(whereCondition);
			}
		}
		return sb.toString();
	}
}
