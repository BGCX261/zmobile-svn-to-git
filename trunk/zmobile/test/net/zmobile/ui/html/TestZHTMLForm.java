package net.zmobile.ui.html;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import net.zmobile.ZMobile;
import net.zmobile.ui.ZButton;
import net.zmobile.ui.ZContainer;
import net.zmobile.ui.ZTicker;
import net.zmobile.ui.layout.ZFillLayout;

public class TestZHTMLForm extends MIDlet {

	protected void destroyApp(boolean flag) throws MIDletStateChangeException {

	}

	protected void pauseApp() {

	}

	protected void startApp() throws MIDletStateChangeException {
		final ZHTMLForm form = new ZHTMLForm();
		form.setTitle("ZHTMLForm");
		form.setScroll(true);
		
		form.add(new ZButton("ZButton"));
		form.add(new ZButton("ZButton"));
		form.add(new ZBr());
		form.setAlign(ZMobile.CENTER);
		form.add(new ZButton("ZButton"));
		form.add(new ZButton("ZButton"));
		form.add(new ZBr());
		form.add(new ZBr());
		form.add(new ZBr());
		form.add(new ZBr());
		form.setAlign(ZMobile.RIGHT);
		form.add(new ZButton("ZButton"));
		form.add(new ZButton("ZButton"));
		form.add(new ZTicker(ZTicker.MOVE));
		form.add(new ZBr());
		
		form.setLeftLabel("Left");
		form.setRightLabel("Right");
		form.show(Display.getDisplay(this));
	}
	public void addContainerZButton(ZContainer container){
		ZContainer con = new ZContainer();
		ZFillLayout  fila = new ZFillLayout(ZMobile.FILL_HORIZONTAL);
		fila.spacing =1;
		con.setLayout(fila);
		con.add(new ZButton("ZButton"));
		con.add(new ZButton("ZButton"));
		con.add(new ZButton("ZButton"));
		container.add(con);
	}
}
