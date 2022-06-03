<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー</title>
<link href="css/commons.css" rel="stylesheet">
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
  <div id="app">

    <div class="header">
      <h1 class="site_logo"><a href="back">商品管理システム</a></h1>
      <div class="user">
        <p class="user_name">${ userInfo.getName() }さん、こんにちは</p>
        <form class="logout_form" action="logout" method="get">
          <button class="logout_btn" type="submit">
            <img src="images/ドアアイコン.png">ログアウト</button>
        </form>
      </div>
    </div>

    <hr>
	<c:if test="${ userInfo.getRole() eq '1' }">
    <div class="btn"><a class="basic_btn regist" href="/insert">新規登録</a></div>
    </c:if>
    <p>${ msg }</p>
    <form method="get" action="/search">
    <div class="search_container">
      <input type="text" size="25" name="keyword" placeholder="キーワード検索">
      <input type="submit" value="&#xf002">
    </div>
    <div class="caption"><p>検索結果：${ SearchResult.size() }件</p></div>
        <div class="order">
          <select class="base-text" name="order">
            <option value="" selected>並び替え</option>
            <option value="product_id">商品ID</option>
            <option value="c.name">カテゴリ</option>
            <option value="price">単価：安い順</option>
            <option value="price DESC">単価：高い順</option>
            <option value="p.created_at">登録日：古い順</option>
            <option value="p.created_at DESC">登録日：新しい順</option>
          </select>
        </div>
    </form>
    
    
    <table>
		
        
      <thead>
        <tr>
          <th>商品ID</th>
          <th>商品名</th>
          <th>単価</th>
          <th>カテゴリ</th>
          <th>詳細</th>
        </tr>
      </thead>
      <tbody>
      <c:forEach var="data" items="${SearchResult}">
          <tr>
            <td>${ data.getProductId() }</td>
            <td>${ data.getName() }</td>
            <td>${ data.getPrice() }</td>
            <td>${ data.getCategory() }</td>
            <td><a class="detail_btn" href="./detail?id=${ data.getProductId() }">詳細</a></td>
          </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
  <footer></footer>

  
</body>
</html>