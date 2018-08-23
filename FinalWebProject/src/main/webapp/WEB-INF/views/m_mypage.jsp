<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko-KR"
	itemscope
	itemtype="http://schema.org/Article"
	prefix="og: http://ogp.me/ns#"  class="no-js">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="format-detection" content="telephone=no">
	<link rel="profile" href="http://gmpg.org/xfn/11">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
	
	<script>(function(html){html.className = html.className.replace(/\bno-js\b/,'js')})(document.documentElement);</script>
<title>SMASH</title>

<link rel='dns-prefetch' href='//code.jquery.com' />
<link rel='dns-prefetch' href='//fonts.googleapis.com' />
<style type="text/css">
img.wp-smiley,
img.emoji {
	display: inline !important;
	border: none !important;
	box-shadow: none !important;
	height: 1em !important;
	width: 1em !important;
	margin: 0 .07em !important;
	vertical-align: -0.1em !important;
	background: none !important;
	padding: 0 !important;
}
 
.contain{
	width: 100%;
	text-align:center;
} 
.footer_navbx{

}
.max_container > ul{
	padding: 0 0 0 40%;
}
</style>
<link rel='stylesheet' id='dashicons-css'  href='./resources/css/dashicons.min.css' type='text/css' media='all' />
<link rel='stylesheet' id='post-views-counter-frontend-css'  href='./resources/css/frontend.css' type='text/css' media='all' />
<link rel='stylesheet' id='twentysixteen-fonts-css'  href='./resources/css/css.css' type='text/css' media='all' />
<link rel='stylesheet' id='genericons-css'  href='./resources/css/genericons.css' type='text/css' media='all' />
<link rel='stylesheet' id='twentysixteen-style-css'  href='./resources/css/style.css' type='text/css' media='all' />
<link rel='stylesheet' id='jquery-ui-css'  href='//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.min.css' type='text/css' media='all' />
<link rel='stylesheet' id='unslider-css'  href='./resources/css/unslider.css' type='text/css' media='all' />
<script type='text/javascript' src='./resources/js/jquery.js'></script>
<script type='text/javascript' src='./resources/js/jquery-migrate.min.js'></script>
<script type='text/javascript' src='//code.jquery.com/ui/1.11.4/jquery-ui.min.js'></script>
<script type='text/javascript' src='./resources/css/unslider.js'></script>
<link rel="alternate" type="application/json+oembed" href="./resources/json/embed.json" />
<link rel="alternate" type="text/xml+oembed" href="./resources/json/embed.json" />
		<style type="text/css">.recentcomments a{display:inline !important;padding:0 !important;margin:0 !important;}</style>
	<link rel="stylesheet" href="http://gangwon-fc.com/wp-content/themes/gangwonfc/css/template.css?20170607" type="text/css" media="all">
	<link rel="stylesheet" href="./resources/css/metabrain-editor.css" type="text/css" media="all">
	<script type="text/javascript" src="./resources/js/common.js"></script>
</head>

<body style="background:white;">
<div id="masthead" class="site-header" role="banner"><!-- site-header-menu 영역 호버시 nav_on 클래스 적용   .search_btn 클릭시 search_on 클래스 적용-->
			<div class="hover_bg"></div>
			<!-- 서치박스 -->
			<div class="head_searchbx">
				<div class="max_container">
					<label for="head_search">검색</label>
					<div class="search_anibx">
						<input type="text" class="textbx" id="head_search" placeholder="검색어를 입력해주세요.">
						<input type="submit" class="submitbx" value="검색하기">
						<a href="#;" class="close_btn">close</a>
					</div>
				</div>
			</div>
			<!-- 서치박스 -->
			<!-- 상단헤더 -->
			<div class="top_headbx">
				<div class="max_container">
					<!-- SNS -->
					<div class="head_sns_bx">
						<ul>
							<li class="instargram"><a href="https://www.instagram.com/" target="_blank">instargram</a></li>
							<li class="facebook"><a href="https://www.facebook.com/" target="_blank">facebook</a></li>
							<li class="youtube"><a href="http://www.youtube.com/" target="_blank">youtube</a></li>
						</ul>
					</div>
					<!-- .SNS -->
					<!-- 링크박스 -->
					<div class="head_linkbx"><!-- 사이트맵 클릭시 active 클래스 적용 -->
						<ul>
							<li><a href="login.jsp">LOGIN</a></li>
							<li><a href="signup.jsp">JOIN</a></li>
						</ul>
					</div>
					<!-- .링크박스 -->
					<!-- 서치버튼 -->
					<!--<div class="search_btn"><a class="search_item" href="#;">검색하기</a></div>-->
					<!-- .서치버튼 -->
				</div>
			</div>
			<!-- .상단헤더 -->
			<!-- 하단헤더 -->
			<div class="nav_headbx">
				<div class="max_container">
					<div id="site-header-menu" class="site-header-menu">
						<nav id="site-navigation" class="main-navigation" role="navigation" aria-label="기본 메뉴">
							<div class="menu-main_nav-container">
							<ul id="menu-main_nav-1" class="primary-menu">
							<li class="nav_greatu menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-item-91">
							<a href="index.jsp">Home</a>
							<ul class="sub-menu">
								<li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-92"><a href="noticeboard.jsp">공지사항</a></li>
								<li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-item-95"><a href="m_board.jsp">자유게시판</a>
									<ul class="sub-menu">
										<li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-7014"><a href="#">Q&#038;A</a></li>
								</ul>
							</li>
						</ul>
					</li>
					<li class="nav_about menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-item-1167"><a href="#">Smash</a>
						<ul class="sub-menu">
							<li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-97"><a href="#">Smash 소개</a></li>
							<li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-item-99"><a href="#">Smash 이용 방법</a>
						<ul class="sub-menu">
					<li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-7020"><a href="#">연혁</a></li>
	</ul>
