package commonserver.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import commonserver.dao.CmDao;
import commonserver.dao.CmUserBaseInfoDao;
import commonserver.entity.CmUserBaseInfoEntity;
import commonserver.utils.UserSex;

@Service(value = "cmUserBaseInfoService")
public class CmUserBaseInfoService {
	
	@Autowired
	private CmUserBaseInfoDao cmUserBaseInfoDao;
	
	@Autowired
	private CmDao cmDao;

	public List<CmUserBaseInfoEntity> queryUserInfo() {
		return cmUserBaseInfoDao.queryUserInfo();
	}
	
	public CmUserBaseInfoEntity queryOneUserInfo(String name) {
		return cmUserBaseInfoDao.queryOneUserInfo(name);
	}
	
	public List<Map<String, Object>> queryUserOrganInfo(int organId, String organName) {
		return cmUserBaseInfoDao.queryUserOrganInfo(organId, organName);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> queryByMapUserOrganInfo(Map<String, Object> paramMap) {
		return cmDao.selectListObject("commonserver.dao.CmUserBaseInfoDao.queryByMapUserOrganInfo", paramMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> queryByListUserOrganInfo(List<String> paramList) {
		return cmDao.selectListObject("commonserver.dao.CmUserBaseInfoDao.queryByListUserOrganInfo", paramList);
	}
	
	@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
	@Transactional
	public int insertOneObj() {
		Map<String, Object> pMap1 = new HashMap<String, Object>(16);
		pMap1.put("userEnName", "aaaa");pMap1.put("userChName", "遥遥");pMap1.put("createdBy", 1);
		pMap1.put("userAccount", "fef");pMap1.put("userSex", UserSex.woman);pMap1.put("updatedBy", 1);
		pMap1.put("createdDate", new java.sql.Timestamp(System.currentTimeMillis()));
		pMap1.put("updatedDate", new java.sql.Timestamp(System.currentTimeMillis()));
		
		Map<String, Object> pMap2 = new HashMap<String, Object>(16);
		pMap2.put("userEnName", "bbbb");pMap2.put("userChName", "瞳手");pMap2.put("createdBy", 1);
		pMap2.put("userAccount", "ikik");pMap2.put("userSex", UserSex.man);pMap2.put("updatedBy", 1);
		pMap2.put("createdDate", new java.sql.Timestamp(System.currentTimeMillis()));
		pMap2.put("updatedDate", new java.sql.Timestamp(System.currentTimeMillis()));
		List<Map<String, Object>> tList = new ArrayList(){{add(pMap1);add(pMap2);}};
		
		try {
			cmDao.insertManyObject("commonserver.dao.CmUserBaseInfoDao.insertCmUserInfo", tList);
			return cmDao.insertOneObject("commonserver.dao.CmUserBaseInfoDao.insertCmUserInfo", pMap1);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("");
		}
	}
	
	
}
