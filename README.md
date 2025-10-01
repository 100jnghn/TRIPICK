# TRIPICK

**🧳국내 관광지 리뷰 및 평점 공유 플랫폼**

사용자는 신뢰도 높은 실제 후기를 남길 수 있으며,  
다양한 검색 옵션(지역, 장소, 나이대)을 통해 자신의 취향에 완벽히 맞는 관광 정보를 얻을 수 있습니다.

<br><br>

## 👥 Team & Features
👦🏻 **백종훈** (팀장)  
  - 로그인 / 회원가입 / 인증관리  
  - 유저 정보 등록, 삭제, 수정
<br><br>

👱🏻 **정종원**  
- 평점 기반 관광지 조회
- 연령대 기반 관광지 조회
<br><br>

🧑🏻‍🦰 **최민혁**  
- csv 데이터 변환
- 리뷰 등록, 삭제, 수정
- 리뷰 장소, 작성자 기준 조회 + 세부 조회
<br><br>

👧🏻 **홍보경**
- 권역별 관광지 조회
- 지역별 관광지 조회

<br><br>

## ⚙️ 기술 스택
- **Language** : ![](https://img.shields.io/badge/Java-007396?style=plastic&logo=OpenJDK&logoColor=white) 
- **Database** : ![](https://img.shields.io/badge/MySQL-4479A1?style=plastic&logo=MySQL&logoColor=white) (JDBC 연동)
- **IDE** : ![](https://img.shields.io/badge/IntelliJ-000000?style=plastic&logo=intellijidea&logoColor=white) 
- **Structure**: MVC Pattern  
  - View: 사용자가 보는 화면. Controller에게 요청을 전달하고 응답을 받아 표시
  - Controller: 사용자 요청을 받아 Service 호출을 통해 처리된 결과를 View에 전달
  - Service: 데이터 가공, DAO호출을 수행. 트랜잭션을 관리
  - DAO: Database에 직접 접근해 query를 실행하고 결과를 반환
  - DTO: 계층 간 데이터 교환을 위한 Data Container

 <br><br>

## 🗂️프로젝트 구조

## 💾 테이블 설계

`USERS`
|  ATTRIBUTE  |  TYPE   |  NULL  |  DEFAULT VALUE    |  CONSTRAINT  |
| ----------- | ------- | ------ | ----------------- | ------------ |
| user_no     | int     | N      | auto_increment    | PK           |
| id          | varchar | N      |                   | UK           |
| pw          | varchar | N      |                   |              |
| nickname    | varchar | N      |                   | UK           |
| age         | int     | N      |                   |              |
| created_at  | date    | N      | current_timestamp |              |
| updated_at  | date    | Y      |                   |              |

<br>

`REVIEW`
|  ATTRIBUTE  |  TYPE   |  NULL  |  DEFAULT VALUE    |  CONSTRAINT  |
| ----------- | ------- | ------ | ----------------- | ------------ |
| review_no   | int     | N      | auto_increment    | PK           |
| user_no     | int     | N      |                   | FK           |
| travel_no   | int     | N      |                   | FK           |
| title       | varchar | N      |                   |              |
| content     | text    | N      |                   |              |
| rate        | float   | N      |                   |              |
| created_at  | date    | N      | current_timestamp |              |
| updated_at  | date    | Y      |                   |              |

<br>

`TRAVEL`
|  ATTRIBUTE  |  TYPE   |  NULL  |  DEFAULT VALUE    |  CONSTRAINT  |
| ----------- | ------- | ------ | ----------------- | ------------ |
| travel_no   | int     | N      | auto_increment    | PK           |
| district    | varchar | N      |                   | FK           |
| title       | varchar | N      |                   |              |
| description | text    | N      |                   |              |
| address     | varchar | Y      |                   |              |
| phone       | varchar | N      |                   |              |
| sum         | float   | Y      | 0                 |              |
| count       | int     | Y      | 0                 |              |

<br><br>

## 📹시연 영상

