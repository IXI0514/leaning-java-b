<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Exam</title>
<script type="text/javascript">
	function setanswerspan(me) {
		var nodes=document.getElementsByName("checknode");
		for (var i=0;i<nodes.length;i++){
			nodes[i].checked=false;
		}
		me.checked=true;
		document.getElementById("answerspan").textContent=me.value;
	}


</script>
</head>
<body>
	<div>
		<table>
			<c:set var="pagenum" value="${pagebean.getCurrentPage()}"></c:set>
			
			<c:set var="pagequestion" value="${pagebean.getList().get(pagenum)}"></c:set>
			<tr>
				<td>题目:${pagenum+1 }</td>
				<td>${pagequestion.getQuestion()}</td>
			</tr>
			<tr>
				<td> <input type="checkbox"  name="checknode"  value="A" onclick="setanswerspan(this)"> </td>
				<td >${pagequestion.getOptiona()}</td>	
			</tr>
			<tr>
				<td> <input type="checkbox" name="checknode"   value="B" onclick="setanswerspan(this)"> </td>
				<td >${pagequestion.getOptionb()}</td>	
			</tr>
			
			<c:if test="${ pagequestion.getType()=='选择题'} ">
				<tr>
					<td> <input type="checkbox"  name="checknode"   value="C" onclick="setanswerspan(this)"> </td>
					<td >${pagequestion.getOptionc()}</td>	
				</tr>
				<tr>
					<td> <input type="checkbox"  name="checknode"   value="D" onclick="setanswerspan(this)"> </td>
					<td >${pagequestion.getOptiond()}</td>	
				</tr>
			</c:if>
			<tr><td colspan="2">答案：<span id="answerspan">&nbsp</span></td></tr>
			<tr>
				<td colspan="2">
					<c:if test="${ pagenum!=0}">
						<a href="../exam/goexam?turnpage=back">上一题</a>&nbsp
					</c:if>

					<c:if test="${pagenum!=pagebean.getTotalpage()-1}">
						<a href="../exam/goexam?turnpage=go">下一题</a>&nbsp
					</c:if>
						<a href="../exam/goexam?turnpage=submit">提交</a>
				<td>
			<tr>
		</table>
	</div>
</body>
</html>