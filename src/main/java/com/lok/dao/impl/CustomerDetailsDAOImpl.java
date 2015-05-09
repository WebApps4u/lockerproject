package com.lok.dao.impl;

import org.springframework.stereotype.Repository;

import com.lok.dao.BaseDAO;
import com.lok.dao.CustomerDetailsDAO;
import com.lok.model.CustomerDetails;


@Repository
public class CustomerDetailsDAOImpl extends BaseDAO<CustomerDetails, String> implements CustomerDetailsDAO {

}
