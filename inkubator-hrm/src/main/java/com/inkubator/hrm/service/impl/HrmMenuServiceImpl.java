package com.inkubator.hrm.service.impl;

import ch.lambdaj.Lambda;
import java.util.Date;

import org.apache.commons.collections.ListUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.HrmMenuDao;
import com.inkubator.hrm.dao.HrmMenuRoleDao;
import com.inkubator.hrm.entity.HrmMenu;
import com.inkubator.hrm.entity.HrmMenuRole;
import com.inkubator.hrm.service.HrmMenuService;
import com.inkubator.hrm.util.ResourceBundleUtil;
import com.inkubator.hrm.web.search.HrmMenuSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.DynamicMenuModel;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author rizkykojek
 */
@Service(value = "hrmMenuService")
@Lazy
public class HrmMenuServiceImpl extends IServiceImpl implements HrmMenuService {

    @Autowired
    private HrmMenuDao hrmMenuDao;
    @Autowired
    private HrmMenuRoleDao hrmMenuRoleDao;

    @Override
    public HrmMenu getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public HrmMenu getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public HrmMenu getEntiyByPK(Long id) throws Exception {
        return hrmMenuDao.getEntiyByPK(id);

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(HrmMenu entity) throws Exception {
        long totalDuplicates = hrmMenuDao.getTotalByName(entity.getName());
        if (totalDuplicates > 0) {
            throw new BussinessException("hrm_menu.error_duplicate_menu_name");
        }

        entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        HrmMenu parentMenu = entity.getHrmMenu() == null ? null : hrmMenuDao.getEntiyByPK(entity.getHrmMenu().getId());
        entity.setHrmMenu(parentMenu);
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        this.hrmMenuDao.save(entity);

        //increment others orderLevelMenu if conflict
        Long parentMenuId = parentMenu == null ? null : parentMenu.getId();
        if (hrmMenuDao.getEntityByOrderLevelMenuAndParentMenuIdAndExceptId(entity.getOrderLevelMenu(), parentMenuId, entity.getId()) != null) {
            List<HrmMenu> listGreaterMenu = hrmMenuDao.gelAllDataByOrderLevelMenuGreaterThan(entity.getOrderLevelMenu(), parentMenuId, entity.getId());
            for (HrmMenu m : listGreaterMenu) {
                m.setOrderLevelMenu(m.getOrderLevelMenu() + 1);
                m.setUpdatedBy(UserInfoUtil.getUserName());
                m.setUpatedOn(new Date());
                this.hrmMenuDao.update(m);
            }
        }
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(HrmMenu entity) throws Exception {
        long totalDuplicates = hrmMenuDao.getTotalByNameAndNotId(entity.getName(), entity.getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("hrm_menu.error_duplicate_menu_name");
        }

        HrmMenu menu = hrmMenuDao.getEntiyByPK(entity.getId());
        //update child menu level recursive
        if ((menu.getMenuLevel() != entity.getMenuLevel()) && menu.getHrmMenus().size() > 0) {
            Integer deviation = entity.getMenuLevel() - menu.getMenuLevel();
            this.updateChildMenuLevel(menu, deviation);
        }

        HrmMenu parentMenu = entity.getHrmMenu() == null ? null : hrmMenuDao.getEntiyByPK(entity.getHrmMenu().getId());
        menu.setHrmMenu(parentMenu);
        menu.setName(entity.getName());
        menu.setIconName(entity.getIconName());
        menu.setUrlName(entity.getUrlName());
        menu.setMenuLevel(entity.getMenuLevel());
        menu.setMenuStyle(entity.getMenuStyle());
        menu.setMenuStyleClass(entity.getMenuStyleClass());
        menu.setIsGroup(entity.getIsGroup());
        menu.setOrderLevelMenu(entity.getOrderLevelMenu());
        menu.setUpdatedBy(UserInfoUtil.getUserName());
        menu.setUpatedOn(new Date());
        this.hrmMenuDao.update(menu);

        //increment others orderLevelMenu if conflict
        Long parentMenuId = parentMenu == null ? null : parentMenu.getId();
        if (hrmMenuDao.getEntityByOrderLevelMenuAndParentMenuIdAndExceptId(menu.getOrderLevelMenu(), parentMenuId, menu.getId()) != null) {
            List<HrmMenu> listGreaterMenu = hrmMenuDao.gelAllDataByOrderLevelMenuGreaterThan(menu.getOrderLevelMenu(), parentMenuId, menu.getId());
            for (HrmMenu m : listGreaterMenu) {
                m.setOrderLevelMenu(m.getOrderLevelMenu() + 1);
                m.setUpdatedBy(UserInfoUtil.getUserName());
                m.setUpatedOn(new Date());
                this.hrmMenuDao.update(m);
            }
        }
    }

    private void updateChildMenuLevel(HrmMenu menu, Integer deviation) {
        Iterator<HrmMenu> listChild = menu.getHrmMenus().iterator();
        while (listChild.hasNext()) {
            HrmMenu child = listChild.next();
            Integer level = child.getMenuLevel() + deviation;
            child.setMenuLevel(level);
            child.setUpdatedBy(UserInfoUtil.getUserName());
            child.setUpatedOn(new Date());
            this.hrmMenuDao.update(child);

            this.updateChildMenuLevel(child, deviation);
        }
    }

    @Override
    public void saveOrUpdate(HrmMenu enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public HrmMenu saveData(HrmMenu entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public HrmMenu updateData(HrmMenu entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public HrmMenu saveOrUpdateData(HrmMenu entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public HrmMenu getEntityByPkIsActive(String id, Integer isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public HrmMenu getEntityByPkIsActive(String id, Byte isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public HrmMenu getEntityByPkIsActive(String id, Boolean isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public HrmMenu getEntityByPkIsActive(Integer id, Integer isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public HrmMenu getEntityByPkIsActive(Integer id, Byte isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public HrmMenu getEntityByPkIsActive(Integer id, Boolean isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public HrmMenu getEntityByPkIsActive(Long id, Integer isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public HrmMenu getEntityByPkIsActive(Long id, Byte isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public HrmMenu getEntityByPkIsActive(Long id, Boolean isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(HrmMenu entity) throws Exception {
        hrmMenuDao.delete(entity);

    }

    @Override
    public void softDelete(HrmMenu entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Long getTotalData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Long getTotalDataIsActive(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Long getTotalDataIsActive(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Long getTotalDataIsActive(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<HrmMenu> getAllData() throws Exception {
        return hrmMenuDao.getAllData();

    }

    @Override
    public List<HrmMenu> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<HrmMenu> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<HrmMenu> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<HrmMenu> getAllDataPageAble(int firstResult, int maxResults,
            Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<HrmMenu> getAllDataPageAbleIsActive(int firstResult,
            int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<HrmMenu> getAllDataPageAbleIsActive(int firstResult,
            int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<HrmMenu> getAllDataPageAbleIsActive(int firstResult,
            int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<HrmMenu> getByParam(HrmMenuSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
        return hrmMenuDao.getByParam(parameter, firstResult, maxResults, orderable);

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalByParam(HrmMenuSearchParameter parameter) throws Exception {
        return hrmMenuDao.getTotalByParam(parameter);

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<HrmMenu> getAllDataFetchChildByLevel(Integer level) throws Exception {
        return hrmMenuDao.getAllDataFetchChildByLevel(level);

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public HrmMenu getEntityByPkWithDetail(long id) throws Exception {
        return hrmMenuDao.getEntityByPkWithDetail(id);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<HrmMenu> getAllDataByLevelAndNotId(int level, Long id) throws Exception {
        return hrmMenuDao.getAllDataByLevelAndNotId(level, id);

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<HrmMenu> getAllDataByLevelAndNotId(int level, List<Long> ids) throws Exception {
        return hrmMenuDao.getAllDataByLevelAndNotId(level, ids);

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<HrmMenu> getAllDataByParamAndNotIds(HrmMenuSearchParameter parameter, List<Long> ids, int firstResult, int maxResults, Order orderable) throws Exception {
        return hrmMenuDao.getAllDataByParamAndNotIds(parameter, ids, firstResult, maxResults, orderable);

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalByParamAndNotIds(HrmMenuSearchParameter parameter, List<Long> ids) throws Exception {
        return hrmMenuDao.getTotalByParamAndNotIds(parameter, ids);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<HrmMenu> getAllDataByUserRolesAndHaveNoChild(String parameter, List<Long> exceptMenuIds, List<String> roles, int firstResult, int maxResults, Order orderable) throws Exception {
        return hrmMenuDao.getAllDataByUserRolesAndHaveNoChild(parameter, exceptMenuIds, roles, firstResult, maxResults, orderable);

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public Long getTotalByUserRolesAndHaveNoChild(String parameter, List<Long> exceptMenuIds, List<String> roles) throws Exception {
        return hrmMenuDao.getTotalByUserRolesAndHaveNoChild(parameter, exceptMenuIds, roles);

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public MenuModel getMenuHirarki() throws Exception {
        MenuModel menuModel = new DynamicMenuModel();
        List<HrmMenu> listMenuToGenerate = new ArrayList<>();
        Set<HrmMenuRole> setRoleMenus = new HashSet<>();
//        Set<HrmMenu> listChildTogenerate = new HashSet<>();
        List<String> roleUsers = UserInfoUtil.getRoles();
        roleUsers.stream().map((userRole) -> this.hrmMenuRoleDao.getByLevelOneAndRoleName(userRole)).filter((listRoleMenu) -> (listRoleMenu != null)).forEach((listRoleMenu) -> {
            setRoleMenus.addAll(listRoleMenu);
        });
        for (HrmMenuRole menuRole : setRoleMenus) {
            if (!listMenuToGenerate.contains(menuRole.getHrmMenu())) {
               listMenuToGenerate.add(menuRole.getHrmMenu());
            } 
        }

        listMenuToGenerate = Lambda.sort(listMenuToGenerate, Lambda.on(HrmMenu.class).getOrderLevelMenu());
        for (HrmMenu hrmMenu : listMenuToGenerate) {
            DefaultSubMenu masterSubMenu = null;
            if (hrmMenu.getUrlName() != null) {
                DefaultMenuItem defaultMenuItem;
                if (hrmMenu.getName() != null) {
                    String name = ResourceBundleUtil.getAsString(hrmMenu.getName());
                    defaultMenuItem = new DefaultMenuItem(name);
                    defaultMenuItem.setTitle(name);
                } else {
                    defaultMenuItem = new DefaultMenuItem();

                }
                if (hrmMenu.getMenuStyle() != null) {
                    defaultMenuItem.setStyle(hrmMenu.getMenuStyle());
                }
                defaultMenuItem.setIcon(hrmMenu.getIconName());
                defaultMenuItem.setUrl(hrmMenu.getUrlName());
                menuModel.addElement(defaultMenuItem);
            } else {
                masterSubMenu = new DefaultSubMenu();
                masterSubMenu.setIcon(hrmMenu.getIconName());
                masterSubMenu.setLabel(ResourceBundleUtil.getAsString(hrmMenu.getName()));
                masterSubMenu.setStyleClass(hrmMenu.getMenuStyleClass());
                menuModel.addElement(masterSubMenu);
            }
            doCreateChild(hrmMenu, masterSubMenu, roleUsers);
        }
        return menuModel;
    }

    private void doCreateChild(HrmMenu hrmMenu, DefaultSubMenu masterSubMenu, List<String> roleUsers) {
        Set<HrmMenu> listChildTogenerate = new HashSet<>();
        if (!hrmMenu.getHrmMenus().isEmpty()) {
            roleUsers.stream().map((userRole) -> this.hrmMenuDao.getlistChildByParentMenu(hrmMenu.getId(), userRole)).filter((listChild) -> (listChild.size() > 0)).forEach((listChild) -> {
                listChildTogenerate.addAll(listChild);
            });
    
            List<HrmMenu> onlyOneMenus = new ArrayList<>(listChildTogenerate);
            onlyOneMenus = Lambda.sort(onlyOneMenus, Lambda.on(HrmMenu.class).getOrderLevelMenu());
            for (HrmMenu turunan : onlyOneMenus) {
                DefaultSubMenu masterSubSubMenu = null;
                if (turunan.getIsGroup()) {
                    masterSubSubMenu = new DefaultSubMenu();
                    masterSubSubMenu.setIcon(turunan.getIconName());
                    masterSubSubMenu.setLabel(ResourceBundleUtil.getAsString(turunan.getName()));
                    masterSubSubMenu.setStyleClass(turunan.getMenuStyleClass());
                    masterSubMenu.addElement(masterSubSubMenu);
                } else {
                    DefaultMenuItem defaultMenuItem;
                    if (turunan.getName() != null) {
                        String name = ResourceBundleUtil.getAsString(turunan.getName());
                        defaultMenuItem = new DefaultMenuItem(name);
                        defaultMenuItem.setTitle(name);
                    } else {
                        defaultMenuItem = new DefaultMenuItem();
                    }
                    if (turunan.getMenuStyle() != null) {
                        defaultMenuItem.setStyle(turunan.getMenuStyle());
                    }
                    defaultMenuItem.setIcon(turunan.getIconName());
                    defaultMenuItem.setUrl(turunan.getUrlName());
                    masterSubMenu.addElement(defaultMenuItem);
                }

                if (!turunan.getHrmMenus().isEmpty()) {
                    doCreateChild(turunan, masterSubSubMenu, roleUsers);
                }

            }
        }
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<HrmMenu> getAllDataFetchChildByParentId(Long parentId, List<Long> exceptId) throws Exception {
        return hrmMenuDao.getAllDataFetchChildByParentId(parentId, exceptId);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<HrmMenu> getAllDataFetchChildByParentId(Long parentId) throws Exception {
        return hrmMenuDao.getAllDataFetchChildByParentId(parentId, ListUtils.EMPTY_LIST);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public HrmMenu getEntityFetchChildById(Long id) throws Exception {
        return hrmMenuDao.getEntityFetchChildById(id);
    }

}
