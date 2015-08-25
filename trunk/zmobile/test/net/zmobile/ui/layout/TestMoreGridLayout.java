package net.zmobile.ui.layout;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import net.zmobile.ui.ZButton;
import net.zmobile.ui.ZContainer;
import net.zmobile.ui.ZForm;
import net.zmobile.ui.ZLabel;
import net.zmobile.ui.ZTextBox;

public class TestMoreGridLayout extends MIDlet {

	protected void destroyApp(boolean flag) throws MIDletStateChangeException {
		// TODO Auto-generated method stub

	}

	protected void pauseApp() {
		// TODO Auto-generated method stub

	}

	protected void startApp() throws MIDletStateChangeException {
		ZForm form = new ZForm();
		form.setTitle("ComplexGridLayout");
		form.setScroll(true);
		
		ZGridLayout gridLayout = new ZGridLayout();
		gridLayout.numColumns = 3;
		form.setLayout(gridLayout);
		
		form.add(new ZLabel("Dog's Name:"));
		
		ZTextBox dogName = new ZTextBox("");
		ZGridData gridData = new ZGridData(ZGridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan = 2;
		dogName.setLayoutData(gridData);
		form.add(dogName);
		
		form.add(new ZLabel("Breed:"));
		
		ZTextBox dogBreed = new ZTextBox("Collie");
		dogBreed.setLayoutData(new ZGridData(ZGridData.HORIZONTAL_ALIGN_FILL));
		
		form.add(dogBreed);
		
		ZLabel label = new ZLabel("Categories");
		label.setLayoutData(new ZGridData(ZGridData.HORIZONTAL_ALIGN_CENTER));
		form.add(label);
		
		form.add(new ZLabel("Photo:"));
		ZTextBox dogPhoto = new ZTextBox("");
		gridData = new ZGridData(ZGridData.FILL_BOTH);
		gridData.widthHint = 80;
		gridData.heightHint = 80;
		gridData.verticalSpan = 3;
		dogPhoto.setLayoutData(gridData);
		form.add(dogPhoto);
		
		ZButton categories = new ZButton("");
		gridData = new ZGridData(ZGridData.HORIZONTAL_ALIGN_FILL
				| ZGridData.VERTICAL_ALIGN_FILL);
		gridData.verticalSpan = 12;
		gridData.heightHint = 10;
		categories.setLayoutData(gridData);
		form.add(categories);
		
		ZButton browse = new ZButton("Browse...");
		gridData = new ZGridData(ZGridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalIndent = 5;
		browse.setLayoutData(gridData);
		form.add(browse);
		
		ZButton delete = new ZButton("Delete");
		gridData = new ZGridData(ZGridData.HORIZONTAL_ALIGN_FILL
				| ZGridData.VERTICAL_ALIGN_BEGINNING);
		gridData.horizontalIndent = 5;
		delete.setLayoutData(gridData);
		form.add(delete);
		
		ZContainer ownerInfo = new ZContainer();
		gridLayout = new ZGridLayout();
		gridLayout.numColumns = 2;
		ownerInfo.setLayout(gridLayout);
		
		gridData = new ZGridData(ZGridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan = 2;
		ownerInfo.setLayoutData(gridData);
		form.add(ownerInfo);
		
		ownerInfo.add(new ZLabel("Name:"));
		ZTextBox ownerName = new ZTextBox("");
		ownerName.setLayoutData(new ZGridData(ZGridData.FILL_HORIZONTAL));
		ownerInfo.add(ownerName);
		
		ownerInfo.add(new ZLabel("Phone:"));
		ZTextBox ownerPhone = new ZTextBox("");
		ownerPhone.setLayoutData(new ZGridData(ZGridData.FILL_HORIZONTAL));
		ownerInfo.add(ownerPhone);
		
		ZButton enter = new ZButton("Enter");
		gridData = new ZGridData(ZGridData.HORIZONTAL_ALIGN_END);
		gridData.horizontalSpan = 3;
		enter.setLayoutData(gridData);
		form.add(enter);

		form.show(Display.getDisplay(this));
	}

}
