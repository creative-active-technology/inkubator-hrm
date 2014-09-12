
function handleApproval(approvalPushMessageModel) {
	approvalRC([{name:'approverUserId',value:approvalPushMessageModel.approverUserId}, 
	            {name:'requestUserId',value:approvalPushMessageModel.requestUserId},
	            {name:'approverFullName',value:approvalPushMessageModel.approverFullName}, 
	            {name:'requestFullName',value:approvalPushMessageModel.requestFullName},
	            {name:'approvalName',value:approvalPushMessageModel.approvalName}]);
}