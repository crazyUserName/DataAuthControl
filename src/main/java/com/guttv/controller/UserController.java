package com.guttv.controller;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guttv.mapper.UserMapper;
import com.guttv.model.User;

/**   
 * @Description: TODO
 * @author: koukaiqiang
 * @date:   2017年6月19日 下午7:09:14   
 *     
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserMapper userMapper;
	
	@RequestMapping("/login")
	@ResponseBody
	public String login(String userName, String password, HttpSession session){
		
		if (StringUtils.isBlank(userName) || StringUtils.isBlank(password))  {
			return "login";
		}
		
		User user = userMapper.findByUserName(userName);
		
		if (user == null || !user.getPassword().equals(password)) {
			return "login";
		}
		
		Set<Integer> roles = userMapper.findRolesById(user.getId());
		session.setAttribute("userDataRole", roles);
		return "package/list";
	}
}
