Ext.define('Sys.view.system.SystemSelectPanel', {//1.修改路径及类名
	extend: 'Ext.panel.Panel',

	alias: 'widget.system_select_panel',//2.组件选择器名xtype
		
	title: '系统管理模块',
	margins: '5 0 5 5',
	layout: 'fit',
	initComponent: function() {
		
		Ext.apply(this, {
		
			items: [{
				xtype: 'dataview',
				trackOver: true,
				store: this.store,//3.禁用store
				cls: 'feed-list',
				itemSelector: '.feed-list-item',
				overItemCls: 'feed-list-item-hover',
				tpl: '<tpl for="."><div class="feed-list-item">{name}</div></tpl>'
				//4.删除监听事件
			}]
		
			//5.删除其他代码
		});
		this.callParent(arguments);
	}
    //6.删除自定义函数
});

