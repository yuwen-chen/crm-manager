package com.crm.manager.system.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.crm.manager.common.base.service.IBaseService;
import com.crm.manager.system.dto.RoleDTO;

/**
 * <p>
 * 角色服务类
 * </p>
 *
 * @author SPPan
 * @since 2016-12-28
 */
public interface IRoleService extends IBaseService<RoleDTO,Integer> {

	/**
	 * 添加或者修改角色
	 * @param roleDTO
	 */
	void saveOrUpdate(RoleDTO roleDTO);

	/**
	 * 给角色分配资源
	 * @param id 角色ID
	 * @param resourceIds 资源ids
	 */
	void grant(Integer id, String[] resourceIds);

	/**
	 * 根据关键字查询分页
	 * @param searchText
	 * @param pageRequest
	 * @return
	 */
	Page<RoleDTO> findAllByLike(String searchText, PageRequest pageRequest);
	
}
