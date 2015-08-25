package net.zmobile.ui;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

public class SplashCanvas extends Canvas implements Runnable{  
	  
    Image img;  
    Image imgBuff;  
    Graphics ibg;  
    int w,h;  
    int num=0;  
    double angle;  
    int wave;  
  
    public SplashCanvas() {  
        this.setFullScreenMode(true);  
        try{  
            init();  
        }  
        catch(Exception e){  
  
        }  
    }  
  
    public void init() throws Exception  
    {  
          img=Image.createImage("/marble.png");  
          w=img.getWidth();  
          h=img.getHeight();  
          imgBuff=Image.createImage(2*w,h);  
          ibg=imgBuff.getGraphics();  
          ibg.drawImage(img,0,0,0);  
     }  
  
    public void paint(Graphics g) {  
        g.setColor(255,255,255);  
        g.fillRect(0,0,g.getClipWidth(),g.getClipHeight());  
        angle=num*Math.PI/6;  
        double parm = 14.0;  
        for(int i=0;i<100;i++)
        {  
              wave=(int)((i/parm+1)*Math.sin(h/parm*(h-i)/(i+1)+angle));  
                  ibg.drawRegion(img, 0, i, w, 1, 0, 0, i + wave, 0);  
  
        }  
        num=++num%12;  
        g.drawImage(img,10,0,0);  
        g.drawRegion(imgBuff,0,0,2*w,h,Sprite.TRANS_MIRROR_ROT180,10,h,0);  
  
    }  
  
    public void run(){  
        while(true){  
            try{  
                Thread.sleep(80);  
                repaint();  
            }  
            catch(Exception e){  
  
            }  
        }  
    }  
}  
