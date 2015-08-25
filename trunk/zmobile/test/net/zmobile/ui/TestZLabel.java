package net.zmobile.ui;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import net.zmobile.ZMobile;
import net.zmobile.ui.layout.ZDefautLayout;
import net.zmobile.ui.layout.ZFillLayout;

public class TestZLabel extends MIDlet {

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {

	}

	protected void pauseApp() {

	}

	protected void startApp() throws MIDletStateChangeException {
		ZForm form = new ZForm();
		form.setTitle("We are necer!");
//
		form.setLayout(new ZDefautLayout());

		form.add(new ZLabel("活动，切实为返乡农民工   全文>>"));

		addContainerLabel(form);
//		addContainerLink(form);
//		addContainerImage(form);
//		addContainerImage(form);
//		addContainerImage(form);
//		addContainerRadioButton(form);
//		ZMenu menu = new ZMenu();
//
//		ZDialog dialog = new ZDialog();
//		dialog.show();
		
		form.show(Display.getDisplay(this));
	}

	public void addContainerZButton(ZContainer container) {
		ZContainer con = new ZContainer();
		con.setLayout(new ZFillLayout(ZMobile.FILL_HORIZONTAL));
		con.add(new ZButton("ZButton"));
		con.add(new ZButton("ZButton"));
		con.add(new ZButton("ZButton"));
		container.add(con);
	}

	public void addContainerLink(ZContainer container) {
		ZContainer con = new ZContainer();
		con.setLayout(new ZFillLayout(ZMobile.FILL_HORIZONTAL));
		con.add(new ZLink("link"));
		con.add(new ZLink("link"));
		con.add(new ZLink("link"));
		container.add(con);
	}

	public void addContainerImage(ZContainer container) {
		ZContainer con = new ZContainer();
		con.setLayout(new ZFillLayout(ZMobile.FILL_HORIZONTAL));
		con.add(new ZImage("/marble.png"));
		con.add(new ZImage("/marble.png"));
		con.add(new ZImage("/marble.png"));
		container.add(con);
	}

	public void addContainerLabel(ZContainer container) {
		ZContainer con = new ZContainer();
		con.setLayout(new ZFillLayout(ZMobile.FILL_HORIZONTAL));
		con.add(new ZLabel("/marble.png"));
		con.add(new ZLabel("/marble.png"));
		con.add(new ZLabel("/marble.png"));
		container.add(con);
	}

	public void addContainerTextBox(ZContainer container) {
		ZContainer con = new ZContainer();
		con.setLayout(new ZFillLayout(ZMobile.FILL_HORIZONTAL));
		con.add(new ZTextBox("/marble.png"));
		con.add(new ZTextBox("/marble.png"));
		con.add(new ZTextBox("/marble.png"));
		container.add(con);
	}
	public void addContainerRadioButton(ZContainer container) {
		ZContainer con = new ZContainer();
		con.setLayout(new ZFillLayout(ZMobile.FILL_HORIZONTAL));
		con.add(new ZStatusButton("RadioButton",ZStatusButton.CHECK_BUTTON));
		con.add(new ZStatusButton("RadioButton",ZStatusButton.CHECK_BUTTON));
		con.add(new ZStatusButton("RadioButton",ZStatusButton.CHECK_BUTTON));

		container.add(con);
	}
}
