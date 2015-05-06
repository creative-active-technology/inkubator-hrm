package com.inkubator.hrm.web.account;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.inkubator.hrm.entity.HrmMenu;
import com.inkubator.hrm.service.HrmMenuService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "roleMenusFormController")
@ViewScoped
public class RoleMenusFormController extends BaseController {

	private List<TreeNode> temporaryNodes;
	private TreeNode rootNode;
	private TreeNode[] selectedNodes;
	private List<Long> selectedMenuIds;
	@ManagedProperty(value = "#{hrmMenuService}")
    private HrmMenuService hrmMenuService;
	
	@PostConstruct
    @Override
    public void initialization() {
		super.initialization();
		
		//get all menu that already selected
		selectedMenuIds = new ArrayList<Long>();		
		String[] params = FacesUtil.getExternalContext().getRequestParameterValuesMap().get("menuIds");
		List<String> strMenuIds = (params == null) ? new ArrayList<String>() : new ArrayList<String>(Arrays.asList(params));
		for(String id : strMenuIds){
			selectedMenuIds.add(Long.parseLong(id));
        }		
		
		//create menu tree
		try {
			temporaryNodes =  new ArrayList<TreeNode>();
			rootNode = new DefaultTreeNode(new HrmMenu(), null);
			this.createNodes(null, null);
		} catch (Exception e) {
			LOGGER.error("Error ", e);
		}		
	}
	
	@PreDestroy
    public void cleanAndExit() {
		rootNode = null;
		selectedMenuIds = null;
		hrmMenuService = null;
		selectedNodes = null;
		temporaryNodes = null;
	}
	
	private void createNodes(Long parentId, TreeNode parentNode) throws Exception{
		if(parentNode == null){
			List<HrmMenu> menus = hrmMenuService.getAllDataFetchChildByLevel(1);
			for(HrmMenu m : menus){
				boolean isSelected = selectedMenuIds.contains(m.getId());
				TreeNode node = new DefaultTreeNode(m, rootNode);	
				node.setExpanded(Boolean.FALSE);
				node.setSelected(isSelected);
                if (!m.getHrmMenus().isEmpty()) {
                    this.createNodes(m.getId(), node);
                }
			}
			
		} else {
			List<HrmMenu> menus = hrmMenuService.getAllDataFetchChildByParentId(parentId);
			for(HrmMenu m : menus){
				boolean isSelected = selectedMenuIds.contains(m.getId());
				TreeNode node = new DefaultTreeNode(m, parentNode);                
				node.setExpanded(Boolean.FALSE);
                node.setSelected(isSelected);
                if (!m.getHrmMenus().isEmpty()) {
                    this.createNodes(m.getId(), node);
                }
			}
		}		
	}
	
	public void selectAllNode(){	
		temporaryNodes.clear();
		this.selectOrUnselectNode(rootNode, Boolean.TRUE);	
		selectedNodes = new TreeNode[temporaryNodes.size()];
		temporaryNodes.toArray(selectedNodes);
	}
	
	public void unselectAllNode(){
		selectedNodes =  new TreeNode[]{};
		this.selectOrUnselectNode(rootNode, Boolean.FALSE);		
	}
	
	private void selectOrUnselectNode(TreeNode node, Boolean option){
		if(node.getChildren().size() == 0){
			node.setSelected(option);
		} else {
			for(TreeNode child : node.getChildren()){
				this.selectOrUnselectNode(child, option);
			}
			node.setSelected(option);
		}		
		
		if(option == Boolean.TRUE){
			temporaryNodes.add(node);
		}
	}
	
	public void doPickMenuFromList(TreeNode[] nodes){
		RequestContext.getCurrentInstance().closeDialog(nodes);
		cleanAndExit();
	}

	public TreeNode getRootNode() {
		return rootNode;
	}

	public void setRootNode(TreeNode rootNode) {
		this.rootNode = rootNode;
	}	

	public List<Long> getSelectedMenuIds() {
		return selectedMenuIds;
	}

	public void setSelectedMenuIds(List<Long> selectedMenuIds) {
		this.selectedMenuIds = selectedMenuIds;
	}

	public HrmMenuService getHrmMenuService() {
		return hrmMenuService;
	}

	public void setHrmMenuService(HrmMenuService hrmMenuService) {
		this.hrmMenuService = hrmMenuService;
	}

	public TreeNode[] getSelectedNodes() {
		return selectedNodes;
	}

	public void setSelectedNodes(TreeNode[] selectedNodes) {
		this.selectedNodes = selectedNodes;
	}
	
}
