Ext.define('Sys.store.system.SystemSelectPanelStore', {
    extend: 'Ext.data.Store',
	alias: 'widget.system_select_panel_store',//重要
    model: 'Sys.model.SelectPanelModel',

    data: [
        {id:'user' , name: '用户管理',iconCls:'application_view_list'},
		{id:'log' , name: '系统日志',iconCls:'application_view_list'}
    ]
});
