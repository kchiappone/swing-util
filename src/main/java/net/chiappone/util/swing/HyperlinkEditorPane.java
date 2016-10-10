package net.chiappone.util.swing;

import javax.swing.*;
import java.awt.*;

/**
 * A HyperlinkEditorPane is a JEditorPane with embedded and clickable
 * hyperlinks.
 *
 * @author Kurtis Chiappone
 */
public class HyperlinkEditorPane extends JEditorPane {

    private static final long serialVersionUID = 1L;

    /**
     * @param text
     */
    public HyperlinkEditorPane( String text ) {

        super();
        this.setContentType( "text/html" );
        this.setText( "<html><body style=\"" + getStyle() + "\">" + text + "</body></html>" );
        this.setEditable( false );
        this.addHyperlinkListener( new HyperlinkBrowser() );

    }

    /**
     * @return
     */
    private String getStyle() {

        // Set the style by copying JLabel's style
        JLabel label = new JLabel();
        this.setBackground( label.getBackground() );

        Font font = label.getFont();
        StringBuffer style = new StringBuffer( "font-family:" + font.getFamily() + ";" );
        style.append( "font-weight:" + ( font.isBold() ? "bold" : "normal" ) + ";" );
        style.append( "font-size:" + font.getSize() + "pt;" );

        return style.toString();

    }

}
