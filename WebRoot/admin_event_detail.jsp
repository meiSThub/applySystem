<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="admin.bean.*" %>
<jsp:useBean id="item" class="admin.bean.Item" scope="page" />
<html lang="zh">
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
	request.setCharacterEncoding("utf-8");
	if(session.getAttribute("managerName")!=null){
	  item=(Item)request.getAttribute("Item");
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
          <li><a href="#new-account" data-toggle="tab">新建单位账号</a></li>
          <li class="active"><a href="#event-approve" data-toggle="tab">活动审批</a></li>
          <li><a href="#group-user" data-toggle="tab">查看单位用户</a></li>
          <li><a href="#person-user" data-toggle="tab">查看个人用户</a></li>
        </ul>
        <div id="myContent" class="tab-content">
          <ul class="breadcrumb">
            <li><a href="#">主页</a> <span class="divider">/</span></li>
            <li><a href="#">科技处</a> <span class="divider">/</span></li>
            <li class="active">活动详情</li>
          </ul>
          <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
            <h3><%=item.getItemTitle() %>
              <small>
                <div class="span2 pull-right">
                  <button class="btn btn-success btn-block">通&nbsp;&nbsp;过</button>
                </div>
                <div class="span2 pull-right">
                  <button class="btn btn-danger btn-block">不通过</button>
                </div>
              </small>
            </h3>
          </a>
            <div class="row">
              <div class="span8">
                <h3>活动主题</h3>
                <p>Microscopic Imaging in Physics and Biomedicine</p>
                <h3>活动简介</h3>
                <p><%=item.getItemIntro() %></p>
                <h3>活动时间</h3>
                <p><%=item.getItemStartTime() %></p>
                <h3>活动地点</h3>
                <p><%=item.getItemAddress() %></p>
                <h3>报名人数上限</h3>
                <p><%=item.getItemApplyMax() %>人</p>
                <h3>参与对象</h3>
                 <%
                  if(item.isStaff()){
                 %>
                   <span class="users"> 教师</span>
                 <%
                   }else if(item.isStudent()){
                 %>
                   <span class="users">学生 </span>
                 <%
                   }else if(item.isStaff()&&item.isStudent()){
                 %>
                   <span class="users">学生 教师</span>
                 <%
                   }
                 %>
                <h3>注意事项</h3>
                <p><%=item.getAttentions() %></p>
                <h3>承办方</h3>
                <p><%=item.getItemOrganizer() %></p>
              </div>
              <div class="span2">
                <h4>联系电话</h4><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;12345678901</p>
                <h4>电子邮件</h4><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;kejichu@zzuli.edu.cn</p>
                <h4>联系地址</h4><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;行政楼xxx</p>
              </div>
            </div>
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
   		
		请先登录，两秒之后跳转，如果没有跳转请点击<a href="admin_login.html">这里</a><br>
   	<%
    }
    %>
  </body>
</html>
