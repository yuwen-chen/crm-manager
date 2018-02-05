package com.crm.manager.system.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.crm.manager.common.Constats;
import com.crm.manager.common.base.dao.IBaseDao;
import com.crm.manager.common.base.service.impl.BaseServiceImpl;
import com.crm.manager.system.dao.IRoleDao;
import com.crm.manager.system.dto.ResourceDTO;
import com.crm.manager.system.dto.RoleDTO;
import com.crm.manager.system.service.IResourceService;
import com.crm.manager.system.service.IRoleService;

/**
 * <p>
 * 角色表  服务实现类
 * </p>
 *
 * @author SPPan
 * @since 2016-12-28
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleDTO, Integer> implements IRoleService {

	@Autowired
	private IRoleDao roleDao;
	@Autowired
	private IResourceService resourceService;
	
	@Override
	public IBaseDao<RoleDTO, Integer> getBaseDao() {
		return this.roleDao;
	}

	@Override
	public void saveOrUpdate(RoleDTO roleDTO) {
		if(roleDTO.getId() != null){
			RoleDTO dbRole = find(roleDTO.getId());
			dbRole.setUpdateTime(new Date());
			dbRole.setName(roleDTO.getName());
			dbRole.setDescription(roleDTO.getDescription());
			dbRole.setUpdateTime(new Date());
			dbRole.setStatus(roleDTO.getStatus());
			update(dbRole);
		}else{
			roleDTO.setCreateTime(new Date());
			roleDTO.setUpdateTime(new Date());
			save(roleDTO);
		}
	}

	
	
	@Override
	public void delete(Integer id) {
		RoleDTO roleDTO = find(id);
		Assert.state(!"administrator".equals(roleDTO.getRoleKey()),"超级管理员角色不能删除");
		super.delete(id);
	}

	@Override
	@CacheEvict(value = Constats.RESOURCECACHENAME, key = "'tree_' + #id")
	public void grant(Integer id, String[] resourceIds) {
		RoleDTO roleDTO = find(id);
		Assert.notNull(roleDTO, "角色不存在");
		
		Assert.state(!"administrator".equals(roleDTO.getRoleKey()),"超级管理员角色不能进行资源分配");
		ResourceDTO resource;
		Set<ResourceDTO> resources = new HashSet<ResourceDTO>();
		if(resourceIds != null){
			for (int i = 0; i < resourceIds.length; i++) {
				if(StringUtils.isBlank(resourceIds[i]) || "0".equals(resourceIds[i])){
					continue;
				}
				Integer rid = Integer.parseInt(resourceIds[i]);
				resource = resourceService.find(rid);
				if(resource != null){
					resources.add(resource);
				}
			}
		}
		roleDTO.setResources(resources);
		update(roleDTO);
	}

	@Override
	public Page<RoleDTO> findAllByLike(String searchText, PageRequest pageRequest) {
		if(StringUtils.isBlank(searchText)){
			searchText = "";
		}
		return roleDao.findAllByNameContainingOrDescriptionContaining(searchText,searchText, pageRequest);
	}
	
}
