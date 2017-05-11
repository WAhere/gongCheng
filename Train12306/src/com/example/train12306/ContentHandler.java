package com.example.train12306;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

import com.example.train12306.Ticket;

public class ContentHandler extends DefaultHandler{
	private List<Ticket> tickets=new ArrayList<Ticket>();
	Ticket ticket=new Ticket();
	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	private String nodeName;

	private StringBuilder car_id;

	private StringBuilder origin;

	private StringBuilder point;
	
	private StringBuilder num;
	
	private StringBuilder price;
	

	@Override
	public void startDocument() throws SAXException {
		car_id = new StringBuilder();
		origin = new StringBuilder();
		point = new StringBuilder();
		num = new StringBuilder();
		price = new StringBuilder();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// 记录当前结点名
		nodeName = localName;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// 根据当前的结点名判断将内容添加到哪一个StringBuilder对象中
		if ("carid".equals(nodeName)) {
			car_id.append(ch, start, length);
		} else if ("origin".equals(nodeName)) {
			origin.append(ch, start, length);
		} else if ("point".equals(nodeName)) {
			point.append(ch, start, length);
		} else if ("num".equals(nodeName)) {
			num.append(ch, start, length);
		} else if ("price".equals(nodeName)) {
			price.append(ch, start, length);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if ("ticket".equals(localName)) {
			ticket.setCarid(car_id.toString().trim());
			ticket.setOrigin(origin.toString().trim());
			ticket.setPoint(point.toString().trim());
			ticket.setNum(num.toString().trim());
			ticket.setPrice(price.toString().trim());
			Log.d("ContentHandler", "car_id is " + car_id.toString().trim());
			Log.d("ContentHandler", "origin is " + origin.toString().trim());
			Log.d("ContentHandler", "point is " + point.toString().trim());
			Log.d("ContentHandler", "num is " + num.toString().trim());
			Log.d("ContentHandler", "price is " + price.toString().trim());
			// 最后要将StringBuilder清空掉
			car_id.setLength(0);
			origin.setLength(0);
			point.setLength(0);
			num.setLength(0);
			price.setLength(0);
			tickets.add(ticket);
			Log.d("Tickets","测试："+ticket.getCarid());
			ticket=new Ticket();
		}
	}

	@Override
	public void endDocument() throws SAXException {
	}


}
