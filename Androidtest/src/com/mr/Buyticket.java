package com.mr;

import org.hibernate.Query;
import org.hibernate.Session;



public class Buyticket {
	public static boolean buytickets(String car_id,String num){
		boolean isbuy=false;
		Session session=null;
		try{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			//String hql="update Ticket ticket set ticket_num=ticket_num-"+d+" where car_id="+a+" and origin_num>="+b+" and origin_num<="+c;
			String hql="update Train train set num=num-"+num+" where car_id="+car_id;
			Query queryupdate=session.createQuery(hql);
			int ret=queryupdate.executeUpdate();
			if(ret==1){
				isbuy=true;
			}
			session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return isbuy;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		buytickets("101","100");
	}

}
