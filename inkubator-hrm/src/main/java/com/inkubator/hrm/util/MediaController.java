package com.inkubator.hrm.util;

import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesIO;
import com.inkubator.webcore.util.FacesUtil;
import java.io.IOException;
import java.io.InputStream;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "mediaController")
@ApplicationScoped
public class MediaController extends BaseController {

    @ManagedProperty(value = "#{facesIO}")
    private FacesIO facesIO;

    public void setFacesIO(FacesIO facesIO) {
        this.facesIO = facesIO;
    }

    public StreamedContent getPaySalaryUploadFile() throws IOException {
        FacesContext context = FacesUtil.getFacesContext();
        StreamedContent streamedContent = new DefaultStreamedContent();

        if (!context.getRenderResponse()) {
            InputStream is = facesIO.getInputStreamFromName("over_view.mp4");
            streamedContent = new DefaultStreamedContent(is, "video/mp4", "over_view.mp4");
        }
        return streamedContent;
    }

}
