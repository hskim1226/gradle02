/**
 * Created by hanmomhanda on 16. 3. 24.
 */
var mprotocol=window.location.protocol;
var mappversion=window.navigator.appVersion;
var vista_flag = false;
vista_flag = (window.navigator.appVersion.indexOf('Windows NT 6.') != -1);
var lgdacom_atx_flag = true;
var https_flag = true;

var installinfo = "";
var win8styleie10installinfo = "";


var XPay_win10_flag = false;
var XPay_edge_flag = false;
var XPay_win10_ie11_flag = false;



if(mprotocol == 'https:')
{
    installinfo  = "<table width='400' border='0' cellpadding='0' cellspacing='1' bgcolor='#F2F2F2'> <tr> <td align='center' bgcolor='#FFFFFF' > <table width='398' border='0' cellpadding='0' cellspacing='0' > <tr> <td align='center' bgcolor='#0565AB' height='30' style='color: #FFFFFF; font-weight: bold;font-size:12px; font-family: '돋움체', 'Arial';padding:6 6 6 6'>LG U+ 전자결제를 위한 ActiveX를 설치해 주세요.</td> </tr> <tr> <td align='center' height='30' style='font-size:12px; font-family: '돋움체', 'Arial';padding:19 0 5 0'> ActiveX를 설치하면 <span style='color: #0565AB; font-weight: bold;'>안전하고 빠른 결제</span>가 가능해집니다.</td> </tr> <tr> <td align='center'><table width='90%' border='0' bgcolor='b2c4d4'> <tr> <td align='center' bgcolor='#FEFEFE'><table border='0' cellspacing='0' cellpadding='0'> <tr> <td align='center' height='40' valign='bottom' style='font-size:12px; font-family: '돋움체', 'Arial';padding:10 0 0 0'>보안경고창이나 브라우저 상단의 알림 표시줄에서<br> <span style='color: #0565AB; font-weight: bold;'>ActiveX 컨트롤 설치</span>를 선택해주세요.</td> </tr> <tr> <td align='center' style='padding:7 0 11 0'><img src='https://xpay.lgdacom.net/xpay/image/activex/Activex_img.gif' width='315' height='32' border='0'></td> </tr> </table></td> </tr> </table></td> </tr> <tr> <td height='10'></td> </tr> </table></td> </tr> </table>";
    win8styleie10installinfo = "<table width='400' border='0' cellpadding='0' cellspacing='1' bgcolor='#F2F2F2'> <tr> <td align='center' bgcolor='#FFFFFF' > <table width='398' border='0' cellpadding='0' cellspacing='0' > <tr> <td align='center' bgcolor='#0565AB' height='30' style='color: #FFFFFF; font-weight: bold;font-size:12px; font-family: '돋움체', 'Arial';padding:6 6 6 6'>LG U+ 전자결제를 위한 ActiveX를 설치할 수 없습니다.</td> </tr> <tr> <td align='center' height='30' style='font-size:12px; font-family: '돋움체', 'Arial';padding:19 0 5 0'> Windows 8 <span style='color: #0565AB; font-weight: bold;'> Style UI </span>환경에서는 이용 할수 없습니다.</td> </tr> <tr> <td align='center'><table width='90%' border='0' bgcolor='b2c4d4'> <tr> <td align='center' bgcolor='#FEFEFE'><table border='0' cellspacing='0' cellpadding='0'> <tr> <td height='40' valign='bottom' style='font-size:12px; font-family: '돋움체', 'Arial';padding:10 0 0 0'><span style='color: #FF5599; font-weight: bold;'>안내</span><br>브라우저 하단 메뉴의 <span style='color: #0565AB; font-weight: bold;'>[페이지 도구](<img src='https://xpay.uplus.co.kr/xpay/image/common/pagetoolicon50p.png' border='0'>)에서 [데스크톱 브라우저에서 보기]</span>를 선택해 주십시오.</td> </tr> <tr><td align='left' style='font-size:12px; font-family: '돋움체', 'Arial';padding:10 0 0 0'><br><br><b>설명</b><br>1. 스패너 모양을 한 [페이지 도구] 아이콘을 선택해 주십시오.<br><center><img src='https://xpay.uplus.co.kr/xpay/image/common/ie10bar1.png' border='0'></center><br>2. 메뉴에서 [데스크톱 브라우저에서 보기]를 선택해 주십시오.<br><center><img src='https://xpay.uplus.co.kr/xpay/image/common/ie10bar2.png' border='0'></center></td></tr> </table></td> </tr> </table></td> </tr> <tr> <td height='10'></td> </tr> </table></td> </tr> </table>";
}
else
{
    installinfo = "<table width='400' border='0' cellpadding='0' cellspacing='1' bgcolor='#F2F2F2'> <tr> <td align='center' bgcolor='#FFFFFF' > <table width='398' border='0' cellpadding='0' cellspacing='0' > <tr> <td align='center' bgcolor='#0565AB' height='30' style='color: #FFFFFF; font-weight: bold;font-size:12px; font-family: '돋움체', 'Arial';padding:6 6 6 6'>LG U+ 전자결제를 위한 ActiveX를 설치해 주세요.</td> </tr> <tr> <td align='center' height='30' style='font-size:12px; font-family: '돋움체', 'Arial';padding:19 0 5 0'> ActiveX를 설치하면 <span style='color: #0565AB; font-weight: bold;'>안전하고 빠른 결제</span>가 가능해집니다.</td> </tr> <tr> <td align='center'><table width='90%' border='0' bgcolor='b2c4d4'> <tr> <td align='center' bgcolor='#FEFEFE'><table border='0' cellspacing='0' cellpadding='0'> <tr> <td align='center' height='40' valign='bottom' style='font-size:12px; font-family: '돋움체', 'Arial';padding:10 0 0 0'>보안경고창이나 브라우저 상단의 알림 표시줄에서<br> <span style='color: #0565AB; font-weight: bold;'>ActiveX 컨트롤 설치</span>를 선택해주세요.</td> </tr> <tr> <td align='center' style='padding:7 0 11 0'><img src='http://xpay.lgdacom.net/xpay/image/activex/Activex_img.gif' width='315' height='32' border='0'></td> </tr> </table></td> </tr> </table></td> </tr> <tr> <td height='10'></td> </tr> </table></td> </tr> </table>";
    win8styleie10installinfo = "<table width='400' border='0' cellpadding='0' cellspacing='1' bgcolor='#F2F2F2'> <tr> <td align='center' bgcolor='#FFFFFF' > <table width='398' border='0' cellpadding='0' cellspacing='0' > <tr> <td align='center' bgcolor='#0565AB' height='30' style='color: #FFFFFF; font-weight: bold;font-size:12px; font-family: '돋움체', 'Arial';padding:6 6 6 6'>LG U+ 전자결제를 위한 ActiveX를 설치할 수 없습니다.</td> </tr> <tr> <td align='center' height='30' style='font-size:12px; font-family: '돋움체', 'Arial';padding:19 0 5 0'> Windows 8 <span style='color: #0565AB; font-weight: bold;'> Style UI </span>환경에서는 이용 할수 없습니다.</td> </tr> <tr> <td align='center'><table width='90%' border='0' bgcolor='b2c4d4'> <tr> <td align='center' bgcolor='#FEFEFE'><table border='0' cellspacing='0' cellpadding='0'> <tr> <td height='40' valign='bottom' style='font-size:12px; font-family: '돋움체', 'Arial';padding:10 0 0 0'><span style='color: #FF5599; font-weight: bold;'>안내</span><br>브라우저 하단 메뉴의 <span style='color: #0565AB; font-weight: bold;'>[페이지 도구](<img src='http://xpay.uplus.co.kr/xpay/image/common/pagetoolicon50p.png' border='0'>)에서 [데스크톱 브라우저에서 보기]</span>를 선택해 주십시오.</td> </tr> <tr><td align='left' style='font-size:12px; font-family: '돋움체', 'Arial';padding:10 0 0 0'><br><br><b>설명</b><br>1. 스패너 모양을 한 [페이지 도구] 아이콘을 선택해 주십시오.<br><center><img src='http://xpay.uplus.co.kr/xpay/image/common/ie10bar1.png' border='0'></center><br>2. 메뉴에서 [데스크톱 브라우저에서 보기]를 선택해 주십시오.<br><center><img src='http://xpay.uplus.co.kr/xpay/image/common/ie10bar2.png' border='0'></center></td></tr> </table></td> </tr> </table></td> </tr> <tr> <td height='10'></td> </tr> </table></td> </tr> </table>";
}

