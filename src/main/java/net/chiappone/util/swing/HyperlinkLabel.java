package net.chiappone.util.swing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * A HyperlinkLabel is a JLabel with an embedded clickable hyperlink.
 *
 * @author Kurtis Chiappone
 */
public class HyperlinkLabel extends JLabel implements MouseListener {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger( HyperlinkLabel.class );
    private URI uri = null;

    public HyperlinkLabel( String text, URI uri ) throws URISyntaxException {

        this.setText( "<html><u>" + text + "</u></html>" );
        this.uri = uri;
        initialize();

    }

    public HyperlinkLabel( String text, String uri ) throws URISyntaxException {

        this.setText( "<html><u>" + text + "</u></html>" );
        this.uri = new URI( uri );
        initialize();

    }

    public URI getURI() {

        return uri;

    }

    private void initialize() {

        this.setForeground( Color.BLUE );
        this.setOpaque( false );
        this.setToolTipText( getURI().toString() );
        this.addMouseListener( this );

    }

    @Override public void mouseClicked( MouseEvent e ) {

        if ( e.getClickCount() > 0 ) {

            if ( Desktop.isDesktopSupported() ) {

                Desktop desktop = Desktop.getDesktop();

                try {

                    desktop.browse( getURI() );

                } catch ( IOException ex ) {

                    logger.error( "Error browsing to URI: {}", getURI(), e );

                }

            }

        }

    }

    @Override public void mouseEntered( MouseEvent e ) {

        this.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );

    }

    @Override public void mouseExited( MouseEvent e ) {

        this.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );

    }

    @Override public void mousePressed( MouseEvent e ) {

        this.setForeground( Color.RED );
        this.setCursor( Cursor.getPredefinedCursor( Cursor.WAIT_CURSOR ) );

    }

    @Override public void mouseReleased( MouseEvent e ) {

        this.setForeground( Color.BLUE );
        this.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );

    }

}