/**
 * Created by hanmomhanda on 15. 2. 27.
 */
var apex = {
    rx : {
        engName : /^[a-zA-Z.\-\s]*$/,
        rxSpace : /(\s*)/gi,
        numOnly : /^[0-9]*$/,
        email : /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
        id : /^(?=\w{6,}$)(?=.*\d)(?=.*[A-Z]).*/,
        password : /^(?=\w{6,}$)(?=.*\d)(?=.*[A-Z]).*/,
        korPhoneNumber : /(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/,
        hyphen : /-/g
    },
    engNameCheck : function(className) { // 영문 이름 : 영 대소문자, 공백, '.', '-'만 허용
        $('.'+className).on('blur', function () {
            var val = this.value;
            if (!apex.rx.engName.test(val) && val != '') {
                this.value = "";
                this.focus();
                alert("영 대소문자와 공백, '.', '-'만 가능합니다.");
            } else {
                this.value = this.value.toUpperCase();
            }
        });
    },
    removeSpace : function(className) { // 입력받은 문자열 내 공백 문자 제거
        $('.'+className).on('blur', function () {
            this.value = this.value.replace(apex.rx.space,"");
        });
    },
    numCheck : function(className) { // 숫자만 허용
        $('.'+className).on('blur', function () {
            var val = this.value;
            if (!apex.rx.numOnly.test(val) && val != '') {
                alert("숫자만 입력해 주세요.");
                this.value = "";
                this.focus();
            }
        });
    },
    emailCheck : function(className) { // 이메일 주소만 허용
        $('.'+className).on('blur', function () {
            var val = this.value;
            if (!apex.rx.email.test(val) && val != '') {
                alert("이메일 주소를 정확히 기재해 주세요");
                this.value = "";
                this.focus();
            }
        });
    },
    passwordCheck : function(className) { // 비밀 번호 6자리 이상, 영 대/소문자와 숫자 포함
        $('.'+className).on('blur', function () {
            var val = this.value;
            if (!apex.rx.password.test(val) && val != '') {
                alert("비밀번호는 6자리 이상, 영 대문자, 소문자, 숫자가 하나 이상 포함되어야 합니다.");
                this.value = "";
                this.focus();
            }
        });
    },
    idCheck : function(className) {
        $('.'+className).on('blur', function () {
            var val = this.value;
            if (!apex.rx.id.test(val) && val != '') {
                alert("아이디는 6자리 이상, 영 대/소문자와 숫자가 포함되어야 합니다.");
                this.value = "";
                this.focus();
            }
        });
    },
    transKorPhoneNumber : function(className) { // 국내 전화번호를 #-#-# 형태로 변환
        $('.'+className).each( function() {
            this.value = this.value.replace(apex.rx.korPhoneNumber,"$1-$2-$3");
        });
    },
    removeHyphen : function(str) {
        return str.replace(apex.rx.hyphen, '');
    },
    transKorRgstNumber : function(className) {
        $('.'+className).each( function() {
            var val = this.value;
            this.value = val.substring(0, 6) + '-' + val.substring(6);
        });
    }
};
