package net.zmobile;


public class ZMobile {

	public final static byte COMP = (byte)0;
	public final static byte BUTTON = (byte)1;
	public final static byte LABEL = (byte)2;
	public final static byte TEXTBOX = (byte)3;
	public final static byte IMAGE = (byte)4;
	public final static byte LINK = (byte)5;
	public final static byte LISTBOX = (byte)6;
	public final static byte TICKER = (byte)7;
	public final static byte PROGRESSBAR = (byte)8;
	
	
	public final static byte FILL_LAYOUT = (byte)10;
	public final static byte FILL_LAYOUT_V = (byte)10;
	public final static byte FILL_LAYOUT_H = (byte)11;
	public static final int FILL_VERTICAL = 512;
	public static final int FILL_HORIZONTAL = 256;
	
	public final static byte EVENT_NULL = (byte)0;
	
	public final static byte EVENT_KEY_PRESS = (byte)1;
	public final static byte EVENT_KEY_RELEASED = (byte)2;
	
	public final static byte EVENT_KEY_SELECT = (byte)4;
	public final static byte EVENT_KEY_SELECT_RELESE = (byte)8;
	
	public final static byte EVENT_POINTER_PRESS = (byte)16;
	public final static byte EVENT_POINTER_RELEASED = (byte)32;
	
	public final static byte EVENT_CHECKED = (byte)64;
	public final static byte EVENT_UNCHECKED = (byte)128;

	
	public static final int FILL = 4;
	public static final int TOP = 128;
	public static final int DOWN = 1024;
	public static final int BOTTOM = 1024;
	public static final int LEAD = 16384;
	public static final int LEFT = 16384;
	public static final int TRAIL = 131072;
	public static final int RIGHT = 131072;
	public static final int CENTER = 16777216;
	public static final int HORIZONTAL = 256;
	public static final int VERTICAL = 512;
	public static void error(int error){
		
	}
	
	public final static byte GRAP = 1;
	
	public static final int KEY_MIDDLE = 8;
	public static int KEY_LEFT_SOFT = -6;
	public static int KEY_RIGHT_SOFT = -7;
	public static int KEY_GREEN = -10;
	public static int KEY_STATE_NULL = 0;
	public static int KEY_STATE_PRESSED = 1;
	public static int KEY_STATE_RELEASED = 2;
	public static int KEY_STATE_COMMANDACTION = 3;
	
	public static final int DEFAULT = -1;
	
	
}
