package net.zmobile.ui;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import net.zmobile.ZMobile;
import net.zmobile.ge.ZPoint;
import net.zmobile.ge.ZRect;

public class ZImage extends ZComponent {

	Image _image = null;
	public ZImage(Image image){
		_image = image;
		_bodyRect.width = _image.getWidth();
		_bodyRect.height = _image.getHeight();
	}
	public ZImage(String string) {
		try {
			_image = Image.createImage(string);
			_bodyRect.width = _image.getWidth();
			_bodyRect.height = _image.getHeight();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void draw(Graphics g) {
		g.setColor(0x0);
		drawRect(g,_bodyRect,0);
		if (_image != null) {
			g.drawImage(_image, _bodyRect.x, _bodyRect.y, Graphics.TOP | Graphics.LEFT);
		}else{
			if(ZThemes.NULL_IMAGE!=null){
				g.drawImage(ZThemes.NULL_IMAGE, _bodyRect.x, _bodyRect.y, Graphics.TOP | Graphics.LEFT);
			}
		}
		
	}
	public void drawStatus(Graphics g) {
		if (_isFoucus) {
			g.setColor(0x0000ff);
			if(_isPressed){
				g.setColor(0xff0000);
			}
		}else{
			return;
		}
		
		drawRect(g,_bodyRect,0);
		drawRect(g,_bodyRect,-ZMobile.GRAP);
	}

	public ZPoint computeSize(int w, int h) {
		return new ZPoint(_bodyRect.width,_bodyRect.height);
	}
	public void setBounds(ZRect rect) {
		this._bodyRect.x = rect.x;
		this._bodyRect.y = rect.y;
	}
}
