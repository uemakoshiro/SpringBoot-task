<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品登録</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>

  <div class="header">
    <h1 class="site_logo"><a href="back">商品管理システム</a></h1>
    <div class="user">
      <p class="user_name">${ userInfo.getName() }さん、こんにちは</p>
      <form class="logout_form" action="logout" method="get">
        <button class="logout_btn" type="submit">
          <img src="images/ドアアイコン.png"/>ログアウト</button>
      </form>
    </div>
  </div>

  <hr>
   
  <div class="insert">
    <div class="discription">
      <p>
        登録情報を入力してください（<span class="required"></span>は必須です）
      </p>
    </div>
  
    <div class="form_body">
      <p class="error">${ msg }</p>
  
      <form:form action="insertexe" modelAttribute="insert" method="post">
        <fieldset class="label-130">
          <div>
            <label class="required">商品ID</label>
            <form:input type="text" path="productId" class="base-text"/>
            <span class="error"><form:errors path="productId" cssStyle="color: red"/></span>
          </div>
          <div>
            <label class="required">商品名</label>
            <form:input type="text" path="productName" class="base-text"/>
            <span class="error"><form:errors path="productName" cssStyle="color: red"/></span>
          </div>
          <div>
            <label class="required">単価</label>
            <form:input type="text" path="price" class="base-text"/>
            <span class="error"><form:errors path="price" cssStyle="color: red"/></span>
          </div>
          <div class="select_block">
            <label class="required">カテゴリ</label>
            <form:select path="category" class="base-text">
	            
	              <form:options items="${CategoryList}" itemLabel="name" itemValue="id" />
	            
            </form:select>
          </div>
          <div>
            <label>商品説明</label>
            <form:textarea path="description" class="base-text"></form:textarea>
          </div>
          <div>
            <label>画像</label>
            <form:input type="file" path="imagePath"/>
            <span class="error"><!-- エラーメッセージ --></span>
          </div>
        </fieldset>
        <div class="btns">
          <button type="button" onclick="openModal()" class="basic_btn">登録</button>
          <input type="button" onclick="location.href='./back'" value="戻る" class="cancel_btn"/>
        </div>
        <div id="modal">
          <p class="modal_message">登録しますか？</p>
          <div class="btns">
            <button type="submit" class="basic_btn">登録</button>
            <button type="button" onclick="closeModal()" class="cancel_btn">キャンセル</button>
          </div>
        </div>
      </form:form>
    </div>
  </div>
  <div id="fadeLayer"></div> 
</body>
</html>
<script src="./js/commons.js"></script>