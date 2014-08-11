/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function handleLogin(loginHistoryPushMessageModel) {
    //facesmessage.severity = 'info';
    //                    doCall();
    //PF('loginNotification').show([facesmessage]);
	
	//update growl message
	PF('loginNotification').renderMessage({summary:loginHistoryPushMessageModel.growlTitle, detail: loginHistoryPushMessageModel.growlMessage, severity: 'info'});
	
	//update table login history
	if(loginHistoryPushMessageModel.isLogin){
		var loginName = loginHistoryPushMessageModel.loginName;
		var loginDate = loginHistoryPushMessageModel.loginDate;
		var loginTime = loginHistoryPushMessageModel.loginTime;
		for (var i = 0, end = 5; i < end; i++ ) {
			if($("#loginHistoryName_" + i).length){
				var loginNameTemp = $("#loginHistoryName_" + i).html();			
				var loginDateTemp = $("#loginHistoryDate_" + i).html();
				var loginTimeTemp = $("#loginHistoryTime_" + i).html();
				
				$("#loginHistoryName_" + i).html(loginName);
				$("#loginHistoryDate_" + i).html(loginDate);
				$("#loginHistoryTime_" + i).html(loginTime);
				
				loginName = loginNameTemp;
				loginDate = loginDateTemp;
				loginTime = loginTimeTemp;
			}
		}
	}
}
