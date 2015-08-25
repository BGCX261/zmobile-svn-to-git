package net.zmobile.ui;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;

import net.zmobile.ZMobile;
import net.zmobile.ge.ZPoint;

public class ZTextBox extends ZComponent {

	TextBox textBox = new TextBox(_text, _text, 5000, TextField.ANY);

	public ZTextBox(String string) {
		this.setText(string);

		textBox.addCommand(new Command("OK", Command.BACK, Command.OK));
		textBox.addCommand(new Command("Cancel", Command.BACK, Command.OK));
		textBox.setCommandListener(new CommandListener(){

			public void commandAction(Command command, Displayable displayable) {
						if(command.getLabel().equals("OK")){
							_text = textBox.getString();
						}
						ZForm.display.setCurrent(ZFormCanvas.instance);
			}
			
		});
		this.addListener(ZMobile.EVENT_KEY_SELECT_RELESE, new Listener(){

			public void event(int x, int y, int key,ZComponent comp) {
				if(!(ZForm.display==null)){
					textBox.setString(getText());
					ZForm.display.setCurrent(textBox);
				}
			}
			
		});
		this.addListener(ZMobile.EVENT_POINTER_RELEASED, new Listener(){
			
			public void event(int x, int y, int key,ZComponent comp) {
				if(!(ZForm.display==null)){
					textBox.setString(getText());
					ZForm.display.setCurrent(textBox);
				}
			}
			
		});
	}

	public ZTextBox(String value, byte cols, byte rows, byte type) {

	}

	Image _image = null;

	public void draw(Graphics g) {
		if (_bodyRect.width > 0 && _bodyRect.height > 0 && _image == null) {
			_image = Image.createImage(_bodyRect.width, _bodyRect.height);
		}
		if (_image == null)
			return;
		Graphics tmpg = _image.getGraphics();
		tmpg.setColor(0xffffff);
		tmpg.fillRect(0, 0, _bodyRect.width - ZMobile.GRAP, _bodyRect.height - ZMobile.GRAP);

		tmpg.setColor(0x0);
		Text.paint(_text, _font, tmpg, _bodyRect.width, _bodyRect.height, 0, Text.NORMAL, null);

		g.drawImage(_image, _bodyRect.x + 2 * ZMobile.GRAP, _bodyRect.y + 2 * ZMobile.GRAP,
				Graphics.TOP | Graphics.LEFT);
		g.setColor(0x0);
		drawRect(g,_bodyRect,-ZMobile.GRAP);
	}

	public void drawStatus(Graphics g) {
		if (this._isFoucus) {
			g.setColor(0x0000ff);
		} else if (this._isPressed) {
			g.setColor(0x00ff00);
		} else {
			return;
		}
		drawRect(g,_bodyRect,-ZMobile.GRAP);
		drawRect(g,_bodyRect,- 2 * ZMobile.GRAP);
	}

	public ZPoint computeSize(int w, int h) {
		_bodyRect.width = Font.getDefaultFont().stringWidth(_text);
		_bodyRect.height = Font.getDefaultFont().getHeight();
		return new ZPoint(w,_bodyRect.height);
	}

	public void setInputMode(byte mode) {
		
	}

}
