package net.zmobile.ui;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import net.zmobile.ZMobile;
import net.zmobile.ui.layout.ZFillLayout;

public class TestZMenu extends MIDlet {

	protected void destroyApp(boolean flag) throws MIDletStateChangeException {

	}

	protected void pauseApp() {

	}

	protected void startApp() throws MIDletStateChangeException {
		final ZForm form = new ZForm();
		form.setTitle("ZButtonMIDlet");
		form.setScroll(true);
		ZFillLayout fillLayout = new ZFillLayout(ZMobile.FILL_VERTICAL);
		form.setLayout(fillLayout);
		
		form.setLeftLabel("Left");
		form.setRightLabel("Right");
		
		form.show(Display.getDisplay(this));
	}

}
