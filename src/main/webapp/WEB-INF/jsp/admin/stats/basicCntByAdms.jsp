<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/env.jsp"%>
<html>
<head>
    <title></title>
</head>
<body>
<div class="content">
    <div class="con_tit">
        <h2><span>전형별 지원현황</span></h2>
        <div class="location"> HOME &gt; 통계 &gt; <span>전형별 지원현황</span> </div>

    </div>
    <div class="con_section">
        <div class="srch_box">
            <label for="admsNo"><strong>지원전형</strong></label>
            <td>
                <select id="admsNo" name="admsNo" class="ipt_slt1">
                    <option value="" label="--전체--" />
                    <c:forEach items="${admsList}" var="admsNo" varStatus="admsSatus">
                        <option value="${admsNo.admsNo}" label="${admsNo.admsName}" />
                    </c:forEach>
                </select>&nbsp;&nbsp;&nbsp;
            </td>

            <label for="applAttrCode"><strong>지원구분</strong></label>
            <select id="applAttrCode" name="applAttrCode" class="ipt_slt1">
                <option value="">-- 전체 --</option>
                <option value="00001">일반</option>
                <option value="00002">학연산</option>
                <option value="00003">위탁</option>
                <option value="00004">새터민</option>
            </select>&nbsp;&nbsp;&nbsp;

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

        <table summary="전형별 지원현황"  id="applicantCntTbl"  ></table>
        <div id="gridpager"></div>


    </div>
</div>
<!-- /con_section -->
</div>
<!-- /content -->

<content tag="local-script">
    <script>
  
    jQuery(document).ready( function(){
		jQuery("#applicantCntTbl").jqGrid({
			datatype: "json",
			height: 'auto',
            colNames:['전형','지원구분','캠퍼스','대학명', '학과명', '석사(명)','박사(명)','통합(명)','연구(명)','Total(명)'],
		   	colModel:[
                {name: 'admsName', index: 'admsName', align: "right", sortable:false, width:120},
                {name: 'applAttrName', index: 'applAttrName', align: "right", sortable:false, width:70},
                {name: 'campName', index: 'campName', align: "right", sortable:false, width:50},
                {name: 'collName', index: 'collName',  align: "center", sortable:false, width:100},
                {name: 'deptName', index: 'deptName', align: "center", sortable:false, width:100},
                {name: 'cnt1', index: 'cnt1', width:80, align: "right" ,sortable:false, width:70, summaryType: 'sum' },
                {name: 'cnt2', index: 'cnt2', width:80, align: "right" ,sortable:false, width:70, summaryType: 'sum'},
                {name: 'cnt3', index: 'cnt3', width:80, align: "right" ,sortable:false, width:70, summaryType: 'sum'},
                {name: 'cnt4', index: 'cnt4', width:80, align: "right" ,sortable:false, width:70, summaryType: 'sum'},
                {name: 'totalCnt', index: 'totalCnt', width:80, align: "right", sortable:false, width:85, summaryType: 'sum'}
		   	],

		    viewrecords: true,
	       	rowNum:10,
	       	rowList:[10,20,30]

		});
		
			
		var $source = jQuery('#searchBtn');
      	$source.on('click', function(event) {
      		event.preventDefault();
      		
			var newUrl ='${contextPath}/admin/stats/basicCntByAdms/search';
			newUrl = newUrl +"?admsNo="+jQuery("#admsNo option:selected").val();
			newUrl = newUrl +"&applAttrCode="+jQuery("#applAttrCode option:selected").val();
            newUrl = newUrl +"&campCode="+jQuery("#campCode option:selected").val();
			newUrl = newUrl +"&collCode="+jQuery("#collCode option:selected").val();
			jQuery("#applicantCntTbl").jqGrid('GridUnload'); 				
			jQuery("#applicantCntTbl").jqGrid({
				url : newUrl,
                datatype: "json",
                height: 'auto',
                colNames:['전형','지원구분','캠퍼스','대학명', '학과명', '석사(명)','박사(명)','통합(명)','연구(명)','Total(명)'],
                colModel:[
                    {name: 'admsName', index: 'admsName', align: "right", sortable:false, width:120},
                    {name: 'applAttrName', index: 'applAttrName', align: "right", sortable:false, width:70},
                    {name: 'campName', index: 'campName', align: "right", sortable:false, width:50},
                    {name: 'collName', index: 'collName',  align: "center", sortable:false, width:100},
                    {name: 'deptName', index: 'deptName', align: "center", sortable:false, width:100},
                    {name: 'cnt1', index: 'cnt1', width:80, align: "right" ,sortable:false, width:70, summaryType: 'sum' },
                    {name: 'cnt2', index: 'cnt2', width:80, align: "right" ,sortable:false, width:70, summaryType: 'sum'},
                    {name: 'cnt3', index: 'cnt3', width:80, align: "right" ,sortable:false, width:70, summaryType: 'sum'},
                    {name: 'cnt4', index: 'cnt4', width:80, align: "right" ,sortable:false, width:70, summaryType: 'sum'},
                    {name: 'totalCnt', index: 'totalCnt', width:80, align: "right", sortable:false, width:85, summaryType: 'sum'}
                ],

                grouping:true,

                groupingView : {
                    groupField : [ 'admsName','applAttrName','campName'],
                    groupSummary: [true, true, true ],
                    groupText : ['<b> {0}</b>','<b> {0}</b>','<b> {0}</b>'],
                    groupColumnShow : [true, true, true],
                    hideFirstGroupCol: false,
                    groupSummaryPos: ['header', 'header', 'header'],
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
