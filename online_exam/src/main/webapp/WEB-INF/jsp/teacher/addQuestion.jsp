<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>无标题文档</title>
</head>
<script src="js/jquery/2.0.0/jquery.min.js"></script>
<link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<link href="css/studentIndex.css" rel="stylesheet">
<script src="js/bootstrap/3.3.6/bootstrap.min.js"></script>
<body>
<script type="text/javascript">
	$(function(){
		html = '<div class="subject-title">'
        	+'添加问题：<input type="text" class="form-control cqQuestion" value="">'
        	+'题目分值：<input type="text" class="form-control cqScorePre" value="">'
       		+' 选项一：<input type="text" class="form-control cqOne" value="">'
         	+' 选项二：<input type="text" class="form-control cqTwo" value="">'
            +'选项三：<input type="text" class="form-control cqThree"  value="">'
          	+' 选项四：<input type="text" class="form-control cqFour" value="">'
          	+'<input type="hidden" class="form-control cqQuestionType" value="0">'
			+'</div>'
		$('.addMore').click(function(){
			$('.contentArea').append(html);
		});
	});
</script>
    <div class="my-container">
        <div class="my-header">
           <div class="subject-title black">
           <center>
           	你好${sessionScope.userName}，这里是教师端！
           	<a href="findPaperNotCorrecting?score=-1">待批改的试卷</a>
            <a href="toAddQuestion">新建考试试卷</a>
            </center>
           </div>           
        </div>
        <div class="nk-main  clearfix">
            <div class="subject-progress">        
            </div>
            <div class="subject-title">
                                           试卷title：<input type="text" class="form-control title" value=""></input>
                                          出题老师：<input type="text" class="form-control teacherName" value="">
                                           
            </div>
          <div class="subject-title">
                                添加问题：<input type="text" class="form-control cqQuestion" value="">
                                题目分值：<input type="text" class="form-control cqScorePre" value="">
                                 选项一：<input type="text" class="form-control cqOne" value="">
                                   选项二：<input type="text" class="form-control cqTwo" value="">
                                    选项三：<input type="text" class="form-control cqThree" value="">
                                    选项四：<input type="text" class="form-control cqFour" value=""> 
               <input type="hidden" class="form-control cqQuestionType" value="0">                 
            </div>
            <div class="contentArea"></div>
             <button class="btn btn-warning addMore">继续添加</button>
             <button class="btn btn-success" id="submitForm">生成试卷</button>  
           
    </div>
</body>
<script type="text/javascript">
$(function(){
	$('#submitForm').click(function(){
		var totalQuestion = $('.cqQuestion').length;
		//alert(totalQuestion);
		var totalMessage = new Array();
		for(var i=0;i<totalQuestion;i++){
			totalMessage.push({cqQuestion:$('.cqQuestion')[i].value,cqScorePre:$('.cqScorePre')[i].value,
				cqOne:$('.cqOne')[i].value,cqTwo:$('.cqTwo')[i].value,
				cqThree:$('.cqThree')[i].value,cqFour:$('.cqFour')[i].value,cqQuestionType:$('.cqQuestionType')[i].value});
		}
		teacherName = $('.teacherName').val();
		title = $('.title').val();
		console.log(totalMessage);
		//提交前判断非空
		
		$.ajax({
            url: "addQuestion?teacherName="+teacherName+"&title="+title+"",
            type: "POST",
            contentType : 'application/json;charset=utf-8', //设置请求头信息
            dataType:"json",
            data: JSON.stringify(totalMessage),    //将Json对象序列化成Json字符串，JSON.stringify()原生态方法
            success: function(data){
                if(data.message=="请完整填写数据!"){
                	alert("请完整填写数据!");
                }
                if(data.message=="提交成功!"){
                	alert("提交成功!");
                	location.href="findPaperNotCorrecting?score=-1";
                }
                if(data.data.message=="提交失败!"){
                	alert("提交失败!");
                }
            },
            error: function(res){
                alert(res.responseText);
            }
        });
		
	});	
});



</script>
</html>