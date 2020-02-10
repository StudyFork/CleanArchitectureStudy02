# Android Clean Architecture Study-02

</br>

### Navigation 이점

- fragment transactions 을 자동으로 조작해줍니다.
- up, back (상단바, 소프트 키보드 뒤로가기)를 기본으로 지원합니다.
- 애니메이션을 지원합니다.
- Deep Linking 지원합니다.
- 적은 동작으로 네비게이션 UI 패턴을 지원합니다. (navigation drawers, bottom nav)
- 네비게이션 간에 정보 타입 전달에 안전합니다. (Safe Args)
- 안드로이드 스튜디오를 통패 네비게이션 플로우를 시각화 및 수정이 가능합니다.
- ViewModel 지원합니다.


### 지원 버전

- Android Studio 3.3 버전부터 Navigation Editor를 지원합니다.


### Navigation 원칙

- 시작 위치가 정해져 있어야 한다.
- 스택으로 표시되어 져야 한다.
- 처음 시작하는 부분에 up 버튼이 있으면 안된다.
- up, back 버튼이 동일한 동작을 해야 한다.
- 딥링크의 탐색은 네비게이션 스택과 동일해야 한다.


### Navigation Three component

**Navigation graph**

- 화면 이동에 관한 모든 정보를 저장하는 XML 코드
- 경로 res/navigation
- <navigation> : NavGraph의 기본 태그
- <fragment> <Activity> <Dialog(2.1.0 부터 지원)> 등의 Destination
- <action> : 화면 이동에 대한 액션을 정의할 수 있다
- <argument> : 화면 이동에 대한 파라미터를 정의한다.
- <deeplink> : 딥링크에 대한 내용을 정의한다.

**NavHost**

- 네비게이션 보여주는 컨테이너 역활
- NavHost를 구현한 NavHostFragment 이다.

```xml
<fragment
        android:id="@+id/nav_host_fragment"
        android:name="androidx.navigation.fragment.NavHostFragment"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:defaultNavHost="true"
        app:navGraph="@navigation/nav_graph" />
```

> 네비게이션은 SingleActicity로 디자인 되었습니다.

**NavController**

- 화면 이동에 대한 컨트롤 역활
- NavHost로부터 얻을 수 있음
- 불러오는 방법

```xml
<!-- From within an Activity -->
val navController = findNavController(R.id.nav_container)

<!-- From within an Fragment -->
val navController = findNavController()

<!-- From within an onClickListener -->
val navController = view.findVanController()

```

- 이동하는 방법

```kotlin
//action에 정의된 id 사용
findNavController().navigate(R.id.action_global_thirdHomeScreen)

//자동 생성된 Directions 사용
val navDirection = NavMainDirections.actionGlobalThirdHomeScreen()
findNavController().navigate(navDirection)

//listener 사용
Navigation.createNavigateOnClickListener(R.id.action_global_thirdHomeScreen)
```