if( navigator.userAgent.toLowerCase().indexOf("windows nt 10.0") != -1 )
{
    XPay_win10_flag = true;

    if ( navigator.userAgent.toLowerCase().indexOf("trident") != -1 && navigator.product == "Gecko" ) //IE 11 이상 버전 체크
    {
        var ua = navigator.userAgent;
        var re = new RegExp("Trident/.*rv:([0-9]{1,}[\.0-9]{0,})");
        if (re.exec(ua) != null) rv = parseFloat(RegExp.$1);

        if ( rv == 11 ){
            XPay_win10_ie11_flag = true;
        }
    }
}

if( XPay_win10_flag == true && navigator.userAgent.toLowerCase().indexOf("edge") != -1 ) {
    XPay_edge_flag = true;
}



/* by nico
 * StyleUI IE10 확인 함수 by nico
 */
function isBrowserWin8StyleIE10()
{
    var win8ie10 = false;
    if (navigator.appVersion.indexOf("NT 6.2")!=-1 || navigator.appVersion.indexOf("NT 6.3")!=-1)
    {
        if (navigator.appVersion.indexOf("MSIE")!=-1 || navigator.appVersion.indexOf("Trident")!=-1 )
        {
            win8ie10 = true;
        }
    }

    if (win8ie10==false)
    {
        return false;
    }

    var supported = null;
    try
    {
        new ActiveXObject("");
    }
    catch (e)
    {
        errorName = e.name;
    }
    try
    {
        supported = !!new ActiveXObject("htmlfile");
    }
    catch (e)
    {
        supported = false;
    }
    if(errorName != 'ReferenceError' && supported==false)
    {
        supported = false;
    }
    else
    {
        supported = true;
    }
    if (supported==true)
    {
        return false;
    }
    else
    {
        return true;
    }
}


