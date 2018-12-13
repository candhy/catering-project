package com.cg.cater.pl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.cg.cater.bean.CaterBean;
import com.cg.cater.exception.CaterException;
import com.cg.cater.service.CaterserviceImpl;
import com.cg.cater.service.ICaterService;

public class CaterMain {
	
	static Scanner sc=new Scanner(System.in);
	static ICaterService caterService=null;
	static CaterserviceImpl caterServiceImpl=null;

    
	public static void main(String[] args) throws ClassNotFoundException, CaterException, SQLException, IOException {
		 
		    CaterBean caterBean=null;
		    String caterId=null;
		    int option=0;
		    while(true)
		    {
			 System.out.println();
			 System.out.println();
			 System.out.println("Welcome to PIZZA  HUNTT");
			 System.out.println("________________________________");
			 System.out.println("1.Provide ur details");
			 System.out.println("2.ur orderid with ur payment");
			 System.out.println("3.Retrieve Donor");
			 System.out.println("4.Exit");
			 System.out.println("______________________________");
			 System.out.println("select the option");
			 try 
			 {
				option=sc.nextInt();
				    switch(option)
				    {
				              case 1:
					                 while(caterBean==null)
					                      {
						                  caterBean=populateDonorBean();
					                      }
					                 try
					                      {
						                   caterService=new CaterserviceImpl();
						                   caterId=caterService.addCater(caterBean);
						                   System.out.println("customer details has been successfully registered");
						                   System.out.println("customer order id is :"+caterId);
					                       }
					                 catch(CaterException caterException)
				                          {
						                  System.err.println("ERROR :"+caterException.getMessage());
					                      caterException.printStackTrace();
					                      }
					                finally
					                      {
					                	 caterId=null;
					                	 caterService=null;
					                	 caterBean=null;
				                          }
					       break;
					          
				           case 2:
					                    System.out.println("enter donor_id");
					                    caterId=sc.next();
					                    caterService=new CaterserviceImpl();
					                    caterBean= caterService.viewCustomerDetails(caterId);
					                    System.out.println(caterBean);
					       break;
				            case 3:
				            	
								try {
									caterService = new CaterserviceImpl();
									List<CaterBean> donorList = new ArrayList<CaterBean>();
									donorList = caterService.retrieveAll();

									if (donorList != null) {
										Iterator<CaterBean> i = donorList.iterator();
										while (i.hasNext()) {
											System.out.println(i.next());
										}
									} else {
										System.out
												.println("Nobody has made a donation, yet.");
									}

								}

								catch (CaterException e) {

									System.out.println("Error  :" + e.getMessage());
									e.printStackTrace();
								}

								break;
						
					       
				        case 4:
				        	   

								System.out.print("Exit Trust Application");
								System.exit(0);
								break;
						default:
								System.out.println("Enter a valid option[1-4]");
							}// end of switch
						}

						catch (InputMismatchException e) {
							sc.nextLine();
							System.err.println("Please enter a numeric value, try again");
						}
						
					       break;
				          
						
				}
		 

	}

	private static CaterBean populateDonorBean() {
		CaterBean cater=new CaterBean();
		System.out.println("Enter details");
		System.out.println("Enter the customer name:");
		cater.setCustomername(sc.next());
		System.out.println("Enter the customer Contact");
		cater.setPhonenumber(sc.next());
		System.out.println("Enter the donor Address");
		cater.setAddress(sc.next());
		System.out.println("Enter the donor amount");
		try
		{
		cater.setPayAmount(sc.nextFloat());
		}
		catch(InputMismatchException ime)
		{
			sc.nextLine();
			System.err.println("please enter the numeric value for donation amount,try again");
		}
		caterServiceImpl=new CaterserviceImpl();
		try
		{
		if(caterServiceImpl.validateDonor(cater));
		return cater;
		}
		catch(CaterException donorException)
		{
			System.out.println("Invalid data");
			System.err.println(donorException.getMessage()+"\n Try Again...");
			donorException.printStackTrace();
			System.exit(0);
			
		}
		return null;
	}

}












