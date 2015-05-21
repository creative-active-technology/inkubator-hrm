function handleCheckInOut(facesmessage) {
	facesmessage.severity = 'info';
	PF("socketNotificationGrowl").show([facesmessage])
};