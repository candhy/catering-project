package com.cg.cater.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.cg.cater.bean.CaterBean;
import com.cg.cater.exception.CaterException;


public interface ICaterDao {
	
	public String addCater(CaterBean cater) throws   ClassNotFoundException, IOException, SQLException, CaterException;
	public CaterBean viewCustomerDetails(String donorid)throws  CaterException, SQLException, ClassNotFoundException, IOException;
	public List retrieveAll()throws  CaterException, ClassNotFoundException, IOException, SQLException;

          
}
