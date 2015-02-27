/**
 * Created by hanmomhanda on 15. 2. 27.
 */
var apex = {
    'engNameCheck': function(className) { // 영문 이름 : 영 대소문자, 공백, '.', '-'만 허용
        $('.'+className).on('blur', function () {
            var engNameCheckRegExp = /^[a-zA-Z.\-\s]*$/,
                val = this.value;
            if (!engNameCheckRegExp.test(val) && val != '') {
                this.value = "";
                this.focus();
                alert("영 대소문자와 공백, '.', '-'만 가능합니다.");
            } else {
                this.value = this.value.toUpperCase();
            }
        });
    },
    'removeSpace' : function(className) { // 입력받은 문자열 내 공백 문자 제거
        $('.'+className).on('blur', function () {
            this.value = this.value.replace(/(\s*)/gi,"");
        });
    },
    'numCheck' : function(className) { // 숫자만 허용
        $('.'+className).on('blur', function () {
            var numCheckRegExp = /^[0-9]*$/,
                val = this.value;
            if (!numCheckRegExp.test(val) && val != '') {
                alert("숫자만 입력해 주세요.");
                this.value = "";
                this.focus();
            }
        });
    },
    'emailCheck' : function(className) { // 이메일 주소만 허용
        $('.'+className).on('blur', function () {
            var emailRegExp = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
                val = this.value;
            if (!emailRegExp.test(val) && val != '') {
                alert("이메일 주소를 정확히 기재해 주세요");
                this.value = "";
                this.focus();
            }
        });
    },
    'transKorPhoneNumber' : function(className) { // 국내 전화번호를 #-#-# 형태로 변환
        $('.'+className).each( function() {
            this.value = this.value.replace(/(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/,"$1-$2-$3");
        });
    }
};
