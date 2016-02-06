Ext.define('Sys.view.Viewport', {
    extend: 'Ext.container.Viewport',
    layout: 'border',
    items: [{
    	region: 'north',
    	baseCls: 'app-header',
        html: 'ECRM企业客户关系管理系统'
    },{
        region: 'center',
        //title:'Center',
        id:'centerTabPanel',
        xtype:'tabpanel'
    }, {
        region: 'west',
        split:true,
        width:'15%',
        collapsible: true,
        title:'Navigation',
        //html:'west'
        layout:'accordion',
        items:[{
        	xtype:'system_select_panel'
        }]
        
    }]
});