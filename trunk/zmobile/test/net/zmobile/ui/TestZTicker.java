package net.zmobile.ui;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import net.zmobile.ZMobile;
import net.zmobile.ui.layout.ZFillLayout;

public class TestZTicker extends MIDlet {

	protected void destroyApp(boolean flag) throws MIDletStateChangeException {

	}

	protected void pauseApp() {

	}

	int proo = 10;
	protected void startApp() throws MIDletStateChangeException {
		ZForm form = new ZForm();
		form.setTitle("ZButtonMIDlet");
		form.setLayout(new ZFillLayout(ZMobile.FILL_VERTICAL));
		form.add(new ZButton("Button"));
		form.add(new ZTicker(ZTicker.MOVE));
		final ZTicker ticker = new ZTicker(ZTicker.PROGRESS);
		ZButton btn = new ZButton("Button");
		btn.addListener(ZMobile.EVENT_POINTER_RELEASED, new Listener(){

			public void event(int x, int y, int key, ZComponent src) {
				proo +=10;
				ticker.setProgress(proo);
			}
			
		});
		form.add(btn);
		form.add(ticker);
		form.show(Display.getDisplay(this));
	}

}
