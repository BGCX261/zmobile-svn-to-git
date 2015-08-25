package net.zmobile.ui;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import net.zmobile.ZMobile;
import net.zmobile.ui.layout.ZFillLayout;

public class TestZTextBox extends MIDlet {

	protected void destroyApp(boolean flag) throws MIDletStateChangeException {

	}

	protected void pauseApp() {

	}

	int proo = 10;
	protected void startApp() throws MIDletStateChangeException {
		ZForm form = new ZForm();
		form.setTitle("ZButtonMIDlet");
		form.setLayout(new ZFillLayout(ZMobile.FILL_VERTICAL));
		form.add(new ZTextBox("Button"));
		form.add(new ZTextBox("Button"));
		form.add(new ZTextBox("Button"));
		form.add(new ZTextBox("Button"));
		form.add(new ZTextBox("Button"));
		form.add(new ZTextBox("Button"));
		form.add(new ZTextBox("Button"));
		form.add(new ZTextBox("Button"));
		form.add(new ZTextBox("Button"));
		form.add(new ZTextBox("Button"));
		form.add(new ZTextBox(""));

		ZTextBox btn = new ZTextBox("Button");
		form.add(btn);
		form.show(Display.getDisplay(this));
	}

}
