$(document).ready(function(){
   
   $("#btn_print").click(function(){
       const completeParam = makeHtml();
          reportPrint(completeParam);
      });
});

// 프린트할 영역 Html로 만들기
function makeHtml(){
    const obj = {html : ''};
    let html = document.getElementById("printArea").innerHTML ;  
    obj.html = html;
    return obj;
}

// 프린트 출력
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

