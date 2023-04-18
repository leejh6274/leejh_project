DROP DATABASE IF EXISTS sbs_proj_2023;
CREATE DATABASE sbs_proj_2023;
USE sbs_proj_2023;

CREATE TABLE article (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    title CHAR(100) NOT NULL,
    `body` TEXT NOT NULL
    
);

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목1',
`body` = '내용1';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목2',
`body` = '내용2';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목3',
`body` = '내용3';

SELECT * FROM article;

#회원 테이블 생성
CREATE TABLE `member` (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    loginId CHAR(20) NOT NULL,
    loginPw CHAR(60) NOT NULL,
    `authLevel` SMALLINT(2) UNSIGNED DEFAULT 3 COMMENT '(3=일반, 7=관리자)',
    `name` CHAR(20) NOT NULL,
    `nickname` CHAR(20) NOT NULL,
    cellphoneNo CHAR(20) NOT NULL,
    email CHAR(50) NOT NULL,
    delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '(탈퇴여부)',
    delDate DATETIME COMMENT '탈퇴날짜'
);


#회원 테스트 데이터 생성
INSERT INTO MEMBER
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'admin',
loginPw = 'admin',
authLevel = 7,
`name` = '관리자',
nickname = '관리자',
cellphoneNo = '01011111111',
email = 'admin@gmail.com';

INSERT INTO MEMBER
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'user1',
loginPw = 'user1',
authLevel = 7,
`name` = '사용자1',
nickname = '사용자1',
cellphoneNo = '01022222222',
email = 'user1@gmail.com';

INSERT INTO MEMBER
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'user2',
loginPw = 'user2',
authLevel = 7,
`name` = '사용자2',
nickname = '사용자2',
cellphoneNo = '01033333333',
email = 'user2@gmail.com';

SELECT * FROM `member`;