</li>
</li>
</ul>
</li>
<li class="nav_athlete menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-item-103"><a href="#">축구</a>
<ul class="sub-menu">
	<li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-22305"><a href="#">매칭 게시판</a></li>
	<li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-22305"><a href="#">축구 게시판</a></li>
</ul>
</li>
<li class="nav_match menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-item-105"><a href="#">야구</a>
<ul class="sub-menu">
	<li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-22305"><a href="#">매칭 게시판</a></li>
	<li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-22305"><a href="#">야구 게시판</a></li>
</ul>
</li>
<li class="nav_media menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-item-110"><a href="#">배드민턴</a>
<ul class="sub-menu">
	<li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-22305"><a href="#">매칭 게시판</a></li>
	<li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-22305"><a href="#">배드민턴 게시판</a></li>
</ul>
</li>
<li class="nav_youth menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-item-114"><a href="#">농구</a>
<ul class="sub-menu">
	<li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-22305"><a href="#">매칭 게시판</a></li>
	<li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-22305"><a href="#">농구 게시판</a></li>
</ul>
</li>
<li class="nav_management menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-item-119"><a href="#">e-Sports</a>
<ul class="sub-menu">
	<li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-22305"><a href="#">매칭 게시판</a></li>
	<li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-22305"><a href="#">e-Sports 게시판</a></li>
</ul>
</li>
<li class="nav_quick menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children menu-item-1161"><a href="#;">Quick Menu</a>
<ul class="sub-menu">
	<li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-123"><a target="_blank" href="#">고객센터</a></li>
</ul>
</li>
</ul></div>								</nav><!-- .main-navigation -->
													</div><!-- .site-header-menu -->
				</div>
			</div>
			<!-- .하단헤더 -->
		</div>
		<div class="pg_topbannerbx">
	<div class="bg"></div>
	<div class="max_container">
		<!-- 타이틀 박스 -->
		<div class="title_bx">
			<h2 class="entry-title">마이페이지</h2>
						<!-- 빵가루 -->
			<div class="breadcrumb">
				<ul>
					<li class="home"><a class="item" href="/index">home</a></li>						
					<li><a class="item" href="/m_mypage">마이페이지</a></li>						
				</ul>
			</div>
			<!-- .빵가루 -->
		</div>
		<!-- .타이틀 박스 -->
	</div>
</div>
<!-- .헤더 -->
<div id="primary" class="content-area">
	<div id="main" class="site-main" role="main">
		<div class="content_rowbx">
			<div class="max_container">
				<article id="post-42" class="post-42 page type-page status-publish hentry">
					<div class="entry-content">
						<!-- 컨텐츠 시작 -->
	
						<div class="pg_headbx align_c">
							<div class="basic_s_bx">
								<h5 style="font-size:30pt">내가 작성한 글</h5>
							</div>
						</div>
						<!-- 리스트 -->
			
						
						<div>
							<table class="tbl_basiclist free_board" width="100%" cellspacing="1" cellpadding="0" border="0">
							<caption>자유게시판</caption>
							<tr>
							<td>
							<table>
								<colgroup>
									<col style="width: 15%;">
									<col style="width: 60%;">
									<col style="width: 16%;">
									<col style="width: 8%;">
									<col style="width: 3%;">
								</colgroup>
								<thead>
									<tr>
										<th scope="col">게시판명</th>
										<th scope="col">제목</th>
										<th scope="col">작성일</th>
										<th scope="col">조회수</th>
										<th scope="col">&nbsp;</th>
									</tr>
								</thead>
							</table>
							</td>
							</tr>
							<tr>
							<td>
							<div style="width:100%; height:600px; overflow:scroll">
							<table>
								<colgroup>
									<col style="width: 15%;">
									<col style="width: 60%;">
									<col style="width: 14%;">
									<col style="width: 11%;">
								</colgroup>
								<tbody>
								<c:forEach var="memberArticleVO" items="${memberArticleList}">
									<tr>
										<th class="step1" scope="row" id="boardName">${memberArticleVO.boardName}</th>
										<td class="step3" id="articleTitle">${memberArticleVO.articleTitle}</td>
										<!-- <span class="comment">[4]</span> -->
										<td class="step4">${memberArticleVO.articleDate}</td>
										<td class="step5">${memberArticleVO.articleHits}</td>
									</tr>
								</c:forEach>								
							</tbody>
						</table>
						</div>
						</td>
						</tr>
					</table>
				</div>
				<!-- .리스트 -->
								<!-- 글쓰기 버튼 -->
				<div class="basic_btnbx">
					<a href="#" class="basic_btn"><span class="text">회원 정보 수정</span></a>
				</div>
				<!--. 글쓰기 버튼 -->
				
			</div><!-- .entry-content -->
		</article><!-- #post-## -->
	</div>
</div>
</div><!-- .site-main -->
</div><!-- .content-area -->
</body>
</html>