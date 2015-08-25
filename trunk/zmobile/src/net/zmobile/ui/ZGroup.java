package net.zmobile.ui;

import javax.microedition.lcdui.Graphics;

import net.zmobile.ui.layout.ZDefautLayout;

public class ZGroup extends ZContainer {

	int grap = 4;
	public ZGroup(String text){
		_text = text;
		this.setLayout(new ZDefautLayout());
	}
	public void draw(Graphics g) {
		setClip(g,_bodyRect,0);	
		g.setColor(ZThemes.GROUP_COLOR);
		g.drawRoundRect(this._bodyRect.x+4, this._bodyRect.y+_font.getHeight()/2, this._bodyRect.width-8, this._bodyRect.height-4-_font.getHeight()/2,ZThemes.ACR_HEIGHT,ZThemes.ACR_HEIGHT);
		
		g.setColor(ZThemes.BODY_BACK_COLOR);
		g.fillRect(this._bodyRect.x+4+ZThemes.ACR_HEIGHT, this._bodyRect.y, _font.stringWidth(_text), _font.getHeight());
		
		g.setColor(0x0000ff);
		g.drawString(_text, this._bodyRect.x+4+ZThemes.ACR_HEIGHT, this._bodyRect.y, Graphics.TOP|Graphics.LEFT);
		super.draw(g);
	}
	protected void resizeClientBounds() {
//		_client_x = _x+4+ZThemes.ACR_HEIGHT;
//		_client_y = _y+_font.getHeight()/2+ZThemes.ACR_HEIGHT;
//		_client_w =  _w-8-ZThemes.ACR_HEIGHT;
//		_client_h = _h-4-_font.getHeight()/2-ZThemes.ACR_HEIGHT;
	}

}
