package net.zmobile.ui;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import net.zmobile.ge.ZPoint;

public class ZAnimation extends ZComponent {
	Image[] image = null;
	int index =0;
	private static Timer tm = new Timer();

	private static TimerTask tk = new TimerTask() {
		public void run() {
			ZFormCanvas.getInstance().repaint();
		}
	};
	static {
		tm.schedule(tk, 0, 160);
	}
	public ZAnimation(String[] images){
		image = new Image[images.length];
		for (int i = 0; i < images.length; i++) {
			try {
				image[i] = Image.createImage(images[i]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public ZPoint computeSize(int w, int h) {
		return new ZPoint(image[0].getWidth(),image[0].getHeight());
	}

	public void draw(Graphics g) {
		g.drawImage(image[index],_bodyRect.x, _bodyRect.y, Graphics.TOP|Graphics.LEFT);
		index = (index+1)%image.length;
	}

	public void drawStatus(Graphics g) {

	}

}