function XPay_Windows10_Check()
{
    var returnmsg;
    if( XPay_win10_flag == true && XPay_edge_flag == true )
    {
        returnmsg = "사용중이신 EDGE 브라우저에서는 결제가 불가능합니다.\n Internet Explorer 11 를 이용해 주세요.\n";
        alert(returnmsg);
        return 0;
    }
    else
    {
        return -1;
    }
}

function onError(activex_id)
{
    if( xpay_browserChk() < 1 )
    {
        var blocking_msg = "해당 브라우저에서는 진행되지 않습니다.\nPC의 Internet Explorer 브라우저로 진행해 주세요.";
        if ( activex_id == 'XPayUpdater' )
        {
            alertViewCheck(blocking_msg);
        }
        return false;
    }

    if(activex_id == 'XPayUpdater')
    {
        lgdacom_atx_flag = false;
    }
    var attachElement = document.getElementById("LGD_ACTIVEX_DIV");
    if(attachElement == null)
    {
        attachElement = document.getElementsByTagName("form").item(0);
    }
    var lgdacom_onerr_body = document.getElementById("lgdacom_onerr_body");
    if(lgdacom_onerr_body == null)
    {
        lgdacom_onerr_body = document.createElement("div");
        lgdacom_onerr_body.setAttribute("id", "lgdacom_onerr_body");
        lgdacom_onerr_body.onclick = function ()
        {
            document.getElementById('lgdacom_onerr_body').style.display = 'none';
        }
        if(document.getElementById("LGD_ACTIVEX_DIV") == null)
        {
            lgdacom_onerr_body.style.cssText = "position:absolute; left:0px; top:0px; width:384px; height:240px; z-index:100; ";
        }
        else
        {
            lgdacom_onerr_body.style.cssText = "position:width:384px; height:240px; z-index:100; ";
        }
        if(isBrowserWin8StyleIE10()==true)
        {
            lgdacom_onerr_body.innerHTML = win8styleie10installinfo;
        }
        else
        {
            lgdacom_onerr_body.innerHTML = installinfo;
        }
        attachElement.appendChild(lgdacom_onerr_body);
    }
}

function doUpdate()
{
    if(XPayUpdater.object != null)
    {
        /* sangman.park Removed 2013.01.07
         if (vista_flag)
         {
         XPayUpdater.SetListFileURL('http://pgdownload.uplus.co.kr/lgdacom/updatelist_vista.txt');
         XPayUpdater.SetDataFileURL('http://pgdownload.uplus.co.kr/lgdacom/files_vista/');
         }
         else
         {
         XPayUpdater.SetListFileURL('http://pgdownload.uplus.co.kr/lgdacom/updatelist.txt');
         XPayUpdater.SetDataFileURL('http://pgdownload.uplus.co.kr/lgdacom/files/');
         }
         */
        /* sangman.park Added 2013.01.07 */
        XPayUpdater.SetListFileURL('http://pgdownload.uplus.co.kr/lguplus/XPayUpdateAX_3.0.0.3.ver');
        XPayUpdater.SetDataFileURL('http://pgdownload.uplus.co.kr/lguplus/');
        var result = XPayUpdater.DoUpdate();
        if (result == 0 && document.getElementById('dpop') == null )
        {
            dpopobject = document.createElement("object");
            dpopobject.setAttribute("id", "dpop");
            dpopobject.setAttribute("classid", "CLSID:CBE25D2B-A3CE-4170-8043-3214736DDD89");
            dpopobject.setAttribute("width", "0");
            dpopobject.setAttribute("height", "0");
            dpopobject.setAttribute("style", "display:none");
            document.getElementById("dop_parent").appendChild(dpopobject);
            document.getElementById("dpop").style.display = "none";
        }
    }
    else
    {
        onError('XPayUpdater');
    }
}

