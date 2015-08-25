package net.zmobile.ui.layout;

import net.zmobile.ge.ZPoint;
import net.zmobile.ui.ZComponent;

	class ZFillData {

		ZFillData() {
			defaultWidth = -1;
			defaultHeight = -1;
			currentWidth = -1;
			currentHeight = -1;
		}

		ZPoint computeSize(ZComponent control, int i, int j) {
			if (i == -1 && j == -1) {
				if (defaultWidth == -1 || defaultHeight == -1) {
					ZPoint point = control.computeSize(i, j);
					defaultWidth = point.x;
					defaultHeight = point.y;
				}
				return new ZPoint(defaultWidth, defaultHeight);
			}
			if (currentWidth == -1 || currentHeight == -1 || i != currentWhint
					|| j != currentHhint) {
				ZPoint point1 = control.computeSize(i, j);
				currentWhint = i;
				currentHhint = j;
				currentWidth = point1.x;
				currentHeight = point1.y;
			}
			return new ZPoint(currentWidth, currentHeight);
		}

		int defaultWidth;
		int defaultHeight;
		int currentWhint;
		int currentHhint;
		int currentWidth;
		int currentHeight;
}
