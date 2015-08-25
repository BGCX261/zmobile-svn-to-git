package net.zmobile.ui.layout;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import net.zmobile.ui.ZButton;
import net.zmobile.ui.ZForm;
import net.zmobile.ui.layout.ZGridData;
import net.zmobile.ui.layout.ZGridLayout;

public class TestGridLayout extends MIDlet {

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {

	}

	protected void pauseApp() {

	}

	protected void startApp() throws MIDletStateChangeException {
		ZForm form = new ZForm();
		form.setTitle("ZButtonMIDlet");
		form.setScroll(true);
		form.setLayout(new ZGridLayout(3, true));

		add(form);
		add(form);
		add(form);
		add(form);
		add(form);
		add(form);
//		add(form);
		
		ZButton button4 = new ZButton("Button");
		button4.setText("B4");
		ZGridData gridData = new ZGridData();
		gridData.horizontalAlignment = ZGridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalSpan = 3;
		button4.setLayoutData(gridData);

		form.add(button4);
		form.show(Display.getDisplay(this));
	}

	public void add(ZForm form) {
		ZButton button4 = new ZButton("Button");
		button4.setText("B4");
		ZGridData gridData = new ZGridData();
		gridData.horizontalAlignment = ZGridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessHorizontalSpace = true;
		button4.setLayoutData(gridData);

		form.add(button4);
	}
}
