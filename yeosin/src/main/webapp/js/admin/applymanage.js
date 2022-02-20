// 좌석배치 체크함수(manage_status_site.jsp)
function IsCheckedSeatConfirm() 
{
	var checkBox = $("input[name=examZoneCheck]:checked");
			
	if (checkBox.length < 1)
	{
		alert("좌석배치할 고사장이 한개도 선택되지 않았습니다.");
		return false;
	}
}