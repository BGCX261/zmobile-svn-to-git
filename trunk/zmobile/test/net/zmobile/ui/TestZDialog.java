package net.zmobile.ui;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import net.zmobile.ZMobile;
import net.zmobile.ui.layout.ZFillLayout;

public class TestZDialog extends MIDlet {

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {

	}

	protected void pauseApp() {

	}

	protected void startApp() throws MIDletStateChangeException {
		final ZForm form = new ZForm();
		form.setTitle("ZButtonMIDlet");
		form.setScroll(true);
		
		ZFillLayout fillLayout = new ZFillLayout(ZMobile.FILL_VERTICAL);
		form.setLayout(fillLayout);
		ZButton btn =new ZButton("Button");
		btn.addListener(ZMobile.EVENT_KEY_SELECT_RELESE, new Listener(){

			public void event(int x, int y, int key, ZComponent src) {
				ZDialog dialog = new ZDialog();
				dialog.show();
			}
			
		});
		form.add(btn);
		
		form.setLeftLabel("Left");
		form.setRightLabel("Right");
		form.show(Display.getDisplay(this));

	}

}
