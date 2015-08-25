/*
 * %W% %E%
 *
 * Copyright (c) 2000-2004 Sun Microsystems, Inc. All rights reserved. 
 * PROPRIETARY/CONFIDENTIAL
 * Use is subject to license terms
 */
package net.zmobile.ui;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;


/**
 *
 * @version 2.0
 */
public class TextInput extends TextBox implements CommandListener {

    private final static Command CMD_OK = new Command("OK", Command.OK,    
                                                        1);        
    private final static Command CMD_CANCEL =
	new Command("Cancel", Command.CANCEL, 1);

    public TextInput(String text) {
        super("Enter Text", text, 50, TextField.ANY);
        addCommand(CMD_OK);
        addCommand(CMD_CANCEL);
        setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == CMD_OK) {
            // update the table's cell and return
        } else if (c == CMD_CANCEL) {
            // return without updating the table's cell
        }
    }
    
}
