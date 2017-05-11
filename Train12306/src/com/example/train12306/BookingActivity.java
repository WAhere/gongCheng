package com.example.train12306;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.DataOutputStream;
import java.io.StringReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;

import org.xml.sax.XMLReader;

import android.os.Handler;
import android.os.Message;
import android.util.Log;


import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import android.R.string;
import android.widget.EditText;



public class BookingActivity extends Activity implements OnClickListener{

	public static final int SHOW_RESPONSE = 0;

	private Button btn_search;
	private Button btnbuyticket;
	private String origin;
	private String point;
	private EditText edit_start;
	private EditText edit_end;
	
	private TextView responseText;

	private Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SHOW_RESPONSE:
				String response = (String) msg.obj;
				//��ʾ���
				parseXMLWithSAX(response);
			}
		}
	};
	

	
	private EditText _origin;
	private EditText _point;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        Button btnsearch = (Button) findViewById(R.id.btn_search);
        _origin = (EditText) findViewById(R.id.edit_start);//��ȡ�Ի����е�EditText
        _point = (EditText) findViewById(R.id.edit_end);
       /* String string1 = edit_start.getText().toString();
		String string2 = edit_end.getText().toString();*/
        
		responseText = (TextView) findViewById(R.id.response_text);
		btnsearch.setOnClickListener((OnClickListener) this);
		Button btnbuyticket=(Button)findViewById(R.id.btnbuyticket);
		btnbuyticket.setOnClickListener(this);
    }
	@Override
	public void onClick(View v) {
		/*switch (v.getId()) {
		case R.id.btn_search:
			String string1 = edit_start.getText().toString();
			String string2 = edit_end.getText().toString();
		}*/
		if (v.getId() == R.id.btn_search) {
			origin=_origin.getText().toString();
			point=_point.getText().toString();
			btnsearchWithHttpURLConnection(origin,point);
		}
		if(v.getId()==R.id.btnbuyticket){
					Intent intent = new Intent(BookingActivity.this, BuyActivity.class);
					startActivity(intent);
		}
	}
    
	private void btnsearchWithHttpURLConnection(final String origin,final String point) {
		// �����߳���������������
		new Thread(new Runnable() {
			@Override
			public void run() {
				HttpURLConnection connection = null;
				try {
					//���Ķ�ȡ���
					String origins=URLEncoder.encode(origin,"utf-8");
					String points=URLEncoder.encode(point,"utf-8");
					//����
					URL url = new URL("http://192.168.43.60:8080/Androidtest/Selectservlet?origin="+origins+"&point="+points);
					connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setConnectTimeout(8000);
					connection.setReadTimeout(8000);
					InputStream in = connection.getInputStream();
					// ����Ի�ȡ�������������ж�ȡ
					BufferedReader reader = new BufferedReader(new InputStreamReader(in));
					StringBuilder response = new StringBuilder();
					String line;
					while ((line = reader.readLine()) != null) {
						response.append(line);
					}
					Message message = new Message();
					message.what = SHOW_RESPONSE;
					// �����������صĽ����ŵ�Message��
					message.obj = response.toString();
					handler.sendMessage(message);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (connection != null) {
						connection.disconnect();
					}
				}
			}
		}).start();
	}
	
	private void parseXMLWithSAX(String xmlData) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			XMLReader xmlReader = factory.newSAXParser().getXMLReader();
			com.example.train12306.ContentHandler handler=new com.example.train12306.ContentHandler();
			// ��ContentHandler��ʵ�����õ�XMLReader��
			xmlReader.setContentHandler(handler);
			// ��ʼִ�н���
			xmlReader.parse(new InputSource(new StringReader(xmlData)));
			List<Ticket> tickets=new ArrayList<Ticket>();
			tickets=handler.getTickets();
			Log.d("mainactivyt",tickets.size()+"tiao");
			StringBuilder response=new StringBuilder();
			for(Ticket ticket:tickets){
				Log.d("mainactivity",ticket.getCarid());
				response.append(ticket.getCarid()+",	"+ticket.getOrigin()+",	"+ticket.getPoint()+",	"+ticket.getNum()+",	"+ticket.getPrice());
			}
			responseText.setText(response.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}  
}	
