package com.yunwoo.cybershop.storage.db;

import com.yunwoo.cybershop.db.DaoSupport;
import com.yunwoo.cybershop.model.Pagination;
import com.yunwoo.cybershop.model.PaginationResult;
import com.yunwoo.cybershop.storage.po.MemberPO;
import com.yunwoo.cybershop.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 仓储实现类
 */
@Repository
public class MemberDBStorageImpl implements MemberDBStorage {
	private static final Logger log = LoggerFactory.getLogger(MemberDBStorageImpl.class);

	@Autowired
	private DaoSupport dao;

	@Override
	public boolean add(MemberPO memberPO) {
		return dao.add(memberPO);
	}

	@Override
	public boolean delete(int id) {
		return dao.delete(MemberPO.class, id);
	}

	@Override
	public boolean update(MemberPO memberPO) {
		return dao.update(memberPO);
	}

	@Override
	public MemberPO get(int id) {
		return dao.get(MemberPO.class, id);
	}



	@Override
	public PaginationResult<Integer> getByPagination(Pagination pagination) {
		String name = (String) pagination.getQuery().get("name");
		if(StringUtils.isBlank(name)) {
			name = "";
		}
		List<Integer> ids = new ArrayList<Integer>();
		String sqlCount = "select count(1) as totalcount from member where username like '%"+name+"%' or phonenumber like '%"+name+"%'";
		int total = dao.count(sqlCount);

		if(total != 0){
			String sql = "select id from member where username like '%"+name+"%' or phonenumber like '%"+name+"%'";
			sql +=  " limit ?,?";
			ids = dao.queryIntegers(sql, pagination.getStart(), pagination.getPageSize());
		}
		return new PaginationResult<Integer>(pagination,total,ids);
	}

	@Override
	public MemberPO getByUsername(String username) {
		String sql = "select * from member where username = ?";
		return dao.queryForObject(sql,MemberPO.class,username);
	}

	@Override
	public MemberPO getByPhonenum(String phonenum) {
		String sql = "select * from member where phonenumber = ?";
		return dao.queryForObject(sql,MemberPO.class,phonenum);
	}
}
