package be.cytomine.formats.standard

import grails.util.Holders
import org.openslide.OpenSlide
import utils.ServerUtils

/**
 * Created by stevben on 22/04/14.
 */
class JPEGFormat extends CommonFormat {

    public JPEGFormat () {
        extensions = ["jpg", "jpeg"]
        IMAGE_MAGICK_FORMAT_IDENTIFIER = "Format: JPEG (Joint Photographic Experts Group JFIF format)"
        mimeType = "image/jpeg"
        iipURL = ServerUtils.getServers(Holders.config.cytomine.iipImageServerBase)
    }

    boolean detect() {
        boolean isJPEG = super.detect()
        if (isJPEG) { //check if not MRXS (fake JPEG)
            File slideFile = new File(absoluteFilePath)
            if (slideFile.canRead()) {
                try {
                    return !OpenSlide.detectVendor(slideFile)
                } catch (java.io.IOException e) {
                    //Not a file that OpenSlide can recognize
                    return true
                }
            } else {
                //throw ERROR reading file
            }
        }


    }
}
