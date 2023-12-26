# 파일 확장자 차단

## 요구 사항

### 확장자 목록 조회

- 고정/커스텀 확장자 목록을 조회할 수 있어야 합니다.

### 고정 확장자 차단

- 고정 확장자를 차단할 수 있습니다.

### 고정 확장자 차단 해제

- 고정 확장자 차단을 해제할 수 있습니다.

### 커스텀 확장자 차단 (생성)

- 커스텀 확장자를 차단할 수 있습니다.

### 커스텀 확장자 차단 해제 (삭제)

- 커스텀 확장자를 차단을 해제할 수 있습니다.

## API 명세

### 확장자 목록 조회

```http request
GET /api/v1/file-extensions
```

> Response

| Name                         | Type    | Description |
|------------------------------|---------|-------------|
| fixidExtention.extensionId   | Number  | 확장자 ID      |
| fixidExtention.extension     | String  | 확장자         |
| fixidExtention.isBlocked     | Boolean | 차단 여부       |
| customExtensions.extensionId | Number  | 확장자 ID      |
| customExtensions.extension   | String  | 확장자         |
| customExtensions.isBlocked   | Boolean | 차단 여부       |

### 고정 확장자 차단

```http request
PATCH /api/v1/file-extensions/{{extensionId}}/fixed-block
```

### 고정 확장자 차단 해제

```http request
PATCH /api/v1/file-extensions/{{extensionId}}/fixed-unblock
```

### 커스텀 확장자 차단 (생성)

```http request
POST /api/v1/file-extensions/custom-block
```

> Request

| Name      | Type   | Description | Required |
|-----------|--------|-------------|----------|
| extension | String | 확장자         | Y        |

> Response

| Name        | Type   | Description |
|-------------|--------|-------------|
| extensionId | Number | 확장자 ID      |

### 커스텀 확장자 차단 해제 (삭제)

```http request
DELETE /api/v1/file-extensions/{{extensionId}}/custom-unblock
```

## 에러 코드

| API             | Code | Message                       | Description     |
|-----------------|------|-------------------------------|-----------------|
| 고정 확장자 차단       | 404  | NotFoundException             | 확장자가 존재하지 않습니다. |
| 커스텀 확장자 차단 (생성) | 400  | InvalidInputException         | 확장자가 올바르지 않습니다. |
|                 | 409  | ExtensionDuplicationException | 확장자가 이미 존재합니다.  |

## 프로젝트 구조

외부 변경에 유연하게 대처하기 위해 멀티 모듈 구성의 헥사고날 아키텍처를 적용하였습니다.
외부 요소에 의존하지 않고 도메인 계층에 집중할 수 있도록 하였습니다.

### 계층 구조

- Application
    - 비즈니스 로직과 유즈케이스를 구현하는 계층입니다.
    - 도메인 모델과 상호작용하며 핵심 비즈니스 규칙을 수행합니다.
- Domain
    - 핵심 도메인 모델과 비즈니스 규칙을 정의하는 계층입니다.
    - 소프트웨어의 핵심 로직이 위치합니다.
- infrastructure
    - 외부 시스템과의 통합, 데이터베이스 접근 등을 담당하는 계층입니다.
    - Application 계층의 요청에 따라 외부와의 연동을 처리합니다.
- Presentation
    - 사용자와의 상호작용을 담당하는 계층입니다.
    - 사용자의 요청을 처리하고 응답을 반환하는 역할을 수행합니다.
- Bootstrap
    - 애플리케이션의 초기화와 설정을 담당하며, 프로젝트의 시작점입니다.
    - 의존성 주입, 환경 설정 등을 처리하여 전체 애플리케이션을 설정합니다.

## 개발 환경

- Java 17
- Spring Boot 3.2.1
- Gradle
- JPA
- H2
- Thymeleaf
- JUnit5

## 빌드 및 실행

### 빌드

```shell
./gradlew clean build
```

### 실행

```shell
./gradlew :fileshield-bootstrap:bootRun
```

브라우저에서 http://localhost:8080/home 로 접속합니다.

## 추가 구현 사항

### 확장자 중복 체크
  - 확장자가 이미 존재하는 경우 예외를 발생시킵니다.
### 확장자 유효성 체크
  - 확장자는 공백이거나 20자를 초과할 수 없습니다.
  - 확장자는 영문자, 숫자로 이루어져야 합니다.
### 초기 데이터 생성
  - 개발 및 테스트 편의를 위해 ApplicationRunner를 활용하여 임시로 초기 데이터를 생성합니다.
### 테스트 코드
  - Domain과 Application 계층에 대한 테스트 코드를 작성하였습니다.
  - Domain 계층은 비즈니스 규칙과 제약 조건을 테스트하기 위해 상태 기반 테스트를 작성하였습니다.
  - Application 계층은 비즈니스 로직이 변경되는 경우를 고려하여 행위 기반 테스트를 작성하였습니다.

![file-extension-ss](https://github.com/sa46lll/file-shield/assets/62706048/65f8414b-b9fd-4d7a-b87b-2d55e6c8a369)
