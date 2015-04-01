function handleApproval(a) {
	approvalRC([ {
		name : "approverUserId",
		value : a.approverUserId
	}, {
		name : "requestUserId",
		value : a.requestUserId
	}, {
		name : "approverFullName",
		value : a.approverFullName
	}, {
		name : "requestFullName",
		value : a.requestFullName
	}, {
		name : "approvalName",
		value : a.approvalName
	}, {
		name : "approvalStatus",
		value : a.approvalStatus
	} ])
};