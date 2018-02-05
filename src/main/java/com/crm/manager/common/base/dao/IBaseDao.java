package com.crm.manager.common.base.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.crm.manager.common.base.dto.BaseDTO;

@NoRepositoryBean
public interface IBaseDao<T extends BaseDTO, ID extends Serializable>
        extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

}
