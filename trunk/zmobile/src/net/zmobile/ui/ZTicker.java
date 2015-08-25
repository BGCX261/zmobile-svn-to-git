package net.zmobile.ui;

import java.util.Timer;
import java.util.TimerTask;

import javax.microedition.lcdui.Graphics;

import net.zmobile.ge.ZPoint;
import net.zmobile.ge.ZRect;

public class ZTicker extends ZComponent {
	public static byte MOVE = 0;
	public static byte PROGRESS = 1;

	private static Timer tm = new Timer();

	private static TimerTask tk = new TimerTask() {
		public void run() {
			ZFormCanvas.getInstance().repaint();
		}
	};
	static {
		tm.schedule(tk, 0, 50);
	}

	public ZTicker(byte type) {
		_type = type;
		this._isCanFoucus = false;
		_bodyRect.height = ZThemes.TICKER_HEIGHT;
	}

	public int _type = MOVE;

	public void draw(Graphics g) {
		updatePostion();
		if (_type == PROGRESS || _type == MOVE) {
			_bodyRect.height = 10;
		}
		setClip(g, this._bodyRect, 1);
		g.setColor(0xffffff);
		fillRect(g, _bodyRect, 0);
		if (_type == MOVE) {
			g.setColor(0x00FF00);
			g.fillRect(startX, _bodyRect.y + 2, 10, _bodyRect.height - 4);
			g.fillRect(startX + 12, _bodyRect.y + 2, 10, _bodyRect.height - 4);
			g.fillRect(startX + 24, _bodyRect.y + 2, 10, _bodyRect.height - 4);
			g.fillRect(startX + 36, _bodyRect.y + 2, 10, _bodyRect.height - 4);
		} else if (_type == PROGRESS) {
			g.setColor(0x0000FF);
			g.fillRect(_bodyRect.x + 2, _bodyRect.y + 2, (_bodyRect.width - 4)
					* progress / 100, _bodyRect.height - 4);
		}
		g.setColor(0x0);
		drawRect(g, _bodyRect, 0);
	}

	boolean flag = false;
	int startX = _bodyRect.x;
	int INC = 2;

	void updatePostion() {
		if (_type == MOVE) {
			if (flag) {
				startX -= INC;
			} else {
				startX += INC;
			}
			if (startX <= _bodyRect.x) {
				flag = false;
			} else if (startX + 46 > _bodyRect.width) {
				flag = true;
			}
		} else {
			if (flag) {
				startX -= INC;
			} else {
				startX += INC;
			}
			if (startX <= _bodyRect.x) {
				flag = false;
			} else if (startX + _bodyRect.x > _bodyRect.width) {
				flag = true;
			}
		}
	}

	private int progress = 0;

	public void setProgress(int prog) {
		if (prog > 100)
			prog = 100;
		if (prog < 0)
			prog = 0;

		this.progress = prog;
	}

	/**
	 * Can not be focus.
	 */
	public void drawStatus(Graphics g) {

	}

	/**
	 * The size is max size of width.
	 */
	public ZPoint computeSize(int w, int h) {
		if(w==-1)
			_bodyRect.width = ZFormCanvas.getInstance().getWidth();
		return new ZPoint(w, ZThemes.TICKER_HEIGHT);
	}

	/**
	 * The Ticker postion is always in center of vertical and max size of width.
	 */
	public void setBounds(ZRect rect) {
		_bodyRect.x = rect.x;
		_bodyRect.y = rect.y;
		if (rect.width > 0) {
			_bodyRect.width = 50;//rect.width;
		}
	}

}
