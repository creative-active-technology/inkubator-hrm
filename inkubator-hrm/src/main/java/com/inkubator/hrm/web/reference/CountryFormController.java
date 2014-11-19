package com.inkubator.hrm.web.reference;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Country;
import com.inkubator.hrm.service.CountryService;
import com.inkubator.hrm.web.model.CountryModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author Taufik Hidayat
 */
@ManagedBean(name = "countryFormController")
@ViewScoped
public class CountryFormController extends BaseController {

    private CountryModel countryModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{countryService}")
    private CountryService countryService;
    private UploadedFile file;
    private byte[] buffer;
    private Boolean infoRendered;
    private MapModel emptyModel;
    private double lat;
    private double lng;
    private double defaultLat;
    private double defaultLng;
    private String tipeImage;
    private String locale;
    private ResourceBundle messages;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        locale = FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString();
        messages = ResourceBundle.getBundle("messages", new Locale(locale));
        String id = FacesUtil.getRequestParameter("execution");
        countryModel = new CountryModel();
        isUpdate = Boolean.FALSE;
        infoRendered = Boolean.FALSE;
        emptyModel = new DefaultMapModel();

        defaultLat = HRMConstant.DEFAULT_LATITUDE;
        defaultLng = HRMConstant.DEFAULT_LONGITUDE;

        if (id != null) {
            try {
                Country country = countryService.getEntiyByPK(Long.parseLong(id.substring(1)));

                countryModel.setId(country.getId());
                countryModel.setCountryCode(country.getCountryCode());
                countryModel.setCountryName(country.getCountryName());
                countryModel.setFlagIcon(country.getFlagIcon());
                countryModel.setPhoneCode(country.getPhoneCode());
                isUpdate = Boolean.TRUE;
                infoRendered = Boolean.TRUE;

                lat = Double.parseDouble(country.getLatitude());
                lng = Double.parseDouble(country.getLongitude());
                LatLng coord = new LatLng(lat, lng);

                //Basic marker
                emptyModel.addOverlay(new Marker(coord, country.getCountryName()));

                defaultLat = Double.parseDouble(country.getLatitude());
                defaultLng = Double.parseDouble(country.getLongitude());

            } catch (Exception e) {
                LOGGER.error("Error", e);
            }
        }

    }

    @PreDestroy
    public void cleanAndExit() {
        countryService = null;
        countryModel = null;
        isUpdate = null;
    }

    public CountryModel getCountryModel() {
        return countryModel;
    }

    public void setCountryModel(CountryModel countryModel) {
        this.countryModel = countryModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    public Boolean getInfoRendered() {
        return infoRendered;
    }

    public void setInfoRendered(Boolean infoRendered) {
        this.infoRendered = infoRendered;
    }

    public MapModel getEmptyModel() {
        return emptyModel;
    }

    public void setEmptyModel(MapModel emptyModel) {
        this.emptyModel = emptyModel;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getDefaultLat() {
        return defaultLat;
    }

    public void setDefaultLat(double defaultLat) {
        this.defaultLat = defaultLat;
    }

    public double getDefaultLng() {
        return defaultLng;
    }

    public void setDefaultLng(double defaultLng) {
        this.defaultLng = defaultLng;
    }

    public String doBack() {
        return "/protected/reference/country_view.htm?faces-redirect=true";
    }

    public String doSave() {
        Country country = getEntityFromViewModel(countryModel);
        try {
            if (isUpdate) {
                countryService.update(country);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            } else {
                countryService.save(country);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }
            return "/protected/reference/country_detail.htm?faces-redirect=true&execution=e" + country.getId();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    private Country getEntityFromViewModel(CountryModel countryModel) {
        Country country = new Country();
        if (countryModel.getId() != null) {
            country.setId(countryModel.getId());
        }
        country.setCountryCode(countryModel.getCountryCode());

        country.setCountryName(countryModel.getCountryName());
        if (buffer != null) {
            country.setFlagIcon(buffer);
        } else {
            country.setFlagIcon(countryModel.getFlagIcon());
        }
        country.setPhoneCode(countryModel.getPhoneCode());
        country.setLatitude(String.valueOf(lat));
        country.setLongitude(String.valueOf(lng));
        return country;
    }

    public void handleFileUpload(FileUploadEvent event) {
        file = event.getFile();
        tipeImage = file.getContentType().split("/")[1];
        System.out.println(" File " + file);
        InputStream inputStream = null;
        try {
            inputStream = file.getInputstream();
            BufferedImage sourceImage = ImageIO.read(inputStream);
            int type = sourceImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : sourceImage.getType();
            BufferedImage scaledImg = resizeImage(sourceImage, type);
            buffer = bufferToByteArray(scaledImg);
            System.out.println("Buffernya : " + buffer);
            if (buffer != null) {
                FacesContext.getCurrentInstance().addMessage("formCountryFormId:flagUploadId", new FacesMessage(FacesMessage.SEVERITY_INFO, messages.getString("country.upload_info"), messages.getString("country.upload_successfully")));
            } else {
                FacesContext.getCurrentInstance().addMessage("formCountryFormId:flagUploadId", new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("country.upload_info"), messages.getString("country.upload_error")));
            }
        } catch (IOException ex) {
            LOGGER.error("Error", ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                LOGGER.error("Error", ex);
            }
        }
//        fileName = file.getFileName();
    }

    public void onPointSelect(PointSelectEvent event) {
//        System.out.println("Map Clicked");
        emptyModel.getMarkers().clear();
        LatLng location = event.getLatLng();

        lat = location.getLat();
        lng = location.getLng();
        defaultLat = lat;
        defaultLng = lng;
        emptyModel.addOverlay(new Marker(location, countryModel.getCountryName()));
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int type) {
        BufferedImage resizedImage = new BufferedImage(48, 32, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, 48, 32, null);
        g.dispose();
//        g.setComposite(AlphaComposite.Src);
//        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        return resizedImage;
    }

    public byte[] bufferToByteArray(BufferedImage originalImage) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.out.println("TIpe File " + tipeImage);
        ImageIO.write(originalImage, tipeImage, baos);
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();
        return imageInByte;
    }
}
