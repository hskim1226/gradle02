<%@ page import="java.util.Calendar" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
</head>
<body>

<div class="content">
    <div class="con_tit">
        <h2><span>최근 1주일 지원현황</span></h2>
        <div class="location"> HOME &gt; 통계 &gt; <span>최근 1주일 지원현황</span> </div>

    </div>
    <div class="con_section">
        <div class="srch_box">
            <label for="admsNo"><strong>지원전형</strong></label>
            <td>
                <select id="admsNo" name="admsNo" class="ipt_slt1">
                    <option value="" label="--전체--" />
                    <c:forEach items="${admsList}" var="admsNo" varStatus="admsSatus">
                        <option value="${admsNo.admsNo}" label="${admsNo.admsNo}" />
                    </c:forEach>
                </select>&nbsp;&nbsp;&nbsp;
            </td>

            <label for="campCode"><strong>캠퍼스</strong></label>
            <select id="campCode" name="campCode" class="ipt_slt1">
                <option value="">-- 전체 --</option>
                <option value="10">서울</option>
                <option value="11">원주</option>
                <option value="12">국제</option>
            </select>&nbsp;&nbsp;&nbsp;

            <label for="collCode"><strong>대학</strong></label>
            <select id="collCode" name="collCode" class="ipt_slt1">
                <option value="" label="--전체--">  --전체--
                </option></select>
      <span class="btnBlueS">
          <input type="submit" value="검색" class="btnBox" id='searchBtn' />
          </span>
        </div>

        <table summary="최근 1주일 지원현황"  id="applicantCntTbl"  ></table>
        <div id="gridpager"></div>


    </div>
</div>
<!-- /con_section -->
</div>
<!-- /content -->



<%

    String cnt7 = null;
    java.util.Calendar cal = java.util.Calendar.getInstance();
    int year ;
    int month;
    int day ;
    int date ;
    String fullDate;
    String[] weekDay = {"일","월","화","수","목","금","토"};
    String[] headDay= {"","","","","","",""};
    for( int i=0 ; i < 7 ; i++){
        month = cal.get(java.util.Calendar.MONTH )+1;
        date = cal.get(Calendar.DAY_OF_WEEK);
        day = cal.get(java.util.Calendar.DATE);
        headDay[i] = month+"/"+day+" "+weekDay[date-1];
        cal.add(Calendar.DATE,-1);
    }

%>

