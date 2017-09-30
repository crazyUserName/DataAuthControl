package com.guttv.plugin;

import java.util.Locale;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.guttv.mapper.DataAuthMapper;
import com.guttv.model.DataAuth;
import com.guttv.util.ApplicationContextUtil;
import com.guttv.util.DataThreadLocalUtil;
import com.guttv.util.SqlUtil;

@Intercepts({
@Signature(type = Executor.class, method = "query", args = {
        MappedStatement.class, Object.class, RowBounds.class,
        ResultHandler.class }) })
public class DataAuthSourceIntercept implements Interceptor {

	 	private static final Logger log = LoggerFactory.getLogger(DataAuthSourceIntercept.class);
		
	    @SuppressWarnings("unchecked")
		@Override
	    public Object intercept(Invocation invocation) throws Throwable {
	    	
            Object[] objects = invocation.getArgs();
            MappedStatement ms = (MappedStatement) objects[0];
//            如果不是查询 或者是查询主键 直接返回
            if(!ms.getSqlCommandType().equals(SqlCommandType.SELECT) || ms.getId().contains(SelectKeyGenerator.SELECT_KEY_SUFFIX)) {
            	return invocation.proceed();
            }
            if (ms.getId().startsWith("com.guttv.mapper.DataAuthMapper")) {
            	return invocation.proceed();
			}
            
            BoundSql boundSql = ms.getSqlSource().getBoundSql(objects[1]);
            String executeSql = boundSql.getSql().toLowerCase(Locale.CHINA).replaceAll("[\\t\\n\\r]", " ");
            
            if(StringUtils.isBlank(executeSql)){
            	return invocation.proceed();
            }
            
            log.info("SQL: {}", executeSql);
            
	    	Object object = DataThreadLocalUtil.getData();
	    	if (object == null || !(object instanceof Set<?>)) {
	    		return invocation.proceed();
			}
	    	
	    	Set<Integer> roles = (Set<Integer>)object;
	    	
	    	if (CollectionUtils.isEmpty(roles)) {
	    		return invocation.proceed();
			}
	    	DataAuthMapper dataAuthMapper = (DataAuthMapper) ApplicationContextUtil.getBean("dataAuthMapper");
	    	
	    	DataAuth dataAuth = dataAuthMapper.findBySelectId(ms.getId());
//	    	根据当前sql id没有找到对应权限时，说明这个sql没有进行权限控制
	    	if (dataAuth == null) {
	    		return invocation.proceed();
			}
	    	
	    	Set<Integer> set = dataAuthMapper.findRoleIdById(dataAuth.getId());
	    	set.retainAll(roles);
	    	if (CollectionUtils.isEmpty(set)) {
	    		return invocation.proceed();
			}
	    	
	    	StringBuilder sb = new StringBuilder();
	    	
	    	sb.append(" ").append(dataAuth.getFieldName()).append(" ");
	    	
	    	String value = dataAuth.getFieldValue();
	    	
	    	if (value.contains(",")) {
	    		sb.append(" in(").append(value).append(") ");
			}else{
				sb.append(" = ").append(value).append(" ");
			}
	    	String whereCondition = sb.toString();
	    	
	    	boolean isCount = false;
	    	
	    	if (ms.getId().equals(dataAuth.getSelectCountId())) {
	    		isCount = true;
			}
	    	
	    	
            String sql = SqlUtil.addWhereCondition(executeSql, whereCondition, isCount);
//	         FieldUtils.writeField(boundSql, "sql", sql, true);
	         SqlSource source = ms.getSqlSource();
	         while (!(source instanceof StaticSqlSource)) {
	        	 source = (SqlSource) FieldUtils.readField(source, "sqlSource", true);
			}
	         FieldUtils.writeField(source, "sql", sql, true);
	        log.info("new SQL: {} ", sql);
	        Object result = null;
	        try {
	        	result = invocation.proceed();
			} finally {
				FieldUtils.writeField(source, "sql", executeSql, true);
			}
            return result;
	    }

		@Override
	    public Object plugin(Object target) {
	        if (target instanceof Executor) {
	            return Plugin.wrap(target, this);
	        } else {
	            return target;
	        }
	    }

	    @Override
	    public void setProperties(Properties properties) {
	    }
}
