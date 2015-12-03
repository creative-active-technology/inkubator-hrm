/*
 * Copyright 2009-2015 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.primefaces.spark.component.menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import org.primefaces.component.api.AjaxSource;
import org.primefaces.component.api.UIOutcomeTarget;
import org.primefaces.component.menu.AbstractMenu;
import org.primefaces.component.menu.BaseMenuRenderer;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.Separator;
import org.primefaces.model.menu.Submenu;
import org.primefaces.util.ComponentUtils;

public class SparkMenuRenderer extends BaseMenuRenderer {

    @Override
    protected void encodeMarkup(FacesContext context, AbstractMenu abstractMenu) throws IOException {
        SparkMenu menu = (SparkMenu) abstractMenu;
        ResponseWriter writer = context.getResponseWriter();
        UIComponent options = menu.getFacet("options");
        String style = menu.getStyle();
        String styleClass = menu.getStyleClass();
        String defaultStyleClass = "BordRad3 Unselectable";
        styleClass = (styleClass == null) ? defaultStyleClass : defaultStyleClass + " " + styleClass;
        
        writer.startElement("ul", menu);
        writer.writeAttribute("id", "layout-menu", "id");
        writer.writeAttribute("class", styleClass, "styleClass");
        writer.writeAttribute("tabindex", "0", null);
        
        if(style != null) {
            writer.writeAttribute("style", style, "style");
        }
        
        if(menu.getElementsCount() > 0) {
            encodeElements(context, menu, menu.getElements(), -1);
        }
        
        if(options != null) {
            writer.startElement("li", null);
            writer.writeAttribute("style", "float:right", null);
            options.encodeAll(context);
            writer.endElement("li");
        }
        
        writer.endElement("ul");
    }
    
    protected void encodeElements(FacesContext context, AbstractMenu menu, List<MenuElement> elements, int marginLevel) throws IOException {
        String menuClientId = menu.getClientId(context);
        int size = elements.size();
        
        for (int i = 0; i < size; i++) {
            encodeElement(context, menu, elements.get(i), menuClientId, marginLevel);
        }
    }
    
    protected void encodeElement(FacesContext context, AbstractMenu menu, MenuElement element, String menuClientId, int marginLevel) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
         
        if(element.isRendered()) {
            if(element instanceof MenuItem) {
                MenuItem menuItem = (MenuItem) element;
                String menuItemClientId = (menuItem instanceof UIComponent) ? menuItem.getClientId() : menuClientId + "_" + menuItem.getClientId();
                String containerStyle = menuItem.getContainerStyle();
                String containerStyleClass = menuItem.getContainerStyleClass();

                writer.startElement("li", null);
                writer.writeAttribute("id", menuItemClientId, null);
                writer.writeAttribute("role", "menuitem", null);

                if(containerStyle != null) writer.writeAttribute("style", containerStyle, null);
                if(containerStyleClass != null) writer.writeAttribute("class", containerStyleClass, null);

                encodeMenuItem(context, menu, menuItem, marginLevel);

                writer.endElement("li");
            }
            else if(element instanceof Submenu) {
                Submenu submenu = (Submenu) element;
                String submenuClientId = (submenu instanceof UIComponent) ? ((UIComponent) submenu).getClientId() : menuClientId + "_" + submenu.getId();
                String style = submenu.getStyle();
                String styleClass = submenu.getStyleClass();

                writer.startElement("li", null);
                writer.writeAttribute("id", submenuClientId, null);
                writer.writeAttribute("role", "menuitem", null);

                if(style != null) writer.writeAttribute("style", style, null);
                if(styleClass != null) writer.writeAttribute("class", styleClass, null);

                encodeSubmenu(context, menu, submenu, marginLevel);

                writer.endElement("li");
            }
            else if(element instanceof Separator) {
                encodeSeparator(context, (Separator) element);
            }
        }
    }
    
    protected void encodeMenuItem(FacesContext context, AbstractMenu menu, MenuItem menuitem, int marginLevel) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String title = menuitem.getTitle();
        boolean disabled = menuitem.isDisabled();
        String style = menuitem.getStyle();
        String styleClass = "Animated05";
        if(marginLevel >= 1) {
            styleClass = styleClass + " menuLevel" + marginLevel;
        }

        writer.startElement("a", null);
        if(title != null) writer.writeAttribute("title", title, null);
        if(style != null) writer.writeAttribute("style", style, null);
        writer.writeAttribute("tabindex", "-1", null);
            
        writer.writeAttribute("class", styleClass, null);

        if(disabled) {
            writer.writeAttribute("href", "#", null);
            writer.writeAttribute("onclick", "return false;", null);
        }
        else {
            String onclick = menuitem.getOnclick();

            //GET
            if(menuitem.getUrl() != null || menuitem.getOutcome() != null) {                
                String targetURL = getTargetURL(context, (UIOutcomeTarget) menuitem);
                writer.writeAttribute("href", targetURL, null);

                if(menuitem.getTarget() != null) {
                    writer.writeAttribute("target", menuitem.getTarget(), null);
                }
            }
            //POST
            else {
                writer.writeAttribute("href", "#", null);

                UIComponent form = ComponentUtils.findParentForm(context, menu);
                if(form == null) {
                    throw new FacesException("MenuItem must be inside a form element");
                }

                String command;
                if(menuitem.isDynamic()) {
                    String menuClientId = menu.getClientId(context);
                    Map<String,List<String>> params = menuitem.getParams();
                    if(params == null) {
                        params = new LinkedHashMap<String, List<String>>();
                    }
                    List<String> idParams = new ArrayList<String>();
                    idParams.add(menuitem.getId());
                    params.put(menuClientId + "_menuid", idParams);

                    command = menuitem.isAjax() ? buildAjaxRequest(context, menu, (AjaxSource) menuitem, form, params) : buildNonAjaxRequest(context, menu, form, menuClientId, params, true);
                } 
                else {
                    command = menuitem.isAjax() ? buildAjaxRequest(context, (AjaxSource) menuitem, form) : buildNonAjaxRequest(context, ((UIComponent) menuitem), form, ((UIComponent) menuitem).getClientId(context), true);
                }

                onclick = (onclick == null) ? command : onclick + ";" + command;
            }

            if(onclick != null) {
                writer.writeAttribute("onclick", onclick, null);
            }
        }

        encodeMenuItemContent(context, menu, menuitem);

        writer.endElement("a");
	}
    
    @Override
    protected void encodeMenuItemContent(FacesContext context, AbstractMenu menu, MenuItem menuitem) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String icon = menuitem.getIcon();
        Object value = menuitem.getValue();
        
        if(icon != null) {
            writer.startElement("i", null);
            writer.writeAttribute("class", icon, null);
            writer.endElement("i");
        }

        if(value != null) {
            writer.writeText(" " + value, "value");
        }
    }
    
    @Override
    protected void encodeSeparator(FacesContext context, Separator separator) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        writer.startElement("li", null);
        writer.writeAttribute("class", "fa fa-ellipsis-v menu-separator", null);
        writer.endElement("li");
    } 
    
    protected void encodeSubmenu(FacesContext context, AbstractMenu menu, Submenu submenu, int marginLevel) throws IOException{
		ResponseWriter writer = context.getResponseWriter();
        String icon = submenu.getIcon();
        String label = submenu.getLabel();
        boolean rootSubmenu = (submenu.getParent() instanceof SparkMenu);
        String menuIconClass = rootSubmenu ? "fa fa-chevron-down Fs14 Fright ShowOnMobile" : "fa fa-chevron-down Fs12 Fright";

        //title
        String anchorClass = "Animated05 CursPointer";
        if(marginLevel >= 1) {
            anchorClass = anchorClass + " menuLevel" + marginLevel; 
        }
        
        writer.startElement("a", null);
        writer.writeAttribute("href", "#", null);
        writer.writeAttribute("onclick", "return false;", null);
        writer.writeAttribute("class", anchorClass, null);
        writer.writeAttribute("tabindex", "-1", null);
        
        if(icon != null) {
            writer.startElement("i", null);
            writer.writeAttribute("class", icon, null);
            writer.endElement("i");
        }

        if(label != null) {
            writer.writeText(" " + label, null);
        }
        
        writer.startElement("i", null);
        writer.writeAttribute("class", menuIconClass, null);
        writer.endElement("i");
       
        writer.endElement("a");

        //submenus and menuitems
        if(submenu.getElementsCount() > 0) {
            String styleClass = (rootSubmenu) ? "Animated03 submenu" : "submenu";
            
            writer.startElement("ul", null);          
            writer.writeAttribute("class", styleClass, null);
            writer.writeAttribute("role", "menu", null);
			encodeElements(context, menu, submenu.getElements(), ++marginLevel);
			writer.endElement("ul");
        }
	}

    @Override
    protected void encodeScript(FacesContext context, AbstractMenu abstractMenu) throws IOException {
        
    }
}
