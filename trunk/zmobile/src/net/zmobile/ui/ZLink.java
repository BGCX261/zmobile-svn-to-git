package net.zmobile.ui;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import net.zmobile.ge.ZPoint;
import net.zmobile.ge.ZRect;

public class ZLink extends ZComponent {

	public ZLink(String text) {
		setText(text);
	}

	public void draw(Graphics g) {
		if(_isFoucus)return;
		g.setFont(Font.getFont(_font.getFace(), Font.STYLE_UNDERLINED, _font
				.getSize()));
		g.setColor(0x0000ff);
		drawString(g, _bodyRect, _text);
		g.setFont(_font);
	}

	public void drawStatus(Graphics g) {
		g.setFont(Font.getFont(_font.getFace(), Font.STYLE_UNDERLINED, _font
				.getSize()));

		g.setColor(0x0000ff);
		if (this._isPressed) {
			g.setColor(0xff0000);
		} else {
			g.setColor(0, 0, 255);
			fillRect(g, _bodyRect, 0);
			g.setColor(0xffffff);
		}

		drawString(g, _bodyRect, _text);
		g.setFont(_font);
	}

	public ZPoint computeSize(int w, int h) {
		return new ZPoint(Font.getDefaultFont().stringWidth(_text), Font
				.getDefaultFont().getHeight());
	}

	public void setText(String _text) {
		super.setText(_text);
		_bodyRect.width = _font.stringWidth(_text);
		_bodyRect.height = _font.getHeight();
	}

	public void setBounds(ZRect rect) {
		_bodyRect.width = _font.stringWidth(_text);
		_bodyRect.height = _font.getHeight();
		this._bodyRect.x = rect.x + (rect.width - _bodyRect.width) / 2;
		this._bodyRect.y = rect.y + (rect.height - _bodyRect.height) / 2;
	}
}