/* sangman.park Removed 2013.01.07
 document.write("<OBJECT id='XPayUpdater' onerror=onError('XPayUpdater') classid='CLSID:9963FACF-7618-417B-B6DD-AB8B65AF8CD1' style='display:none' width='0' height='0' codebase='"+mprotocol+"//pgdownload.uplus.co.kr/lgdacom/LGDacomXPayUpdater.cab#version=1,0,0,20' ></OBJECT>");
 document.write("<div id='dop_parent'></div>");*/
/* sangman.park Added 2013.01.07 */
document.write("<OBJECT id='XPayUpdater' onerror=onError('XPayUpdater') classid='CLSID:E42F7FEB-DE20-43F4-A342-47F1DA77F667' style='display:none' width='0' height='0' codebase='"+mprotocol+"//pgdownload.uplus.co.kr/lguplus/XPayPlugin_3.0.0.3.cab#version=3,0,0,3' ></OBJECT>");
document.write("<div id='dop_parent'></div>");


/*
 * ActiveX버전 설치 체크 및 기본값 셋팅
 */
function xpay_check(formid, service_type, title)
{


    if(service_type != null && service_type == 'test') //if it is test, use the ssl.
    {
        https_flag = true;
    }
    doUpdate();
    var serializer = new Form_DR.Serializer(formid);
    var sendData = serializer.queryString();
    sendData = sendData + "&LGD_CUSTOM_RESULTTYPE=ACTIVEX&LGD_ENCODING=UTF-8";
    var res = "00";

    if ( XPay_Windows10_Check() == 0 ) { 	return "11"; 	}

    plugin =document.getElementById("dpop");
    if(plugin ==null || typeof(plugin) == "undefined" || plugin.object==null)
    {
        onError('dpop');
        return "11";
    }
    else
    {
        dpop.setViewsize(650,650);
        if(title != null)
        {
            dpop.setTitle(title);
        }
        else
        {
            dpop.setTitle("LG U+ 전자결제 서비스");
        }
        var paywindowtype = document.getElementById("LGD_PAYWINDOWTYPE");
        if(paywindowtype != null)
        {
            if(paywindowtype.value == 'CardBillingAuth')
            {
                dpop.setViewsize(380,480);
            }
            else if(paywindowtype.value == 'CUPS')
            {
                dpop.setViewsize(800,700);
                dpop.setTitle("LG U+ China UnionPay");
            }
            else if(paywindowtype.value == 'AEGIS')
            {
                dpop.setViewsize(353,325);
                dpop.setTitle("올더게이트");
            }
        }
        var customskin    = document.getElementById("LGD_CUSTOM_SKIN");
        if(customskin != null && customskin.value.toLowerCase() == 'naveropen')
        {
            dpop.setViewsize(520,540);
            dpop.setTitle("NHN 체크아웃");
        }
        if(customskin != null && customskin.value.toLowerCase() == 'coupang')
        {
            dpop.setViewsize(490,520);
            dpop.setTitle("LG U+ 전자결제 서비스");
        }
        sendData = sendData + "&LGD_UB_AGENT=n1_xpay_utf-8-" + navigator.userAgent;

        var domain_url = '';
        var lgd_domain_url =  document.getElementById("LGD_DOMAIN_URL");
        if( lgd_domain_url != null && lgd_domain_url.value != "" )
        {
            var url =  lgd_domain_url.value;
            domain_url =  url + ".uplus.co.kr/xpay/Request.do";
        }
        else
        {
            domain_url = "xpay.lgdacom.net/xpay/Request.do";
        }

        if(service_type == "test")
        {
            if(https_flag)
            {
                dpop.param(sendData ,'https://xpay.lgdacom.net:7443/xpay/Request.do' );
            }
            else
            {
                dpop.param(sendData ,'http://xpay.lgdacom.net:7080/xpay/Request.do' );
            }
        }
        else if (service_type == "dvlp")
        {
            if(https_flag)
            {
                dpop.param(sendData ,'https://pg2.lgdacom.net:7443/xpay/Request.do' );
            }
            else
            {
                dpop.param(sendData ,'http://pg2.lgdacom.net:7080/xpay/Request.do' );
            }
        }
        else
        {
            if(https_flag)
            {
                dpop.param(sendData ,'https://'+domain_url);
            }
            else
            {
                dpop.param(sendData ,'http://'+domain_url);
            }
        }
    }
    return res;
}

