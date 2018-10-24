package com.yunwoo.cybershop.db;

import com.yunwoo.cybershop.model.*;
import com.yunwoo.cybershop.utils.ObjectSQLUtil;
import com.yunwoo.cybershop.utils.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.Map.Entry;

/**
 * 继承JdbcTemplate类，重写常用的方法，主要是处理父类中抛出的底层数据连接异常
 * 
 * @author ShenLiang
 * @create 2014年5月28日
 */
public class DaoSupport {

	public final String DEFAULT_COUNT_COLUMN_NAME = "totalcount";
	
	public final String DEFAULT_MAX_COLUMN_NAME = "id";

	private JdbcTemplate jdbcTemplate;

	public DaoSupport(){

	}
	public DaoSupport(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate=jdbcTemplate;
	}


	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * 添加方法
	 * 
	 * @param obj
	 * @return
	 */
	public boolean add(Object obj) {
		String sql = ObjectSQLUtil.getAdd(obj);
		return update(sql, ObjectSQLUtil.getAddParameters(obj));
	}

	/**
	 * 修改
	 * 
	 * @param obj
	 * @return
	 */
	public boolean update(Object obj) {
		String sql = ObjectSQLUtil.getUpdate(obj);
		return update(sql, ObjectSQLUtil.getUpdateParameters(obj));
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public boolean delete(Class<?> elementType, int id) {
		String sql = ObjectSQLUtil.getDelete(elementType);
		return update(sql, id);
	}

	/**
	 * 查询单条数据
	 * 
	 * @param elementType
	 * @param id
	 * @return
	 */
	public <T> T get(Class<T> elementType, int id) {
		String sql = null;
		try {
			sql = ObjectSQLUtil.getQuery(elementType.newInstance());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return queryForObject(sql, elementType, id);
	}

	/**
	 * 查询所有
	 * 
	 * @param elementType
	 * @return
	 */
	public <T> List<T> find(Class<T> elementType) {
		String sql = ObjectSQLUtil.getAll(elementType);
		return queryForList(sql, elementType);
	}

	/**
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	public boolean update(String sql, Object... args) {
		try {
			return jdbcTemplate.update(sql, args) > 0;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 只写where语句之后的querySql,不能带select * from 表名，例如querySql为 and id=1 
	 * @param <T>
	 * @param elementType
	 * @param querySql
	 * @param args
	 * @return
	 */
	public <T> T queryForObject(Class<T> elementType, String querySql,
			Object... args) {
		String sql = new StringBuffer(ObjectSQLUtil.getQuery(elementType))
				.append(" where true ")
				.append(querySql != null ? querySql : "").toString();
		return queryForObject(sql, elementType, args);
	}

	/**
	 * 自己拼装的sql 
	 * @param sql
	 * @param elementType
	 * @param args
	 * @return
	 */
	public <T> T queryForObject(String sql, Class<T> elementType,
			Object... args) {
		try {
			return jdbcTemplate.queryForObject(sql, args,
					new BeanPropertyRowMapper<T>(elementType));
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
//			log.error("数据库异常" + e);
		}
		return null;
	}

	/**
	 * 单表某些字段查询
	 * @param <T>
	 * @param elementType
	 * @param querySql where 后面的查询sql
	 * @param args
	 * @return
	 */
	public <T> List<T> queryForList(Class<T> elementType, String querySql,
			Object... args) {
		String sql = new StringBuffer(ObjectSQLUtil.getQuery(elementType))
				.append(" where true ")
				.append(querySql != null ? querySql : "").toString();
		return queryForList(sql, elementType, args);
	}

	/**
	 * 连表查询
	 * @param sql 查询的全部sql
	 * @param elementType
	 * @param args
	 * @return
	 */
	public <T> List<T> queryForList(String sql, Class<T> elementType,
			Object... args) {
		try {
			return jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<T>(
					elementType));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 此方法会拼接分页查询的其他参数（除了where条件的）
	 * 
	 * @param elementType
	 * @param args
	 * @return
	 */
	public <T> List<T> queryForList(Class<T> elementType,
			Pagination pagination, String querySql, Object... args) {
		StringBuffer sql = new StringBuffer(ObjectSQLUtil.getQuery(elementType));
		sql.append(" where true ");
		sql.append(StringUtils.isEmpty(querySql) ? "" : querySql);
		sql.append(" order by ");
		sql.append(Sort.getSortString(pagination.getSort()));
		sql.append(" limit ");
		sql.append(pagination.getStart());
		sql.append(",");
		sql.append(pagination.getPageSize());
		try {
			return jdbcTemplate.query(sql.toString(), args,
					new BeanPropertyRowMapper<T>(elementType));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 此方法只拼接分页参数
	 * 
	 * @param elementType
	 * @param args
	 * @return
	 */
	public <T> List<T> queryForList(Pagination pagination, Class<T> elementType, String querySql, Object... args) {
		StringBuffer sql = new StringBuffer(querySql);
		sql.append(" limit ");
		sql.append(pagination.getStart());
		sql.append(",");
		sql.append(pagination.getPageSize());
		try {
			return jdbcTemplate.query(sql.toString(), args,
					new BeanPropertyRowMapper<T>(elementType));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 查询总数的别名是【totalcount】
	 * 
	 * @param sql
	 * @return
	 */
	public int count(String sql, Object... args) {
		return count(sql, DEFAULT_COUNT_COLUMN_NAME, args);
	}

	public int count(Class<?> obj, String querySql, Object... args) {
		String sql = new StringBuffer(ObjectSQLUtil.getCount(obj))
				.append(" where true ").append(querySql).toString();
		return count(sql, args);
	}
	
	/**
	 * 获取数据库中最大的主键值
	 * @param tableName
	 * @return
	 */
	public int max(String tableName) {
		String sql = "select max(id) as id from "+tableName;
		return maxValue(sql);
	}
	
	/**
	 * 获取数据库中最大的主键值
	 * @param obj
	 * @return
	 */
	public int max(Class<?> obj) {
		String sql = new StringBuffer(ObjectSQLUtil.getMax(obj)).toString();
		return maxValue(sql);
	}
	
	/**
	 * 获取主键
	 * @param obj
	 * @return
	 */
	public int getPrimaryKey(Class<?> obj){
		return max(obj)+1;
	}
	
	private int maxValue(String sql) {
		Integer key = jdbcTemplate.queryForObject(sql,
				new RowMapper<Integer>() {
					public Integer mapRow(ResultSet rs, int rowNum) {
						try {
							return rs.getInt(DEFAULT_MAX_COLUMN_NAME);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						return 0;
					}
				});
		return key;
	}

	/**
	 * 查询总数
	 * 
	 * @param sql
	 * @param countColumnName
	 * @return
	 */
	private int count(String sql, final String countColumnName, Object... args) {
		Integer key = jdbcTemplate.queryForObject(sql, args,
				new RowMapper<Integer>() {
					public Integer mapRow(ResultSet rs, int rowNum) {
						try {
							return rs.getInt(countColumnName);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						return 0;
					}
				});
		return key;
	}

	/**
	 * 查询一列string类型的数据
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	public String queryString(String sql, Object... args) {
		String result = null;
		try {
			result = jdbcTemplate.queryForObject(sql, args,
					new RowMapper<String>() {
						public String mapRow(ResultSet rs, int rowNum) {
							try {
								return rs.getString(1);
							} catch (SQLException e) {
								e.printStackTrace();
							}
							return null;
						}
					});
		} catch (EmptyResultDataAccessException e) {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Integer queryInteger(String sql, Object... args) {
		Integer key = jdbcTemplate.queryForObject(sql, args,
				new RowMapper<Integer>() {
					public Integer mapRow(ResultSet rs, int rowNum) {
						try {
							return rs.getInt(1);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						return 0;
					}
				});
		return key;
	}
	/**
	 * 查询分页
	 * @param pagination
	 * @param elementType
	 * @return
	 */
	public <T extends Serializable> PaginationResult<T> find(Pagination pagination, Class<T> elementType) {
		int total = count(pagination,elementType);
		List<T> result = null;
		if(total>0){
			result = getResultList(pagination,elementType);
		}
		PaginationResult<T> paginationResult 
		        = new PaginationResult<T>(pagination,total,result);
		return paginationResult;
	}
	
	
	
	
	/**
	 * 查询出符合条件的记录的总数
	 * @param pagination
	 * @return
	 */
	private <T> int count(Pagination pagination,Class<T> elementType) {
		String	tableName = ObjectSQLUtil.getTableName( elementType);
		StringBuffer sql = new StringBuffer("select count(*) totalcount from ").append(tableName).append(" where true ");
		List<Object> values = generateConditonSql(sql,pagination.getQuery());
		int count = count(sql.toString(), values.toArray());
		return count;
	}
	
	
	
	private <T> List<T> getResultList(Pagination pagination,Class<T> elementType) {
		String	tableName = ObjectSQLUtil.getTableName(elementType);
		StringBuffer sql = new StringBuffer("select * from ").append(tableName).append(" where true ");
		List<Object> values = generateConditonSql(sql,pagination.getQuery());
		String sortStr = Sort.getSortString(pagination.getSort());
		sql.append(" order by ").append(sortStr);
		sql.append(" limit ?,?");
		values.add(pagination.getStart());
		values.add(pagination.getPageSize());
		List<T> result = queryForList(sql.toString(), elementType, values.toArray());
		return result;
	}
	
	
	/**
	 * 生成条件sql语句，以及返回条件值数组
	 * @param sql
	 * @param map
	 * @return
	 */
	private List<Object> generateConditonSql(StringBuffer sql,Map<String,Object> map) {
		List<Object> valueList = new ArrayList<Object>();
		if(!map.isEmpty()) {
			Set<Entry<String,Object>> set = map.entrySet();
			Iterator<Entry<String,Object>> iterator = set.iterator();
			while(iterator.hasNext()){
				Entry<String,Object> entry = iterator.next();
				String name = entry.getKey();
				String value = entry.getValue().toString();
				sql.append(" and ").append(name).append("=? ");
				valueList.add(value);
			}
		}
		return valueList;
	}
	
	//获取小数数据
	public Double queryDouble(String sql, Object... args) {
		Double key = jdbcTemplate.queryForObject(sql, args,
				new RowMapper<Double>() {
					public Double mapRow(ResultSet rs, int rowNum) {
						try {
							return rs.getDouble(1);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						return 0.0;
					}
				});
		return key;
	}


	/**
	 * 查找整型变量字段
	 * @param sql
	 * @param args
	 * @return
	 */
	public List<Integer> queryIntegers(String sql, Object... args) {
		List<Integer> list = new ArrayList<Integer>();
		try{
			list = (List<Integer>)jdbcTemplate.query(sql, args,  new RowMapper<Integer>(){
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getInt(1);
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public <T> List<T> queryForList(Class<T> elementType,
									PaginationNew pagination, String querySql, Object... args) {
		StringBuffer sql = new StringBuffer(ObjectSQLUtil.getQuery(elementType));
		sql.append(" where true ");
		sql.append(StringUtils.isEmpty(querySql) ? "" : querySql);
		sql.append(" order by ");
		sql.append(pagination.getSortField()+" "+pagination.getSortOrder());
		sql.append(" limit ");
		sql.append(pagination.getStartNum());
		sql.append(",");
		sql.append(pagination.getPageSize());
		try {
			return jdbcTemplate.query(sql.toString(), args,
					new BeanPropertyRowMapper<T>(elementType));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**查询得到分页结果
	 * @param elementType 对象泛型
	 * @param pagination 分页信息
	 * @param querySql 查询sql
	 * @param args 参数
	 * @return 分页结果
	 */
	public <T> PaginationResultNew<T> queryForPaginationNew(Class<T> elementType,
															PaginationNew pagination, String querySql, Object... args) {
		StringBuffer sql = new StringBuffer(ObjectSQLUtil.getQuery(elementType));
		StringBuffer countSql = new StringBuffer();
		StringBuffer theQuerySql = new StringBuffer();
		//设置where后的查询语句（约定不能含有模糊查询）
		theQuerySql.append(StringUtils.isEmpty(querySql) ? "" : querySql);
		//设置模糊查询
		if(StringUtils.isNotBlank(pagination.getKeyField())){
			theQuerySql.append(" and "+pagination.getKeyField()+" like "+"'%"+pagination.getKeyValue()+"%'");
		}
		countSql.append(theQuerySql);
		sql.append(" where true ");
		sql.append(theQuerySql);
		if(StringUtils.isNotBlank(pagination.getSortField())){
			sql.append(" order by ");
		}
		sql.append(pagination.getSortField()+" "+pagination.getSortOrder());
		sql.append(" limit ");
		sql.append(pagination.getStartNum());
		sql.append(",");
		sql.append(pagination.getPageSize());
		try {
			List<T> data = jdbcTemplate.query(sql.toString(), args,
					new BeanPropertyRowMapper<T>(elementType));
			int total = count(elementType, theQuerySql.toString(), args);
			return new PaginationResultNew<T>(total, data);
		} catch (Exception e) {
			e.printStackTrace();
			return new PaginationResultNew<T>();
		}
	}
}
