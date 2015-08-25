package net.zmobile;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import net.zmobile.ui.ZButton;
import net.zmobile.ui.ZContainer;
import net.zmobile.ui.ZForm;
import net.zmobile.ui.ZImage;
import net.zmobile.ui.layout.ZFillLayout;
import net.zmobile.ui.layout.ZGridData;
import net.zmobile.ui.layout.ZGridLayout;

public class Test9Cells extends MIDlet {

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {

	}

	protected void pauseApp() {

	}

	protected void startApp() throws MIDletStateChangeException {
		ZForm form = new ZForm();
		form.setTitle("ZButtonMIDlet");
		form.setScroll(true);
		form.setLayout(new ZFillLayout(ZMobile.FILL_VERTICAL));

		add1(form);
		add2(form);
		add3(form);

		form.show(Display.getDisplay(this));
	}
	public void add1(ZForm form) {
		ZContainer container = new ZContainer();
		container.setLayout(new ZFillLayout(ZMobile.FILL_HORIZONTAL));
		container.add(new ZImage("/an/small1.gif"));
		container.add(new ZImage("/an/small2.gif"));
		container.add(new ZImage("/an/small3.gif"));
		form.add(container);
	}
	public void add2(ZForm form) {
		ZContainer container = new ZContainer();
		container.setLayout(new ZFillLayout(ZMobile.FILL_HORIZONTAL));
		container.add(new ZImage("/an/small4.gif"));
		container.add(new ZImage("/an/small5.gif"));
		container.add(new ZImage("/an/small6.gif"));
		form.add(container);
	}
	public void add3(ZForm form) {
		ZContainer container = new ZContainer();
		container.setLayout(new ZFillLayout(ZMobile.FILL_HORIZONTAL));
		container.add(new ZImage("/an/small7.gif"));
		container.add(new ZImage("/an/small8.gif"));
		container.add(new ZImage("/an/small1.gif"));
		form.add(container);
	}

}
