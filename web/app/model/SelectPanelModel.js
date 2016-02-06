Ext.define('Sys.model.SelectPanelModel', {
    extend: 'Ext.data.Model',
    proxy: {
        type: 'memory'
    },
    fields: [
        {name: 'id',  type: 'string'},
        {name: 'name', type: 'string'},
    	{name: 'iconCls', type: 'string'}
    ]
});