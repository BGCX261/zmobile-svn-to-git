package net.zmobile;

import java.io.UnsupportedEncodingException;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Font;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import net.zmobile.ui.Listener;
import net.zmobile.ui.ZButton;
import net.zmobile.ui.ZComponent;
import net.zmobile.ui.ZForm;
import net.zmobile.ui.ZLabel;
import net.zmobile.ui.ZTextBox;
import net.zmobile.ui.layout.ZGridData;
import net.zmobile.ui.layout.ZGridLayout;
import net.zmobile.util.URLCoder;

public class ZMobileDemo extends MIDlet {

	protected void destroyApp(boolean flag) throws MIDletStateChangeException {

	}

	protected void pauseApp() {

	}

	protected void startApp() throws MIDletStateChangeException {
		ZForm form = new ZForm();
		form.setTitle("ZButtonMIDlet");
		form.setScroll(true);

		ZGridLayout gridlayout = new ZGridLayout();
		gridlayout.numColumns = 2;
		gridlayout.makeColumnsEqualWidth = true;

		form.setLayout(gridlayout);
		form.add(new ZLabel("String:"));

		final ZTextBox btn = new ZTextBox("中国员工");

		ZGridData gridData = new ZGridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalSpan = 2;

		gridData.horizontalAlignment = ZMobile.FILL;
		gridData.verticalAlignment = ZMobile.FILL;
		gridData.minimumHeight = ZForm.instance.getBounds().height / 3;
		btn.setLayoutData(gridData);
		form.add(btn);
		/**
		 * 
		 */
		form.add(new ZLabel("URLCode:"));

		final ZTextBox result = new ZTextBox("");

		ZGridData resultgridData = new ZGridData();
		resultgridData.grabExcessHorizontalSpace = true;
		resultgridData.grabExcessVerticalSpace = true;
		resultgridData.horizontalSpan = 2;

		resultgridData.horizontalAlignment = ZMobile.FILL;
		resultgridData.verticalAlignment = ZMobile.FILL;
		resultgridData.minimumHeight = ZForm.instance.getBounds().height / 3;
		result.setLayoutData(resultgridData);
		form.add(result);
		
		ZButton ok = new ZButton("Convert");
		
		ZGridData okgridData = new ZGridData();
		okgridData.grabExcessHorizontalSpace = true;
		okgridData.grabExcessVerticalSpace = true;
		okgridData.horizontalSpan = 2;

		okgridData.horizontalAlignment = ZMobile.RIGHT;
		okgridData.minimumHeight = Font.getDefaultFont().getHeight();
		ok.setLayoutData(okgridData);
		form.add(ok);
		
		ok.addListener(ZMobile.EVENT_KEY_SELECT, new Listener(){

			public void event(int x, int y, int key, ZComponent src) {
				try {
					result.setText(URLCoder.encode(new String(btn.getText().getBytes("gb2312"))));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			
		});
		form.setLeftLabel("Left");
		form.setRightLabel("Right");

		form.show(Display.getDisplay(this));
	}

	void add(ZForm form) {

	}
}
