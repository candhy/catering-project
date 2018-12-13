package com.cg.cater.dao;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cg.cater.bean.CaterBean;
import com.cg.cater.exception.CaterException;
import com.cg.cater.util.dbConnection;

public class CaterdaoImpl implements ICaterDao{

 
	
  @Override
  
  public String addCater(CaterBean cater) throws  CaterException, ClassNotFoundException, IOException, SQLException{
		Connection connection=dbConnection.getconnection();
		ResultSet resultset=null;
		PreparedStatement preparedstatement=null;
		String orderid=null;
		int queryresult=0;
		
		try
		{
			preparedstatement=connection.prepareStatement("insert into Donor_Details values(donorId_sequence.nextVal,?,?,?,Sysdate,?)");
			preparedstatement.setString(1, cater.getOrderid());//that 1 represent the first question mark,
			preparedstatement.setString(2,cater.getAddress() );
			preparedstatement.setString(3, cater.getPhonenumber());
			preparedstatement.setDouble(4, cater.getPayAmount());
			preparedstatement.executeUpdate();
			Statement st=null;
			st=connection.createStatement();
			resultset=st.executeQuery("select * from Donor_Details");
			while(resultset.next())
				{ 
				orderid=resultset.getString(1);
				}
			                       /*donorid=resultset.getString(2);
			                        donorid=resultset.getString(3);
			                        donorid=resultset.getString(4);
			                        donorid=resultset.getString(5);*/
			                                                       
			}
	  catch(Exception sql)
		    {
			System.out.println(sql);
			sql.printStackTrace();
		    }
		
		    return orderid;
      }

	
	
	
	@Override
	
	public CaterBean viewCustomerDetails(String donorid)throws  CaterException, ClassNotFoundException, IOException, SQLException{
		Connection connection=dbConnection.getconnection();
		ResultSet resultset=null;
		Statement st=null;
		CaterBean cater=new CaterBean();
		st=connection.createStatement();
		resultset=st.executeQuery("select *from donor_details where donor_id="+donorid+"");
		
		while(resultset.next())
		{
		cater.setOrderid(resultset.getString(1));
		cater.setCustomername(resultset.getString(2));
		cater.setAddress(resultset.getString(3));
		cater.setPhonenumber(resultset.getString(4));
		cater.setDeleverydate(resultset.getDate(5));
		cater.setPayAmount(resultset.getDouble(6));
		}
		return cater;
	}

	
	
	@Override
	public List retrieveAll() throws  CaterException, ClassNotFoundException, IOException, SQLException {

		Connection connection=dbConnection.getconnection();
		int caterCount = 0;
		
		PreparedStatement ps=null;
		ResultSet resultset = null;
		
		List<CaterBean> caterList=new ArrayList<CaterBean>();
		try
		{
			ps=connection.prepareStatement("SELECT * FROM donor_details");
			resultset=ps.executeQuery();
			
			while(resultset.next())
			{	
				CaterBean cater=new CaterBean();
				cater.setOrderid(resultset.getString(1));
				cater.setCustomername(resultset.getString(2));
				cater.setAddress(resultset.getString(3));
				cater.setPhonenumber(resultset.getString(4));
				cater.setDeleverydate(resultset.getDate(5));
				cater.setPayAmount(resultset.getDouble(6));
				caterList.add(cater);
				
		        caterCount++;
			}			
			
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			throw new  CaterException("Tehnical problem occured. Refer log");
		}
		
		finally
		{
			try 
			{
				resultset.close();
				ps.close();
				connection.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
				throw new  CaterException("Error in closing db connection");

			}
		}
		
		if( caterCount == 0)
			return null;
		else
			return caterList;
	
		

}
}