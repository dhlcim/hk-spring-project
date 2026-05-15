<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>DB 연동</title>
<style>
    @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap');

    body {
        font-family: 'Noto Sans KR', sans-serif;
        text-align: center;
        padding-top: 80px;
        background: linear-gradient(135deg, #f0f4ff 0%, #e6eeff 100%);
        min-height: 100vh;
        margin: 0;
    }

    .box {
        border: none;
        display: inline-block;
        padding: 40px 50px;
        border-radius: 20px;
        background: white;
        box-shadow: 0 8px 32px rgba(0, 123, 255, 0.15);
        min-width: 360px;
    }

    .icon { font-size: 2.5rem; margin-bottom: 10px; }

    h2 {
        color: #007bff;
        font-size: 1.3rem;
        font-weight: 700;
        margin-bottom: 16px;
    }

    hr {
        border: none;
        border-top: 2px solid #e8eeff;
        margin-bottom: 20px;
    }

    .result-box {
        background: #f0f4ff;
        border-radius: 12px;
        padding: 16px 20px;
        margin-bottom: 24px;
        font-size: 15px;
        color: #1a1a1a;
        line-height: 1.8;
        border: 1px solid #b7c9ff;
    }

    .result-box strong { color: #007bff; }

    button {
        background: #007bff;
        color: white;
        border: none;
        padding: 10px 28px;
        border-radius: 50px;
        font-size: 14px;
        font-weight: 700;
        cursor: pointer;
        font-family: 'Noto Sans KR', sans-serif;
        transition: background 0.2s, transform 0.1s;
    }

    button:hover { background: #0056b3; transform: translateY(-1px); }
    button:active { transform: translateY(0); }
</style>
</head>
<body>
    <div class="box">
        <div class="icon">🏆</div>
        <h2>지역별 최대 판매량 조회</h2>
        <hr>
        <div class="result-box">
            <strong>결과:</strong> ${msg}
        </div>
        <button onclick="history.back()">뒤로가기</button>
    </div>
</body>
</html>