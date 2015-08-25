package net.zmobile.ui;

import java.util.Stack;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import net.zmobile.ZMobile;
import net.zmobile.ge.ZPoint;
import net.zmobile.ge.ZRect;

public class ZFormCanvas extends Canvas implements Runnable {

	Image img = null;
	public static final Stack uiStack = new Stack();
	public static ZFormCanvas instance = null;

	public static int status = 0;

	private ZFormCanvas() {
		
	}

	public static Stack getUIStack() {
		return uiStack;
	}

	public static void push(Object form) {
		uiStack.push(form);
	}

	public static Object pop() {
		return uiStack.pop();
	}

	public static ZFormCanvas getInstance() {
		if (instance == null) {
			instance = new ZFormCanvas();
		}
		return instance;
	}

	protected void paint(Graphics g) {
		if (img == null)
			img = Image.createImage(getWidth(), getHeight());

		Graphics tmpg = img.getGraphics();
		tmpg.setColor(0xffffff);
		tmpg.fillRect(0, 0, getWidth(), getHeight());
		for (int t = 0; t < uiStack.size(); t++) {
			ZContainer container = (ZContainer) uiStack.elementAt(t);
			container.redraw(tmpg);
		}
		g.drawImage(img, 0, 0, Graphics.TOP | Graphics.LEFT);
	}

	protected void pointerDragged(int x, int y) {
		super.pointerDragged(x, y);
	}

	public static Object mainThreadMetux = new Object();
	public static Object dialogMetux = new Object();

	protected void pointerReleased(int i, int j) {
		super.pointerPressed(i, j);
		ZContainer container = (ZContainer) uiStack.peek();
		System.out.println("container.getTranslateY():"+container.getTranslateY());
		procEvent(ZMobile.EVENT_POINTER_RELEASED, i, j-container.getTranslateY());
	}

	int _keyCode = 0;

	protected void keyReleased(int keyCode) {
		super.keyReleased(keyCode);
		this._keyCode = convertKeyCode(keyCode);
		procEvent(ZMobile.EVENT_KEY_RELEASED, 0, 0);
	}

	protected void pointerPressed(int i, int j) {
		super.pointerPressed(i, j);
		ZContainer container = (ZContainer) uiStack.peek();
		System.out.println("container.getTranslateY():"+container.getTranslateY());
		procEvent(ZMobile.EVENT_POINTER_PRESS, i, j-container.getTranslateY());
	}

	private void procEvent(int type, int x, int y) {
		MSG_TYPE = type;
		MSG_POINTER = new ZPoint(x, y);
		try {
			new Thread(this).start();
			synchronized (mainThreadMetux) {
				mainThreadMetux.wait();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		repaint();
	}

	int convertKeyCode(int keyCode) {
		switch (keyCode) {
		case Canvas.KEY_NUM0:
		case Canvas.KEY_NUM1:
		case Canvas.KEY_NUM2:
		case Canvas.KEY_NUM3:
		case Canvas.KEY_NUM4:
		case Canvas.KEY_NUM5:
		case Canvas.KEY_NUM6:
		case Canvas.KEY_NUM7:
		case Canvas.KEY_NUM8:
		case Canvas.KEY_NUM9:
		case Canvas.KEY_STAR:
		case Canvas.KEY_POUND:
			return keyCode;
		default:
			if (keyCode == ZMobile.KEY_LEFT_SOFT
					|| keyCode == ZMobile.KEY_RIGHT_SOFT) {
				return keyCode;
			}
		}
		return getGameAction(keyCode);
	}

	protected void keyPressed(int keyCode) {
		super.keyPressed(keyCode);
		_keyCode = convertKeyCode(keyCode);
		procEvent(ZMobile.EVENT_KEY_PRESS, 0, 0);
	}

	private int MSG_TYPE = ZMobile.EVENT_KEY_RELEASED;
	private ZPoint MSG_POINTER = null;

	public void run() {
		ZContainer container = (ZContainer) uiStack.peek();
		System.out.println("container.getTranslateY():"+container.getTranslateY());
		ZRect rect = container.getBounds();

		switch (MSG_TYPE) {
		case ZMobile.EVENT_KEY_PRESS:
			if (_keyCode == ZMobile.KEY_MIDDLE) {
				container.fireSelectedPressed();
			}
			break;
		case ZMobile.EVENT_POINTER_PRESS:
			if (rect.contains(MSG_POINTER)) {
				container.firePointPressed(MSG_POINTER);
			} else {
				if (container instanceof ZMenu)
					uiStack.pop();
			}
			break;
		case ZMobile.EVENT_KEY_SELECT:
			container.fireSelectedPressed();
			break;
		case ZMobile.EVENT_KEY_SELECT_RELESE:
			container.fireSelectedRelease();
			break;
		case ZMobile.EVENT_KEY_RELEASED: {
			status = 0;
			switch (_keyCode) {
			case Canvas.UP:
			case Canvas.LEFT:
				if (!container.previousFoucus()) {
					container.setFoucus();
				}
				break;
			case Canvas.DOWN:
			case Canvas.RIGHT:
				if (!container.nextFoucus()) {
					container.setFoucus();
					System.out.println("down");
				}
				break;
			case ZMobile.KEY_MIDDLE:
				container.fireSelectedRelease();
				break;
			default:
				if (_keyCode == ZMobile.KEY_LEFT_SOFT
						|| _keyCode == ZMobile.KEY_RIGHT_SOFT) {
					System.out.println("left right key");
				}
			}
			if (container._listeners != null) {
				for (int i = 0; i < container._listeners.size(); i++) {
					if ((ZMobile.EVENT_KEY_RELEASED == ((Byte) container._listenerTypes
							.elementAt(i)).byteValue())) {
						((Listener) container._listeners.elementAt(i))
						.event(0, 0, _keyCode,container);
					}
				}
			}
		}
			break;
		case ZMobile.EVENT_POINTER_RELEASED:
			if (rect.contains(MSG_POINTER)) {
				container.firePointRelease(MSG_POINTER);
			} else {
				if (container instanceof ZMenu)
					uiStack.pop();
			}
			_keyCode = ZMobile.KEY_MIDDLE;
			MSG_TYPE = ZMobile.EVENT_KEY_SELECT_RELESE;
			run();
			break;
		}
		if (!(container instanceof ZDialog)) {
			try {
				synchronized (mainThreadMetux) {
					mainThreadMetux.notify();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if ((container.equals(uiStack.peek()))) {
			try {
				synchronized (mainThreadMetux) {
					mainThreadMetux.notify();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		container = null;
		rect= null;
	}

}
