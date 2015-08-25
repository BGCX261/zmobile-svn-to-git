package net.zmobile.ui;

import java.io.IOException;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Image;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import net.zmobile.ZMobile;
import net.zmobile.ui.layout.ZFillLayout;

public class TestZButton extends MIDlet {

	protected void destroyApp(boolean flag) throws MIDletStateChangeException {

	}

	protected void pauseApp() {

	}

	protected void startApp() throws MIDletStateChangeException {
		final ZForm form = new ZForm();
		form.setTitle("ZButtonMIDlet");
		form.setScroll(true);
		ZFillLayout fillLayout = new ZFillLayout(ZMobile.FILL_VERTICAL);
		fillLayout.spacing =1;
		form.setLayout(fillLayout);
		form.add(new ZButton("Button"));
		form.add(new ZButton("Button"));
		addContainerZButton(form);
		form.add(new ZButton("Button"));
		form.add(new ZButton("Button"));
		ZButton zbutton = new ZButton("Click Me");
		zbutton.addListener(ZMobile.EVENT_KEY_SELECT_RELESE, new Listener(){
			public void event(int x, int y, int key, ZComponent src) {
				System.out.println("TranslateY:"+form.translateY);
				form.translateY+=10;
			}
			
		});

		form.add(zbutton);
		try {
			form.add(new ZButton(Image.createImage("/marble.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ZContainer con = new ZContainer();
		ZFillLayout  fila = new ZFillLayout(ZMobile.FILL_HORIZONTAL);
		fila.spacing =1;
		con.setLayout(fila);
		addContainerZButton(con);
		addContainerZButton(con);
		addContainerZButton(con);
		addContainerZButton(con);
		
		form.add(con);
		
		addContainerZButton(form);
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
