package net.chiappone.util.swing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.net.URI;
import java.net.URL;

/**
 * A HyperlinkBrowser is a HyperlinkListener that uses
 * {@link Desktop#browse(URI)} to browse to the URI.
 *
 * @author Kurtis Chiappone
 */
public class HyperlinkBrowser implements HyperlinkListener {

    private static final Logger logger = LoggerFactory.getLogger( HyperlinkBrowser.class );

    @Override public void hyperlinkUpdate( HyperlinkEvent e ) {

        if ( e.getEventType().equals( HyperlinkEvent.EventType.ACTIVATED ) ) {

            if ( Desktop.isDesktopSupported() ) {

                Desktop desktop = Desktop.getDesktop();
                URL url = e.getURL();

                if ( url != null ) {

                    try {

                        URI uri = url.toURI();
                        desktop.browse( uri );

                    } catch ( Exception ex ) {

                        logger.error( "Error browsing to URL: {}", url, ex );

                    }

                } else {

                    logger.error( "URL is null" );

                }

            } else {

                logger.error( "Desktop is not supported" );

            }

        }

    }

}
