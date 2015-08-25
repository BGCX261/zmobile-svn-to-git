package net.zmobile.ui.layout;

import java.util.Vector;

import net.zmobile.ge.ZPoint;
import net.zmobile.ge.ZRect;
import net.zmobile.ui.ZComponent;
import net.zmobile.ui.ZContainer;

public class ZDefautLayout extends ZLayout {

	public ZPoint resize(ZRect rect, Vector components) {
		int startX = rect.x;
		int startY = rect.y;

		for (int i = 0; i < components.size(); i++) {
			ZComponent comp = (ZComponent) components.elementAt(i);
			comp.computeSize(-1, -1);
			if(startX+comp.getBounds().width>rect.x+rect.width){
				startY +=comp.getBounds().height;
				startX = 0;
			}
			comp.setBounds(new ZRect(startX, startY,-1, -1));
			startX+=comp.getBounds().width;
		}
		return new ZPoint(rect.x,startY);
	}

	public ZPoint layout(ZContainer composite) {
		return resize(composite.getClientArea(),composite.getComponents());
	}

}
