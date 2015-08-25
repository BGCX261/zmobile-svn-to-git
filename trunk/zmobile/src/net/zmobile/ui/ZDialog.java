package net.zmobile.ui;

import java.util.Vector;

import javax.microedition.lcdui.Graphics;

import net.zmobile.ZMobile;
import net.zmobile.ge.ZPoint;
import net.zmobile.ge.ZRect;
import net.zmobile.ui.layout.ZFillLayout;
import net.zmobile.ui.layout.html.ZHTMLLayout;

public class ZDialog extends ZContainer {

	public static final int BUTTON_YES = 0;

	public static final int BUTTON_OK = 0;

	public static int DIALOG_ERROR;

	public Object dialogMtuex = new Object();

	ZLabel label = new ZLabel(
			"1552648 bytecodes");
	public ZDialog(String str) {
		this();
		this.setText(str);
	}
	public ZDialog() {
		this.setFocus(true);
		this._bodyRect.x = 20;
		this._bodyRect.y = 20;
		this._bodyRect.width = 200;
		this._bodyRect.height = 200;
		
		this._clientRect.x = 20;
		this._clientRect.y = 20+ZThemes.TILTE_HEIGHT;
		this._clientRect.width = 200;
		this._clientRect.height = 200;
		
		setLayout(new ZFillLayout(ZMobile.VERTICAL));
		
		add(label);
		addBtn();
		resize();
		setFoucus();
	}

	public ZDialog(int dialog_error2, String strTitle) {
		this();
	}
	public void addBtn() {
		ZContainer contean = new  ZContainer();
		contean.setLayout(new ZFillLayout());
		ZButton btnNo = new ZButton("Yes");
		btnNo.addListener(ZMobile.EVENT_KEY_SELECT_RELESE, new Listener() {

			public void event(int x, int y, int key,ZComponent comp) {
				try {
					synchronized (ZFormCanvas.dialogMetux) {
						ZFormCanvas.dialogMetux.notify();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				ZFormCanvas.uiStack.pop();
				//ZFormCanvas.pop();
			}

		});
		contean.add(btnNo);
		contean.add(new ZButton("No"));
		add(contean);
	}

	public void draw(Graphics g) {
		g.translate(this.translateX, this.translateY);
//		g.setClip(_x - 1, _y - 1, _w + 2, _h + 2);
		g.setColor(ZThemes.BODY_BACK_COLOR);
		fillRect(g, _bodyRect, 0);

		g.setColor(0xff0000);
		fillRect(g,new ZRect(_bodyRect.x, _bodyRect.y, _bodyRect.width, ZThemes.TILTE_HEIGHT),0);

		g.setColor(0x0);
		setClip(g,_bodyRect,1);
		drawRect(g,_bodyRect,0);

		g.setColor(0x0000ff);
		drawRect(g,_bodyRect,-ZMobile.GRAP);
		
		g.setColor(0x00ff00);
		
		for (int i = 0; i < _components.size(); i++) {
			Object comp = _components.elementAt(i);
			((ZComponent) comp).redraw(g);
		}
		g.translate(-this.translateX, -this.translateY);
		// g.drawRect(_x + ZMobile.GRAP + 1, _y + ZMobile.GRAP + 1, _w - 2
		// * ZMobile.GRAP - 2, _h - 2 * ZMobile.GRAP - 2);
	}

//	private ZForm getZForm() {
//		Stack uiStack = ZFormCanvas.getUIStack();
//		for (int i = uiStack.size() - 1; i >= 0; i--) {
//			if (uiStack.elementAt(i) instanceof ZForm) {
//				return (ZForm) uiStack.elementAt(i);
//			}
//		}
//		return null;
//	}

	public int show() {
//		resize();
//		ZFormCanvas.uiStack.push(this);
//		synchronized (ZFormCanvas.mainThreadMetux) {
//			ZFormCanvas.mainThreadMetux.notify();
//		}
//		//ZForm.instance.repaint();
//		ZFormCanvas.instance.repaint();		
//		try {	
//			synchronized (ZFormCanvas.dialogMetux) {
//				ZFormCanvas.dialogMetux.wait();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return 0;
	}

	public void resize() {
		if (_lManger != null) {
			ZPoint size = _lManger.layout(this);
			_clientRect.width = _bodyRect.width;
			_clientRect.height = size.y;
			
			_bodyRect.width = size.x;
			_bodyRect.height = _clientRect.height+ZThemes.TILTE_HEIGHT;
			
		}
	}

	Vector dialogListeners = new Vector();

	public void addListener(Listener listener) {
		dialogListeners.addElement(listener);
	}
	public void setText(String _text) {
		label.setText(_text);
	}
}
