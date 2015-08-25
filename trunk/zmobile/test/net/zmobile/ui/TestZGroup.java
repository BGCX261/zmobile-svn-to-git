package net.zmobile.ui;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import net.zmobile.ZMobile;
import net.zmobile.ui.layout.ZRowLayout;
import net.zmobile.ui.layout.ZFillLayout;

public class TestZGroup extends MIDlet{

	protected void destroyApp(boolean flag) throws MIDletStateChangeException {
		
	}

	protected void pauseApp() {
		
	}

	protected void startApp() throws MIDletStateChangeException {
		ZForm form = new ZForm();
		form.setTitle("ZButtonMIDlet");
		form.setLayout(new ZFillLayout(ZMobile.FILL_VERTICAL));
		
		addZGRoup(form,ZStatusButton.CHECK_BUTTON);
		addZGRoup(form,ZStatusButton.RADIO_BUTTON);
		addZGRoup(form,ZStatusButton.CHECK_BUTTON);
		addZGRoup(form,ZStatusButton.RADIO_BUTTON);


		form.show(Display.getDisplay(this));
	}

	public void addZGRoup(ZForm form,byte type){
		ZGroup group = new ZGroup("CheckBox") ;
		group.setLayout(new ZRowLayout());
		
		group.add(new ZStatusButton("CheckBox",type));
		group.add(new ZStatusButton("CheckBox",type));
		group.add(new ZStatusButton("CheckBox",type));
		group.add(new ZStatusButton("CheckBox",type));
		group.add(new ZStatusButton("CheckBox",type));
		group.add(new ZStatusButton("CheckBox",type));
		
		form.add(group);
	}
}
