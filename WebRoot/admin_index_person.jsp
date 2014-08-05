<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="admin.bean.*" %>
<%@ page import="admin.dao.imp.*" %>
<jsp:useBean id="item" class="admin.bean.Item" scope="page" />
<jsp:useBean id="itemDaoImp" class="admin.dao.imp.ItemDaoImp" scope="page" />
<jsp:useBean id="teamDaoImp" class="admin.dao.imp.TeamDaoImp" scope="page" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta charset="utf-8">
    <title>轻院报名管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="./css/bootstrap.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
      .sidebar-nav {
        padding: 9px 0;
      }

      @media (max-width: 980px) {
        /* Enable use of floated navbar text */
        .navbar-text.pull-right {
          float: none;
          padding-left: 5px;
          padding-right: 5px;
        }
      }
    </style>
    <!-- <link href="./css/bootstrap-responsive.css" rel="stylesheet"> -->

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="./js/html5shiv.js"></script>
    <![endif]-->
  </head>
  
  <body>
  <%  
	request.setCharacterEncoding("gb2312");
	if(session.getAttribute("managerName")!=null){
	  
  %>
  <%!
  	int pageCount;    	//总页数
  	int currentPage;    //当前页数
  	int size=3;    		//每页记录数
 	//int result_count;   //总记录数,
 	int begin;    		//当前页第一条记录索引号
 	ResultSet rs=null;	//结果集 
 	String pageChildren="enventApprove";
  %>
  <div class="navbar navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <a class="brand" href="#"><img src="./img/enroll-system-logo.png" width="204px"></a>
          <div class="nav-collapse collapse">
            <ul class="nav pull-right">
              <li><a href="./admin_event_enrolling.html">报名中的活动</a></li>
              <li><a href="./admin_event_enrolled.html">已截至的活动</a></li>
              <li><a href="./admin_event_denied.html">未通过审核的活动</a></li>
              <li><a href="#search" role="button" data-toggle="modal">高级查询</a></li>
              <div class="btn-group">
                <a href="./admin_index.html" class="btn btn-primary" role="button">管理中心</a>
                <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                  <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                  <li><a tabindex="-1" href="index.html">退出</a></li>
                </ul>
              </div>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <br>
    <div class="container">
      <div class="tabbable tabs-left">
        <ul id="myTab" class="nav nav-tabs">
          <li><a href="#new-account" >新建单位账号</a></li>
          <li ><a href="#event-approve?pageChildren=enventApprove" >活动审批</a></li>
          <li><a href="admin_group.html" >查看单位用户</a></li>
          <li class="active"><a href="adminEventDetail?pageChildren=personUser" >查看个人用户</a></li>
        </ul>
        <div id="myTabContent" class="tab-content">
          <div class="tab-pane fade" id="new-account">
            <form>
              单位名称：<input type="text" class="input-large" placeholder="请输入单位名称"><br>
              负&nbsp;责&nbsp;人&nbsp;：&nbsp;<input type="text" class="input-large" placeholder="请输入单位名称"><br>
              地&nbsp;&nbsp;&nbsp;址&nbsp;&nbsp;&nbsp;：&nbsp;<input type="text" class="input-large" placeholder="请输入单位地址"><br>
              联系电话：<input type="text" class="input-large" placeholder="请输入联系电话"><br>
              电子邮箱：<input type="text" class="input-large" placeholder="请输入电子邮箱"><br>
              <hr>
              账&nbsp;&nbsp;&nbsp;号&nbsp;&nbsp;&nbsp;：&nbsp;<input type="text" class="input-large" placeholder="请输入账号"><br>
              密&nbsp;&nbsp;&nbsp;码&nbsp;&nbsp;&nbsp;：&nbsp;<input type="password" class="input-large" placeholder="请输入密码"><br>
              确认密码：<input type="password" class="input-large" placeholder="请重复输入密码"><br>
              <button class="btn btn-primary" aria-hidden="true" submit="submit">创建账号</button>
            </form>
          </div>

<!-- 查看个人用户 -->
          <div class="tab-pane fade in active" id="person-user">
            <form>
              用户姓名：<input type="text" class="input-large" placeholder="请输入用户姓名"><br>
              用户性别：<label class="radio inline">
                      <input type="radio" name="gender" id="optionsRadios1" value="male" checked>
                      男
                      </label>&nbsp;&nbsp;&nbsp;
                      <label class="radio inline">
                      <input type="radio" name="gender" id="optionsRadios2" value="female">
                      女
                      </label><br>
              用户身份：<label class="radio inline">
                      <input type="radio" name="identity" id="optionsRadios1" value="student" checked>
                      学生
                      </label>
                      <label class="radio inline">
                      <input type="radio" name="identity" id="optionsRadios2" value="teacher">
                      教师
                      </label><br>
              所属院系：<select class="span3">
                        <option>国际教育学院</option>
                        <option>软件学院</option>
                        <option>化工学院</option>
                        <option>艺术教育学院</option>
                        <option>机电学院</option>
                        <option>......</option>
                      </select><br>
              <button class="btn btn-primary">查询</button>
            </form>
            <table class="table table-hover ">
              <thead>
              <tr>
                <th>序号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>职位</th>
                <th>院系</th>
              </tr>
              </thead>
              <tr>
                <td>1</td>
                <td><a href="admin_person.html">张三</a></td>
                <td>男</td>
                <td>学生</td>
                <td>国际教育学院</td>
              </tr>
              <tr>
                <td>2</td>
                <td><a href="admin_person.html">张三</a></td>
                <td>男</td>
                <td>学生</td>
                <td>国际教育学院</td>
              </tr>
              <tr>
                <td>3</td>
                <td><a href="admin_person.html">张三</a></td>
                <td>男</td>
                <td>学生</td>
                <td>国际教育学院</td>
              </tr>
              <tr>
                <td>4</td>
                <td><a href="admin_person.html">张三</a></td>
                <td>男</td>
                <td>学生</td>
                <td>国际教育学院</td>
              </tr>
              <tr>
                <td>5</td>
                <td><a href="admin_person.html">张三</a></td>
                <td>男</td>
                <td>学生</td>
                <td>国际教育学院</td>
              </tr>
              <tr>
                <td>6</td>
                <td><a href="admin_person.html">张三</a></td>
                <td>男</td>
                <td>学生</td>
                <td>国际教育学院</td>
              </tr>
              <tr>
                <td>7</td>
                <td><a href="admin_person.html">张三</a></td>
                <td>男</td>
                <td>学生</td>
                <td>国际教育学院</td>
              </tr>
              <tr>
                <td>8</td>
                <td><a href="admin_person.html">张三</a></td>
                <td>男</td>
                <td>学生</td>
                <td>国际教育学院</td>
              </tr>
            </table>
            <div class="pagination pagination-centered">
              <ul>
                <li class="disabled"><a href="#">&laquo;</a></li>
                <li class="active"><span>1</span></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li><a href="#">&raquo;</a></li>
              </ul>
            </div> 
          </div><!-- 个人用户结束 -->
          
        </div>
      </div>
      <hr>

      <footer>
        <div class="row">
          <div class="span3">
            <h5>相关网站</h5>
            <p><a href="http://www.zzuli.edu.cn">郑州轻工业学院</a></p>
            <p><a href="http://my.zzuli.edu.cn/">信息门户</a></p>
            <p><a href="http://metc.zzuli.edu.cn">现代教育技术中心</a></p>
          </div>
          <div class="span3">
            <h5>校内链接</h5>
            <p><a href="http://lib.zzuli.edu.cn">图书馆网站</a></p>
            <p><a href="http://jwc.zzuli.edu.cn">教务处网站</a></p>
            <p><a href="http://source.zzuli.edu.cn/navigate.aspx">资源门户</a></p>
            <p><a href="http://gyws.zzuli.edu.cn/">光音网视</a></p>
          </div>
          <div class="span3">
            <h5>系统管理</h5>
            <a href="./admin.html">管理入口</a>
          </div>
          <div class="span3">
            <h5>联系我们</h5>
            <p>现代教育技术中心</p>
            <p>大学生IT创新工作室</p>
            <p>东风校区：教二楼501</p>
            <p>科学校区：电教楼522</p>
            <a href="mailto:itstudiozzuli@gmail.com">发送邮件</a><br>
          </div>
        </div>
      </footer>
    </div> <!-- /container -->

    <div id="search" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="advanced search" aria-hidden="true">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
        <h3>高级查询</h3>
      </div>
      <div class="modal-body">
        <form>
          关键字：<input type="text" class="input-large" placeholder="请输入关键字"><br>
          从&nbsp;<input type="text" class="input-small" placeholder="起始时间">&nbsp;
          到&nbsp;<input type="text" class="input-small" placeholder="结束时间">&nbsp;
          主办单位：
          <select class="span2">
            <option>教务处</option>
            <option>图书馆</option>
            <option>大学生IT创新工作室</option>
            <option>校团委</option>
            <option>艺术团</option>
          </select>
          <br><br>
          活动群体：
          <label class="checkbox inline">
            <input type="checkbox" id="inlineCheckbox1" value="option1">本科生
          </label>
          <label class="checkbox inline">
            <input type="checkbox" id="inlineCheckbox2" value="option2">研究生
          </label>
          <label class="checkbox inline">
            <input type="checkbox" id="inlineCheckbox3" value="option3">教职工
          </label>
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" aria-hidden="true" submit="submit">查询</button>
        </form>
        <button class="btn" data-dismiss="modal">取消</button>
      </div>
    </div>


    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="./js/jquery.js"></script>
    <script src="./js/bootstrap.js"></script>
  
    
    <% 
    }
    else{
    	response.setHeader("refresh","2;URL=admin_login.html");
		
	%>
   		
		两秒后跳转，如果没有跳转请点击<a href="admin_login.html">这里</a><br>
   	<%
    }
    %>
  </body>
</html>
