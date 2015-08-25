package net.zmobile.ui;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import net.zmobile.ge.ZPoint;

public class ZStatusButton extends ZComponent {
	public static byte RADIO_BUTTON = 0;
	public static byte CHECK_BUTTON = 1;

	boolean isChecked = false;

	int GRAP = 1;
	byte _type = RADIO_BUTTON;

	public ZStatusButton(String text, byte type) {
		_type = type;
		this._text = text;
		_font = Font.getDefaultFont();
	}

	public ZStatusButton(byte check_button2) {
	}

	public void draw(Graphics g) {
		if (_font == null)
			_font = g.getFont();
		g.setColor(0x0);
		g.drawString(_text, this._bodyRect.x + 16, this._bodyRect.y, Graphics.TOP | Graphics.LEFT);
	}

	public void drawStatus(Graphics g) {
		if (_type==RADIO_BUTTON) {
			if (!isChecked) {
				g.drawArc(this._bodyRect.x + GRAP, this._bodyRect.y + GRAP, 14, 14, 0, 360);
			} else {
				g.drawArc(this._bodyRect.x + GRAP, this._bodyRect.y + GRAP, 14, 14, 0, 360);
				g.fillArc(this._bodyRect.x + 4 + GRAP, this._bodyRect.y + 4 + GRAP, 6, 6, 0, 360);
			}
		}else{
			if(isChecked){
				//g.drawImage(CHECK_BUTTON_ON,x+GRAP, y+GRAP, Graphics.LEFT|Graphics.TOP);
				g.drawRect(this._bodyRect.x+GRAP, this._bodyRect.y+GRAP, 14, 14);
				g.drawLine(this._bodyRect.x+GRAP, this._bodyRect.y+GRAP, this._bodyRect.x+GRAP+14, this._bodyRect.y+GRAP+14);
				g.drawLine(this._bodyRect.x+GRAP+14, this._bodyRect.y+GRAP, this._bodyRect.x+GRAP, this._bodyRect.y+GRAP+14);
			}else{
				//g.drawImage(CHECK_BUTTON_OFF,x+GRAP, y+GRAP, Graphics.LEFT|Graphics.TOP);
				g.drawRect(this._bodyRect.x+GRAP, this._bodyRect.y+GRAP, 14, 14);
			}
		}
		setClip(g,_bodyRect,0);
		if (_isFoucus) {
			g.setStrokeStyle(Graphics.DOTTED);
			drawRect(g,this._bodyRect,0);
			g.setStrokeStyle(Graphics.SOLID);
		}
	}

	public void fireSelectedRelease() {
		super.fireSelectedRelease();
		if (_isFoucus)
			isChecked = !isChecked;
	}

	public ZPoint computeSize(int w, int h) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setStatus(boolean b) {
		
	}

}
