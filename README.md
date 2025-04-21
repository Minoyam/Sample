# 프로젝트 구조

각 모듈은 역할별로 분리되어 있으며, 멀티모듈 구조로 설계되었습니다.


## 1. 모듈 구성

### 1.1 `app`
- Android 애플리케이션 모듈
- `Application` 클래스: `App.kt` (`@HiltAndroidApp`)을 통해 Hilt 초기화 수행

### 1.2 `core`
- 공통 UI 구성요소(`designsystem`) 및 모델(`model`) 보관

```
core/
├─ designsystem/ (Compose 테마, 공통 컴포넌트)
└─ model/ (앱 전역에서 사용하는 Model)
```

### 1.3 `data`
- DI 설정(`di`),
- 모델(`data`),
- 리포지토리 구현부(`repositoryImpl`),
- RemoteDataSource 구현(`sourceInterface`)

```
data/
├─ di/ (Hilt 모듈)
├─ model/ (Data)
├─ repository/ (RepositoryImpl 구현)
├─ source/ (RemoteDataSource interface 구현)
└─ Mapper.kt (Data → Domain 매핑)
```

### 1.4 `datastore`
- 로컬 `DataStore` 기반 찜(Like) 관리

```
datastore/
└─LikeDataStore.kt (토글, 관찰 흐름 제공)
```

### 1.5 `domain`
- 도메인 레이어: UseCase 인터페이스 및 도메인 모델 정의

```
domain/
├─ model/ (명칭 그대로 사용)
├─ repository/ (도메인 인터페이스)
└─ usecase/ (비즈니스 로직)
```

### 1.6 `feature`
- Compose 화면(Feature) 모듈

```
feature/
├─ ui/ (Compose Screen 및 ViewModel)
```

### 1.7 `server`
- 서버 관련 DI 구현
- 모델(`Response`),
- 서버 API mocking 및 테스트 지원
- RemoteDataSource 구현부
- SectionPagingSource 구현 (페이징)
```
server/
├─ di/ (File, Remote, Service)
├─ service/ (api interface)
├─ mock/ (MockServer, AssetFileProvider, TestAssetFileProvider)
└─ source (remote 구현부, paging 구현)
```
