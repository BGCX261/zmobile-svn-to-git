package net.zmobile.ui;


import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import net.zmobile.ZMobile;
import net.zmobile.ge.ZRect;
import net.zmobile.ui.layout.ZFillLayout;
import net.zmobile.ui.layout.html.ZHTMLLayout;

public class TestZHTMLLayout extends MIDlet {

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {

	}

	protected void pauseApp() {

	}

	protected void startApp() throws MIDletStateChangeException {

		ZForm form = new ZForm();
		form.setTitle("123456789");

		form.setLayout(new ZHTMLLayout());
		form.add(new ZButton("ZButton"));
		form.add(new ZLink("link"));
		form.add(new ZButton("ZButton"));
		form.add(new ZLink("link"));
		form.add(new ZButton("ZButton"));
		form.add(new ZLink("link"));
		form.add(new ZButton("ZButton"));
		form.add(new ZLink("link"));
		form.add(new ZButton("ZButton"));
		form.add(new ZLink("link"));
		form.add(new ZButton("ZButton"));
		form.add(new ZLink("link"));
		ZButton btn = new ZButton("ZButton");
		btn.setBounds(new ZRect(50, 50, 100, 100));
		form.add(btn);
		form.add(new ZLink("link"));
		form.add(new ZLink("link"));
		form.add(new ZLink("link"));
		form.add(new ZLink("link"));
		form.add(new ZLink("link"));
		form.add(new ZLink("link"));
		form.add(new ZLink("link"));
		form.add(new ZLink("link"));
		form.add(new ZLink("link"));

		form.add(new ZLink("link"));
		form.add(new ZLink("link"));
		form.add(new ZLink("link"));
		form.add(new ZLink("link"));
		form.add(new ZLink("link"));
		form.add(new ZLink("link"));
		form.add(new ZLink("link"));
		form.add(new ZLink("link"));
		
		ZContainer container = new ZContainer();
		container.setLayout(new ZFillLayout(ZMobile.FILL_HORIZONTAL));
		container.setBounds(new ZRect(10, 10, 100, 100));
		addContainerZButton(container);
		
		ZContainer container2 = new ZContainer();
		container2.setLayout(new ZFillLayout(ZMobile.FILL_HORIZONTAL));
		container2.setBounds(new ZRect(10, 10, 100, 100));
		addContainerLink(container2);
		
		ZContainer container3 = new ZContainer();
		container3.setLayout(new ZFillLayout(ZMobile.FILL_HORIZONTAL));
		container3.setBounds(new ZRect(10, 10, 100, 100));
		addContainerLink(container3);
		
		ZContainer container4 = new ZContainer();
		container4.setLayout(new ZFillLayout(ZMobile.FILL_HORIZONTAL));
		container4.setBounds(new ZRect(10, 10, 100, 100));
		addContainerLink(container4);
		
		form.add(container);
		form.add(container2);
		form.add(container3);
		form.add(container4);
		
		form.show(Display.getDisplay(this));

	}
	public void addContainerZButton(ZContainer container){
		ZContainer con = new ZContainer();
		con.setLayout(new ZFillLayout(ZMobile.FILL_HORIZONTAL));
		con.add(new ZButton("ZButton"));
		con.add(new ZButton("ZButton"));
		con.add(new ZButton("ZButton"));
		container.add(con);
	}
	public void addContainerLink(ZContainer container){
		ZContainer con = new ZContainer();
		con.setLayout(new ZFillLayout(ZMobile.FILL_HORIZONTAL));
		con.add(new ZLink("link"));
		con.add(new ZLink("link"));
		con.add(new ZLink("link"));
		con.add(new ZLink("link"));
		con.add(new ZLink("link"));
		con.add(new ZLink("link"));
		container.add(con);
	}
	public void addContainerImage(ZContainer container){
		ZContainer con = new ZContainer();
		con.setLayout(new ZFillLayout(ZMobile.FILL_HORIZONTAL));
		con.add(new ZImage("/marble.png"));
		con.add(new ZImage("/marble.png"));
		con.add(new ZImage("/marble.png"));
		con.add(new ZImage("/marble.png"));
		con.add(new ZImage("/marble.png"));
		container.add(con);
	}
	public void addContainerLabel(ZContainer container){
		ZContainer con = new ZContainer();
		con.setLayout(new ZFillLayout(ZMobile.FILL_HORIZONTAL));
		con.add(new ZLabel("/marble.png"));
		con.add(new ZLabel("/marble.png"));
		con.add(new ZLabel("/marble.png"));
		con.add(new ZLabel("/marble.png"));
		con.add(new ZLabel("/marble.png"));
		container.add(con);
	}
}
