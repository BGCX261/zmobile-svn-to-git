package net.zmobile.ui;


import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import com.sun.midp.lcdui.TextCursor;

public class Text {

	public Text() {
	}

	public static void validateCursor(String str, Font font, int w, int h,
			int offset, int options, TextCursor cursor) {
		if (cursor == null && (str == null || str.length() == 0))
			return;
		if (str == null)
			str = "";
		char text[] = str.toCharArray();
		int fontHeight = font.getHeight();
		if (cursor != null && !cursor.visible)
			cursor = null;
		int inout[] = new int[11];
		inout[5] = fontHeight;
		inout[3] = w;
		inout[4] = h;
		inout[7] = options;
		inout[9] = font.charsWidth(ellipsis, 0, 3);
		inout[0] = 0;
		inout[1] = 0;
		inout[2] = 0;
		inout[8] = offset;
		int height = 0;
		do {
			height += fontHeight;
			getNextLine(text, font, inout);
			int lineStart = inout[0];
			int lineEnd = inout[1];
			int newLineStart = inout[2];
			if (cursor.index >= lineStart && cursor.index <= lineEnd) {
				int off = offset;
				if (cursor.index > lineStart)
					off += font.charsWidth(text, lineStart, cursor.index
							- lineStart);
				cursor.x = off;
				cursor.y = height;
				cursor.width = 1;
				cursor.height = fontHeight;
				return;
			}
			inout[0] = newLineStart;
			offset = 0;
		} while (inout[1] < text.length);
	}

	public static int getHeightForWidth(String str, Font font, int w, int offset) {
		if (str == null || str.length() == 0 || w <= 0)
			return 0;
		else
			return font.getHeight()
					* linesOfText(str.toCharArray(), offset, w, font);
	}

	public static int paint(String str, Font font, Graphics g, int w, int h,
			int offset, int options, TextCursor cursor) {
		if (w <= 0 || cursor == null && (str == null || str.length() == 0))
			return 0;
		if (str == null)
			str = "";
		Font oldFont = g != null ? g.getFont() : null;
		if (oldFont != font && g != null)
			g.setFont(font);
		char text[] = str.toCharArray();
		int fontHeight = font.getHeight();
		int inout[] = new int[11];
		inout[5] = fontHeight;
		inout[3] = w;
		inout[4] = h;
		inout[7] = options;
		inout[9] = font.charsWidth(ellipsis, 0, 3);
		inout[0] = 0;
		inout[1] = 0;
		inout[2] = 0;
		inout[8] = offset;
		int numLines = 0;
		int height = 0;
		do {
			numLines++;
			height += fontHeight;
			if (height > h && g != null)
				break;
			inout[6] = numLines;
			boolean truncate = getNextLine(text, font, inout);
			int lineStart = inout[0];
			int lineEnd = inout[1];
			int newLineStart = inout[2];
			if (lineEnd > lineStart) {
				if ((options & 1) == 1 && g != null) {
					g.fillRect(offset, height - fontHeight, inout[10],
							fontHeight);
					g.setColor(FG_H_COLOR);
				}
				if ((options & 2) == 2 && g != null)
					drawHyperLink(g, offset, height, inout[10]);
				if (cursor != null && cursor.option == 1 && cursor.x >= 0
						&& cursor.y == height) {
					int bestIndex = lineStart;
					int bestX = offset;
					int curX = offset;
					int curY = height;
					for (int i = lineStart; i < lineEnd; i++) {
						char ch = text[i];
						if (g != null)
							g.drawChar(ch, curX, curY, 36);
						if (Math.abs(curX - cursor.preferredX) < Math.abs(bestX
								- cursor.preferredX)) {
							bestIndex = i;
							bestX = curX;
						}
						curX += font.charWidth(ch);
					}

					if (Math.abs(curX - cursor.preferredX) < Math.abs(bestX
							- cursor.preferredX)) {
						bestIndex = lineEnd;
						bestX = curX;
					}
					cursor.index = bestIndex;
					cursor.x = bestX;
					cursor.y = height;
					cursor.option = 0;
				} else if (g != null)
					g.drawChars(text, lineStart, lineEnd - lineStart, offset,
							height, 36);
				if (truncate) {
					int curX = inout[10];
					if (g != null)
						g.drawChars(ellipsis, 0, 3, curX + offset, height, 36);
				}
			}
			if (cursor != null && cursor.option == 0
					&& cursor.index >= lineStart && cursor.index <= lineEnd) {
				int off = offset;
				if (cursor.index > lineStart)
					off += font.charsWidth(text, lineStart, cursor.index
							- lineStart);
				cursor.x = off;
				cursor.y = height;
				cursor.width = 1;
				cursor.height = fontHeight;
				if (cursor.visible && g != null)
					cursor.paint(g);
				cursor = null;
			}
			if ((options & 1) == 1 && g != null)
				g.setColor(FG_COLOR);
			inout[0] = newLineStart;
			inout[8] = 0;
			offset = 0;
		} while (inout[1] < text.length);
		if (g != null)
			g.setFont(oldFont);
		return inout[10];
	}

