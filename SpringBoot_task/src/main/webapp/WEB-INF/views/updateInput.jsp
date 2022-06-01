<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
  <div class="header">
    <h1 class="site_logo"><a href="MenuServlet">商品管理システム</a></h1>
    <div class="user">
      <p class="user_name">${ userInfo.getName() }さん、こんにちは</p>
      <form class="logout_form" action="logout.jsp" method="get">
        <button class="logout_btn" type="submit">
          <img src="images/ドアアイコン.png">ログアウト</button>
      </form>
    </div>
  </div>

  <hr>

  <div class="insert">
    <div class="form_body">
      <p class="error">${ error }</p>
	
      <form:form action="updateexe" modelAttribute="update" method="post">
        <fieldset class="label-130">
          <div>
            <label>商品ID</label>
            <input type="text" path="productId" value=${ data.getProductId() } class="base-text">
            <span class="error"><form:errors path="productId" cssStyle="color: red"/></span>
          </div>
          <div>
            <label>商品名</label>
            <input type="text" path="productName" value=${ data.getName() } class="base-text">
            <span class="error"><form:errors path="productName" cssStyle="color: red"/></span>
          </div>
          <div>
            <label>単価</label>
            <form:input type="text" path="price" value="a" class="base-text"/>
            <span class="error"><form:errors path="price" cssStyle="color: red"/></span>
          </div>
          <div>
            <label>カテゴリ</label> <form:select path="category" class="base-text">
              <option value="1" <c:if test="${ data.getCategoryId() eq '1' }">selected</c:if>>筆記具</option>
              <option value="2" <c:if test="${ data.getCategoryId() eq '2' }">selected</c:if>>オフィス機器</option>
              <option value="3" <c:if test="${ data.getCategoryId() eq '3' }">selected</c:if>>事務消耗品</option>
              <option value="4" <c:if test="${ data.getCategoryId() eq '4' }">selected</c:if>>紙製品</option>
              <option value="5" <c:if test="${ data.getCategoryId() eq '5' }">selected</c:if>>雑貨</option>
            </form:select>
          </div>
          <div>
            <label>商品説明</label>
            <form:textarea path="description" class="base-text"></form:textarea>
          </div>
          <div>
            <label>画像</label>
            <form:input type="file" path="img"/>
            <span class="error">エラーメッセージ</span>
          </div>
        </fieldset>
          <div class="btns">
            <button type="button" onclick="openModal()" class="basic_btn">更新</button>
            <input type="button" onclick="location.href='./back'" value="メニューに戻る" class="cancel_btn">
          </div>
          <div id="modal">
            <p class="modal_message">更新しますか？</p>
            <div class="btns">
              <button type="submit" class="basic_btn">更新</button>
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