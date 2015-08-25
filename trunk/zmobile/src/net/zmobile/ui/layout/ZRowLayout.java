package net.zmobile.ui.layout;

import java.util.Vector;

import net.zmobile.ge.ZPoint;
import net.zmobile.ge.ZRect;
import net.zmobile.ui.ZComponent;
import net.zmobile.ui.ZContainer;

public final class ZRowLayout extends ZLayout {

	public ZRowLayout() {
		type = 256;
		marginWidth = 0;
		marginHeight = 0;
		spacing = 3;
		wrap = true;
		pack = true;
		fill = false;
		center = false;
		justify = false;
		marginLeft = 3;
		marginTop = 3;
		marginRight = 3;
		marginBottom = 3;
	}

	public ZRowLayout(int i) {
		type = 256;
		marginWidth = 0;
		marginHeight = 0;
		spacing = 3;
		wrap = true;
		pack = true;
		fill = false;
		center = false;
		justify = false;
		marginLeft = 3;
		marginTop = 3;
		marginRight = 3;
		marginBottom = 3;
		type = i;
	}

	protected ZPoint computeSize(ZContainer composite, int i, int j,
			boolean flag) {
		ZPoint point;
		if (type == 256)
			point = layoutHorizontal(composite, false, i != -1 && wrap, i, flag);
		else
			point = layoutVertical(composite, false, j != -1 && wrap, j, flag);
		if (i != -1)
			point.x = i;
		if (j != -1)
			point.y = j;
		return point;
	}

	ZPoint computeSize(ZComponent control, boolean flag) {
		int i = -1;
		int j = -1;
		ZRowData rowdata = (ZRowData) control.getLayoutData();
		if (rowdata != null) {
			i = rowdata.width;
			j = rowdata.height;
		}
		return control.computeSize(i, j);
	}

	protected boolean flushCache(ZComponent control) {
		return true;
	}

	String getName() {
		String s = getClass().getName();
		int i = s.lastIndexOf('.');
		if (i == -1)
			return s;
		else
			return s.substring(i + 1, s.length());
	}

	public ZPoint layout(ZContainer composite) {
		ZRect rectangle = composite.getClientArea();
		if (type == 256)
			return layoutHorizontal(composite, true, wrap, rectangle.width,true);
		else
			return layoutVertical(composite, true, wrap, rectangle.height, true);
	}

