package com.inkubator.hrm.web.personalia;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BioAddress;
import com.inkubator.hrm.service.BioAddressService;
import com.inkubator.hrm.web.model.BioAddressModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "bioAddressMapController")
@ViewScoped
public class BioAddressMapController extends BaseController {
	
	private BioAddressModel model;
	private MapModel mapModel;
	@ManagedProperty(value = "#{bioAddressService}")
	private BioAddressService bioAddressService;
	
	@PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try{
        	model = new BioAddressModel();
        	mapModel =  new DefaultMapModel();
        	String bioAddressId = FacesUtil.getRequestParameter("bioAddressId");
            if (StringUtils.isNotEmpty(bioAddressId)) {
            	BioAddress bioAddress = bioAddressService.getEntiyByPK(Long.parseLong(bioAddressId));
            	if(bioAddress != null){
            		model.setId(bioAddress.getId());
            		model.setAddressDetail(bioAddress.getAddressDetail());
            		Double latitude = bioAddress.getLatitude() == null ? HRMConstant.DEFAULT_LATITUDE : Double.parseDouble(bioAddress.getLatitude());
            		Double longitude = bioAddress.getLongitude() == null ? HRMConstant.DEFAULT_LONGITUDE : Double.parseDouble(bioAddress.getLongitude());
            		model.setLatitude(latitude);
            		model.setLongitude(longitude);

                    //marker the address
            		LatLng coord = new LatLng(latitude, longitude);
                    mapModel.addOverlay(new Marker(coord, bioAddress.getAddressDetail()));
            	}
            }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
	}
	
	@PreDestroy
    public void cleanAndExit() {
		model = null;
		mapModel = null;
		bioAddressService = null;
	}
	
	public BioAddressModel getModel() {
		return model;
	}

	public void setModel(BioAddressModel model) {
		this.model = model;
	}

	public MapModel getMapModel() {
		return mapModel;
	}

	public void setMapModel(MapModel mapModel) {
		this.mapModel = mapModel;
	}

	public void setBioAddressService(BioAddressService bioAddressService) {
		this.bioAddressService = bioAddressService;
	}

	public void doSave() {
        try {
            bioAddressService.updateMapCoordinate(model.getId(), String.valueOf(model.getLatitude()), String.valueOf(model.getLongitude()));
            RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            cleanAndExit();
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
	}
	
	public void onPointSelect(PointSelectEvent event) {
      mapModel.getMarkers().clear();
      LatLng location = event.getLatLng();
      model.setLatitude(location.getLat());
      model.setLongitude(location.getLng());
      mapModel.addOverlay(new Marker(location, model.getAddressDetail()));
  }

}
