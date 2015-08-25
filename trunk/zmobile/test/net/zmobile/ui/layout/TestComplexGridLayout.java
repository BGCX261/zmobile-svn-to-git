package net.zmobile.ui.layout;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import net.zmobile.ZMobile;
import net.zmobile.ui.ZButton;
import net.zmobile.ui.ZForm;
import net.zmobile.ui.ZLabel;
import net.zmobile.ui.ZTextBox;
import net.zmobile.ui.layout.ZGridData;
import net.zmobile.ui.layout.ZGridLayout;

public class TestComplexGridLayout extends MIDlet {

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {

	}

	protected void pauseApp() {

	}

	protected void startApp() throws MIDletStateChangeException {
		ZForm form = new ZForm();
		form.setTitle("ComplexGridLayout");
		form.setScroll(true);
		ZGridLayout gridLayout = new ZGridLayout(2, true);
		gridLayout.layout(form);
		gridLayout.makeColumnsEqualWidth = false;
		form.setLayout(gridLayout);

		ZLabel nameLabel = new ZLabel("Name:");
		form.add(nameLabel);

		ZTextBox nameText = new ZTextBox("Text grows horizontally");
		ZGridData gridData = new ZGridData();
		gridData.horizontalAlignment = ZMobile.FILL;
		gridData.grabExcessHorizontalSpace = true;
		nameText.setLayoutData(gridData);
		form.add(nameText);

		ZLabel nameLabel1 = new ZLabel("Gender:");
		form.add(nameLabel1);
		ZTextBox nameText1 = new ZTextBox("Text grows horizontally");
		ZGridData gridData1 = new ZGridData();
		gridData1.horizontalAlignment = ZMobile.FILL;
		gridData1.grabExcessHorizontalSpace = true;
		nameText1.setLayoutData(gridData1);
		form.add(nameText1);

//		add(form,"123");
//		add(form,"123");
//		add(form,"123");
//		add(form,"123");
//		add(form,"123");
//		add(form,"123");
//		add(form,"123");
//		add(form,"123");
//		add(form,"123");
//		add(form,"123");
		
		ZLabel nameLabel2 = new ZLabel("Old:");
		form.add(nameLabel2);

		ZTextBox nameText2 = new ZTextBox("Text grows horizontally");
		ZGridData gridData2 = new ZGridData();
		gridData2.horizontalAlignment = ZMobile.FILL;
		gridData2.grabExcessHorizontalSpace = true;
		nameText2.setLayoutData(gridData2);
		form.add(nameText2);

		ZLabel addressLabel = new ZLabel("Address:");
		gridData = new ZGridData();
		gridData.verticalAlignment = ZMobile.TOP;
		addressLabel.setLayoutData(gridData);
		form.add(addressLabel);

		ZTextBox addressText = new ZTextBox("");
		gridData = new ZGridData();
		gridData.horizontalAlignment = ZMobile.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = ZMobile.FILL;
		gridData.grabExcessVerticalSpace = true;
		addressText.setLayoutData(gridData);
		addressText
				.setText("This text field and the List\nbelow share any excess space.");
		form.add(addressText);

		ZLabel sportsLabel = new ZLabel("Sports played:");
		gridData = new ZGridData();
		gridData.horizontalSpan = 2;
		sportsLabel.setLayoutData(gridData);
		form.add(sportsLabel);

		ZTextBox sportsList = new ZTextBox("Button");
		gridData = new ZGridData();
		gridData.horizontalSpan = 2;
		gridData.horizontalAlignment = ZMobile.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = ZMobile.FILL;
		gridData.grabExcessVerticalSpace = true;
		sportsList.setLayoutData(gridData);
		form.add(sportsList);

		ZButton onBtn = new ZButton("OK");
		gridData = new ZGridData();
		gridData.horizontalSpan = 2;
		gridData.horizontalAlignment = ZMobile.RIGHT;
		gridData.grabExcessHorizontalSpace = true;
		onBtn.setLayoutData(gridData);
		form.add(onBtn);

		form.show(Display.getDisplay(this));
	}

	void add(ZForm form,String label) {
		ZLabel nameLabel1 = new ZLabel(label);
		form.add(nameLabel1);

		ZTextBox nameText1 = new ZTextBox("Text grows horizontally");
		ZGridData gridData1 = new ZGridData();
		gridData1.horizontalAlignment = ZMobile.FILL;
		gridData1.grabExcessHorizontalSpace = true;
		nameText1.setLayoutData(gridData1);
		form.add(nameText1);
	}
}
