package net.zmobile.ui.html;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Graphics;

import net.zmobile.ge.ZRect;
import net.zmobile.ui.ZContainer;
import net.zmobile.ui.ZFormCanvas;
import net.zmobile.ui.ZThemes;
import net.zmobile.ui.layout.html.ZHTMLLayout;

public class ZHTMLForm extends ZContainer {

	ZFormCanvas canvas = ZFormCanvas.instance;

	public static ZHTMLForm instance = null;

	public ZHTMLForm() {
		instance = this;
		canvas = ZFormCanvas.getInstance();
		setFullScreen(true);
		setBounds(new ZRect(0, 0, canvas.getWidth(), canvas.getHeight()));
		setLayout(new ZHTMLLayout());
	}
	
	public void setAlign(int align){
		add(new ZAlign(align));
	}

	public void repaint() {
		canvas.repaint();
	}

	private void setFullScreen(boolean value) {
		canvas.setFullScreenMode(value);
		setBounds(new ZRect(0, 0, canvas.getWidth(), canvas.getHeight()));
		resize();
		canvas.repaint();
	}

	public static Display display = null;
	int backColor = 0x0;
	int foreColor = 0x0;
	public void setBackground(int rgb){
		backColor = rgb;
	}
	public void show(Display display) {
		ZFormCanvas.getUIStack().empty();
		ZFormCanvas.push(this);
		setFocus(true);
		canvas.repaint();
		display.setCurrent(canvas);
		ZHTMLForm.display = display;
	}

	String _title = "";

	public void setTitle(String title) {
		_title = title;
	}

	public String getTitle() {
		return _title;
	}

	public void draw(Graphics g) {
		g.setColor(ZThemes.BODY_BACK_COLOR);
		fillRect(g, _bodyRect, 0);
		super.draw(g);
		g.setColor(0x0);
		drawRect(g, new ZRect(50,50,_contentSize.x,_contentSize.y), -5);
		drawTitle(g);
		drawSoftKey(g);
	}

	protected void drawTitle(Graphics g) {
		if (getTitle() == null || getTitle().equals(""))
			return;
		g.setClip(0, 0, _bodyRect.width + 1, ZThemes.TILTE_HEIGHT + 1);

		g.setColor(ZThemes.TILTE_BACK_COLOR);
		g.fillRect(0, 0, _bodyRect.width, ZThemes.TILTE_HEIGHT);

		g.setColor(0);
		g.drawLine(0, ZThemes.TILTE_HEIGHT, _bodyRect.width,
				ZThemes.TILTE_HEIGHT);

		g.setColor(ZThemes.TILTE_FORE_COLOR);
		g.drawString(getTitle(), 2, 0, Graphics.TOP | Graphics.LEFT);
	}

	private String leftLabel = "";

	public String getLeftLabel() {
		return leftLabel;
	}

	public void setLeftLabel(String leftLabel) {
		this.leftLabel = leftLabel;
	}

	public String getRightLabel() {
		return rightLabel;
	}

	public void setRightLabel(String rightLabel) {
		this.rightLabel = rightLabel;
	}

	private String rightLabel = "";

	protected void drawSoftKey(Graphics g) {
		g.setClip(0, _bodyRect.height - ZThemes.FOOTER_HEIGHT,
				_bodyRect.width + 1, ZThemes.FOOTER_HEIGHT + 1);

		g.setColor(ZThemes.FOOTER_BACK_COLOR);
		g.fillRect(0, _bodyRect.height - ZThemes.FOOTER_HEIGHT,
				_bodyRect.width, _bodyRect.height - ZThemes.FOOTER_HEIGHT);

		g.setColor(0);
		g.drawLine(0, _bodyRect.height - ZThemes.FOOTER_HEIGHT,
				_bodyRect.width, _bodyRect.height - ZThemes.FOOTER_HEIGHT);

		g.setColor(ZThemes.FOOTER_FORE_COLOR);
		g.drawString(leftLabel, 0, _bodyRect.height - ZThemes.FOOTER_HEIGHT,
				Graphics.TOP | Graphics.LEFT);
		g.drawString(rightLabel, _bodyRect.width
				- _font.stringWidth(rightLabel), _bodyRect.height
				- ZThemes.FOOTER_HEIGHT, Graphics.TOP | Graphics.LEFT);
	}

	public void setBounds(ZRect rect) {
		super.setBounds(rect);
		this._clientRect.x = rect.x;
		this._clientRect.y = rect.y + ZThemes.TILTE_HEIGHT;
		if (rect.width > 0) {
			this._clientRect.width = isScroll() ? rect.width
					- ZThemes.SCROLL_WIDTH : rect.width;
		}
		if (rect.height > 0) {
			this._clientRect.height = rect.height - ZThemes.TILTE_HEIGHT
					- ZThemes.FOOTER_HEIGHT;
		}
		resize();
	}
}
