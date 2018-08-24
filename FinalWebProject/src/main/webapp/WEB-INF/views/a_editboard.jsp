<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Insert title here</title>
  <meta charset="utf-8">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   <style>
  	#sidebar{
  		float: left;
  	}
  	#sidebar:before, #sidebar:after{
  		clear:both;
  		content:"";
  	}
  </style>
</head>
<body>
<div id="header">
	<%@ include file="adminheader.jsp" %>
</div>
<div id="sidebar">
<%@ include file="adminsidebar.jsp" %>
</div>
<div id="content">
   	 <div class="well text-center">
   	 	<h2 style="font-weight:bold;">자유 게시판 관리</h2>   
 	 </div>
 	 	<form id="updateform" >
        <div class="col-sm-10">
          <div class="well" style="height:100%;background:white;">
            <div class="container-fluid">
			  <h4 style="font-weight:bold;">${boardDTO.boardName} 수정</h4><br>  
			  <input type="hidden" name="boardCode" value="${boardDTO.boardCode }">
			  

			<article class="container">
            <div class="col-sm-6 col-md-offset-3">
                
                    <div class="form-group">
						<label for="boardName">게시판명</label>
                        <input type="text" class="form-control" id="boardName" name="boardName" placeholder="게시판명을 입력해 주세요" value="${boardDTO.boardName }">
                    </div>
                    <label for="divgrade">분류 선택</label>
                    <div class="form-group grade">
                    	3분류 : <input type="radio" class="Radios2" id="treetier" name="divgrade" value="3" checked>
                    	<span>&nbsp&nbsp</span>
                    	4분류 : <input type="radio" class="Radios2" id="fourtier" name="divgrade" value="4">
                    	<br>
                        <div class="table-responsive text-center">
                        	<table class="table" style="width:100%">
                        		<thead>
	                        		<tr>
	                        			<th>레벨</th>
	                        			<th>등급명</th>
	                        			<th>등업기준</th>
	                        		</tr>
                        		</thead>
                        		<tbody>
                        			<c:forEach var="grade" items = "${gradeDTO }" varStatus="status">
                        			<tr>
                        				<td  width="30%"><span>${status.index+1 }</span></td>
                        				<td><input type="hidden" name="gradeNum" value="${grade.gradeNum}"></td>
                        				<td><input type="text" class="col-sm-10" id="gradeName" name="gradeName" value="${grade.gradeName}"></td>
                        				<td><input type="text" class="col-sm-10" id="articleCount" name="articleCount" value="${grade.articleCount}"></td>
                        			</tr>
                        			</c:forEach>
                        		</tbody>
                        	</table>
                        </div>
                    </div>
                    <label for="year">등급업 기준일 변경</label>
                    <div class="grade2">
	                    <div class="form-group">
							<script class="birth_sc">
								document.write("<select name='gradeUpDate'>");
								for (i=1;i<=31;i++) document.write("<option value='"+ i +"'>"+i+"일</option>"); 
								document.write("</select> </font>");
							</script>
	                    </div>
                    </div>
                    <div class="form-group text-center">
                        <button class="btn btn-success" id="themeChange">
                          	  등록<i class="fa fa-check spaceLeft"></i>
                        </button>
                        <a href="/ad_boardlist" type="button" class="btn btn-warning">
                           	 취소<i class="fa fa-times spaceLeft"></i>
                        </a>
                    </div>
               
            </div>
        </article>
        </div>
        </div>
        </div>
        </form>
</div>  
</body>
    <script>
	$(document).ready(function() {
	    //set initial state.
	   
 	    /* $('#fourtier').change(function() {
	        if(this.checked) {
	            
	        }        
	    });
	    
	    $('#divgrade').click(function(){
	    	var radio = $('input[name="divgrade"]:checked'	).val();
	    	for(int i=0;i<radio;i++){
	    		
	    	}
	    }); */
	     
	    $('#themeChange').click(function(){
	    	$('#updateform').attr("method", "post");
	    	$('#updateform').attr("action", "a_editboard");
	    	$('#updateform').submit();
	    }); 
	    
	});
</script>
</html>