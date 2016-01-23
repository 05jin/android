package com.ray.handler;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;

public class TestHandler extends Activity {
	 protected static final int GUIUPDATEIDENTIFIER = 0x101; 
	 
     Thread myRefreshThread = null; 
     BounceView myBounceView = null; 
 
     Handler myHandler = new Handler() 
     {
          public void handleMessage(Message msg) 
          { 
               switch (msg.what) 
               { 
                    case TestHandler.GUIUPDATEIDENTIFIER: 
                         myBounceView.invalidate();
                         System.out.println("jin "+Thread.currentThread().getStackTrace()[2].toString()+" ");
                         break; 
               } 
               super.handleMessage(msg); 
          } 
     };
     public void onCreate(Bundle savedInstanceState) { 
          super.onCreate(savedInstanceState); 
          this.requestWindowFeature(Window.FEATURE_NO_TITLE); 
 
          this.myBounceView = new BounceView(this);
          this.setContentView(this.myBounceView); 
          new Thread(new myThread()).start();
     } 
 
     class myThread implements Runnable 
     { 
          public void run() 
          {
               while (!Thread.currentThread().isInterrupted()) 
               {  
                     
                    Message message = new Message(); 
                    message.what = TestHandler.GUIUPDATEIDENTIFIER;                     
                    TestHandler.this.myHandler.sendMessage(message); 
                    System.out.println("jin "+Thread.currentThread().getStackTrace()[2].toString()+" ");
                    try { 
                         Thread.sleep(1000);  
                    } catch (InterruptedException e) { 
                         Thread.currentThread().interrupt(); 
                    } 
               } 
          } 
     } 
}

