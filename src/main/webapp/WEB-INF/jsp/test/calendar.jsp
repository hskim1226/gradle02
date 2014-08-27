<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
    <style>
        section.application-selfintro {
            padding: 200px 0 60px;
            background: #5f5f5f;
            color: #fdfdfd;
            position:relative;
        }

        section h2.slogan {
            color: #fff;
            font-size: 36px;
            font-weight: 900;
        }

        section h3.slogan {
            color: #fdfdfd;
            font-size: 24px;
            font-weight: 500;
            text-align: left;
        }

        section.notice-detail h4.slogan {
            color: #ff8d8d;
            font-size: 18px;
            font-weight: 500;
            text-align: left;
        }

        section .slogan {
            font-weight: 900;
        }

        section .big-font {
            color: #fdfdfd;
            font-size: 20px;
            font-weight: 500;
            text-align: left;
        }

        section .mid-font {
            color: #fdfdfd;
            font-size: 18px;
            text-align: left;
        }

        section .small-font {
            color: #fdfdfd;
            font-size: 12px;
            text-align: left;
        }

        section .text-center {
            text-align: center;
        }

        /* 팝업창이 보여질 부분 */
        #popup, #popup2, .bMulti {
            background-color: #fff;
            color: #111;
            display: none;
            min-width: 450px;
            padding: 25px;
        }

        #popup, .bMulti {
            min-height: 250px;
        }
        /* 클릭할 버튼 */
        .button {
            background-color: #2b91af;
            color: #fff;
            cursor: pointer;
            display: inline-block;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
        }
        /* 닫기 버튼 */
        .button.b-close, .button.bClose {
            box-shadow: none;
            font: bold 131% sans-serif;
            padding: 0 6px 2px;
            position: absolute;
            right: -7px;
            top: -7px;
        }
    </style>
</head>
<body>
<section class="application-selfintro" id="application-create">
    <div class="container" id="container">
        <h2 class="slogan">Calendar Test</h2>

        <div class="spacer-small"></div>
        <!-- default -->
        <span class="button small pop1">Example 1:default</span>

        <!-- easing plugin을 사용하는 예제 -->
        <span class="button small pop1" data-bpopup='{"transition":"slideDown","speed":850,"easing":"easeOutBack"}'>Example 3a(transition,speed,easing)</span>

        <!-- 'ajax', 'iframe' or 'image' 를 사용하는 예제- 여기서는 image 를 사용했습니다. -->
        <span class="button small pop2" data-bpopup='{"content":"image","contentContainer":".content","loadUrl":"../images/image.jpg"}'>Example 5b(Image popup)</span>


        <div id="popup">
            <span class="button b-close"><span>X</span></span>
            <p>처음부터 팝업 문서에 포함되어 있는 div - 클릭시 레이어로 나옴.</p>
        </div>
        <div id="popup2">
            <span class="button b-close"><span>X</span></span>
            <div class="content"></div>
        </div>
    </div>
</section>
<content tag="local-script">
    <script src="${contextPath}/js/bootstrap-datepicker.js"></script>
    <script src="${contextPath}/js/bootstrap-datepicker.kr.js"></script>
    <script>
        $(document).ready( function() {
            $(function() {
                var $p1 = $('#popup'),
                        $p2 = $('#popup2');
                // i = 0;

//                $('body').on('click', '.small', function(e) {
//                    e.preventDefault();
//                    var popup = $(this).hasClass('pop1') ? $p1 : $p2,
//                            content = $('.content'),
//                            self = $(this);
//
//                    popup.bPopup(self.data('bpopup') || {});
//                });

                $('.small').on('click', function(e) {
                    e.preventDefault();
                    var popup = $(this).hasClass('pop1') ? $p1 : $p2,
                            content = $('.content'),
                            self = $(this);

                    popup.bPopup(self.data('bpopup') || {});
                });

            });
        });
    </script>
</content>
</body>
</html>