<content tag="local-script">
    <script>
  
    jQuery(document).ready( function(){
		jQuery("#applicantCntTbl").jqGrid({
			datatype: "json",
			height: 'auto',			
		   	colNames:['캠퍼스','대학명', '학과명', '<%=headDay[6]%>','<%=headDay[5]%>','<%=headDay[4]%>','<%=headDay[3]%>','<%=headDay[2]%>','<%=headDay[1]%>','<%=headDay[0]%>','총원'],
		   	colModel:[
		   		{name: 'campName', index: 'campName', align: "right", sortable:false, width:100},
                {name: 'collName', index: 'collName',  align: "center", sortable:false, width:90},
                {name: 'deptName', index: 'deptName', align: "center", sortable:false, width:120},
                {name: 'cnt1', index: 'cnt1', width:80, align: "right" ,sortable:false, width:50, summaryType: 'sum' },
                {name: 'cnt2', index: 'cnt2', width:80, align: "right" ,sortable:false, width:50, summaryType: 'sum'},
                {name: 'cnt3', index: 'cnt3', width:80, align: "right" ,sortable:false, width:50, summaryType: 'sum'},
                {name: 'cnt4', index: 'cnt4', width:80, align: "right" ,sortable:false, width:50, summaryType: 'sum'},
                {name: 'cnt5', index: 'cnt5', width:80, align: "right" ,sortable:false, width:50, summaryType: 'sum'},
                {name: 'cnt6', index: 'cnt6', width:80, align: "right" ,sortable:false, width:50, summaryType: 'sum'},
                {name: 'cnt7', index: 'cnt7', width:80, align: "right" ,sortable:false, width:50, summaryType: 'sum'},
                {name: 'totalCnt', index: 'totalCnt', width:80, align: "right" ,sortable:false, width:50, summaryType: 'sum'}
		   	],

		    viewrecords: true,
	       	rowNum:10,
	       	rowList:[10,20,30]

		});
		
			
		var $source = jQuery('#searchBtn');
      	$source.on('click', function(event) {
      		event.preventDefault();
      		
			var newUrl ='${contextPath}/admin/stats/category/search';	 
			newUrl = newUrl +"?admsNo="+jQuery("#admsNo option:selected").val();
			newUrl = newUrl +"&campCode="+jQuery("#campCode option:selected").val();
			newUrl = newUrl +"&collCode="+jQuery("#collCode option:selected").val();
			jQuery("#applicantCntTbl").jqGrid('GridUnload'); 				
			jQuery("#applicantCntTbl").jqGrid({
				url : newUrl,
                datatype: "json",
                height: 'auto',
                colNames:['캠퍼스','대학명', '학과명', '<%=headDay[6]%>','<%=headDay[5]%>','<%=headDay[4]%>','<%=headDay[3]%>','<%=headDay[2]%>','<%=headDay[1]%>','<%=headDay[0]%>','총원'],
			   	colModel:[
                    {name: 'campName', index: 'campName', align: "right", sortable:false, width:100},
                    {name: 'collName', index: 'collName',  align: "center", sortable:false, width:90},
                    {name: 'deptName', index: 'deptName', align: "center", sortable:false, width:120},
                    {name: 'cnt1', index: 'cnt1', width:80, align: "right" ,sortable:false, width:50, summaryType: 'sum' },
                    {name: 'cnt2', index: 'cnt2', width:80, align: "right" ,sortable:false, width:50, summaryType: 'sum'},
                    {name: 'cnt3', index: 'cnt3', width:80, align: "right" ,sortable:false, width:50, summaryType: 'sum'},
                    {name: 'cnt4', index: 'cnt4', width:80, align: "right" ,sortable:false, width:50, summaryType: 'sum'},
                    {name: 'cnt5', index: 'cnt5', width:80, align: "right" ,sortable:false, width:50, summaryType: 'sum'},
                    {name: 'cnt6', index: 'cnt6', width:80, align: "right" ,sortable:false, width:50, summaryType: 'sum'},
                    {name: 'cnt7', index: 'cnt7', width:80, align: "right" ,sortable:false, width:50, summaryType: 'sum'},
                    {name: 'totalCnt', index: 'totalCnt', width:80, align: "right" ,sortable:false, width:50, summaryType: 'sum'}
			   				
			   	],
                grouping:true,

                groupingView : {
                    groupField : ['campName','collName'],
                    groupSummary: [true, true],
                    groupText : ['<b>{0}</b>','<b>{0}</b>'],
                    groupColumnShow : [true,  true],
                    hideFirstGroupCol: false,
                    groupSummaryPos: ['header',  'header'],
                    groupCollapse : true

                },
                viewrecords: true,

                rowNum:200
            });



        });
      	

        
		  function attachChangeEvent( sourceId, context ) {
		        var $source = jQuery('#' + sourceId);

		        $source.on('change', function(event) {
		            var info, targetId, valueKey, labelKey, url, clean, addon, i;
		            var baseUrl = '${contextPath}/common/code';
		            var val = this.options[this.selectedIndex].value;

		            info = context;
		            if (context.hasOwnProperty($source.val())) {
		                info = context[$source.val()];
		            }

		            targetId = info.targetId ? info.targetId : context.targetId;
		            if( !targetId ) {
		                return;
		            }

		            valueKey = info.valueKey ? info.valueKey : context.valueKey;
		            labelKey = info.labelKey ? info.labelKey : context.labelKey;
		            url = info.url ? info.url : context.url;
		            if( url && typeof url === 'function' ) {
		                baseUrl += url(val);
		            } else if( url ) {
		                baseUrl += url;
		            }

		            clean = info.clean ? info.clean : context.clean;
		            if (typeof clean === 'string') {
		                clean = [].concat( clean );
		            }
		            clean = [].concat( targetId, clean );
		            for (i = 0; i < clean.length; i++) {
		            	jQuery('#' + clean[i]).children('option').filter(function() {
		                    return this.value !== '';
		                }).remove();

		            	jQuery('#' + clean[i]).trigger('change');
		            }

		            jQuery.ajax({
		                type: 'GET',
		                url: baseUrl,
		                success: function(e) {
                            var ec = JSON && JSON.parse(e) || $.parseJSON(e);
		                    if(ec.result && ec.result === 'SUCCESS') {
		                        var $target = jQuery('#' + targetId);
		                        var data = JSON && JSON.parse(ec.data) || $.parseJSON(ec.data);
		                        jQuery(data).each(function (i, item) {
		                            var $op = jQuery('<option>').attr({
		                                'value': item[valueKey],
		                                'label': item[labelKey]}
		                            )
		                            for (var key in item) {
		                                if (key !== valueKey && key !== labelKey) {
		                                    $op.attr(key, item[key]);
		                                }
		                            }
		                            $op.appendTo($target);
		                        });
		                    }
		                },
		                error: function(e) {
		                    if(console) console.log(e);
		                }
		            });
		        });
		    }

		    <%-- 캠퍼스 변경 --%>
		    attachChangeEvent( 'campCode',
		            {
		                targetId: 'collCode',
		                valueKey: 'collCode',
		                labelKey: 'collName',
		                url: function(arg) {
		                    return '/college/' + arg;
		                }
		            }
		    );		    			
			
	
		});
    


  
    </script>
</content>
</body>
</html>
