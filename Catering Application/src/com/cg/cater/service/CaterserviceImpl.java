package com.cg.cater.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.cater.bean.CaterBean;
import com.cg.cater.dao.CaterdaoImpl;
import com.cg.cater.dao.ICaterDao;
import com.cg.cater.exception.CaterException;


public class CaterserviceImpl implements ICaterService {
	ICaterDao caterdao=new CaterdaoImpl();

	
	@Override
	public String addCater(CaterBean cater) throws CaterException, ClassNotFoundException, IOException, SQLException {
        String orderseq;
        orderseq=caterdao.addCater(cater);
		return orderseq;
	}
	@Override
	public CaterBean viewCustomerDetails(String donorid) throws CaterException, ClassNotFoundException, SQLException, IOException {
		System.out.println(donorid);
		CaterBean cater=new CaterBean();
		 cater=caterdao.viewCustomerDetails(donorid);
		// System.out.println(caterbean);
		return cater;
	}
	@Override
	public List retrieveAll() throws CaterException, ClassNotFoundException, IOException, SQLException {
		caterdao=new CaterdaoImpl();
		List<CaterBean> caterList=null;
		caterList=caterdao.retrieveAll();
		return caterList;
		
	}


public boolean validateDonor(CaterBean bean)throws CaterException
{
	List<String> validationErrors=new ArrayList<String>();
	
	
	if(!(isValidName(bean.getCustomername())))
	{
		validationErrors.add("\\ncustomer name should be Alphabets and minimum 3 characters long!\\n");
	}
	if(!(isValidAddress(bean.getAddress())))
	{
		validationErrors.add("\\n Address should be greater than 5 characters!\\n");
	}
	if(!(isValidphonenumber(bean.getPhonenumber())))
	{
		validationErrors.add("\\nphone number should be in 10 digits");
	}
	if(!(isValidAmount(bean.getPayAmount())))
	{
		validationErrors.add("\\n Amount should be positive");
	}
	
	if(!validationErrors.isEmpty())
	{
		return false;
		
}return true;
}



private boolean isValidName(String customername) {
	Pattern namePattern=Pattern.compile("^[A-Za-z]{3,}$");
	Matcher nameMatcher=namePattern.matcher(customername);
	return nameMatcher.matches();
     }


private boolean isValidAmount(double custometerAmount) {
	return custometerAmount > 0;
	// TODO Auto-generated method stub
	
}

private boolean isValidAddress(String address) {
	// TODO Auto-generated method stub
	return (address.length()>6);
	
}

private boolean isValidphonenumber(String phonenumber) {
	// TODO Auto-generated method stub
	Pattern phonePattern=Pattern.compile("^[6-9]{1}[0-9]{9}$");
    Matcher phoneMatcher=phonePattern.matcher(phonenumber);
	return phoneMatcher.matches();

	
}
}



















