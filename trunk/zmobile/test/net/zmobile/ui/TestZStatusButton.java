package net.zmobile.ui;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import net.zmobile.ui.layout.html.ZHTMLLayout;

public class TestZStatusButton extends MIDlet {

	protected void destroyApp(boolean flag) throws MIDletStateChangeException {

	}

	protected void pauseApp() {

	}

	protected void startApp() throws MIDletStateChangeException {
		ZForm form = new ZForm();
		form.setTitle("ZButtonMIDlet");
		form.setLayout(new ZHTMLLayout());
		form.add(new ZStatusButton("Button",ZStatusButton.RADIO_BUTTON));
		form.add(new ZStatusButton("Button",ZStatusButton.RADIO_BUTTON));
		form.add(new ZStatusButton("Button",ZStatusButton.RADIO_BUTTON));
		form.add(new ZStatusButton("Button",ZStatusButton.RADIO_BUTTON));
		
		form.add(new ZStatusButton("Button",ZStatusButton.CHECK_BUTTON));
		form.add(new ZStatusButton("Button",ZStatusButton.CHECK_BUTTON));
		form.add(new ZStatusButton("Button",ZStatusButton.CHECK_BUTTON));
		form.add(new ZStatusButton("Button",ZStatusButton.CHECK_BUTTON));
		form.add(new ZStatusButton("Button",ZStatusButton.CHECK_BUTTON));

		form.show(Display.getDisplay(this));
	}

}
