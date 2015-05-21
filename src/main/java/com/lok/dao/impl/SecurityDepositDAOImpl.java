package com.lok.dao.impl;

import org.springframework.stereotype.Repository;

import com.lok.dao.BaseDAO;
import com.lok.dao.SecurityDepositDAO;
import com.lok.model.SecurityDeposit;

@Repository
public class SecurityDepositDAOImpl extends BaseDAO<SecurityDeposit, String> implements SecurityDepositDAO {

}
