function fileSize()
{
	 var size = 0;
	
	 var node = $('#file')[0];
	 if(node.files.length != 0)
		 size = node.files[0].size;
	
	 return size;
}