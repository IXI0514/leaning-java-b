<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加人员信息</title>
<script type="text/javascript">
	alert("添加员工！！");
	function checkpost() {
		var ename= document.getElementById("nameid").value;
		var ejob= document.getElementById("jobid").value;
		var ehiredate= document.getElementById("dateid").value;
		var esal= document.getElementById("salid").value;
		var reg=/^[A-Z][a-z]{2,10}$/;
		var a=reg.test(ename);
		//var b=ejob.test(/^[\u4e00-\u9fa5]{1,8}[\u4e00-\u9fa5]$/);
		reg=/^\d{4}-\d{1,2}-\d{1,2}/;
		var c=reg.test(ehiredate);
		reg=/^\d\d{2,7}$/;
		var d=reg.test(esal);
		if(a&&c&&d){
			return true;
		}else{
			alert("请正确输入！！！");
			return false;
		}
	}

</script>
</head>
<body>

	<div>
		<form action="../add" method="post" onsubmit="return checkpost()">
			<table>
				<caption>添加人员信息</caption>
				<tr>
					<td>姓名</td>
					<td><input type="text" name="ename"  id="nameid"/></td>
				</tr>
				<tr>
					<td>工作</td>
					<td><input type="text" name="ejob" id="jobid" /></td>
				</tr>
				<tr>
					<td>入职时间</td>
					<td><input type="text" name="ehiredate"  id="dateid" placeholder="yyyy-mm-dd" /></td>
				</tr>
				<tr>
					<td>工资</td>
					<td><input type="text" name="esal"  id="salid" /></td>
				</tr>
				<tr>
					<td colspan="2"> <button type="submit">提交</button> </td>
				</tr>
			
			</table>
		</form>
	</div>
</body>
</html>