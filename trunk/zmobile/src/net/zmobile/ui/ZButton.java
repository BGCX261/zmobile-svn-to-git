package net.zmobile.ui;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import net.zmobile.ZMobile;
import net.zmobile.ge.ZPoint;

public class ZButton extends ZComponent {

	Image _image = null;

	public ZButton(String text) {
		setText(text);
	}

	public ZButton(Image image) {
		if (image == null)
			throw new RuntimeException("Error");
		_image = image;
		this._bodyRect.width = image.getWidth();
		this._bodyRect.height = image.getHeight();
	}

	public void draw(Graphics g) {
		if (_font != null) {
			g.setFont(_font);
		}
		g.setColor(192, 192, 192);
		fillRect(g,_bodyRect,-ZMobile.GRAP);
		g.setColor(0);
		Font font = g.getFont();
		if (_image != null) {
			g.setColor(0x0);
			g.drawImage(_image, _bodyRect.x, _bodyRect.y, Graphics.TOP | Graphics.LEFT);
			g.drawRect(_bodyRect.x + ZMobile.GRAP, _bodyRect.y + ZMobile.GRAP, _bodyRect.width - 2
					* ZMobile.GRAP, _bodyRect.height - 2 * ZMobile.GRAP);
		} else {
			g.setColor(0x0);
			drawRect(g,_bodyRect,-ZMobile.GRAP);
			g.drawString(_text, _bodyRect.x + _bodyRect.width / 2 - font.stringWidth(_text) / 2, _bodyRect.y
					+ _bodyRect.height / 2 - font.getHeight() / 2, Graphics.TOP
					| Graphics.LEFT);
		}
	}

	public void drawStatus(Graphics g) {
		if (this._isFoucus) {
			g.setColor(0x0000ff);
			if(this._isPressed){
				g.setColor(0xff0000);
			}
		}else{
			return;
		}
		drawRect(g,_bodyRect,-ZMobile.GRAP);
		drawRect(g,_bodyRect,-2*ZMobile.GRAP);

	}

	public ZPoint computeSize(int w, int h) {
		_bodyRect.width = w==-1?_font.stringWidth(_text):w;
		_bodyRect.height = w==-1?_font.getHeight():h;
		return new ZPoint(_bodyRect.width,_bodyRect.height);
	}
}
