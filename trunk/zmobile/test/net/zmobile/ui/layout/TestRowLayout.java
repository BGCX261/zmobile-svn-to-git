package net.zmobile.ui.layout;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import net.zmobile.ZMobile;
import net.zmobile.ui.ZButton;
import net.zmobile.ui.ZForm;

public class TestRowLayout extends MIDlet {

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {

	}

	protected void pauseApp() {

	}

	protected void startApp() throws MIDletStateChangeException {
		ZForm form = new ZForm();
		form.setTitle("ZButtonMIDlet");
		
		ZRowLayout rowLayout = new ZRowLayout();
		rowLayout.wrap = false;
		rowLayout.pack = false;
		rowLayout.justify = true;
		rowLayout.type = ZMobile.FILL_VERTICAL;
		rowLayout.spacing = 0;
		
		
		form.setLayout(rowLayout);

		ZButton button1 = new ZButton("button");
		button1.setText("Button 1");
		button1.setLayoutData(new ZRowData(10, 20));
		form.add(button1);
		
		ZButton button2 = new ZButton("button");
		button2.setText("Button 2");
		button2.setLayoutData(new ZRowData(20, 30));
		form.add(button2);
		
		ZButton button3 = new ZButton("button");
		button3.setText("Button 3");
		button3.setLayoutData(new ZRowData(30, 20));
		form.add(button3);

		form.show(Display.getDisplay(this));
	}
}
