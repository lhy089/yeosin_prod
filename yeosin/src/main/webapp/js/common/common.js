$(document).ready(function(){
	
	$("#btn_print").click(function(){
		$("#printArea").printArea();	
	});
	
	(function($)
	{
		var printAreaCount = 0;
		
		$.fn.printArea = function()
		{
			var ele = $(this);
            var idPrefix = "printArea_";

            removePrintArea( idPrefix + printAreaCount );

            printAreaCount++;

            var iframeId = idPrefix + printAreaCount;
            var iframe = document.createElement('IFRAME');

            $(iframe).attr('style','position:absolute;width:0px;height:0px;left:-500px;top:-500px;');
            $(iframe).attr('id', iframeId);

            document.body.appendChild(iframe);

            var doc = iframe.contentWindow.document;
            var links = window.document.getElementsByTagName('link');
			
			var cssLink = document.createElement("link");
                cssLink.href = "/www/inc/css/apply.css"; 
                cssLink.rel = "stylesheet"; 
                cssLink.type = "text/css"; 

			doc.head.appendChild(cssLink);
			
            for (var i = 0; i < links.length; i++)
			{
				if (links[i].rel.toLowerCase() == 'stylesheet')
				{
					doc.write(' <link rel="stylesheet" href="' + "/www/inc/css/apply.css" + '"></link> ');	
				}
				else if (links[i].rel.toLowerCase() == 'shortcut icon')
				{
					doc.write(' <link rel="shortcut icon" href="' + "/www/inc/img/favicon.png" + '"></link> ');	
				}
				else if (links[i].rel.toLowerCase() == 'icon')
				{
					doc.write(' <link rel="icon" href="' + "/www/inc/img/favicon.png" + '"></link> ');	
				}				
			}

            doc.write('<div class="' + $(ele).attr("class") + '">' + $(ele).html() + '</div>');
            doc.close();

            iframe.contentWindow.focus();
            iframe.contentWindow.print();		
		}
		
		var removePrintArea = function(id)
		{
		    $( "iframe#" + id ).remove();
		};
	}(jQuery));

});


