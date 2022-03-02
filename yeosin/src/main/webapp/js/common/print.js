$(document).ready(function(){
   
   // 원서접수 확인 및 취소
   $("#btn_print").click(function(){
       const completeParam = makeHtml();
          reportPrint(completeParam);
      });
      
   // 자격인증서 발급
   $("#btn_print_certificate").click(function(){
       const completeParam = makeHtml();
          reportPrintCertificate(completeParam);
      });
});

// 프린트할 영역 Html로 만들기
function makeHtml(){
    const obj = {html : ''};
    let html = document.getElementById("printArea").innerHTML ;  
    obj.html = html;
    return obj;
}

// 원서접수 확인 및 취소 프린트 출력
function reportPrint(param)
{
    const setting = "width=890, height=841";
    const objWin = window.open('', '', setting);
    
    var html ="";
    html += '<html><head><title>프린트</title>';
    html += '<style>';
    
    html += 'table{width:100%; border-top:3px solid #0074dd; box-sizing:border-box; font-family:sandoll-gothicneo3, sans-serif; margin-bottom:70px;}';
    html += 'th{padding:28px 0; border-bottom:1px solid #c7c6c6; box-sizing:border-box;}';
    html += 'td{padding:28px 0; border-bottom:1px solid #c7c6c6; box-sizing:border-box;}';
    html += 'th{text-align:left; font-size:23px; color:#0074dd; font-weight:600; letter-spacing:-0.5px; border-right:1px solid #c7c6c6;}';
    html += 'td{font-size:23px; color:#000; padding-left:22px;}';
    html += 'td input{height:50px; border:1px solid #c7c6c6; box-sizing:border-box; margin-right:9px; font-family:sandoll-gothicneo3, sans-serif; font-size:23px; color:#58585b; letter-spacing:-0.5px; text-indent:12px;}';
    html += 'td input[type="tel"]{width:206px;}';
    html += 'td input[type="email"]{width:794px;}';  
    html += 'td input[type="text"]{width:639px;}';
    html += 'td input[type="radio"]{width:20px; height:20px;}';
    html += 'td label{font-size:23px; color:#58585b; letter-spacing:-0.5px;}';
    
    html += '</style></head>';
    html += '<body>';
    html += param.html;
    html += '</body></html>';
    
    objWin.document.open();
    objWin.document.write(html);
    objWin.focus(); 
    objWin.document.close();
 
    setTimeout(function(){objWin.print();objWin.close();}, 1000);
}

// 자격인증서 프린트 출력
function reportPrintCertificate(param)
{
    const setting = "width=890, height=650";
    const objWin = window.open('', '', setting);
    
    var html ="";
    html += '<html><head><title>자격인증서</title>';
    html += '<style>';
   
    html += '@media print { * {-webkit-print-color-adjust:exact;}}';
    html += '.document{width:100%; height:92%; border:1px solid #707070; box-sizing:border-box; background:url("/www/inc/img/state/bg_certificate.jpg") no-repeat center / contain; padding-top:120px; margin-bottom:70px;}';
    html += '.docNum{font-family:sandoll-gothicneo3, sans-serif; font-size:5px; line-height:1; color:#000; text-indent:90px; margin-bottom:10px;}';
    html += 'h3{width:100%; height:100px; font-family:ChosunGs, serif; font-size:45px; line-height:80px; color:#000; text-align:center; display:table;}';
    html += 'h3 p{width:100%; height:100%; display:table-cell; vertical-align:middle;}';
    html += 'table{width:75%; margin:0 auto; text-align:left;}';
    html += 'th{font-family:sandoll-gothicneo3, sans-serif; font-size:20px; padding-bottom:20px; line-height:1.48; letter-spacing:-0.5px; box-sizing:border-box;}';
    html += 'td{font-family:sandoll-gothicneo3, sans-serif; font-size:20px; padding-bottom:20px; line-height:1.48; letter-spacing:-0.5px; box-sizing:border-box;}';
    html += 'th{color:#58585b;}';
    html += '.document table td{color:#000; padding-left:16px; background:url("/www/inc/img/state/element_dot.png") no-repeat left 12px /3px;}';
    html += '.evidence{height:230px; font-family:ChosunGs, serif; font-size:25px; line-height:1.6; color:#000; text-align:center; margin-bottom:60px; display:table;}';
    html += '.evidence p{height:100%; display:table-cell; vertical-align:middle;}';
    html += '.date{font-family:ChosunGs, serif; font-size:18px; line-height:1.25; text-align:center;}';
    html += '</style></head>';
    html += '<body>';
    html += param.html;
    html += '</body></html>';
    
    objWin.document.open();
    objWin.document.write(html);
    objWin.focus(); 
    objWin.document.close();
 
    setTimeout(function(){objWin.print();objWin.close();}, 1000);
}
