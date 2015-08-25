package net.zmobile.ui;


import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import net.zmobile.ge.ZPoint;

import com.sun.midp.lcdui.TextCursor;

public class StringItem extends ZComponent {

	private String _label = "";
	public StringItem(String label, String text) {
		this(label, text, 0);
	}

	static int CONTENT_HEIGHT = 0;
	static{
		CONTENT_HEIGHT = Font.getDefaultFont().getHeight();
	}
	public StringItem(String label, String text, int appearanceMode) {
		_font = Font.getDefaultFont();
		this._label = label;
			switch (appearanceMode) {
			case 0: // '\0'
			case 1: // '\001'
			case 2: // '\002'
				this.appearanceMode = appearanceMode;
				break;

			default:
				throw new IllegalArgumentException();
			}
			_text = text;
			int labelFontHeight = LABEL_FONT.getHeight();
			minimumLineHeight = CONTENT_HEIGHT;
			if (minimumLineHeight < labelFontHeight)
				minimumLineHeight = labelFontHeight;
			checkTraverse();
	}

	public String getText() {
		return _text;
	}

	public void setText(String text) {
		_text = text;
	}

	public int getAppearanceMode() {
		return appearanceMode;
	}

	public void setFont(Font font) {
		this._font = font;
	}

	public Font getFont() {
		return _font;
	}

	public void setPreferredSize(int width, int height) {
//		this._w = width;
//		this._h = height;
	}

	int callMinimumWidth() {
		int pW = callPreferredWidth(-1);
		int w = _font.charWidth('W') * 8 + 6 * _font.charWidth('.');
		if (w > pW)
			w = pW;
		if (isButton())
			w += 10;
		return w;
	}

	int callPreferredWidth(int h) {
		if (isButton()) {
			int buttonPad = 10;
			int prefW = Text.getTwoStringsWidth(_label, _text, LABEL_FONT, _font,
					_bodyRect.width - buttonPad, 2);
			return prefW <= 0 ? 0 : prefW + buttonPad;
		} else {
			return Text.getTwoStringsWidth(_label, _text, LABEL_FONT, _font,
					_bodyRect.width, 2);
		}
	}

	int callMinimumHeight() {
		return callPreferredHeight(-1);
	}

	int callPreferredHeight(int w) {
		if (w == -1)
			w = _bodyRect.width;
		if (isButton()) {
			int buttonPad = 10;
			int prefH = Text.getTwoStringsHeight(_label, _text,LABEL_FONT, _font,
					w - buttonPad, 2);
			return prefH <= 0 ? 0 : prefH + buttonPad;
		} else {
			return Text.getTwoStringsHeight(_label, _text,LABEL_FONT, _font, w, 2);
		}
	}

	boolean equateNLB() {
		if (_label != null && _label.length() > 0) {
			if (_label.charAt(0) == '\n')
				return true;
		} else if (_text != null && _text.length() > 0) {
			if (_text.charAt(0) == '\n')
				return true;
		} else {
			return false;
		}
//		if ((layout & 16384) == 16384)
//			return (layout & 256) == 256;
//		else
			return _label != null && _label.length() > 0;
	}

	boolean equateNLA() {
		if (_text != null && _text.length() > 0) {
			if (_text.charAt(_text.length() - 1) == '\n')
				return true;
		} else if (_label != null && _label.length() > 0) {
			if (_label.charAt(_label.length() - 1) == '\n')
				return true;
		} else {
			return false;
		}
		return false;
	}

	boolean shouldSkipTraverse() {
		if ((_label == null || _label.length() == 0)
				&& (_text == null || _text.length() == 0))
			return true;
		else
			return skipTraverse;
	}

