<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
<%@ page import="com.yunsi.jsapemp.bean.Emp" %>
<%@ page import="java.util.List" %>
<%@ page import="com.yunsi.jsapemp.bean.Page"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

	
	function seleAll(me){
		alert("SelectAll");
		var nodes = document.getElementsByName("echeck");
		for(var i=0;i<nodes.length;i++){
			nodes[i].checked = me.checked;
		}
	}
	function deleitems() {

		var array=getchecked();
		if(array!=null&&array.length>0){
			var conf =confirm("确认删除！");
			var str="";
			if(conf){
				for (var i = 0; i < array.length; i++) {
					var node =array[i];
					console.log(node);
					
					str=str+node+"@"
				}	
				alert(str);
				document.getElementById("hempno").value=str;
				
				document.getElementById("myform").submit();
			}
			
		}else{
			alert("没有选择！！");
		}	 
	}
	function getchecked(){
		var nodes = document.getElementsByName("echeck");
		var array = new Array();
		for(var i=0;i<nodes.length;i++){
			var node = nodes[i];
			if(node.checked){
				array.push(node.value);
			}
		}
		return array;
		
	}
</script>
</head>
<body>
	<!--获取list -->
	<%
		
		Page temp=(Page)session.getAttribute("page");
		List<Emp> list=temp.getList();
		int pnum= temp.getPage();
		int num=temp.getPagenumber();
		System.out.print("list:"+list);
			
		
	%>
	<form id="myform" action="dele" method="post">
	<input type="hidden" name="empno" id="hempno"  >
	<table>
		<tr>
			<td> <input type="checkbox" onclick="seleAll(this)"> 全选</td>
			<td>编号</td>
			<td>姓名</td>
			<td>工作</td>
			<td>入职时间</td>		
			<td>工资</td>		
			<td colspan="2">操作</td>		
		</tr>
	
	
	<%
		
		if(list!=null&list.size()>0){
			for (Emp p:list){
	%>
	<tr>
		<td><input type="checkbox" name="echeck" value="<%=p.getEmpno()%>"> </td>
		<td><%=p.getEmpno() %> </td>
		<td><%=p.getEname()%></td>
		<td><%=p.getJob()%></td>
		<td><%=p.getHiredate() %></td>
		<td><%=p.getSal()%></td>
		<td><a href="dele?empno=<%=p.getEmpno()%>">删除</a></td>
		<td><a href="update?empno=<%=p.getEmpno()%>">修改</a></td>
	</tr>
	
	<%		}	
		} 
	%>		
	<tr>
		<td colspan='7' align='center'>
			<a href="list?turnpage=firstpage">第一页</a>&nbsp;
			<a href="list?turnpage=back">上一页</a>&nbsp;
			<a href="list?turnpage=forward">下一页</a>&nbsp;
			<a href="list?turnpage=endpage">最后页</a>
			
		</td>
		<td><%=pnum+"/"+num %></td>
	</tr>
	<tr>
		<td colspan='8' align='right'>
			<a href="javascript:deleitems()">批量删除</a>&nbsp;
			<a href="jsp/add.jsp">添加员工</a>&nbsp;
		</td>
	</tr>
	</table>
	</form>
</body>
</html>