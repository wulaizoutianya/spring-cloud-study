package commonserver.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import commonserver.entity.CmUserBaseInfoEntity;
import commonserver.service.CmUserBaseInfoService;

@RestController
public class UserLoginCheckWeb {
	
	@Autowired
	private CmUserBaseInfoService cmUserBaseInfoService;
	
	@SuppressWarnings("serial")
	@RequestMapping(path = "/userLoginCheck", method = RequestMethod.POST)
	public String userLoginCheck(@RequestBody Map<String, Object> paramMap) {
		for (Entry<String, Object> en : paramMap.entrySet()) {
			System.out.println(en.getKey() + " --1- " + en.getValue());
		}
		List<CmUserBaseInfoEntity> retList1 = cmUserBaseInfoService.queryUserInfo();
		for (CmUserBaseInfoEntity cc : retList1) {
			System.out.println(cc.getUserChName() + " -no param- " + cc.getUserSex().getValue());
		}
		
		CmUserBaseInfoEntity retObj1 = cmUserBaseInfoService.queryOneUserInfo("lixianyuan");
		System.out.println(retObj1.getUserChName() + " -one param- " + retObj1.getUserSex().getValue());
		
		List<Map<String, Object>> retList2 = cmUserBaseInfoService.queryUserOrganInfo(1, "天宫");
		for (Map<String, Object> cc : retList2) {
			System.out.println(cc.get("user_ch_name") + " -many param- " + cc.get("organ_name"));
		}
		
		List<Map<String, Object>> retList3 = cmUserBaseInfoService.queryByMapUserOrganInfo(
				new HashMap<String, Object>(){{
					put("aa", "bb");
					put("arg1", "urt");
				}});
		for (Map<String, Object> cc : retList3) {
			System.out.println(cc.get("user_ch_name") + " -map param- " + cc.get("organ_name"));
		}
		
		List<Map<String, Object>> retList4 = cmUserBaseInfoService.queryByListUserOrganInfo(
				new ArrayList<String>(){{
					add("sdf");
					add("urt");
				}});
		for (Map<String, Object> cc : retList4) {
			System.out.println(cc.get("user_ch_name") + " -list param- " + cc.get("organ_name"));
		}
		
		cmUserBaseInfoService.insertOneObj();
		return "Y";
	}

}
