/**
 * Copyright (c) 2007 NEC-AS
 * All rights reserved
 *
 * This software is the confidential and proprietary information of NEC-AS.
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into
 * with NEC-AS
 */
package net.zmobile.ui.layout.html;

import java.util.Vector;

import net.zmobile.ZMobile;
import net.zmobile.ge.ZPoint;
import net.zmobile.ge.ZRect;
import net.zmobile.ui.ZComponent;
import net.zmobile.ui.ZContainer;
import net.zmobile.ui.html.ZAlign;
import net.zmobile.ui.html.ZBr;
import net.zmobile.ui.layout.ZLayout;

/**
 * @author song jia wei@necas
 * @version 0.0.01
 * @time 2007/09/10 18:29:20
 */
public class ZHTMLLayout extends ZLayout {

	ZHTMLRow currentRow = null;

	public ZHTMLLayout() {

	}

	private class ZHTMLRow {
		private int _align = ZMobile.LEFT;

		ZRect _rect = new ZRect(0, 0, 0, 20);

		public ZHTMLRow(ZRect rect) {
			_rect.x = rect.x;
			_rect.y = rect.y;
			_rect.width = rect.width;
			_align = ZHTMLLayout.this._align;
		}

		Vector components = new Vector();

		boolean layout(ZComponent c) {
			c.computeSize(-1, -1);
			ZRect crect = c.getBounds();
			
			switch (_align) {
			case ZMobile.LEFT:
				if (getRowLength() + crect.width < _rect.width) {
					components.addElement(c);
					layout(_rect.x,c);
					return true;
				}
				break;
			case ZMobile.CENTER:
				if(getRowLength()+crect.width<_rect.width){
					components.addElement(c);
					layout(_rect.x+(_rect.width-getRowLength())/2,c);
					return true;
				}
				break;
			case ZMobile.RIGHT:
				if(getRowLength()+crect.width<_rect.width){
					components.addElement(c);
					layout(_rect.x+(_rect.width-getRowLength()),c);
					return true;
				}
				break;

			}
			return false;
		}

		private void layout(int startx,ZComponent c){
			int pos = startx;
			
			for (int i = 0; i < components.size(); i++) {
				ZComponent comp = (ZComponent) components.elementAt(i);
				ZRect rect = comp.getBounds();
				
				rect.x = pos;
				rect.y = _rect.y;
				pos+=rect.width;
				
				comp.setBounds(rect);
				// Update Height of ZHTMLRow
				_rect.height = Math.max(rect.height, _rect.height);
			}
		}

		int getRowLength() {
			int length = 0;
			for (int i = 0; i < components.size(); i++) {
				ZComponent comp = (ZComponent) components.elementAt(i);
				length += comp.getBounds().width;
			}
			return length;
		}

		void setAlign(int align) {
			this._align = align;
		}
	}

	public ZPoint layout(ZContainer composite) {
		ZPoint size = new ZPoint(0, 0);
		ZRect rect = composite.getClientArea();
		size.x = rect.width;

		currentRow = new ZHTMLRow(rect);

		Vector acontrol = composite.getComponents();
		for (int i = 0; i < acontrol.size(); i++) {
			ZComponent comp = (ZComponent) acontrol.elementAt(i);
			if(comp instanceof ZBr){
				size.y+=currentRow._rect.height;
				currentRow = new ZHTMLRow(new ZRect(rect.x, rect.y + size.y,
						rect.width, rect.height));
				continue;
			}
			if(comp instanceof ZAlign){
				size.y+=currentRow._rect.height;
				currentRow = new ZHTMLRow(new ZRect(rect.x, rect.y + size.y,
						rect.width, rect.height));
				currentRow._align = ((ZAlign)comp).getAlign();
				ZHTMLLayout.this._align = ((ZAlign)comp).getAlign();
				continue;
			}
			if (!currentRow.layout(comp)) {
				size.y += currentRow._rect.height;
				currentRow = new ZHTMLRow(new ZRect(rect.x, rect.y + size.y,
						rect.width, rect.height));
				currentRow.layout((ZComponent) acontrol.elementAt(i));
			}
		}
		return size;
	}

	int _align = ZMobile.LEFT;

	public void setAlign(int align) {
		_align = align;
	}
}
