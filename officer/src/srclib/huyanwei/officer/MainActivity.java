package srclib.huyanwei.officer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private String SOLDIER_SOCKET = "soldier" ;
	
	private Button  btn_sender;
	
	public void notify_soldier(String cmd)
	{
		LocalSocketAddress address;
		LocalSocket Socket = null;
	
		OutputStream mOutputStream = null;
		InputStream mInputStream = null;
	
		BufferedWriter Writer;
		BufferedReader Reader;
	
		if(Socket == null)
		{
			Socket = new LocalSocket();
		}
		
		address = new LocalSocketAddress(SOLDIER_SOCKET,LocalSocketAddress.Namespace.RESERVED);
		
		try {
			Socket.connect(address);
			Socket.setSoTimeout(2500);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		
		try {
			mInputStream  = Socket.getInputStream();
			mOutputStream = Socket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Writer =  new BufferedWriter(new OutputStreamWriter(mOutputStream),256);
        Reader = new BufferedReader(new InputStreamReader(mInputStream), 256);
		
        Log.v("soldier","huyanwei debug cmd="+cmd);
        
		if(Socket != null)
		{
			try {
				//Writer.write(cmd);
				//Writer.flush();
				mOutputStream.write(cmd.getBytes());
				mOutputStream.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}						
		}
		
/*		
		try {
			 String s = Reader.readLine();			 
			 Log.e("soldier", s);
			 
			 mInputStream.close();
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/				
		try {
			if(mOutputStream != null)
			{
				mOutputStream.close();
				mOutputStream = null;
			}
			if(mInputStream != null)
			{
				mInputStream.close();
				mInputStream  = null;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		try {
			if(Socket != null)
			{
				Socket.close();
				Socket = null;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}
	
	Handler mHandler = new Handler() {
//        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
		Log.e("soldier", "handleMessage("+msg+")");
        	
        	Bundle bundle =	msg.getData();
        	String cmd = bundle.getString("CMD");        	
        	notify_soldier(cmd);
        	
            super.handleMessage(msg);
        }
    };	
    
    public void send_command(String cmd)
    {    	
    	Message msg = new Message();
		msg.setTarget(mHandler);
		Bundle bundle = new Bundle();
		bundle.putString("CMD",cmd);		
		msg.setData(bundle);
		msg.sendToTarget();		
    }
    
	private View.OnClickListener btn_OnClickListener = new View.OnClickListener() 
	{
		public void onClick(View arg0) {
			switch(arg0.getId())
			{
				case R.id.sender:
					Log.e("soldier", "send_command(control enable\\0)");
					send_command("control enable\0");
					break;
				default:
					break;			
			}
		}		
	};	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn_sender = (Button)findViewById(R.id.sender);		
		btn_sender.setOnClickListener(btn_OnClickListener); 
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
