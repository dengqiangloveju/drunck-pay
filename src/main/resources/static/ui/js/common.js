function queryPage(pageNo) {
	$("#queryForm").append('<input type="hidden" name="pageNo" value="'+pageNo+'">');
	$("#queryForm").submit();
}