
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
	} else if (data.module == "cert") {
		var findType = $("#findType").val();
		var url = "";
		if(findType=="id") {
			url = "/find_id_cert";
		}else {
			url = "/find_pwd_cert";
		}
		
		var inputData = {
				ciCode:data.ciCode
		};

		$.ajax({
	        type: "POST",
	        url: url,
	        data: inputData,
	        sendDataType : 'string',
	        success: function(data) {
	        	var url = "/popup?type=" + $("#findType").val() + "&data="+data;
	    		var options = 'top=10, left=10, width=400, height=200, status=no, menubar=no, toolbar=no, resizable=no';
	    		window.open(url, "아이디/비밀번호 찾기", options);
	    		
//	        	if(data != "null"){
//	        		if($("#findType").val()=="id") {
//	        			alert("인증  되었습니다.\n회원님의 아이디는 " + data + " 입니다.");
//	        		}else {
//	        			alert("인증  되었습니다.\n임시 비밀번호는 " + data + " 입니다.");
//	        		}
//	        	}else {
//	        		alert("일치하는 정보가 없습니다.");
//	        	}
	        },
	      });
	}
});