# saveo

Library used :

1 Navigation component
2 Hilt Dependency injection
3 Data binding
4 LiveData
5 ViewBinding
6 Glide
7 Retrofit
8 Coroutine

Used MVVM architecture.

There are two modules in this project , "App" and "MyApiServicesModule" . "App" is the default module and a separate
module is being created for storing the class related to Api Services.
Navigation component is being used to achieve single activity app . There are Two Fragments in the App , First fragment shows the list of News
and the second fragment shows the details of the News.

If the Error comes up for the first time when we land on the screen then the full screen shows the Eror with a Retry button but if the List is not empty then we show the erro with
Toast message  so that the user can read the already loaded News Article.