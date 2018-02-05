package com.crm.manager.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.crm.manager.common.Constats;
import com.crm.manager.common.base.dao.IBaseDao;
import com.crm.manager.common.base.service.impl.BaseServiceImpl;
import com.crm.manager.system.dao.IResourceDao;
import com.crm.manager.system.dto.ResourceDTO;
import com.crm.manager.system.dto.RoleDTO;
import com.crm.manager.system.service.IResourceService;
import com.crm.manager.system.service.IRoleService;
import com.crm.manager.vo.ZtreeView;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author SPPan
 * @since 2016-12-28
 */
@Service
public class ResourceServiceImpl extends BaseServiceImpl<ResourceDTO, Integer>
		implements IResourceService {

	@Autowired
	private IResourceDao resourceDao;

	@Autowired
	private IRoleService roleService;

	@Override
	public IBaseDao<ResourceDTO, Integer> getBaseDao() {
		return this.resourceDao;
	}

	@Override
	@Cacheable(value=Constats.RESOURCECACHENAME,key="'tree_' + #roleId")
	public List<ZtreeView> tree(int roleId) {
		List<ZtreeView> resulTreeNodes = new ArrayList<ZtreeView>();
		RoleDTO roleDTO = roleService.find(roleId);
		Set<ResourceDTO> roleResources = roleDTO.getResources();
		resulTreeNodes.add(new ZtreeView(0L, null, "系统菜单", true));
		resulTreeNodes.add(new ZtreeView(100L, null, "CRM菜单", true));
		ZtreeView node;
		List<ResourceDTO> all = resourceDao.findAllByOrderByParentAscIdAscSortAsc();
		for (ResourceDTO resource : all) {
			node = new ZtreeView();
			node.setId(Long.valueOf(resource.getId()));
			if (resource.getParent() == null) {
				if(resource.getSourceKey().startsWith("system:")){
					node.setpId(0L);
				}
				if(resource.getSourceKey().startsWith("crm:")){
					node.setpId(100L);
				}
			} else {
				node.setpId(Long.valueOf(resource.getParent().getId()));
			}
			node.setName(resource.getName());
			if (roleResources != null && roleResources.contains(resource)) {
				node.setChecked(true);
			}
			resulTreeNodes.add(node);
		}
		return resulTreeNodes;
	}

	@Override
	public void saveOrUpdate(ResourceDTO resource) {
		if(resource.getId() != null){
			ResourceDTO dbResource = find(resource.getId());
			dbResource.setUpdateTime(new Date());
			dbResource.setName(resource.getName());
			dbResource.setSourceKey(resource.getSourceKey());
			dbResource.setType(resource.getType());
			dbResource.setSourceUrl(resource.getSourceUrl());
			dbResource.setLevel(resource.getLevel());
			dbResource.setSort(resource.getSort());
			dbResource.setIsHide(resource.getIsHide());
			dbResource.setIcon(resource.getIcon());
			dbResource.setDescription(resource.getDescription());
			dbResource.setUpdateTime(new Date());
			dbResource.setParent(resource.getParent());
			update(dbResource);
		}else{
			resource.setCreateTime(new Date());
			resource.setUpdateTime(new Date());
			save(resource);
		}
	}

	@Override
	public void delete(Integer id) {
		resourceDao.deleteGrant(id);
		super.delete(id);
	}

	@Override
	public Page<ResourceDTO> findAllByLike(String searchText, PageRequest pageRequest) {
		if(StringUtils.isBlank(searchText)){
			searchText = "";
		}
		return resourceDao.findAllByNameContaining(searchText, pageRequest);
	}
	
}