	ZPoint layoutHorizontal(ZContainer composite, boolean flag, boolean flag1,
			int i, boolean flag2) {
		Vector acontrol = composite.getComponents();
		int j = 0;
		for (int k = 0; k < acontrol.size(); k++) {
			ZComponent control = (ZComponent) acontrol.elementAt(k);
			ZRowData rowdata = (ZRowData) control.getLayoutData();
			if (rowdata == null || !rowdata.exclude)
				acontrol.setElementAt(acontrol.elementAt(k), j++);
		}

		if (j == 0)
			return new ZPoint(marginLeft + marginWidth * 2 + marginRight,
					marginTop + marginHeight * 2 + marginBottom);
		int l = 0;
		int i1 = 0;
		int j1 = 0;
		if (!pack) {
			for (int k1 = 0; k1 < j; k1++) {
				ZComponent control1 = (ZComponent) acontrol.elementAt(k1);
				ZPoint point = computeSize(control1, flag2);
				l = Math.max(l, point.x);
				i1 = Math.max(i1, point.y);
			}

			j1 = i1;
		}
		int l1 = 0;
		int i2 = 0;
		if (flag) {
			ZRect rectangle = composite.getClientArea();
			l1 = rectangle.x;
			i2 = rectangle.y;
		}
		int ai[] = (int[]) null;
		boolean flag3 = false;
		ZRect arectangle[] = (ZRect[]) null;
		if (flag && (justify || fill || center)) {
			arectangle = new ZRect[j];
			ai = new int[j];
		}
		int j2 = 0;
		int k2 = marginLeft + marginWidth;
		int l2 = marginTop + marginHeight;
		for (int i3 = 0; i3 < j; i3++) {
			ZComponent control2 = (ZComponent) acontrol.elementAt(i3);
			if (pack) {
				ZPoint point1 = computeSize(control2, flag2);
				l = point1.x;
				i1 = point1.y;
			}
			if (flag1 && i3 != 0 && k2 + l > i) {
				flag3 = true;
				if (flag && (justify || fill || center))
					ai[i3 - 1] = j1;
				k2 = marginLeft + marginWidth;
				l2 += spacing + j1;
				if (pack)
					j1 = 0;
			}
			if (pack || fill || center)
				j1 = Math.max(j1, i1);
			if (flag) {
				int l3 = k2 + l1;
				int k4 = l2 + i2;
				if (justify || fill || center)
					arectangle[i3] = new ZRect(l3, k4, l, i1);
				else
					control2.setBounds(new ZRect(l3, k4, l, i1));
			}
			k2 += spacing + l;
			j2 = Math.max(j2, k2);
		}

		j2 = Math.max(l1 + marginLeft + marginWidth, j2 - spacing);
		if (!flag3)
			j2 += marginRight + marginWidth;
		if (flag && (justify || fill || center)) {
			int j3 = 0;
			int k3 = 0;
			if (!flag3) {
				j3 = Math.max(0, (i - j2) / (j + 1));
				k3 = Math.max(0, ((i - j2) % (j + 1)) / 2);
			} else if (fill || justify || center) {
				int i4 = 0;
				if (j > 0)
					ai[j - 1] = j1;
				for (int l4 = 0; l4 < j; l4++)
					if (ai[l4] != 0) {
						int i5 = (l4 - i4) + 1;
						if (justify) {
							int j5 = 0;
							for (int l5 = i4; l5 <= l4; l5++)
								j5 += arectangle[l5].width + spacing;

							j3 = Math.max(0, (i - j5) / (i5 + 1));
							k3 = Math.max(0, ((i - j5) % (i5 + 1)) / 2);
						}
						for (int k5 = i4; k5 <= l4; k5++) {
							if (justify)
								arectangle[k5].x += j3 * ((k5 - i4) + 1) + k3;
							if (fill)
								arectangle[k5].height = ai[l4];
							else if (center)
								arectangle[k5].y += Math.max(0,
										(ai[l4] - arectangle[k5].height) / 2);
						}

						i4 = l4 + 1;
					}

			}
			for (int j4 = 0; j4 < j; j4++) {
				if (!flag3) {
					if (justify)
						arectangle[j4].x += j3 * (j4 + 1) + k3;
					if (fill)
						arectangle[j4].height = j1;
					else if (center)
						arectangle[j4].y += Math.max(0,
								(j1 - arectangle[j4].height) / 2);
				}
				((ZComponent) acontrol.elementAt(j4)).setBounds(arectangle[j4]);
			}

		}
		return new ZPoint(j2, l2 + j1 + marginBottom + marginHeight);
	}

