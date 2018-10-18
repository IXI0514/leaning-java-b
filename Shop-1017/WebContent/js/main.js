/**
 * 验证用户名密码是否为空
 */
function check() {
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	//alert("username:"+username +"\n" +"password:"+password);
	var str ="";
	if (username==null||username==""||password==null||password==""){
	    alert("用户名密码不能为空");
	    return false;
    }
    return true;
}

//全选方法
function checkAll(node) {
    var eles = document.getElementsByName("check");
    for (var a in eles){
        eles[a].checked = node.checked;
    }
}
//批量删除

function dele() {
    var array = checkedinput();
    var len =array.length;

    if(len==0){
        alert("未选择行！！");
    }else{
        var pid="";
        for (var i=0;i<len;i++){
        var tr = array[i];
            var v =tr.getElementsByTagName("td")[2].textContent;
            pid=pid+v+'@';
        }
        alert("已删除："+len+"行");
        console.log("已删除："+len+"行");
        var url = "DeleProduct?deleno="+pid;
        location.href=url;

    }

  /*  var tab = document.getElementById("carttable");
    var trs = tab.getElementsByTagName("tr");
    var dline= 0;
    pid='';
    for(var i=1;i<trs.length-1;i++){
        var tr = trs[i];
        //console.log(strs.length+":"+i);
        var input = tr.getElementsByTagName("input")[0];

        //console.log(input);
        if(input!=null&&input.checked){
                dline++;
                //i--;
                //tr.parentNode.removeChild(tr);
                var v =tr.getElementsByTagName("td")[2].textContent;
                pid=pid+v+'@';
        }
    }
      if(dline==0){
        alert("未选择行！！");
    }else{
        alert("已删除："+dline+"行");
        console.log("已删除："+dline+"行");
        var url = "DeleProduct?deleno="+pid;
        location.href=url;

    }*/
}
//删除本行
function deleme(me) {
    var tr = me.parentNode.parentNode;
    //var p = tr.parentNode;
    //p.removeChild(tr);
    var v =tr.getElementsByTagName("td")[2].textContent
    var url = "DeleProduct?deleno="+v;
    location.href=url;
}

//结算
function checktotal() {
    var array = checkedinput();
    var total = document.getElementById("total");
    var t=0.0;
    for (var i=0;i<array.length;i++){
        var tr = array[i];
        var price =tr.getElementsByTagName("td")[4].textContent;
        var num =tr.getElementsByTagName("td")[5].textContent;
        t+=price*num;
    }
    total.textContent = t+"￥";

 /*   var tab = document.getElementById("carttable");
    var trs = tab.getElementsByTagName("tr");
    var total = document.getElementById("total");
    console.log("total:"+total.textContent);
    var t =0.0;
    for(var i=1;i<trs.length-1;i++){
        var tr = trs[i];
        var input = tr.getElementsByTagName("input")[0];

        if(input!=null&&input.checked){
            var price =tr.getElementsByTagName("td")[4].textContent;
            var num =tr.getElementsByTagName("td")[5].textContent;
            //console.log("成绩"+price*num);
            t+=price*num;
        }
    }
    console.log("t:"+t);
    total.textContent = t+"￥";*/
}

//付款提交
function goCheckout() {
    var array = checkedinput();
    var total = document.getElementById("total");
    var dline=0;
    for (var i=0;i<array.length;i++){
        var tr = array[i];
        var v =tr.getElementsByTagName("td")[2].textContent;
        pid=pid+v+'@';
    }
}

//被选中

function checkedinput() {
    var array =new Array();
    var tab = document.getElementById("carttable");
    var trs = tab.getElementsByTagName("tr");
    for (var i = 1; i < trs.length - 1; i++) {
        var tr = trs[i];
        var input = tr.getElementsByTagName("input")[0];
        if (input != null && input.checked) {
            array.push(tr);
        }
    }

    return array;
}


