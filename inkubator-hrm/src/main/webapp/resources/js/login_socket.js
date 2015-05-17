//function handleLogin(a){PF("socketNotificationGrowl").renderMessage({summary:a.growlTitle,detail:a.growlMessage,severity:"info"});if(a.isLogin){var c=a.loginName,d=a.loginDate;a=a.loginTime;for(var b=0;5>b;b++)if($("#loginHistoryName_"+b).length){var e=$("#loginHistoryName_"+b).html(),f=$("#loginHistoryDate_"+b).html(),g=$("#loginHistoryTime_"+b).html();$("#loginHistoryName_"+b).html(c);$("#loginHistoryDate_"+b).html(d);$("#loginHistoryTime_"+b).html(a);c=e;d=f;a=g}}};
function handleLogin(facesmessage) {
    facesmessage.severity = 'info';
    PF('socketNotificationGrowl').show([facesmessage]);
}