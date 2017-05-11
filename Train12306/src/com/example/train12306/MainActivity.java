package com.example.train12306;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnload = (Button)findViewById(R.id.btnload);
		final EditText edit_user = (EditText)findViewById(R.id.users);
		final EditText edit_password = (EditText)findViewById(R.id.password);
        
		/*按钮点击事件*/
        btnload.setOnClickListener(new View.OnClickListener() {
		
		@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(edit_user.getText().toString().equals("") ||
					edit_password.getText().toString().equals("")){
					Toast.makeText(getApplicationContext(), "请核实正确信息", Toast.LENGTH_LONG).show();	
				}
			    else if(edit_user.getText().toString().equals("wcczwyxf")
						&& edit_password.getText().toString().equals("123456")){
					Intent intent = new Intent();
					intent.setClass(MainActivity.this, BookingActivity.class);
					startActivity(intent);					
				}
			    else
			    {
			    	Toast.makeText(getApplicationContext(), "用户名或密码错误！", Toast.LENGTH_LONG).show();
			    }
			}
        });       
        /*setOnClickListener是set方法，就是给一个listener，如果发生了click，这个listener就会响应。*/     		
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
