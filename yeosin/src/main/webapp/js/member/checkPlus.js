
function fnPopup(authType){
	$.ajax({
        type: "POST",
        url: "/doOpenCert",
        data: {sAuthType : authType},
        sendDataType : 'string',
        success: function(data) { debugger;
        	$("#encodeData").val(data);
        	window.open('', 'popupChk', 'width=500, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
    		document.form_chk.action = "https://nice.checkplus.co.kr/CheckPlusSafeModel/checkplus.cb";
    		document.form_chk.target = "popupChk";
    		document.form_chk.submit();
        }
      });	
}

function fnPopupIpin(){
	$.ajax({
        type: "POST",
        url: "/doOpenCertForIpin",
        sendDataType : 'string',
        success: function(data) { debugger;
        	$("#encodeDataForIpin").val(data);
        	window.open('', 'popupIPIN2', 'width=450, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
			document.form_chk.target = "popupIPIN2";
			document.form_chk.action = "https://cert.vno.co.kr/ipin.cb";
			document.form_chk.submit();
        }
      });
}

window.addEventListener('message', function(e) {
	debugger;
	var data = e.data;
	if (data.module == "ipin") {
		document.vnoform.enc_data.value = data.enc_data;
		document.vnoform.target = data.target;
		document.vnoform.action = data.action;
		document.vnoform.submit();
	} else {
		alert("인증 되었습니다.");

	}
});