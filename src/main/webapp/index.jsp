<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<!DOCTYPE html>
    <head>
		<!-- BASICS -->
        <meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>연세대학교 대학원 입학원서 접수 시스템</title>
        <meta name="description" content="연세대학교 대학원">
        <style>
            #toList, #toMyList, #toSignUp {
                cursor: pointer;
                cursor: hand;
            }

            section.intro {
                padding: 200px 0 60px;
                background: #70B9B0;
                color: #fdfdfd;
            }

            section.intro h2.slogan {
                color: #fff;
                font-size: 48px;
                font-weight: 900;
            }

            /* inner heading */
            section.intro.inner {
                background: #eee;
                padding: 150px 0 50px;
            }

            section.intro .spacer-big {
                margin-bottom: 7em;
            }

            section.intro .spacer-mid {
                margin-bottom: 5em;
            }

            section.intro .spacer-small {
                margin-bottom: 3em;
            }

            section.intro .spacer-tiny {
                margin-bottom: 1em;
            }
        </style>
    </head>
	 
    <body>
		<section class="intro" id="intro" data-stellar-background-ratio="0.5">
			<div class="container"> 
				<div class="row mar-bot40">
					<div class="col-md-6 col-md-offset-3">
						
						<div class="align-center">
							<h2 class="slogan">연세대학교 대학원</h2>
							<h2 class="slogan">입학원서 접수 시스템</h2>
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
					<div class="align-center" id="toSignUp">
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
                $('#toList').click(function(){location.href='${contextPath}/notice/list'});
                $('#toMyList').click(function(){location.href='${contextPath}/application/mylist'});
                $('#toSignUp').click(function(){location.href='${contextPath}/user/signup'});
            </script>
        </content>
	</body>
</html>
