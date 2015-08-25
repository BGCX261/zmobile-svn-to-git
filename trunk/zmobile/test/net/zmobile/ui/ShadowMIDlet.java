package net.zmobile.ui;

import java.io.IOException;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class ShadowMIDlet extends MIDlet {
	Canvas c = new ShadowCanvas();

	public ShadowMIDlet() {
	}

	protected void startApp() throws MIDletStateChangeException {
		Display.getDisplay(this).setCurrent(c);
	}

	protected void pauseApp() {
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
	}
}

/**
 * 
 * @author Jagie
 * 
 */
class ShadowCanvas extends Canvas implements Runnable {
	int w, h;
	// 原始图片
	Image srcImage;
	// 原始图片的像素数组
	int[] srcRgbImage;
	// 渐变图片的像素数组
	int[] shadowRgbImage;
	int imgWidth, imgHeight;
	int count;

	public ShadowCanvas() {
		w = this.getWidth();
		h = this.getHeight();
		try {
			srcImage = Image.createImage("/marble.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		imgWidth = srcImage.getWidth();
		imgHeight = srcImage.getHeight();
		// 制造原始图片的像素数组，用一个int来代表每一个像素,按位表示方式是:0xAARRGGBB
		srcRgbImage = new int[imgWidth * imgHeight];
		// 获取原始图片的所有像素，参见MIDP APPI文档
		srcImage.getRGB(srcRgbImage, 0, imgWidth, 0, 0, imgWidth, imgHeight);
		shadowRgbImage = new int[srcRgbImage.length];
		System.arraycopy(srcRgbImage, 0, shadowRgbImage, 0,
				shadowRgbImage.length);
		// 渐变图片的所有像素已开始都是全透明的
		for (int i = 0; i < shadowRgbImage.length; i++) {
			shadowRgbImage[i] &= 0x00ffffff;
		}
		new Thread(this).start();
	}

	Image image = Image.createImage(240, 240);

	public void paint(Graphics g) {
		Graphics tmpg = image.getGraphics();
		tmpg.setColor(255, 255, 255);
		tmpg.fillRect(0, 0, w, h);
		// 绘制渐变图片
		tmpg.drawRGB(shadowRgbImage, 0, imgWidth, (w - imgWidth) / 2,
				(h - imgHeight) / 2, imgWidth, imgHeight, true);
		tmpg.setColor(0, 255, 0);
		tmpg.drawString("count=" + count, w / 2, 30, Graphics.HCENTER
				| Graphics.TOP);
		g.drawImage(image, 0, 0, Graphics.TOP | Graphics.LEFT);
	}

	public void run() {
		while (true) {
			boolean changed = false;
			// 改变渐变图片的每一个像素
			for (int i = 0; i < shadowRgbImage.length; i++) {
				// 获取渐变图片的某一像素的alpha值
				int alpha = (shadowRgbImage[i] & 0xff000000) >>> 24;
				// 原始图片的对应像素的alpha值
				int oldAlpha = (srcRgbImage[i] & 0xff000000) >>> 24;
				if (alpha < oldAlpha) {
					// alpha值++
					shadowRgbImage[i] = ((alpha + 3) << 24)
							| (shadowRgbImage[i] & 0x00ffffff);
					changed = true;
				}
			}
			count++;
			repaint();
			// 当所有像素的alpha值都达到原始值后,线程运行结束
			if (!changed) {
				System.out.println("over");
				break;
			}
		}
	}
}
