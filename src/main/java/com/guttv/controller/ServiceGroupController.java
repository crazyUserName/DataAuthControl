package com.guttv.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guttv.mapper.ServiceGroupMapper;
import com.guttv.model.ServiceGroup;
import com.guttv.vo.PageVO;

/**   
 * @Description: TODO
 * @author: koukaiqiang
 * @date:   2017年6月19日 下午7:08:47   
 *     
 */
@Controller
@RequestMapping("/serviceGroup")
public class ServiceGroupController {
	
	private static final Logger log = LoggerFactory.getLogger(ServiceGroupController.class);

	@Autowired
	private ServiceGroupMapper serviceGroupMapper;
	
	@RequestMapping("/findList")
	@ResponseBody
	public List<ServiceGroup> findList(PageVO pageVO){
		List<ServiceGroup> list = serviceGroupMapper.findPage(pageVO);
		return list;
	}
	@RequestMapping("/findListByServiceType")
	@ResponseBody
	public List<ServiceGroup> findListByServiceType(PageVO pageVO, int status){
		List<ServiceGroup> list = serviceGroupMapper.findPageByStatus(status, pageVO);
		return list;
	}
	
}
