package com.crm.manager.system.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.crm.manager.common.Constats;
import com.crm.manager.common.base.dao.IBaseDao;
import com.crm.manager.common.base.service.impl.BaseServiceImpl;
import com.crm.manager.common.utils.MD5Utils;
import com.crm.manager.system.dao.IUserDao;
import com.crm.manager.system.dto.RoleDTO;
import com.crm.manager.system.dto.UserDTO;
import com.crm.manager.system.service.IRoleService;
import com.crm.manager.system.service.IUserService;

/**
 * <p>
 * 用户账户表  服务实现类
 * </p>
 *
 * @author SPPan
 * @since 2016-12-28
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserDTO, Integer> implements IUserService {

	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IRoleService roleService;
	
	@Override
	public IBaseDao<UserDTO, Integer> getBaseDao() {
		return this.userDao;
	}

	@Override
	public UserDTO findByUserName(String username) {
		return userDao.findByUserName(username);
	}

	@Override
	public void saveOrUpdate(UserDTO userDTO) {
		if(userDTO.getId() != null){
			UserDTO dbUser = find(userDTO.getId());
			dbUser.setNickName(userDTO.getNickName());
			dbUser.setSex(userDTO.getSex());
			dbUser.setBirthday(userDTO.getBirthday());
			dbUser.setTelephone(userDTO.getTelephone());
			dbUser.setEmail(userDTO.getEmail());
			dbUser.setAddress(userDTO.getAddress());
			dbUser.setLocked(userDTO.getLocked());
			dbUser.setDescription(userDTO.getDescription());
			dbUser.setUpdateTime(new Date());
			update(dbUser);
		}else{
			userDTO.setCreateTime(new Date());
			userDTO.setUpdateTime(new Date());
			userDTO.setDeleteStatus(0);
			userDTO.setPassword(MD5Utils.md5(Constats.INIT_PASSWORD));
			save(userDTO);
		}
	}
	
	

	@Override
	public void delete(Integer id) {
		UserDTO userDTO = find(id);
		Assert.state(!"admin".equals(userDTO.getUserName()),"超级管理员用户不能删除");
		super.delete(id);
	}

	@Override
	public void grant(Integer id, String[] roleIds) {
		UserDTO userDTO = find(id);
		Assert.notNull(userDTO, "用户不存在");
		Assert.state(!"admin".equals(userDTO.getUserName()),"超级管理员用户不能修改管理角色");
		RoleDTO roleDTO;
		Set<RoleDTO> roleDTOs = new HashSet<RoleDTO>();
		if(roleIds != null){
			for (int i = 0; i < roleIds.length; i++) {
				Integer rid = Integer.parseInt(roleIds[i]);
				roleDTO = roleService.find(rid);
				roleDTOs.add(roleDTO);
			}
		}
		userDTO.setRoles(roleDTOs);
		update(userDTO);
	}

	@Override
	public Page<UserDTO> findAllByLike(String searchText, PageRequest pageRequest) {
		if(StringUtils.isBlank(searchText)){
			searchText = "";
		}
		return userDao.findAllByNickNameContaining(searchText,pageRequest);
	}

	
	@Override
	public void updatePwd(UserDTO userDTO, String oldPassword, String password1, String password2) {
		Assert.notNull(userDTO, "用户不能为空");
		Assert.notNull(oldPassword, "原始密码不能为空");
		Assert.notNull(password1, "新密码不能为空");
		Assert.notNull(password2, "重复密码不能为空");
		
		UserDTO dbUser = userDao.findByUserName(userDTO.getUserName());
		Assert.notNull(dbUser, "用户不存在");
		
		Assert.isTrue(userDTO.getPassword().equals(MD5Utils.md5(oldPassword)), "原始密码不正确");;
		Assert.isTrue(password1.equals(password2), "两次密码不一致");
		dbUser.setPassword(MD5Utils.md5(password1));
		userDao.saveAndFlush(dbUser);
	}
	
}
