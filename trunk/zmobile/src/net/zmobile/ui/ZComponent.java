package net.zmobile.ui;

import java.util.Vector;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import net.zmobile.ZMobile;
import net.zmobile.ge.ZPoint;
import net.zmobile.ge.ZRect;

public abstract class ZComponent {

	protected ZRect _bodyRect = new ZRect(0, 0, 0, 0);
	Object _layoutData = null;

	public void setLayoutData(Object layoutData) {
		_layoutData = layoutData;
	}

	public Object getLayoutData() {
		return _layoutData;
	}

	protected Font _font = Font.getDefaultFont();
	String _text = "";
	int _color = 0xffffff;

	protected boolean _isFoucus = false;
	protected boolean _isCanFoucus = true;
	protected boolean _isPressed = false;

	Vector _listeners = null;
	Vector _listenerTypes = null;

	// new Vector();
	public Vector getListeners() {
		if (_listeners == null) {
			_listeners = new Vector();
		}

		return _listeners;
	}

	public Vector getListenersTypes() {
		if (_listenerTypes == null) {
			_listenerTypes = new Vector();
		}

		return _listenerTypes;
	}

	public void addListener(byte type, Listener listener) {
		getListenersTypes().addElement(new Byte(type));
		getListeners().addElement(listener);
	}

	public boolean isCanFoucus() {
		return _isCanFoucus;
	}

	public boolean isFoucus() {
		return _isFoucus;
	}

	public void setFocus(boolean foucus) {
		_isFoucus = foucus;
	}

	public boolean isPressed() {
		return _isPressed;
	}

	public void setPressed(boolean isPressed) {
		this._isPressed = isPressed;
	}

	public void setBounds(ZRect rect) {
		this._bodyRect.x = rect.x;
		this._bodyRect.y = rect.y;
		if(rect.width>0){
			this._bodyRect.width = rect.width;			
		}
		if(rect.height>0){
			this._bodyRect.height = rect.height;			
		}
	}

	public ZRect getBounds() {
		return _bodyRect;
	}

	public abstract void draw(Graphics g);

	public void redraw(Graphics g) {
		g.setClip(_bodyRect.x - ZMobile.GRAP, _bodyRect.y - ZMobile.GRAP,
				_bodyRect.width + 2 * ZMobile.GRAP, _bodyRect.height + 2
						* ZMobile.GRAP);
		draw(g);
		if(_isFoucus)drawStatus(g);
	}

	public abstract void drawStatus(Graphics g);

	public Font getFont() {
		return _font;
	}

	public void setFont(Font font) {
		this._font = font;
	}

	public String getText() {
		return _text;
	}

	public void setText(String _text) {
		this._text = _text;
	}

	public int getColor() {
		return _color;
	}

	public void setColor(int _color) {
		this._color = _color;
	}

	public void firePointRelease(ZPoint point) {
		setPressed(false);
		if (_bodyRect.contains(point)) {
			fireEvent(ZMobile.EVENT_POINTER_RELEASED, point.x, point.y, 0);
		}
	}

	public void firePointPressed(ZPoint point) {
		if (_bodyRect.contains(point)) {
			setFocus(true);
			setPressed(true);
			fireEvent(ZMobile.EVENT_POINTER_PRESS, point.x, point.y, 0);
		} else {
			setFocus(false);
			setPressed(false);
		}
	}

	public void fireSelectedRelease() {
		if (_isFoucus) {
			setPressed(false);
			fireEvent(ZMobile.EVENT_KEY_SELECT_RELESE, 0, 0, 0);
		}
	}

	public void fireSelectedPressed() {
		if (_isFoucus) {
			setPressed(true);
			fireEvent(ZMobile.EVENT_KEY_SELECT, 0, 0, 0);
		}
	}

	private void fireEvent(int type, int x, int y, int key) {
		if (_listeners != null) {
			for (int index = 0; index < _listeners.size(); index++) {
				if ((type |((Byte) _listenerTypes.elementAt(index))
						.byteValue())!=0) {
					((Listener) _listeners.elementAt(index)).event(x, y, key,ZComponent.this);
				}
			}
		}
	}
	int foreColor = 0x0;
	int backColor = 0x0;
	public void setForeground(int color){
		foreColor = color;
	}
	public void setBackground(int color){
		backColor = color;
	}
	/**
	 * -1 compute your perfered size.
	 * @param w
	 * @param h
	 * @return
	 */
	public abstract ZPoint computeSize(int w, int h);
	public final static void drawRect(Graphics g,ZRect rect,int offset){
		g.drawRect(rect.x-offset, rect.y-offset, rect.width+2*offset, rect.height+2*offset);
	}
	public final static void fillRect(Graphics g,ZRect rect,int offset){
		g.fillRect(rect.x-offset, rect.y-offset, rect.width+2*offset, rect.height+2*offset);
	}
	public final static void setClip(Graphics g,ZRect rect,int offset){
		g.setClip(rect.x-offset, rect.y-offset, rect.width+2*offset, rect.height+2*offset);
	}
	public final static void drawString(Graphics g,ZRect rect,String text){
		g.drawString(text, rect.x, rect.y, Graphics.TOP | Graphics.LEFT);
	}

	public String toString() {
		return _bodyRect.toString();
	}
}
