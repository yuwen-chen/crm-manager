package com.crm.manager.system.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crm.manager.common.base.dao.IBaseDao;
import com.crm.manager.system.dto.ResourceDTO;

@Repository
public interface IResourceDao extends IBaseDao<ResourceDTO, Integer> {

	@Modifying
	@Query(nativeQuery = true,value = "DELETE FROM t_role_resource WHERE resource_id = :id")
	void deleteGrant(@Param("id") Integer id);

	Page<ResourceDTO> findAllByNameContaining(String searchText, Pageable pageable);

	List<ResourceDTO> findAllByOrderByParentAscIdAscSortAsc();

}
