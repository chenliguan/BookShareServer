/*
*代码重用：
	Enum Data Chuang的方法的重用
*/
Ext.define('Sys.util.EnumDataChuang', {
    singleton: true,//单例
  
    changeProgressStage:function (val){
		switch(val){
			case 'DISCOVERY_OPPORTUNITY':
				return '<span style="color:gray;">发现机会</span>';
			case 'PRELIMINARY_APPROACH':
				return '<span style="color:teal;">初步接洽</span>';
			case 'CONFIRMATION_OPPORTUNITY':
				return '<span style="color:black;">确认机会</span>';
			case 'CONTINUED_FOLLOW_UP':
				return '<span style="color:salmon;">持续跟进</span>';
			case 'SUBMISSION_SCHEME':
				return '<span style="color:silver;">提交方案</span>';
			case 'NEGOTIATION_AUDIT':
				return '<span style="color:coral;">谈判审核</span>';
			case 'ACHIEVE_SALES':
				return '<span style="color:blue;">实现销售</span>';
			case 'CUSTOMER_CHURN':
				return '<span style="color:red;">客户流失</span>';
		}
    }
});