	ZPoint layoutVertical(ZContainer composite, boolean flag, boolean flag1,
			int i, boolean flag2) {
		Vector acontrol = composite.getComponents();
		int j = 0;
		for (int k = 0; k < acontrol.size(); k++) {
			ZComponent control = (ZComponent) acontrol.elementAt(k);
			ZRowData rowdata = (ZRowData) control.getLayoutData();
			if (rowdata == null || !rowdata.exclude)
				acontrol.setElementAt(acontrol.elementAt(k), j++);
		}

		if (j == 0)
			return new ZPoint(marginLeft + marginWidth * 2 + marginRight,
					marginTop + marginHeight * 2 + marginBottom);
		int l = 0;
		int i1 = 0;
		int j1 = 0;
		if (!pack) {
			for (int k1 = 0; k1 < j; k1++) {
				ZComponent control1 = (ZComponent)acontrol.elementAt(k1);
				ZPoint point = computeSize(control1, flag2);
				l = Math.max(l, point.x);
				i1 = Math.max(i1, point.y);
			}

			j1 = l;
		}
		int l1 = 0;
		int i2 = 0;
		if (flag) {
			ZRect rectangle = composite.getClientArea();
			l1 = rectangle.x;
			i2 = rectangle.y;
		}
		int ai[] = (int[]) null;
		boolean flag3 = false;
		ZRect arectangle[] = (ZRect[]) null;
		if (flag && (justify || fill || center)) {
			arectangle = new ZRect[j];
			ai = new int[j];
		}
		int j2 = 0;
		int k2 = marginLeft + marginWidth;
		int l2 = marginTop + marginHeight;
		for (int i3 = 0; i3 < j; i3++) {
			ZComponent control2 = (ZComponent)acontrol.elementAt(i3);
			if (pack) {
				ZPoint point1 = computeSize(control2, flag2);
				l = point1.x;
				i1 = point1.y;
			}
			if (flag1 && i3 != 0 && l2 + i1 > i) {
				flag3 = true;
				if (flag && (justify || fill || center))
					ai[i3 - 1] = j1;
				k2 += spacing + j1;
				l2 = marginTop + marginHeight;
				if (pack)
					j1 = 0;
			}
			if (pack || fill || center)
				j1 = Math.max(j1, l);
			if (flag) {
				int l3 = k2 + l1;
				int k4 = l2 + i2;
				if (justify || fill || center)
					arectangle[i3] = new ZRect(l3, k4, l, i1);
				else
					control2.setBounds(new ZRect(l3, k4, l, i1));
			}
			l2 += spacing + i1;
			j2 = Math.max(j2, l2);
		}

		j2 = Math.max(i2 + marginTop + marginHeight, j2 - spacing);
		if (!flag3)
			j2 += marginBottom + marginHeight;
		if (flag && (justify || fill || center)) {
			int j3 = 0;
			int k3 = 0;
			if (!flag3) {
				j3 = Math.max(0, (i - j2) / (j + 1));
				k3 = Math.max(0, ((i - j2) % (j + 1)) / 2);
			} else if (fill || justify || center) {
				int i4 = 0;
				if (j > 0)
					ai[j - 1] = j1;
				for (int l4 = 0; l4 < j; l4++)
					if (ai[l4] != 0) {
						int i5 = (l4 - i4) + 1;
						if (justify) {
							int j5 = 0;
							for (int l5 = i4; l5 <= l4; l5++)
								j5 += arectangle[l5].height + spacing;

							j3 = Math.max(0, (i - j5) / (i5 + 1));
							k3 = Math.max(0, ((i - j5) % (i5 + 1)) / 2);
						}
						for (int k5 = i4; k5 <= l4; k5++) {
							if (justify)
								arectangle[k5].y += j3 * ((k5 - i4) + 1) + k3;
							if (fill)
								arectangle[k5].width = ai[l4];
							else if (center)
								arectangle[k5].x += Math.max(0,
										(ai[l4] - arectangle[k5].width) / 2);
						}

						i4 = l4 + 1;
					}

			}
			for (int j4 = 0; j4 < j; j4++) {
				if (!flag3) {
					if (justify)
						arectangle[j4].y += j3 * (j4 + 1) + k3;
					if (fill)
						arectangle[j4].width = j1;
					else if (center)
						arectangle[j4].x += Math.max(0,
								(j1 - arectangle[j4].width) / 2);
				}
				((ZComponent)acontrol.elementAt(j4)).setBounds(arectangle[j4]);
			}

		}
		return new ZPoint(k2 + j1 + marginRight + marginWidth, j2);
	}

	public String toString() {
		String s = getName() + " {";
		s = s + "type=" + (type == 256 ? "SWT.HORIZONTAL" : "SWT.VERTICAL")
				+ " ";
		if (marginWidth != 0)
			s = s + "marginWidth=" + marginWidth + " ";
		if (marginHeight != 0)
			s = s + "marginHeight=" + marginHeight + " ";
		if (marginLeft != 0)
			s = s + "marginLeft=" + marginLeft + " ";
		if (marginTop != 0)
			s = s + "marginTop=" + marginTop + " ";
		if (marginRight != 0)
			s = s + "marginRight=" + marginRight + " ";
		if (marginBottom != 0)
			s = s + "marginBottom=" + marginBottom + " ";
		if (spacing != 0)
			s = s + "spacing=" + spacing + " ";
		s = s + "wrap=" + wrap + " ";
		s = s + "pack=" + pack + " ";
		s = s + "fill=" + fill + " ";
		s = s + "justify=" + justify + " ";
		s = s.trim();
		s = s + "}";
		return s;
	}

	public int type;
	public int marginWidth;
	public int marginHeight;
	public int spacing;
	public boolean wrap;
	public boolean pack;
	public boolean fill;
	public boolean center;
	public boolean justify;
	public int marginLeft;
	public int marginTop;
	public int marginRight;
	public int marginBottom;
}