	public static void drawHyperLink(Graphics g, int x, int y, int w) {
//		int linkHeight = HYPERLINK_IMG.getHeight();
		int linkWidth = HYPERLINK_IMG.getWidth();
		int oldClipX = g.getClipX();
		int oldClipW = g.getClipWidth();
		int oldClipY = g.getClipY();
		int oldClipH = g.getClipHeight();
		g.clipRect(x, oldClipY, w, oldClipH);
		int j = (x + w) - linkWidth;
		for (int first = x - linkWidth; j > first; j -= linkWidth)
			g.drawImage(HYPERLINK_IMG, j, y, 36);

		g.setClip(oldClipX, oldClipY, oldClipW, oldClipH);
	}

	public static int linesOfText(char text[], int offset, int width, Font font) {
		int numLines = 0;
		if (text == null || text.length == 0)
			return numLines;
		int inout[] = new int[11];
		inout[5] = font.getHeight();
		inout[3] = width;
		inout[7] = 0;
		inout[9] = font.charsWidth(ellipsis, 0, 3);
		inout[0] = 0;
		inout[1] = 0;
		inout[2] = 0;
		inout[8] = offset;
		inout[10] = 0;
		do {
			numLines++;
			inout[6] = numLines;
			getNextLine(text, font, inout);
			inout[0] = inout[2];
			inout[8] = 0;
		} while (inout[1] < text.length);
		return numLines;
	}

	public static int getWidestLineWidth(char text[], int offset, int width,
			Font font) {
		int numLines = 0;
		if (text == null || text.length == 0)
			return 0;
		int inout[] = new int[11];
		inout[5] = font.getHeight();
		inout[3] = width;
		inout[7] = 0;
		inout[9] = font.charsWidth(ellipsis, 0, 3);
		inout[0] = 0;
		inout[1] = 0;
		inout[2] = 0;
		inout[8] = offset;
		inout[10] = 0;
		int widest = 0;
		do {
			numLines++;
			inout[6] = numLines;
			getNextLine(text, font, inout);
			if (inout[10] > width && offset == 0)
				return width;
			if (inout[10] > widest)
				widest = inout[10];
			inout[0] = inout[2];
			inout[8] = 0;
		} while (inout[1] < text.length);
		return widest;
	}

	private static boolean getNextLine(char text[], Font font, int inout[]) {
		int curLoc = inout[0];
		boolean foundBreak = false;
		int leftWidth = 0;
		inout[1] = inout[2] = text.length;
		int i = curLoc;
		do {
			if (i >= text.length)
				break;
			if (text[i] == '\n') {
				inout[1] = i;
				inout[2] = i + 1;
				break;
			}
			i++;
		} while (true);
		inout[10] = font.charsWidth(text, curLoc, inout[1] - curLoc);
		if (inout[10] + inout[8] < inout[3])
			return false;
		inout[10] = 0;
		do {
			if (curLoc >= text.length)
				break;
			if (text[curLoc] == '\n') {
				inout[1] = curLoc;
				inout[2] = curLoc + 1;
				break;
			}
			if (text[curLoc] == ' ') {
				inout[1] = curLoc;
				inout[2] = curLoc + 1;
				foundBreak = true;
			}
			inout[10] += font.charWidth(text[curLoc]);
			if ((inout[7] & 4) == 4 && (inout[6] + 1) * inout[5] > inout[4]
					&& inout[10] + inout[8] + inout[9] > inout[3]) {
				leftWidth = font.charsWidth(text, curLoc + 1, text.length
						- curLoc - 1);
				if (inout[8] + inout[10] + leftWidth > inout[3]) {
					inout[10] += inout[9];
					inout[1] = curLoc;
					inout[2] = curLoc;
					return true;
				} else {
					inout[10] += leftWidth;
					inout[1] = text.length;
					inout[2] = text.length;
					return false;
				}
			}
			if (inout[8] + inout[10] > inout[3]) {
				if (!foundBreak)
					if (inout[8] > 0) {
						inout[1] = inout[0];
						inout[2] = inout[0];
					} else {
						inout[1] = curLoc;
						inout[2] = curLoc;
					}
				return false;
			}
			curLoc++;
		} while (true);
		inout[1] = curLoc;
		return false;
	}

