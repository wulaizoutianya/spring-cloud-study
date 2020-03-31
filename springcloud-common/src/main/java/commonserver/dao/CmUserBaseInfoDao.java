package commonserver.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import commonserver.entity.CmUserBaseInfoEntity;

@Repository
public interface CmUserBaseInfoDao {
	
	//无参，返回集合
	public List<CmUserBaseInfoEntity> queryUserInfo();
	
	//单个参数，返回对象
	public CmUserBaseInfoEntity queryOneUserInfo(String name);
	
	//多个参数，返回集合	没有使用parameterType属性，早期版本用#{arg0}，#{arg1} 。。。
	//public List<Map<String, Object>> queryUserOrganInfo(int organId, String organName);
	//使用@Param注解		可以使用别名作为参数
	public List<Map<String, Object>> queryUserOrganInfo(@Param("arg0") int organId, @Param("arg1") String organName);
	
	// Map传参
	public List<Map<String, Object>> queryByMapUserOrganInfo(Map<String, Object> paramMap);

	// List传参 如果是数组传参 只需要 list改成array
	public List<Map<String, Object>> queryByListUserOrganInfo(List<String> paramList);	
	
}
