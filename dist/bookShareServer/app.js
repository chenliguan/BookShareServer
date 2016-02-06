Ext.application({
	//1.Application命名前缀
    name: 'Sys',
    //2.设置Application自定义classes的目录路径
    paths: 'app', //MVC  lib/util/data
	//3.加载controller文件夹下的 controller类(默认所有均初始化)
    controllers: [
    	'SystemController'
    //	,'UserController'  
    //	,'LogController'
    ],
    /*
	launch: function() {
		alert("init之后 确保App初始化完成后执行,等价于onReady");
	},
	init: function() {
		alert("init");
	},*/
	
    //4.初始化主视图(默认为paths/view/Viewport.js)
    autoCreateViewport: true
});
