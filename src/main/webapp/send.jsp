<%--
  Created by IntelliJ IDEA.
  User: wangrun
  Date: 2018/5/15
  Time: 上午12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body style="margin:30px">

<div class="container">
    <div class="row">
        <div class="col-xs-3">
            <nav class="navbar navbar-inverse">
                <div class="navbar-collapse">
                    <ul id="myTab" class="nav nav-pills nav-stacked">

                        <li class="active"><a>本人邮箱</a></li>
                        <li ><a href="#send">发件箱</a></li>
                        <li><a href="#receive">收件箱</a></li>
                        <li><a href="#drafts">草稿箱</a></li>
                    </ul>
                </div>
            </nav>

        </div>
        <div class="col-md-9">
            <div class="tab-content">
                <div class="tab-pane active" id="send">
                    <form>
                        <div class="form-group">
                            <label>收件人</label>
                            <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email">
                        </div>
                        <div class="form-group">
                            <label>主题</label>
                            <input type="text" class="form-control" id="exampleInputName2" placeholder="Title">
                        </div>
                        <div class="form-group">
                            <label>正文</label>
                            <textarea class="form-control" rows="20"></textarea>
                        </div>
                        <button type="button" class="btn btn-success">发送</button>
                        <button type="button" class="btn btn-info"> 保存</button>

                    </form>
                </div>
                <div class="tab-pane fade" id="receive">
                    <div class="form-group">
                        <label>收件人</label>
                        <textarea class="form-control" rows="25" placeholder="正文内容"></textarea>
                    </div>
                </div>
                <div class="tab-pane fade" id="drafts">
                    <div class="form-group">
                        <label>草稿箱正文</label>
                        <textarea class="form-control" rows="25" placeholder="正文内容"></textarea>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script>
    $(function () {
        $('#myTab a:last').tab('show');//初始化显示哪个tab
        $('#myTab a').click(function (e) {
            e.preventDefault();//阻止a链接的跳转行为
            $(this).tab('show');//显示当前选中的链接及关联的content
        })
    })
</script>


