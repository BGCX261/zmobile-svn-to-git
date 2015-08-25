package net.zmobile.ui;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import net.zmobile.ZMobile;
import net.zmobile.ge.ZPoint;

public class ZLabel extends ZComponent {

	public ZLabel(String text) {
		_font = Font.getDefaultFont();
		_text = text;
		_isCanFoucus = false;
	}


	public void draw(Graphics g) {
		g.setColor(0x0);
		g.translate(_bodyRect.x,_bodyRect.y);
		Text.paint(_text, _font, g, _bodyRect.width,
				_bodyRect.height, 0, Text.NORMAL, null);
		g.translate(-_bodyRect.x,-_bodyRect.y);
	}

	public void drawStatus(Graphics g) {
		if (this._isFoucus) {
			g.setStrokeStyle(Graphics.DOTTED);
			drawRect(g, this._bodyRect, -ZMobile.GRAP);
			g.setStrokeStyle(Graphics.SOLID);
		}
	}

	public ZPoint computeSize(int w, int h) {
		_bodyRect.width = w==-1?_font.stringWidth(_text)+4*ZThemes.LABEL_H_GRAP:w;
		_bodyRect.height = w==-1?_font.getHeight():Text.getHeightForWidth(_text, _font,
				_bodyRect.width, 0);
		return new ZPoint(_bodyRect.width, _bodyRect.height);
	}
}
