package net.zmobile.ui.layout;

import java.util.Vector;

import net.zmobile.ZMobile;
import net.zmobile.ge.ZPoint;
import net.zmobile.ge.ZRect;
import net.zmobile.ui.ZComponent;
import net.zmobile.ui.ZContainer;

public final class ZFillLayout extends ZLayout {

	public ZFillLayout() {
		type = ZMobile.FILL_HORIZONTAL;
		marginWidth = 0;
		marginHeight = 0;
		spacing = 0;
	}

	public ZFillLayout(int i) {
		marginWidth = 0;
		marginHeight = 0;
		spacing = 0;
		type = i;
	}
	ZPoint computeChildSize(ZComponent control, int i, int j) {
		ZFillData filldata = (ZFillData) control.getLayoutData();
		if (filldata == null) {
			filldata = new ZFillData();
			control.setLayoutData(filldata);
		}
		ZPoint ZPoint = null;
		if (i == -1 && j == -1) {
			ZPoint = filldata.computeSize(control, i, j);
		} else {
			int k;
			int l;
			k = l = 1 * 2;
			int i1 = i != -1 ? Math.max(0, i - k) : i;
			int j1 = j != -1 ? Math.max(0, j - l) : j;
			ZPoint = filldata.computeSize(control, i1, j1);
		}
		return ZPoint;
	}

	public ZPoint layout(ZContainer composite) {
		ZRect rect = composite.getClientArea();
		Vector acontrol = composite.getComponents();
		int i = acontrol.size();
		if (i == 0)
			return new ZPoint(rect.width,rect.height);
		int widthWithOutMargin = rect.width - marginWidth * 2;
		int heightWithoutMargin = rect.height - marginHeight * 2;
		if (type == ZMobile.FILL_HORIZONTAL) {//FILL_HORIZONTAL
			widthWithOutMargin -= (i - 1) * spacing;
			int l = rect.x + marginWidth;
			int j1 = widthWithOutMargin % i;
			int l1 = rect.y + marginHeight;
			int j2 = widthWithOutMargin / i;
			for (int l2 = 0; l2 < i; l2++) {
				ZComponent control = (ZComponent) acontrol.elementAt(l2);
				int j3 = j2;
				if (l2 == 0)
					j3 += j1 / 2;
				else if (l2 == i - 1)
					j3 += (j1 + 1) / 2;
				control.setBounds(new ZRect(l, l1, j3, heightWithoutMargin));
				l += j3 + spacing;
			}

		} else {//FILL_VERTICAL
			heightWithoutMargin -= (i - 1) * spacing;
			int width1 = rect.x + marginWidth;
			int k1 = heightWithoutMargin / i;
			int i2 = rect.y + marginHeight;
			int k2 = heightWithoutMargin % i;
			for (int i3 = 0; i3 < i; i3++) {
				ZComponent control1 = (ZComponent) acontrol.elementAt(i3);
				int k3 = k1;
				if (i3 == 0)
					k3 += k2 / 2;
				else if (i3 == i - 1)
					k3 += (k2 + 1) / 2;
				control1.setBounds(new ZRect(width1, i2, widthWithOutMargin, k3));
				i2 += k3 + spacing;
			}

		}
		return new ZPoint(rect.width,rect.height);
	}

	public int type;
	public int marginWidth;
	public int marginHeight;
	public int spacing;
}