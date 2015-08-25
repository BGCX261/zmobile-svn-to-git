package net.zmobile.ui;

import java.io.IOException;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Image;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import net.zmobile.ZMobile;
import net.zmobile.ui.layout.ZFillLayout;

public class TestZAnimation extends MIDlet {

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		// TODO Auto-generated method stub

	}

	protected void pauseApp() {
		// TODO Auto-generated method stub

	}

	protected void startApp() throws MIDletStateChangeException {
		final ZForm form = new ZForm();
		form.setTitle("ZButtonMIDlet");
		form.setScroll(true);
		ZFillLayout fillLayout = new ZFillLayout(ZMobile.FILL_VERTICAL);
		fillLayout.spacing = 1;
		form.setLayout(fillLayout);
		form.add(new ZButton("Button"));
		form.add(new ZButton("Button"));
		form.add(new ZAnimation(new String[] { "/an/small1.gif",
				"/an/small2.gif", "/an/small3.gif", "/an/small4.gif",
				"/an/small5.gif", "/an/small6.gif", "/an/small7.gif",
				"/an/small8.gif", }));

		form.setLeftLabel("Left");
		form.setRightLabel("Right");
		form.show(Display.getDisplay(this));
	}
}
