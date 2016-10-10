package net.chiappone.util;

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
	private URI uri = null;
	private static final Logger logger = LoggerFactory.getLogger( HyperlinkLabel.class );

	/**
	 * @param text
	 * @param uri
	 * @throws URISyntaxException
	 */
	public HyperlinkLabel( String text, URI uri ) throws URISyntaxException {

		this.setText( "<html><u>" + text + "</u></html>" );
		this.uri = uri;
		initialize();

	}

	/**
	 * @param text
	 * @param uri
	 * @throws URISyntaxException
	 */
	public HyperlinkLabel( String text, String uri ) throws URISyntaxException {

		this.setText( "<html><u>" + text + "</u></html>" );
		this.uri = new URI( uri );
		initialize();

	}

	/**
	 * This method returns the URI
	 *
	 * @return uri
	 */

	public URI getURI() {

		return uri;

	}

	/**
	 * This method initializes the link.
	 */

	private void initialize() {

		this.setForeground( Color.BLUE );
		this.setOpaque( false );
		this.setToolTipText( getURI().toString() );
		this.addMouseListener( this );

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked( MouseEvent e ) {

		if ( e.getClickCount() > 0 ) {

			if ( Desktop.isDesktopSupported() ) {

				Desktop desktop = Desktop.getDesktop();

				try {

					desktop.browse( getURI() );

				} catch ( IOException ex ) {

					logger.error( "Error browsing to URI: {}", getURI() );
					logger.error( "Stacktrace: ", e );

				}

			}

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered( MouseEvent e ) {

		this.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited( MouseEvent e ) {

		this.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed( MouseEvent e ) {

		this.setForeground( Color.RED );
		this.setCursor( Cursor.getPredefinedCursor( Cursor.WAIT_CURSOR ) );

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased( MouseEvent e ) {

		this.setForeground( Color.BLUE );
		this.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );

	}

}