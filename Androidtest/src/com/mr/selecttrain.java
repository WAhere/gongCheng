package com.mr;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;



public class selecttrain {
	public static List<Train> selects(String city1,String city2){
		List emplist=new ArrayList();
		Session session=null;
		try{
			session=HibernateUtil.getSession();
			String hql="from Train train where origin=? and point=?";
			Query q=session.createQuery(hql);
			q.setParameter(0, city1);
			q.setParameter(1,city2);
			emplist=q.list();
			System.out.println(emplist.size());
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return emplist;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Train> emplist=new ArrayList<Train>();
		emplist=selects("±±¾©","ÎÂÖÝ");
		for(Train train:emplist){
			System.out.println(train.getCar_id()+train.getOrigin()+train.getPoint());
		}
	}

}
