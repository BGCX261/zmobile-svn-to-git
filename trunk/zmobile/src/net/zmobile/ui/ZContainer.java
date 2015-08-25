package net.zmobile.ui;

import java.util.Vector;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

import net.zmobile.ZMobile;
import net.zmobile.ge.ZPoint;
import net.zmobile.ge.ZRect;
import net.zmobile.ui.layout.ZLayout;

public class ZContainer extends ZComponent {
	public Vector getComponents() {
		return _components;
	}
	protected Vector _components = new Vector();
	protected ZLayout _lManger = null;

	int translateX = 0;
	int translateY = 0;

	protected ZRect _clientRect = new ZRect(0, 0, 0, 0);;
	protected ZPoint _contentSize = new ZPoint(0, 0);
	protected ZRect scrollRect = new ZRect(0, 0, 0, 0);

	int SCROLL_GRAP = 2;
	int scrollPos = 0;
	public ZContainer() {
		_isCanFoucus = true;
		this.addListener(ZMobile.EVENT_KEY_RELEASED, new Listener() {

			public void event(int x, int y, int key,ZComponent comp) {
				if (key == Canvas.DOWN) {
					translateY -= 10;
					if (translateY < ZContainer.this._clientRect.height
							- ZContainer.this._contentSize.y) {
						translateY = ZContainer.this._clientRect.height
								- ZContainer.this._contentSize.y;
					}
				} else if (key == Canvas.UP) {
					if (translateY < 0) {
						translateY += 10;
						if (translateY > 0)
							translateY = 0;
					}
				}
				ZContainer.this.resizeScroll();
			}

		});
	}

	public void add(ZComponent comp) {
		_components.addElement(comp);
		resize();
	}

	public void remove(ZComponent comp) {
		_components.removeElement(comp);
		resize();
	}

	public void setLayout(ZLayout lManger) {
		_lManger = lManger;
		resize();
	}

	public void resize() {
		if (_lManger != null) {
			_contentSize = _lManger.layout(this);
		}
		if (_isScroll)
			resizeScroll();
	}

	public void firePointRelease(ZPoint point) {
		super.firePointRelease(point);
		// ZRect rect = new ZRect(_x, _y, _w, _h);
		for (int i = 0; i < _components.size(); i++) {
			Object comp = _components.elementAt(i);
			((ZComponent) comp).firePointRelease(point);
		}
		if (_isScroll) {
			ZRect rectHeader = new ZRect(scrollRect.x, scrollRect.y,
					scrollRect.width, scrollRect.width);
			System.out.println(rectHeader);
			if (rectHeader.contains(point)) {
				scrollPos -= 10;
				if (scrollPos < 0)
					scrollPos = 0;
			}
			// footer
			ZRect rectFooter = new ZRect(scrollRect.x, scrollRect.y
					+ scrollRect.height - scrollRect.width, scrollRect.width,
					scrollRect.width);
			System.out.println(rectFooter);
			if (rectFooter.contains(point)) {
				scrollPos += 10;
				if (scrollRect.y + scrollRect.height - scrollRect.width < scrollPos) {
					scrollPos = scrollRect.y + scrollRect.height
							- scrollRect.width;
				}
			}
		}
	}

	public void firePointPressed(ZPoint point) {
		super.firePointRelease(point);
		// ZRect rect = new ZRect(_x, _y, _w, _h);
		for (int i = 0; i < _components.size(); i++) {
			Object comp = _components.elementAt(i);
			((ZComponent) comp).firePointPressed(point);
		}

	}

	public void fireSelectedRelease() {
		super.fireSelectedRelease();
		for (int i = 0; i < _components.size(); i++) {
			Object comp = _components.elementAt(i);
			((ZComponent) comp).fireSelectedRelease();
		}

	}

	public void fireSelectedPressed() {
		super.fireSelectedPressed();

		for (int i = 0; i < _components.size(); i++) {
			Object comp = _components.elementAt(i);
			((ZComponent) comp).fireSelectedPressed();
		}

	}

	public void draw(Graphics g) {
		g.setColor(0x0);
		g.translate(this.translateX, this.translateY);
		for (int i = 0; i < _components.size(); i++) {
			Object comp = _components.elementAt(i);
			((ZComponent) comp).redraw(g);
		}
		drawRect(g, new ZRect(_bodyRect.x,_bodyRect.y,_contentSize.x,_contentSize.y), 0);
		g.translate(-this.translateX, -this.translateY);
		if (this._isScroll) {
			drawScroll(g);
		}
	}

