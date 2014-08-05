<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
    <head>
		<!-- BASICS -->
        <meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>연세대학교 대학원 입학 신청 시스템</title>
        <meta name="description" content="연세대학교 대학원">
        <style>
            #toList, #toMyList {
                cursor: pointer;
                cursor: hand;
            }
        </style>
    </head>
	 
    <body>
		<section class="featured" id="intro" data-stellar-background-ratio="0.5">
			<div class="container"> 
				<div class="row mar-bot40">
					<div class="col-md-6 col-md-offset-3">
						
						<div class="align-center">
							<h2 class="slogan">연세대학교 대학원</h2>
							<h2 class="slogan">입학 신청 시스템</h2>							
						</div>
					</div>
				</div>
			</div>
		</section>


		<!-- services -->
		<section id="section-services" class="section pad-bot30 bg-white">
		<div class="container"> 
		
			<div class="row mar-bot40">
				<div class="col-lg-4" >
					<div class="align-center" id="toList">
						<i class="fa fa-code fa-5x mar-bot20"></i>
						<h2 class="text-bold">모집 공고</h2>
						<p>연세대학교 대학원 모집 공고를 확인할 수 있습니다.
						</p>
					</div>
				</div>
					
				<div class="col-lg-4" >
					<div class="align-center" id="toMyList">
						<i class="fa fa-terminal fa-5x mar-bot20"></i>
						<h2 class="text-bold">내 원서</h2>
						<p>작성 중이거나 신청 중인 내 원서를 확인할 수 있습니다.
						</p>
					</div>
				</div>
			
				<div class="col-lg-4" >
					<div class="align-center">
						<i class="fa fa-bolt fa-5x mar-bot20"></i>
						<h2 class="text-bold">회원 가입</h2>
						<p>연세대학교 대학원 입학 신청 시스템의 회원으로 가입할 수 있습니다.
						</p>
					</div>
				</div>
			
			</div>	

		</div>
		</section>
        <content tag="local-script">
            <script>
                $('#toList').click(function(){location.href='/notice/list'});
                $('#toMyList').click(function(){location.href='/application/mylist'});
            </script>
        </content>
	</body>
</html>
