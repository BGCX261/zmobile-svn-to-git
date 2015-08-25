package net.zmobile.ui.layout;

import net.zmobile.ge.ZPoint;
import net.zmobile.ui.ZComponent;


public final class ZGridData {

	public ZGridData() {
		verticalAlignment = 2;
		horizontalAlignment = 1;
		widthHint = -1;
		heightHint = -1;
		horizontalIndent = 0;
		verticalIndent = 0;
		horizontalSpan = 1;
		verticalSpan = 1;
		grabExcessHorizontalSpace = false;
		grabExcessVerticalSpace = false;
		minimumWidth = 0;
		minimumHeight = 0;
		exclude = false;
		cacheWidth = -1;
		cacheHeight = -1;
		defaultWidth = -1;
		defaultHeight = -1;
		currentWidth = -1;
		currentHeight = -1;
	}

	public ZGridData(int i) {
		verticalAlignment = 2;
		horizontalAlignment = 1;
		widthHint = -1;
		heightHint = -1;
		horizontalIndent = 0;
		verticalIndent = 0;
		horizontalSpan = 1;
		verticalSpan = 1;
		grabExcessHorizontalSpace = false;
		grabExcessVerticalSpace = false;
		minimumWidth = 0;
		minimumHeight = 0;
		exclude = false;
		cacheWidth = -1;
		cacheHeight = -1;
		defaultWidth = -1;
		defaultHeight = -1;
		currentWidth = -1;
		currentHeight = -1;
		if ((i & 2) != 0)
			verticalAlignment = 1;
		if ((i & 4) != 0)
			verticalAlignment = 2;
		if ((i & 16) != 0)
			verticalAlignment = 4;
		if ((i & 8) != 0)
			verticalAlignment = 3;
		if ((i & 32) != 0)
			horizontalAlignment = 1;
		if ((i & 64) != 0)
			horizontalAlignment = 2;
		if ((i & 256) != 0)
			horizontalAlignment = 4;
		if ((i & 128) != 0)
			horizontalAlignment = 3;
		grabExcessHorizontalSpace = (i & 512) != 0;
		grabExcessVerticalSpace = (i & 1024) != 0;
	}

	public ZGridData(int i, int j, boolean flag, boolean flag1) {
		this(i, j, flag, flag1, 1, 1);
	}

	public ZGridData(int i, int j, boolean flag, boolean flag1, int k, int l) {
		verticalAlignment = 2;
		horizontalAlignment = 1;
		widthHint = -1;
		heightHint = -1;
		horizontalIndent = 0;
		verticalIndent = 0;
		horizontalSpan = 1;
		verticalSpan = 1;
		grabExcessHorizontalSpace = false;
		grabExcessVerticalSpace = false;
		minimumWidth = 0;
		minimumHeight = 0;
		exclude = false;
		cacheWidth = -1;
		cacheHeight = -1;
		defaultWidth = -1;
		defaultHeight = -1;
		currentWidth = -1;
		currentHeight = -1;
		horizontalAlignment = i;
		verticalAlignment = j;
		grabExcessHorizontalSpace = flag;
		grabExcessVerticalSpace = flag1;
		horizontalSpan = k;
		verticalSpan = l;
	}

	public ZGridData(int i, int j) {
		verticalAlignment = 2;
		horizontalAlignment = 1;
		widthHint = -1;
		heightHint = -1;
		horizontalIndent = 0;
		verticalIndent = 0;
		horizontalSpan = 1;
		verticalSpan = 1;
		grabExcessHorizontalSpace = false;
		grabExcessVerticalSpace = false;
		minimumWidth = 0;
		minimumHeight = 0;
		exclude = false;
		cacheWidth = -1;
		cacheHeight = -1;
		defaultWidth = -1;
		defaultHeight = -1;
		currentWidth = -1;
		currentHeight = -1;
		widthHint = i;
		heightHint = j;
	}

	void computeSize(ZComponent control, int i, int j) {
		if (cacheWidth != -1 && cacheHeight != -1)
			return;
		if (i == widthHint && j == heightHint) {
			if (defaultWidth == -1 || defaultHeight == -1 || i != defaultWhint
					|| j != defaultHhint) {
				ZPoint point = control.computeSize(i, j);
				defaultWhint = i;
				defaultHhint = j;
				defaultWidth = point.x;
				defaultHeight = point.y;
			}
			cacheWidth = defaultWidth;
			cacheHeight = defaultHeight;
			return;
		}
		if (currentWidth == -1 || currentHeight == -1 || i != currentWhint
				|| j != currentHhint) {
			ZPoint point1 = control.computeSize(i, j);
			currentWhint = i;
			currentHhint = j;
			currentWidth = point1.x;
			currentHeight = point1.y;
		}
		cacheWidth = currentWidth;
		cacheHeight = currentHeight;
	}

