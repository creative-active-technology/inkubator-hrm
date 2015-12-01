// FNC for detecting for click outside of any elements ============== 
$.fn.clickOff = function(callback, selfDestroy) {
		var clicked = false;
		var parent = this;
		var destroy = selfDestroy || true;
		
		parent.click(function() {
			clicked = true;
		});
		
		$(document).click(function(event) { 
			if (!clicked) {
				callback(parent, event);
			}
			if (destroy) {
			};
			clicked = false;
		});
	};
	
/** 
 * PrimeFaces Spark Layout
 */
var Spark = {
    
    init: function() {
        this.menubar = $('#layout-menu');
        this.topmenu = $('#layout-topbar-menu');
        this.menumask = $('#menu-mask');
        this.focusedLink = null;
        this.focusedTopLink = null;
        
        // for keyboard navigation
        this.topmenu.attr('tabindex', '0');
        var topmenuItems = this.topmenu.find('a');
        for(var i=0; i<topmenuItems.size(); i++) {
            topmenuItems.eq(i).attr('tabindex', '-1');
        } 
        
        this.bindEvents();
        this.bindKeyEvents();
    },
    
    bindEvents: function() {
        var $this = this;
        
        //main menu
        this.menubar.find('a').click(function() {
            var link = $(this),
            submenu = link.next('ul');

            if(submenu.length) {
                if(link.next().hasClass('openSubMenu')) {
                    link.next().removeClass('openSubMenu');
                    $this.menubarActive = false;
                }
                else {
                    link.parent().parent().find('ul.openSubMenu').removeClass('openSubMenu');
                    link.next('.submenu').addClass('openSubMenu');
                    $this.menubarActive = true;
                }
            }
            else {
                $this.menubar.find('> li > ul').removeClass('openSubMenu');
                $this.menubarActive = false;
            }
            
            if($this.menubarActive) {
                $this.focusedLink = link;
                $this.menubar.trigger('focus');
            }
        })
        .on('mouseenter', function() {
            var link = $(this),
                menuItem = link.parent();
                
            if($this.menubarActive && document.documentElement.clientWidth > 960 && menuItem.closest('ul').attr('id') === "layout-menu") {
                menuItem.parent().find('ul.openSubMenu').removeClass('openSubMenu');
                link.next('.submenu').addClass('openSubMenu');
            }
            
            if($this.menubarActive) {
                if($this.focusedLink) {
                    $this.focusedLink.removeClass('ui-spark-focus');
                }
                $this.focusedLink = link;
                $this.menubar.trigger('focus');
            }
        });
        
        this.menubar.find('li').clickOff(function() {
            $this.menubar.find('> li > ul').removeClass('openSubMenu');
            $this.menubarActive = false;
        });
        
        //topbar
        this.topmenu.find('a').click(function() {
            var link = $(this),
            submenu = link.next('ul');
            
            if(submenu.length) {
                if(link.next().hasClass('openSubMenu')) {
                    link.next().removeClass('openSubMenu');
                    $this.topmenuActive = false;
                }
                else {
                    $this.topmenu.find('> li > ul.openSubMenu').removeClass('openSubMenu');
                    link.next('.submenu').addClass('openSubMenu');
                    $this.topmenuActive = true;
                }
            }
            else {
                $this.topmenu.find('> li > ul').removeClass('openSubMenu');
                $this.topmenuActive = false;
            }
            
            if($this.topmenuActive) {
                $this.focusedTopLink = link;
                $this.topmenu.trigger('focus');
            }
        })
        .on('mouseenter', function() {
            var link = $(this),
                topMenuItem = link.parent();
                
            if($this.topmenuActive && document.documentElement.clientWidth > 960 && topMenuItem.closest('ul').attr('id') === "layout-topbar-menu") {
                $this.topmenu.find('> li > ul.openSubMenu').removeClass('openSubMenu');
                link.next('.submenu').addClass('openSubMenu');
            }
            
            if($this.topmenuActive) {
                if($this.focusedTopLink) {
                    $this.focusedTopLink.removeClass('ui-spark-focus');
                }
                $this.focusedTopLink = link;
                $this.topmenu.trigger('focus');
            }
        });

        this.topmenu.find('li').clickOff(function() {
            $this.topmenu.find('> li > ul').removeClass('openSubMenu');
            $this.topmenuActive = false;
        });
        
        $('#mobile-menu-button-gray').click(function(e){
            if($this.topmenu.is(':hidden')) {
                $this.topmenu.find('ul').removeClass('openSubMenu');
                $this.menubar.hide();
                $this.topmenu.show();
                $this.menumask.addClass('menu-mask-open');
            }
            else {
                $this.topmenu.hide();
                $this.menumask.removeClass('menu-mask-open');
            }
            
            e.preventDefault();
        });

        // mobile mode main menu open
        $('#mobile-menu-button').click(function(e) {
            if($this.menubar.is(':hidden')) {
                $this.menubar.find('ul').removeClass('openSubMenu');
                $this.menubar.show();
                $this.topmenu.hide();
                $this.menumask.addClass('menu-mask-open');
            }
            else {
                $this.menubar.hide();
                $this.menumask.removeClass('menu-mask-open');
            }
            
            e.preventDefault();
        });

        // closing all menus
        $('#menu-mask').click(function(){
            $this.menubar.hide();
            $this.topmenu.hide();
            $this.menumask.removeClass('menu-mask-open');
        });
    },
    
    bindKeyEvents: function() {
        var $this = this;

        // keyboard navigation for Layout Menu
        this.menubar.on('focus', function() {
            if(!$this.focusedLink) {
                $this.focusedLink = $this.menubar.find('a:first');
            }
            $this.focusedLink.addClass('ui-spark-focus');
        })
        .on('blur', function(){
            if($this.focusedLink) {
                $this.focusedLink.removeClass('ui-spark-focus');
                $this.focusedLink = null;
            }
        })
        .on('keydown', function(e) {
            if(!$this.focusedLink) {
                return;
            }
    
            var keyCode = $.ui.keyCode;
            
            switch(e.which) {
                case keyCode.LEFT:
                    if($('#mobile-menu-button').is(':hidden')) {
                        var prevMenuItem = $this.focusedLink.parent().prevAll("li[role='menuitem']:first");
                        if(prevMenuItem.length) {
                            var submenu = $this.focusedLink.next();
                            if(submenu.length && submenu.hasClass('openSubMenu')) {
                                submenu.removeClass('openSubMenu');
                            }
                            $this.focusItem(prevMenuItem);
                        }
                        e.preventDefault();
                    }
                break;

                case keyCode.RIGHT:
                    if($('#mobile-menu-button').is(':hidden')) {
                        var nextMenuItem = $this.focusedLink.parent().nextAll("li[role='menuitem']:first");
                        if(nextMenuItem.length) {
                            var submenu = $this.focusedLink.next();
                            if(submenu.length && submenu.hasClass('openSubMenu')) {
                                submenu.removeClass('openSubMenu');
                            }
                            $this.focusItem(nextMenuItem);
                        }
                        e.preventDefault();
                    }
                break;

                case keyCode.UP:
                    if((!$this.isMenuRoot($this.focusedLink.parent())) || ($this.isMenuRoot($this.focusedLink.parent()) && $('#mobile-menu-button').is(':visible'))) {
                        var prevItem = $this.focusedLink.parent().prevAll("li[role='menuitem']:first"),
                            itemToFocus = null;
                        if(!prevItem.length && $('#mobile-menu-button').is(':visible') && !$this.focusedLink.parent().parent().hasClass('submenu')) {
                            $('#mobile-menu-button').focus();
                        }
                        else {
                            if(prevItem.length) {
                                if($this.isMenuRoot(prevItem) && $('#mobile-menu-button').is(':visible')) {
                                    var temp = prevItem.find('ul.openSubMenu:first > li:last');
                                    if(temp.length) {
                                        prevItem = temp;
                                    }
                                }
                                
                                itemToFocus = prevItem.find('ul.openSubMenu:last').children('li:last');
                                
                                if(!itemToFocus.length) {
                                    itemToFocus = prevItem;
                                }
                            }
                            else {
                                itemToFocus = $this.focusedLink.parent().closest('ul.submenu').parent('li');
                            }

                            if(itemToFocus.length) {
                                $this.focusItem(itemToFocus);
                            }
                        }
                    }
                    e.preventDefault();
                break;

                case keyCode.DOWN:
                    var subMenu = $this.focusedLink.next('ul'),
                        itemToFocus = null;
                    if(subMenu.length && subMenu.hasClass('openSubMenu')) {
                        itemToFocus = subMenu.children('li:first');
                    }
                    else {
                        var nextItem = $this.searchDownInMenu($this.focusedLink.parent());
                        if(nextItem) {
                            itemToFocus = nextItem;
                        }
                    }

                    if(itemToFocus && itemToFocus.length) {
                        $this.focusItem(itemToFocus);
                    }
                    e.preventDefault();
                break;
                
                case keyCode.ENTER:
                case keyCode.NUMPAD_ENTER:
                case keyCode.SPACE:
                    $this.focusedLink.trigger('click');
                    var href = $this.focusedLink.attr('href');
                    if(href && href !== '#') {
                        window.location.href = href;
                    }
                break;
            }       
        });
        
        // keyboard navigation for Top bar
        this.topmenu.on('focus', function(){
            if(!$this.focusedTopLink) {
                if($('#mobile-menu-button-gray').is(':hidden')) {
                    $this.focusedTopLink = $this.topmenu.children("li:not('.menu-separator'):last").children('a');
                }
                else {
                    $this.focusedTopLink = $this.topmenu.children("li:not('.menu-separator'):first").children('a');
                }
            }
            $this.focusedTopLink.addClass('ui-spark-focus');
        })
        .on('blur', function() {
            if($this.focusedTopLink) {
                $this.focusedTopLink.removeClass('ui-spark-focus');
                $this.focusedTopLink = null;
            }
        })
        .on('keydown', function(e) {
            if(!$this.focusedTopLink) {
                return;
            }
            
            var keyCode = $.ui.keyCode;
            
            switch(e.which) {
                case keyCode.LEFT:
                    if($('#mobile-menu-button-gray').is(':hidden')) {
                        var prevMenuItem = $this.focusedTopLink.parent().nextAll("li:not('.menu-separator'):first");
                        if(prevMenuItem.length) {
                            var submenu = $this.focusedTopLink.next();
                            if(submenu.length && submenu.hasClass('openSubMenu')) {
                                submenu.removeClass('openSubMenu');
                            }
                            $this.focusItem(prevMenuItem, true);
                        }
                        e.preventDefault();
                    }
                break;

                case keyCode.RIGHT:
                    if($('#mobile-menu-button-gray').is(':hidden')) {
                        var nextMenuItem = $this.focusedTopLink.parent().prevAll("li:not('.menu-separator'):first");
                        if(nextMenuItem.length) {
                            var submenu = $this.focusedTopLink.next();
                            if(submenu.length && submenu.hasClass('openSubMenu')) {
                                submenu.removeClass('openSubMenu');
                            }
                            $this.focusItem(nextMenuItem, true);
                        }
                        e.preventDefault();
                    }
                break;

                case keyCode.UP:
                    if((!$this.isTopMenuRoot($this.focusedTopLink.parent())) || ($this.isTopMenuRoot($this.focusedTopLink.parent()) && $('#mobile-menu-button-gray').is(':visible'))) {
                        var prevItem = $this.focusedTopLink.parent().prevAll("li:not('.menu-separator'):first"),
                            itemToFocus = null;
                        
                        if(!prevItem.length && $('#mobile-menu-button-gray').is(':visible') && !$this.focusedTopLink.parent().parent().hasClass('submenu')) {
                            $('#mobile-menu-button-gray').focus();
                        }
                        else {
                            if(prevItem.length) {
                                if($this.isTopMenuRoot(prevItem) && $('#mobile-menu-button-gray').is(':visible')) {
                                    var temp = prevItem.find('ul.openSubMenu:first > li:last');
                                    if(temp.length) {
                                        prevItem = temp;
                                    }
                                }
                                
                                itemToFocus = prevItem.find('ul.openSubMenu:last').children('li:last');
                                if(!itemToFocus.length) {
                                    itemToFocus = prevItem;
                                }
                            }
                            else {
                                itemToFocus = $this.focusedTopLink.parent().closest('ul.submenu').parent('li');
                            }

                            if(itemToFocus.length) {
                                $this.focusItem(itemToFocus, true);
                            }
                        }
                    }
                    e.preventDefault();
                break;

                case keyCode.DOWN:
                    var subMenu = $this.focusedTopLink.next('ul'),
                        itemToFocus = null;
                    if(subMenu.length && subMenu.hasClass('openSubMenu')) {
                        itemToFocus = subMenu.children('li:first');
                    }
                    else {
                        var nextItem = $this.searchDownInTopbar($this.focusedTopLink.parent());
                        if(nextItem) {
                            itemToFocus = nextItem;
                        }
                    }

                    if(itemToFocus && itemToFocus.length) {
                        $this.focusItem(itemToFocus, true);
                    }
                    e.preventDefault();
                break;
                
                case keyCode.ENTER:
                case keyCode.NUMPAD_ENTER:
                case keyCode.SPACE:
                    $this.focusedTopLink.trigger('click');
                    var href = $this.focusedTopLink.attr('href');
                    if(href && href !== '#') {
                        window.location.href = href;
                    }
                break;
            }       
        });
        
        $('#mobile-menu-button-gray').keydown(function(e){
            var keyCode = $.ui.keyCode;
            
            if(e.which === keyCode.DOWN && $this.topmenu.is(':visible')) {
                $this.topmenu.focus();
                e.preventDefault();
            }
            
        });

        $('#mobile-menu-button').keydown(function(e) {
            var keyCode = $.ui.keyCode;
            
            if(e.which === keyCode.DOWN && $this.menubar.is(':visible')) {
                $this.menubar.focus();
                e.preventDefault();
            } 
        });
        
    },
    
    isMenuRoot: function(item) {
        return item.parent().attr('id') === "layout-menu";
    },
    
    isTopMenuRoot: function(item) {
        return item.parent().attr('id') === "layout-topbar-menu";
    },        
            
    focusItem: function(item, isTopmenu) {
        if(isTopmenu) {
            this.focusedTopLink.removeClass('ui-spark-focus');
            this.focusedTopLink = item.children('a');
            this.focusedTopLink.addClass('ui-spark-focus');
        }
        else {
            this.focusedLink.removeClass('ui-spark-focus');
            this.focusedLink = item.children('a');
            this.focusedLink.addClass('ui-spark-focus');
        }
    },
            
    searchDownInMenu: function(item) {
        var nextMenuItem = item.nextAll("li[role='menuitem']:first"),
            foundMenuItem = null;

        if(this.isMenuRoot(item) && $('#mobile-menu-button').is(':hidden')) { //for PC and mobile mode
            foundMenuItem = null;   
        }
        else if(this.isMenuRoot(item) && $('#mobile-menu-button').is(':visible') && !nextMenuItem.length) { // for mobile mode
            foundMenuItem = null;
        }
        else if(nextMenuItem.length) {
            foundMenuItem = nextMenuItem;
        }
        else {
            foundMenuItem = this.searchDownInMenu(item.parent().closest('li'));
        }
        
        return foundMenuItem;
    },
    
    searchDownInTopbar: function(item) {
        var nextMenuItem = item.nextAll("li:not('.menu-separator'):first"),
            foundTopMenuItem = null;
        
        if(this.isTopMenuRoot(item) && $('#mobile-menu-button-gray').is(':hidden')) { //for PC and mobile mode
            foundTopMenuItem = null;   
        }
        else if(this.isTopMenuRoot(item) && $('#mobile-menu-button-gray').is(':visible') && !nextMenuItem.length) { // for mobile mode
            foundTopMenuItem = null;
        }
        else if(nextMenuItem.length) {
            foundTopMenuItem = nextMenuItem;
        }
        else {
            foundTopMenuItem = this.searchDownInTopbar(item.parent().closest('li'));
        }
        return foundTopMenuItem;
    }

};

$(function() {
    Spark.init();
});
