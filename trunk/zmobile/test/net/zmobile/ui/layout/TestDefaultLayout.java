package net.zmobile.ui.layout;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import net.zmobile.ui.ZForm;
import net.zmobile.ui.ZLabel;

public class TestDefaultLayout extends MIDlet {

	protected void destroyApp(boolean flag) throws MIDletStateChangeException {

	}

	protected void pauseApp() {

	}

	protected void startApp() throws MIDletStateChangeException {
		final ZForm form = new ZForm();
		form.setTitle("ZButtonMIDlet");
		form.setScroll(true);
		ZDefautLayout fillLayout = new ZDefautLayout();
		form.setLayout(fillLayout);

		form.add(new ZLabel("123456789"));
		form.add(new ZLabel("123456789"));
		form.add(new ZLabel("123456789"));
		form.add(new ZLabel("123456789"));
		form.add(new ZLabel("123456789"));
		form.add(new ZLabel("123456789"));
		
		form.setLeftLabel("Left");
		form.setRightLabel("Right");
		form.show(Display.getDisplay(this));

	}

}