function xpay_browserChk()
{
    var agt = navigator.userAgent.toUpperCase();

    var IE_IS_NOT = 0;
    var IE_OK = 1;

    if( agt == null || typeof(agt) == "UNDEFINED"  )
        return IE_OK;

    if( agt.indexOf("MSIE") != -1 || agt.indexOf("TRIDENT") != -1  )
        return IE_OK;
    else
        return IE_IS_NOT;
}
function alertViewCheck(message)
{
    if(null != document.getElementById("LGD_DISPLAY_ALERT") && "N" == document.getElementById("LGD_DISPLAY_ALERT").value ){
        return ;
    } else{
        alert(message);
    }

}
/*
 * Form Serializer
 */
if ( typeof Form_DR == "undefined" )
{
    Form_DR = {};
}
Form_DR.Serializer = function (name)
{
    return this._initialize(name);
};
Form_DR.Serializer.VERSION = "0.14";
Form_DR.Serializer.ElementTypes = [ "input", "textarea", "select" ];
Form_DR.Serializer.prototype._initialize = function (form)
{
    if ( typeof form == "object" )
    {
        this.form = form;
        return;
    }
    this.form = document.getElementById(form);
    if ( ! this.form )
    {
        for ( var i = 0; i < document.forms.length; i++ )
        {
            if ( document.forms[i].name == form )
            {
                this.form = document.forms[i];
                break;
            }
        }
    }
    if ( ! this.form )
    {
        throw new Error( "Cannot find a form with the name or id '" + name + "'" );
    }
};

Form_DR.Serializer.prototype.pairsArray = function ()
{
    var pairs = new Array;
    for ( var i = 0; i < Form_DR.Serializer.ElementTypes.length; i++ )
    {
        var type = Form_DR.Serializer.ElementTypes[i];
        var elements = this.form.getElementsByTagName(type);
        for ( var j = 0; j < elements.length; j++ )
        {
            var p = eval( "this._serialize_" + type + "(elements[j])" );
            if (p)
            {
                for ( var k = 0; k < p.length; k++ )
                {
                    pairs.push(p[k]);
                }
            }
        }
    }
    return pairs;
}

Form_DR.Serializer.prototype._serialize_input = function (elt)
{
    switch (elt.type.toLowerCase())
    {
        case "hidden":
        case "password":
        case "text":
            return this._simple(elt);
        case "checkbox":
        case "radio":
            return this._simple_if_checked(elt);
        default:
            return false;
    }
}

Form_DR.Serializer.prototype._simple = function (elt)
{
    return [[elt.name,elt.value]];
}

Form_DR.Serializer.prototype._simple_if_checked = function (elt)
{
    if (!elt.checked)
    {
        return;
    }
    return this._simple(elt);
}

Form_DR.Serializer.prototype._serialize_textarea = function (elt)
{
    return this._simple(elt);
}

Form_DR.Serializer.prototype._serialize_select = function (elt)
{
    var options = elt.options;
    var serialized = new Array;
    for ( var i = 0; i < options.length; i++ )
    {
        if (options[i].selected)
        {
            serialized.push([elt.name,options[i].value]);
        }
    }
    return serialized;
}

Form_DR.Serializer.prototype.queryString = function ()
{
    var pairs = this.pairsArray();
    var queryPairs = new Array;
    for ( var i = 0; i < pairs.length; i++ )
    {
        queryPairs.push(encodeURIComponent(pairs[i][0]) + "=" +  encodeURIComponent(pairs[i][1]));
    }
    var sep = arguments.length ? arguments[0] : "&";
    return queryPairs.join(sep);
}

Form_DR.Serializer.prototype.keyValues = function (forceArray)
{
    var pairs = this.pairsArray();
    var named = {};
    for ( var i = 0; i < pairs.length; i++ )
    {
        var k = pairs[i][0];
        var v = pairs[i][1];
        if ( named[k] )
        {
            if(typeof named[k] == 'object')
            {
                named[k].push(v);
            }
            else
            {
                named[k] = [named[k],v];
            }
        }
        else
        {
            if (forceArray)
            {
                named[k] = [v];
            }
            else
            {
                named[k] = v;
            }
        }
    }
    return named;
}
