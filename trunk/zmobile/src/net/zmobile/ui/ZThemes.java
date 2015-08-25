package net.zmobile.ui;

import javax.microedition.lcdui.Image;


public class ZThemes {
	//ZForm
	public final static int TILTE_HEIGHT = 16;
	public final static int TILTE_FORE_COLOR = 0xffffff;
	public final static int TILTE_BACK_COLOR = 0x0000ff;
	
	public final static int BODY_BACK_COLOR = 0xffffff;
	
	public final static int FOOTER_HEIGHT = 16;
	public final static int FOOTER_FORE_COLOR = 0xffffff;
	public final static int FOOTER_BACK_COLOR = 0x0000ff;
	//ZLabel
	public final static int LABEL_H_GRAP = 1;
	public final static int LABEL_V_GRAP = 2;
	//ZTicker
	public final static int TICKER_HEIGHT = 10;
	//ZGroup
	public final static int ACR_HEIGHT = 16;
	public final static int GROUP_COLOR = 0x5a5a5a;
	//ZContainer
	public final static int SCROLL_WIDTH = 12;
	
	public static String NULL_IMAGE_FILE = "";
	public static Image NULL_IMAGE = null;
	static{
		try{
			NULL_IMAGE = Image.createImage(NULL_IMAGE_FILE);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
