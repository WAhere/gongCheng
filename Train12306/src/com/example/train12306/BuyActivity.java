package com.example.train12306;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuyActivity extends Activity implements OnClickListener{
	
	public static final int SHOW_RESPONSE = 0;

	private Button btnbuy;
	private String id;
	private String num;
	private EditText edittrain;
	private EditText editnumber;

	private TextView responseText;

	private Handler handler = new Handler() {

	public void handleMessage(Message msg) {
		switch (msg.what) {
		case SHOW_RESPONSE:
			String response = (String) msg.obj;
			//显示结果
			Toast.makeText(BuyActivity.this, response, Toast.LENGTH_LONG).show();
			}
		}

	};
	private EditText _id;
	private EditText _num;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buy);
		Button btnbuy = (Button) findViewById(R.id.btnbuy);
		_id = (EditText) findViewById(R.id.edittrain);//获取对话框中的EditText
		_num = (EditText) findViewById(R.id.editnumber);
		
    
		responseText = (TextView) findViewById(R.id.response_text);
		btnbuy.setOnClickListener((OnClickListener) this);
	}		

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btnbuy) {
			id = _id.getText().toString();
			num = _num.getText().toString();
			btnbuyWithHttpURLConnection(id,num);
		}
	}

	private void btnbuyWithHttpURLConnection(final String id,final String num) {
		// 开启线程来发起网络请求
		new Thread(new Runnable() {
			@Override
			public void run() {
				HttpURLConnection connection = null;
				try {
					//中文读取理解
					String origins=URLEncoder.encode(id,"utf-8");
					String points=URLEncoder.encode(num,"utf-8");
					//连接
					URL url = new URL("http://192.168.43.60:8080/Androidtest/Buyservlet?car_id="+id+"&num="+num);
					connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setConnectTimeout(8000);
					connection.setReadTimeout(8000);
					InputStream in = connection.getInputStream();
					// 下面对获取到的输入流进行读取
					BufferedReader reader = new BufferedReader(new InputStreamReader(in));
					StringBuilder response = new StringBuilder();
					String line;
					while ((line = reader.readLine()) != null) {
						response.append(line);
					}
					Message message = new Message();
					message.what = SHOW_RESPONSE;
					// 将服务器返回的结果存放到Message中
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
}