	public static int getTwoStringsWidth(String firstStr, String secondStr,
			Font firstFont, Font secondFont, int width, int pad) {
		if ((firstStr == null || firstStr.length() == 0)
				&& (secondStr == null || secondStr.length() == 0) || width <= 0)
			return 0;
		int inout[] = new int[11];
		int offset = 0;
		int widest = 0;
		int numLines = 0;
		if (firstStr != null && firstStr.length() > 0) {
			char text[] = firstStr.toCharArray();
			inout[5] = firstFont.getHeight();
			inout[3] = width;
			inout[7] = 0;
			inout[9] = firstFont.charsWidth(ellipsis, 0, 3);
			inout[0] = 0;
			inout[1] = 0;
			inout[2] = 0;
			inout[8] = offset;
			inout[10] = 0;
			do {
				numLines++;
				inout[6] = numLines;
				getNextLine(text, firstFont, inout);
				if (inout[10] > widest)
					widest = inout[10];
				inout[0] = inout[2];
				inout[8] = 0;
			} while (inout[1] < firstStr.length());
			offset = inout[10];
		}
		if (secondStr != null && secondStr.length() > 0) {
			if (offset > 0)
				offset += pad;
			char text[] = secondStr.toCharArray();
			if (numLines > 0)
				numLines--;
			inout[5] = secondFont.getHeight();
			inout[3] = width;
			inout[7] = 0;
			inout[9] = secondFont.charsWidth(ellipsis, 0, 3);
			inout[0] = 0;
			inout[1] = 0;
			inout[2] = 0;
			inout[8] = offset;
			inout[10] = 0;
			do {
				numLines++;
				inout[6] = numLines;
				getNextLine(text, secondFont, inout);
				if (inout[8] + inout[10] > widest)
					widest = inout[8] + inout[10];
				inout[0] = inout[2];
				inout[8] = 0;
			} while (inout[1] < secondStr.length());
		}
		return widest;
	}

	public static int getTwoStringsHeight(String firstStr, String secondStr,
			Font firstFont, Font secondFont, int width, int pad) {
		if ((firstStr == null || firstStr.length() == 0)
				&& (secondStr == null || secondStr.length() == 0) || width <= 0)
			return 0;
		int inout[] = new int[11];
		int offset = 0;
//		int widest = 0;
		int numLines = 0;
		int height = 0;
		int fontHeight = 0;
		if (firstStr != null && firstStr.length() > 0) {
			char text[] = firstStr.toCharArray();
			fontHeight = firstFont.getHeight();
			inout[5] = fontHeight;
			inout[3] = width;
			inout[7] = 0;
			inout[9] = firstFont.charsWidth(ellipsis, 0, 3);
			inout[0] = 0;
			inout[1] = 0;
			inout[2] = 0;
			inout[8] = 0;
			inout[10] = 0;
			do {
				numLines++;
				height += fontHeight;
				inout[6] = numLines;
				getNextLine(text, firstFont, inout);
				inout[0] = inout[2];
			} while (inout[1] < firstStr.length());
			offset = inout[10];
			if (secondStr == null || secondStr.length() == 0) {
				if (firstStr.charAt(firstStr.length() - 1) == '\n')
					height -= fontHeight;
				return height;
			}
		}
		if (secondStr != null && secondStr.length() > 0) {
			if (offset > 0)
				offset += pad;
			char text[] = secondStr.toCharArray();
			fontHeight = secondFont.getHeight();
			if (numLines > 0) {
				numLines--;
				if (inout[5] > fontHeight)
					height -= fontHeight;
				else
					height -= inout[5];
			}
			inout[5] = fontHeight;
			inout[3] = width;
			inout[7] = 0;
			inout[9] = secondFont.charsWidth(ellipsis, 0, 3);
			inout[0] = 0;
			inout[1] = 0;
			inout[2] = 0;
			inout[8] = offset;
			inout[10] = 0;
			do {
				numLines++;
				height += fontHeight;
				inout[6] = numLines;
				getNextLine(text, secondFont, inout);
				inout[0] = inout[2];
				inout[8] = 0;
			} while (inout[1] < secondStr.length());
			if (secondStr.charAt(secondStr.length() - 1) == '\n')
				height -= fontHeight;
		}
		return height;
	}

	private static final char ellipsis[] = { '.', '.', '.' };
//	private static final int GNL_LINE_START = 0;
//	private static final int GNL_LINE_END = 1;
//	private static final int GNL_NEW_LINE_START = 2;
//	private static final int GNL_WIDTH = 3;
//	private static final int GNL_HEIGHT = 4;
//	private static final int GNL_FONT_HEIGHT = 5;
//	private static final int GNL_NUM_LINES = 6;
//	private static final int GNL_OPTIONS = 7;
//	private static final int GNL_OFFSET = 8;
//	private static final int GNL_ELLIP_WIDTH = 9;
//	private static final int GNL_LINE_WIDTH = 10;
//	private static final int GNL_NUM_PARAMS = 11;
	public static int FG_COLOR;
	public static int FG_H_COLOR;
	public static Image HYPERLINK_IMG;
	public static final int NORMAL = 0;
	public static final int INVERT = 1;
	public static final int HYPERLINK = 2;
	public static final int TRUNCATE = 4;
	public static final int PAINT_USE_CURSOR_INDEX = 0;
	public static final int PAINT_GET_CURSOR_INDEX = 1;
	public static final int PAINT_HIDE_CURSOR = 2;

//	static {
//		ImageItem ii = new ImageItem(null, null, 0, null);
//		ii = null;
//	}
}


/*
	DECOMPILATION REPORT

	Decompiled from: D:\WTK22\lib\midpapi20.jar
	Total time: 94 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/