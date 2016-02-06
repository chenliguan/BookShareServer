Ext.define('Sys.controller.SystemController', {
    extend: 'Ext.app.Controller',
	

    models: [
         'SelectPanelModel'
    ],
    stores: [
     		'system.SystemSelectPanelStore'
    ],
    views: [
    	'system.SystemSelectPanel'
    ],
    
    refs: [
    	 {ref: 'systemDataView', selector: 'system_select_panel dataview' }//派生选择器 提供ref的get/set方法
    	,{autoCreate: true,ref: 'systemSelectPanelStore', selector: 'system_select_panel_store',xtype: 'system_select_panel_store'}
	
    ],
    onLaunch: function() {
    	//ref get
		this.getSystemDataView().bindStore(this.getSystemSelectPanelStore());
	},
	//Controller  init进行View的事件绑定
    init: function() {
    	//alert("SystemController init!");
        this.control({
           'viewport > panel > panel dataview[cls=feed-list]': {
                selectionchange: this.dynamicLoadingController
            }

        });
    },
	dynamicLoadingController: function(selModel, selected) {
			var self = this;
			var record = selected[0];
			if(record){
				var id = record.get('id');
				var controllerName = id.replace(/(\w)/,function(v){return v.toUpperCase()})+'Controller';//User+Controller   Log+Controller
				var controllerPath = 'Sys.controller.'+controllerName;
				if(!Ext.ClassManager.isCreated(controllerPath)){
					//还没加载,现在加载控制器
					Ext.require(controllerPath, function () {//加载完成后执行回调函数
             			this.getController(controllerPath);//ExtJS 4.2后自动初始化
						this.activeTab(id,selected);
					}, self);
				}else{
					//已经加载过控制器
					this.activeTab(id,selected);
				}
	   	   }
	 },
	//自定义被绑定事件/函数
	activeTab: function(id, selected) {
   	   	var modelId = selected[0].get('id')+"_model_panel";
   	   	var tabs = Ext.getCmp('centerTabPanel'); //Viewport-> id:'centerTabPanel'
   	   	var tab = tabs.items.getByKey(modelId); 
		if (!tab) {
           	 tab = tabs.add({
            	xtype: modelId,
				title:selected[0].get('name')+"模块",
            	itemId: modelId,//唯一
            	closable:true
            	,iconCls:selected[0].data.iconCls
             });
	      }
	      tabs.setActiveTab(tab);

    }

});