	void flushCache() {
		cacheWidth = cacheHeight = -1;
		defaultWidth = defaultHeight = -1;
		currentWidth = currentHeight = -1;
	}

	String getName() {
		String s = getClass().getName();
		int i = s.lastIndexOf('.');
		if (i == -1)
			return s;
		else
			return s.substring(i + 1, s.length());
	}

	public String toString() {
		String s = "";
		switch (horizontalAlignment) {
		case 4: // '\004'
			s = "SWT.FILL";
			break;

		case 1: // '\001'
			s = "SWT.BEGINNING";
			break;

		case 16384:
			s = "SWT.LEFT";
			break;

		case 16777224:
			s = "SWT.END";
			break;

		case 3: // '\003'
			s = "GridData.END";
			break;

		case 131072:
			s = "SWT.RIGHT";
			break;

		case 16777216:
			s = "SWT.CENTER";
			break;

		case 2: // '\002'
			s = "GridData.CENTER";
			break;

		default:
			s = "Undefined " + horizontalAlignment;
			break;
		}
		String s1 = "";
		switch (verticalAlignment) {
		case 4: // '\004'
			s1 = "SWT.FILL";
			break;

		case 1: // '\001'
			s1 = "SWT.BEGINNING";
			break;

		case 128:
			s1 = "SWT.TOP";
			break;

		case 16777224:
			s1 = "SWT.END";
			break;

		case 3: // '\003'
			s1 = "GridData.END";
			break;

		case 1024:
			s1 = "SWT.BOTTOM";
			break;

		case 16777216:
			s1 = "SWT.CENTER";
			break;

		case 2: // '\002'
			s1 = "GridData.CENTER";
			break;

		default:
			s1 = "Undefined " + verticalAlignment;
			break;
		}
		String s2 = getName() + " {";
		s2 = s2 + "horizontalAlignment=" + s + " ";
		if (horizontalIndent != 0)
			s2 = s2 + "horizontalIndent=" + horizontalIndent + " ";
		if (horizontalSpan != 1)
			s2 = s2 + "horizontalSpan=" + horizontalSpan + " ";
		if (grabExcessHorizontalSpace)
			s2 = s2 + "grabExcessHorizontalSpace=" + grabExcessHorizontalSpace
					+ " ";
		if (widthHint != -1)
			s2 = s2 + "widthHint=" + widthHint + " ";
		if (minimumWidth != 0)
			s2 = s2 + "minimumWidth=" + minimumWidth + " ";
		s2 = s2 + "verticalAlignment=" + s1 + " ";
		if (verticalIndent != 0)
			s2 = s2 + "verticalIndent=" + verticalIndent + " ";
		if (verticalSpan != 1)
			s2 = s2 + "verticalSpan=" + verticalSpan + " ";
		if (grabExcessVerticalSpace)
			s2 = s2 + "grabExcessVerticalSpace=" + grabExcessVerticalSpace
					+ " ";
		if (heightHint != -1)
			s2 = s2 + "heightHint=" + heightHint + " ";
		if (minimumHeight != 0)
			s2 = s2 + "minimumHeight=" + minimumHeight + " ";
		if (exclude)
			s2 = s2 + "exclude=" + exclude + " ";
		s2 = s2.trim();
		s2 = s2 + "}";
		return s2;
	}

	public int verticalAlignment;
	public int horizontalAlignment;
	public int widthHint;
	public int heightHint;
	public int horizontalIndent;
	public int verticalIndent;
	public int horizontalSpan;
	public int verticalSpan;
	public boolean grabExcessHorizontalSpace;
	public boolean grabExcessVerticalSpace;
	public int minimumWidth;
	public int minimumHeight;
	public boolean exclude;
	public static final int BEGINNING = 1;
	public static final int CENTER = 2;
	public static final int END = 3;
	public static final int FILL = 4;
	public static final int VERTICAL_ALIGN_BEGINNING = 2;
	public static final int VERTICAL_ALIGN_CENTER = 4;
	public static final int VERTICAL_ALIGN_END = 8;
	public static final int VERTICAL_ALIGN_FILL = 16;
	public static final int HORIZONTAL_ALIGN_BEGINNING = 32;
	public static final int HORIZONTAL_ALIGN_CENTER = 64;
	public static final int HORIZONTAL_ALIGN_END = 128;
	public static final int HORIZONTAL_ALIGN_FILL = 256;
	public static final int GRAB_HORIZONTAL = 512;
	public static final int GRAB_VERTICAL = 1024;
	public static final int FILL_VERTICAL = 1040;
	public static final int FILL_HORIZONTAL = 768;
	public static final int FILL_BOTH = 1808;
	int cacheWidth;
	int cacheHeight;
	int defaultWhint;
	int defaultHhint;
	int defaultWidth;
	int defaultHeight;
	int currentWhint;
	int currentHhint;
	int currentWidth;
	int currentHeight;
}