	public void drawScroll(Graphics g) {
		if (_contentSize.y != 0) {
			scrollPos = 100 * this._clientRect.height / _contentSize.y;
		} else {
			scrollPos = 0;
		}

		g.setClip(scrollRect.x, scrollRect.y, scrollRect.width + 1,
				scrollRect.height + 1);

		g.setColor(0xaaaaaa);
		// head
		// g.fillRect(scrollRect.x, scrollRect.y, scrollRect.width,
		// scrollRect.width);
		// // footer
		// g.fillRect(scrollRect.x, scrollRect.y + scrollRect.height
		// - scrollRect.width, scrollRect.width, scrollRect.width);
		// body
		g.fillRect(scrollRect.x, scrollRect.y + scrollPos, scrollRect.width,
				3 * scrollRect.width);

		g.setColor(0x0);
		// body
		g.drawRect(scrollRect.x, scrollRect.y, scrollRect.width,
				scrollRect.height);

		// all rect
		g.drawRect(scrollRect.x, scrollRect.y, scrollRect.width,
				scrollRect.height);
		// // header
		// g.drawRect(scrollRect.x, scrollRect.y, scrollRect.width,
		// scrollRect.width);
		// body
		g.drawRect(scrollRect.x, scrollRect.y + scrollPos, scrollRect.width,
				3 * scrollRect.width);
		// // footer
		// g.drawRect(scrollRect.x, scrollRect.y + scrollRect.height
		// - scrollRect.width, scrollRect.width, scrollRect.width);
	}

	public boolean setFoucus() {
		for (int i = 0; i < _components.size(); i++) {
			ZComponent comp = (ZComponent) _components.elementAt(i);
			if (comp instanceof ZContainer) {
				if (((ZContainer) comp).setFoucus()) {
					return true;
				}
			} else {
				if (comp._isCanFoucus) {
					comp.setFocus(true);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @param status
	 *            0 1
	 * @return
	 */
	public boolean nextFoucus() {
		for (int i = 0; i < _components.size(); i++) {
			ZComponent comp = (ZComponent) _components.elementAt(i);
			if (ZFormCanvas.status == 0) {
				if (comp instanceof ZContainer) {
					if (((ZContainer) comp).nextFoucus()) {
						return true;
					}
				} else {
					if (comp._isFoucus) {
						comp.setFocus(false);
						ZFormCanvas.status = 1;
					}
				}
			} else {
				if (comp instanceof ZContainer) {
					if (((ZContainer) comp).nextFoucus()) {
						return true;
					}
				} else {
					if (comp.isCanFoucus()) {
						comp.setFocus(true);
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean previousFoucus() {
		for (int i = _components.size() - 1; i >= 0; i--) {
			ZComponent comp = (ZComponent) _components.elementAt(i);
			if (ZFormCanvas.status == 0) {
				if (comp instanceof ZContainer) {
					if (((ZContainer) comp).previousFoucus()) {
						return true;
					}
				} else {
					if (comp._isFoucus) {
						comp.setFocus(false);
						ZFormCanvas.status = 1;
					}
				}
			} else {
				if (comp instanceof ZContainer) {
					if (((ZContainer) comp).previousFoucus()) {
						return true;
					}
				} else {
					if (comp.isCanFoucus()) {
						comp.setFocus(true);
						return true;
					}
				}
			}
		}
		if (this instanceof ZForm) {
			for (int i = _components.size() - 1; i >= 0; i--) {
				ZComponent comp = (ZComponent) _components.elementAt(i);
				if (comp instanceof ZContainer) {
					if (((ZContainer) comp).setFoucus()) {
						return true;
					}
				} else {
					if (comp._isCanFoucus) {
						comp.setFocus(true);
						return true;
					}
				}
			}
		}
		return false;
	}

	public void drawStatus(Graphics g) {

	}

	public ZRect getClientArea() {
		return _clientRect;
	}

	/**
	 * Set bounds and client area size, if the container is scrollable, the
	 * client area <b>not include</b> scroll bar.
	 */
	public void setBounds(ZRect rect) {
		super.setBounds(rect);
		this._clientRect.x = rect.x;
		this._clientRect.y = rect.y;
		if (rect.width > 0) {
			this._clientRect.width = _isScroll ? rect.width
					- ZThemes.SCROLL_WIDTH : rect.width;
		}
		if (rect.height > 0) {
			this._clientRect.height = rect.height;
		}
		resize();
	}

	private boolean _isScroll = false;

	public void setScroll(boolean isScroll) {
		_isScroll = isScroll;
	}

	public boolean isScroll() {
		return _isScroll;
	}

	private void resizeScroll() {
		
//		 scrollRect.x = _bodyRect.x+this._clientRect.width;
//		 scrollRect.y = _clientRect.height + SCROLL_GRAP;
//		
//		 scrollRect.height = _contentSize.y==0?_clientRect.height:this._clientRect.height * this._clientRect.height / _contentSize.y;
//		 if (scrollRect.height < scrollRect.width) {
//			 scrollRect.height = scrollRect.width;
//		 }
//
//		 scrollPos = -translateY*_clientRect.height/_contentSize.y;
	}

	public int getTranslateX() {
		return translateX;
	}

	public void setTranslateX(int translateX) {
		this.translateX = translateX;
	}

	public int getTranslateY() {
		return translateY;
	}

	public void setTranslateY(int translateY) {
		this.translateY = translateY;
	}

	public ZPoint computeSize(int w, int h) {
		return new ZPoint(this._bodyRect.width,this._bodyRect.height);
	}
}