[NavController 더 자세한 설명](https://medium.com/@fornewid/navigation-%ED%9B%91%EC%96%B4%EB%B3%B4%EA%B8%B0-82d23fbc85af)

- 잘못된 navigation 실행 시 java.lang.IllegalArgumentException 에러 발생


### SafeArgs

```kotlin
//보낼때
findNavController().navigate(R.id.action_global_thirdHomeScreen, bundleOf("paramCount" to 15))

//받을때
val count = arguments?.getInt("paramCount")
```

argument 전달을 SafeArgs를 사용해 쉽게 처리 할 수 있다.


```kotlin
//보낼때
val direction = FirstHomeScreenDirections.actionFirstHomeScreenToTwoDepthScreen(10)
findNavController().navigate(direction)

//받을때 방법 1
private val twoDepArgs: TwoDepthScreenArgs by navArgs()
val count = twoDepArgs.paramCount

//받을때 방법2
arguments?.let {
    val count = TwoDepthScreenArgs.fromBundle(it).paramCount
}
```

### DeepLink

1. Explicit DeepLinking(명시적 딥링크)

```kotlin
val args = Bundle()
args.putInt("param_count", 100)

val pendingIntent = NavDeepLinkBuilder(requireContext())
    .setGraph(R.navigation.nav_main)
    .setDestination(R.id.twoDepthScreen)
    .setArguments(args)
    .createPendingIntent()

//notification, widget 등을 사용하여 실행할 수 있습니다.
```

2. TODO Implicit DeepLinking(암시적 딥링크)


### NavigationUI

- Toolbar
- CollapsingToolbarLayout
- ActionBar
- DrawerLayout
- BottomNavigationView


### Animation

- enterAnim : action 실행시, 이동할 Destination에 대한 애니메이션
- exitAnim : action을 실행할 때 현재 Destination에 대한 애니메이션
- popExitAnim : 이전 화면으로 돌아갈 때(Pop or Back) 종료되는 현재 Destincation에 대한 애니메이션
- popEnterAnim : 이전 화면으로 돌아갈 때(Pop or Back) 이동할 BackStack의 Destination에 대한 애니메이션

```kotlin
//방법 1
val options = navOptions {
                anim {
                    enter = R.anim.slide_in_right
                    exit = R.anim.slide_out_left
                    popEnter = R.anim.slide_in_left
                    popExit = R.anim.slide_out_right
                }
            }

val direction = FirstHomeScreenDirections.actionFirstHomeScreenToTwoDepthScreen()
findNavController().navigate(direction, options)

//방법 2
<action
    android:id="@+id/action_firstHomeScreen_to_twoDepthScreen"
    app:destination="@id/twoDepthScreen"
    app:enterAnim="@anim/slide_in_right"
    app:exitAnim="@anim/slide_out_left"
    app:popEnterAnim="@anim/slide_in_left"
    app:popExitAnim="@anim/slide_out_right" />
```

### Site
- [Navigation - google](https://developer.android.com/guide/navigation)
- [SOUP - medium](https://medium.com/@fornewid)
- [남잭슨의 개발 블로그](https://namjackson.tistory.com/28)
- [해리의 유목민](https://medium.com/harrythegreat/android-navigation-component-%EA%B0%9C%EB%85%90%EA%B3%BC-%ED%8A%9C%ED%86%A0%EB%A6%AC%EC%96%BC-1-5ac6ac081643)


## 2월 9일 스터디 내용 정리

### Navigation Component 사용 해야 할까?

최근 Google은 Single Activity 사용을 권하고 있는 추세이다. 왜 Single Activity를 사용하려고 할까?

이를 짚어보기에 앞서 2019년 이후 deprecated 되는 일부 함수들을 한번 살펴보자

1. Fragment에서 View 생성과 연관된 경우 onActivityCreated 보다는 onViewCreated 사용 권장 [링크](https://android-review.googlesource.com/c/platform/frameworks/support/+/1189651)
2. Deprecate AsyncTask : AsyncTask는 백그라운드 스레드에서 메인 스레드로의 연산 메커니즘을 손쉽게 구현할 수 있게 해준다. 하지만 context 리소스 누수 문제나 configuration 변경이 일어났을 경우의 크래시 방지에 대한 처리를 직접 해주어야 한다는 불편함이 있다. [링크](https://android-review.googlesource.com/c/platform/frameworks/base/+/1156409)

> 위 업데이트로 짐작 하건데 뷰 간의 화면 전환이 있을 때나 Acitivty 리소스를 사용하는데 있어 구글이 변화를 주는것 같다. Activity가 메모리에서 복원되는 과정이나 Activity간의 데이터를 주고 받는 경우가 복잡해지면서 다양한 사이드 이펙트가 발생하는데 이러한 문제를 고쳐보기 위함이 아닐까?


### 총평

> Navigation Component는 SingleActivity를 구현하기 위한 프레임워크이다. 현재 현업에서 사용하시는 분의 의견으로는 Navigation Drawer나 DeepLinking을 구현하는데 있어서는 정말 편리하다고 해주셨다. 하지만 아직 다른 부분에서는 많이 사용하지는 않으신다고...



