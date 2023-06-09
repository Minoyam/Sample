이 프로젝트는 Android Compose와 MVVM 패턴을 기반으로 구성된 안드로이드 앱입니다.

* 유저가 앱을 실행하면 다양한 콘텐츠들(배너, 상품, 스타일 코디)을 볼 수 있어야합니다.
* 서버 응답에 따라 화면에 보여지는 콘텐츠들의 순서와 형태가 달라져야합니다.
* 디자인 시스템을 구축하여 각 컴포넌트를 재사용 및 조합하여 화면을 구성해야 합니다.

## 구현 사항

* Header
    - header.iconURL , header.linkURL 모두 없을 경우 왼쪽에 타이틀만 표시합니다.
    - header.linkURL 이 있는 경우 오른쪽에 ‘전체’ 버튼을 표시합니다.
    - header.iconURL 이 있는 경우 타이틀과 아이콘을 함께 표시합니다.

* Footer
    - footer.type 이 REFRESH인 경우 콘텐츠 순서를 랜덤하게 바꾸어 갱신합니다.
    - footer.type이 MORE인 경우 현재 콘텐츠에 1열을 추가로 더 표시합니다. (더이상 표시할 데이터가 없는 경우 Footer가 보이지 않아야합니다.)

* Banner
    - contents.type이 BANNER인 경우 콘텐츠를 스와이프하여 다음 배너를 노출할 수 있도록 합니다.
        - 우측 하단에 페이징 인디케이터를 표시합니다.
        - 배너가 3초마다 자동으로 스와이프 되도록 구현합니다.

* Grid
    - contents.type 이 GRID 인 경우 콘텐츠를 3×2 Grid 형태로 표시합니다.

* Scroll
    - contents.type 이 SCROLL 인 경우 콘텐츠를 횡스크롤 형태로 표시합니다.

* Style
    - contents.type 이 STYLE 인 경우 콘텐츠를 첫 번째 아이템이 2x2 Span된 3열 Grid 형태로 표시합니다.

## 사용된 라이브러리

* Retrofit
    - HTTP 통신을 위한 라이브러리로, API와의 통신을 처리하기 위해 했습니다.
* OkHttp
    - HTTP 클라이언트 라이브러리로, Retrofit과 함께 사용되어 네트워크 요청을 처리하기 위해 했습니다.
* Coil
    - 이미지 로딩 라이브러리로, 이미지를 로딩하고 표시하기 위해 했습니다.
* Hilt
    - Android의 의존성 주입 라이브러리로, 의존성 주입을 통해 객체를 관리하기 위해 했습니다.
* Coroutine
    - 비동기 작업 위해 사용 했습니다.

## 패키지 구성

- 이 프로젝트는 아래와 같은 패키지 구조로 구성되어 있습니다

* data:
    - 데이터 관련 패키지로 데이터 모델이 포함되어 있습니다.

* datasource
    - 데이터 소스 관련 패키지로, 데이터를 가져오거나 업데이트하기 위한 인터페이스와 구현체가 포함되어 있습니다.

* di
    - 의존성 주입 관련 패키지로, Hilt를 사용하여 의존성 주입을 설정하는 클래스와 모듈이 포함되어 있습니다.

* network
    - 네트워크 관련 패키지로, Retrofit과 OkHttp를 사용하여 네트워크 요청을 처리하는 인터페이스가 포함되어 있습니다.

* repository
    - 데이터를 관리하는 리포지토리 패키지로, 데이터 소스와 네트워크를 통해 데이터를 가져오고 가공하는 로직이 포함되어 있습니다.

* ui
    - UI 관련 패키지로, 화면을 구성하는 Compose UI 코드가 포함되어 있습니다.

* viewModel
    - MVVM 패턴의 뷰모델 관련 패키지로, UI와 데이터를 연결하는 뷰모델 클래스가 포함되어 있습니다.