	Font LABEL_FONT = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
	int numCommands = 0;
	void callPaint(Graphics g, int width, int height) {
		int w = width;
		int h = height;
		int translateY = 0;
		int translateX = 0;
		if (isButton()) {
			translateX = translateY = 5;
			w -= 2 * translateX;
			h -= 2 * translateY;
			g.translate(translateX, translateY);
		}
		Font lFont = LABEL_FONT;
		int labelHeight = getLabelHeight(w);
		int offset = Text.paint(_label, lFont, g, w, labelHeight, 0, 0, new TextCursor(1));
		if (offset > 0)
			offset += 2;
		int mode = 3;
		if (numCommands > 0 && appearanceMode == 1)
			mode = _isFoucus ? 3 : 2;
		int yOffset = labelHeight;
		if (labelHeight > 0)
			yOffset -= lFont.getHeight() >= _font.getHeight() ? _font.getHeight()
					: lFont.getHeight();
		g.translate(0, yOffset);
		translateY += yOffset;
		Text.paint(_text, _font, g, w, h - yOffset, offset, mode, null);
		g.translate(-translateX, -translateY);
		if (isButton())
			drawButtonBorder(g, 0, 0, width, height, _isFoucus);
	}
	void drawButtonBorder(Graphics g, int x, int y, int w, int h,
			boolean hasFocus) {
		g.setColor(hasFocus ? 6316128 : 11513775);
		g.fillRect(x, y, w, 3);
		g.fillRect(x, y, 3, h);
		g.setColor(hasFocus ? 11513775 : 6316128);
		g.fillTriangle(x, y + h, x + 3, (y + h) - 3, x + 3, y + h);
		g.fillRect(x + 3, (y + h) - 3, w - 3, 3);
		g.fillTriangle(x + w, y, (x + w) - 3, y + 3, x + w, y + 3);
		g.fillRect((x + w) - 3, y + 3, 3, h - 3);
	}
	int getLabelHeight(int w) {
		if (_label == null || _label.length() == 0)
			return 0;
		if (w == -1)
			w = _bodyRect.width;
		return Text.getHeightForWidth(_label, LABEL_FONT, w, 0);
	}
	void callKeyPressed(int keyCode) {
//		if (keyCode != Display.KEYCODE_SELECT)
//			return;
//		if (getCommandCount() <= 0 || commandListener == null)
//			return;
//		ItemCommandListener cl = null;
//		Command defaultCmd = null;
//		synchronized (Display.LCDUILock) {
//			cl = commandListener;
//			defaultCmd = defaultCommand;
//		}
//		if (cl != null)
//			try {
//				synchronized (Display.calloutLock) {
//					if (defaultCmd != null)
//						cl.commandAction(defaultCmd, this);
//				}
//			} catch (Throwable thr) {
//				Display.handleThrowable(thr);
//			}
	}

	void addCommandImpl(Command cmd) {
			if (numCommands >= 1 && appearanceMode == 0)
				appearanceMode = originalAppearanceMode != 2 ? 1 : 2;
			checkTraverse();
	}

	void removeCommandImpl(Command cmd) {
			if (numCommands < 1 && appearanceMode != 0) {
				originalAppearanceMode = appearanceMode;
				appearanceMode = 0;
			}
			checkTraverse();
	}

	private void checkTraverse() {
		if (_text == null && _label == null)
			skipTraverse = true;
		else if (_text == null && _label.trim().equals(""))
			skipTraverse = true;
		else if (_label == null && _text.trim().equals(""))
			skipTraverse = true;
		else
			skipTraverse = false;
	}

	private boolean isButton() {
		return numCommands > 0 && appearanceMode == 2;
	}

	private int appearanceMode;
	private int originalAppearanceMode;
	private int minimumLineHeight;
	private boolean skipTraverse;

	public void drawStatus(Graphics g) {
		
	}

	public void defaultSize(int width) {
		
	}
	Image _image = null;
	public void draw(Graphics g) {
		_bodyRect.width = 50;
		_bodyRect.height = 50;
		if(_bodyRect.width>0&&_bodyRect.height>0&&_image==null)
		{
			_image = Image.createImage(_bodyRect.width,_bodyRect.height);
		}
		this.callPaint(_image.getGraphics(), _bodyRect.width , _bodyRect.height);
		g.drawImage(_image, _bodyRect.x, _bodyRect.y,Graphics.LEFT|Graphics.TOP);
	}

	public ZPoint computeSize(int w, int h) {
		// TODO Auto-generated method stub
		return null;
	}
}
