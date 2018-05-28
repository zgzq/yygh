var hov={};
//选择用户，控件的id
hov.user=function(search,userid,username){
	hov.dx(search,"/hov/user","选择用户",userid,username,"userid","username");
};

hov.dx=function(search,url,stitle,id,name,retid,retname){
	$("#"+search).click(function(){	
		 art.dialog
			.open(
					WEB_ROOT+url,
					{
						title : stitle,
						height : '500px',
						width : '900px',
						lock : true,
						resize : true,
						left : '180px',
						top : '20px',
						esc : false,
						close:function(){
							var data=art.dialog.data("data");
							if(data){
								$("#"+id).val(data[retid]);
								$("#"+name).val(data[retname]);
								art.dialog.removeData("data");
							}
						}
					}, false);
	 }
	);
	
	$("#"+name).blur(function(){
		if($("#"+name).val()==''){
			$("#"+id).val('');
		}
	});
	
	$("#"+name).keydown(function(e){
		if(e.keyCode==13){
			if($("#"+name).val()==''){
				$("#"+id).val('');
			}
		}
	});
};