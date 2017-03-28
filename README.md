## 前言
	
- ### 相信大家都有听说过这三大框架吧,这是最近很火的框架

	---

## Retrofit的详解 

 - 主要参考于 [ Retrofit2.0使用详解](http://blog.csdn.net/ljd2038/article/details/51046512)

 - retrofit是基于okhttp的封装工具类,功能十分丰富.我能力还是有限,在这里先说下它的使用吧!

    ### 简单使用
    1. #### 添加Gradle依赖项
    ```
    compile 'com.squareup.retrofit2:retrofit:2.1.0'
    ```
    2. #### 创建Api接口
	    
	    >作为一个get请求的url,并提供一些可变的参数
	    
	    ```
    @GET("yoururl/{neededParameter}")
    Call<ResponseBody> getParameter(@Path("parameter") String parameter);
	    ```
    
        这里的**@Path**只是为了用后面parameter来代替{neededParameter}这个占位符
        
        还有其他的例如**@Query**就是类似于url?parameter=...这种
        
    3. #### 创建retrofit实例
    ```
    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl(yourUrl)
            .addConverterFactory(GsonConventerFactory.create())
            .build();
    IApimanager apiManager=retrofit.create(IApiManager.class);
    Call<ResponseBody> call=apiManager.getParameter("parameter");
    call.enqueue(new Callback<ResponseBody>(){
        @Override
    public void onResponse(Call<User> call, Response<User> response)
    {
        Log.e(TAG, "getParameter:" + response.body());
    }

    @Override
    public void onFailure(Call<User> call, Throwable t)
    {
        Log.e(TAG,t.printStackTrace();
    }
    });
    ```
    
    #### 大概就是这个样子,代码十分简洁,也没有出现什么线程的地方.这也应该是它之所以火的地方
    
    >这里先留个位子,改天拆下轮子,研究下这个库的源码才行.

---
       
## Rxjava

- 主要参考于[给 Android 开发者的 RxJava 详解](http://gank.io/post/560e15be2dca930e00da1083)

- Rxjava,简单来说, 它是一种模式, 类似于观察者模式的这种,但又好像是升级的版本.总的来说,他也是一种简化异步操作的库。

- 我就结合一下我的这个项目给大家讲一下RxJava的基本使用吧!

- ### RxJava的基本使用
    
    - 相信大家也看到了上面的ApiManager返回的是一个Call类型的, 如果我们要使用RxJava的话, 就应该像下面这样写;
    
    ```
     @GET("index")
     rx.Observable<NewList> getNews(@Query("key") String key);
    ```

    只是把返回值换成了Rxjava 的Observable, 为什么这么做呢, 接下来就在Retrofit下做手脚了:
    
    ```
        public void getData(String key) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL_NEWS)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        IApiManager apiManager = retrofit.create(IApiManager.class);
        apiManager.getNews(key)
                .subscribeOn(Schedulers.io())
                .map(new Func1<NewList, List<News>>() {
                    @Override
                    public List<News> call(NewList newList) {
                        List<News> news = new ArrayList<News>();
                        for (NewList.ResultBean.DataBean eachNews : newList.getResult().getData()) {
                            News everyNews = new News();
                            everyNews.setDate(eachNews.getDate());
                            everyNews.setTitle(eachNews.getTitle());
                            everyNews.setThumbnail_pic_s(eachNews.getThumbnail_pic_s());
                            everyNews.setUrl(eachNews.getUrl());
                            news.add(everyNews);
                        }
                        return news;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<News>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(), "网络出了些问题", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<News> newses) {
                        mRefreshNewsAdapter.addAll(newses);
                    }
                });
    }
    ```
    
    或许你会感到头疼, 为什么这么长一大串啊, 说好的简洁去了哪里.
    
    其实, RxJava是逻辑的简洁, 让我来给你分析一下吧!
    
    首先是**addCallAdapterFactory(RxJavaCallAdapterFactory.create())** ,这个是添加一个RxJava适配器
    
    然后就是那一大串什么鬼, getNews(key)后面是什么鬼, **其实是** getNews返回的一个Obserable类型, 这个Obserable呢, 就是可以被观察的对象了, 详情就可以去了解上面那篇文章.
    
    **map**操作进行了简单的json数据解析, 而这个操作是进行再子线程的io里, 然后再回到主线程, 将数据分别显示出来.
    
    对于RxJava的我这里也就用了这么多, 其实还有很多用法没有在这里写出来, 有兴趣的朋友可以去探索上面那篇文章, 写得真的很适合新手入门的.

---

## MVP

- ### 什么是MVP结构?
    
    相信大家应该听说过什么是MVC结构(model,view,controller) , 这三个英文单词简单的说一下, model(模型)业务逻辑和实体模型, view(视图)就是布局文件, controller(控制器)Activity.
        
    没错, 大家入门开始写的项目, 大多数都是以这种为架构的, 常说的Activity处理一切事物, 导致了一个文件里面代码几百行, 甚至上千行, 所以我们要学习**MVP结构** , 从而来提高项目的解耦性, 啥是**解耦?**
        
    就是各个模块的依赖度, 你依赖着你爸妈, 当你长大了, 慢慢独立了, 解耦性就高了.
        
    好了, 说了那么多MVC, 换到MVP来, MVP和MVC只是一字之差?
        
    P和C 有什么区别啊?  P-presenter / C-controller 
        
    Presenter主要用来完成View和Model的交互, controller是Activity,完成大多数工作. 
        
    ---
    
- ### MVC 和 MCP 的区别

    这里引用一下hongyang大佬的图片, 地址[浅谈 MVP in Android](http://blog.csdn.net/lmj623565791/article/details/46596109)

	![这里写图片描述](http://img.blog.csdn.net/20170328205303098?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvd2lsbGlhbWNoZXc=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

- ### 项目实例

    其实大多数用来做登录注册的demo, 其实整个app用的MVP主要还是在登录注册, 其他的用了Retrofit和RxJava
    
    首先看看下面的红色框的文件
    
    ![这里写图片描述](http://img.blog.csdn.net/20170328205235762?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvd2lsbGlhbWNoZXc=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
    
    1. 创建Model
    
        实体类User

        ```
        public class User {
            private String mUserName;
            private String mPassword;
        
            public User(String userName, String password) {
                mUserName = userName;
                mPassword = password;
            }
        
            public String getUserName() {
                return mUserName;
            }
        
            public void setUserName(String userName) {
                mUserName = userName;
            }
        
            public String getPassword() {
                return mPassword;
            }
        
            public void setPassword(String password) {
                mPassword = password;
            }
        }
        ```
        
        业务逻辑UserModel类
        ```
        public class UserModel {
            public void login(final String userName, final String password, final OnLoginListener onLoginListener){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if("ThatNight".equals(userName)&&"123".equals(password)){
                            User user=new User(userName,password);
                            onLoginListener.loginSuccess(user);
                        }else{
                            onLoginListener.loginFailed();
                        }
                    }
                }).start();
            }
        }
        ```
        
        登录监听接口OnLoginListener
        ```
        public interface OnLoginListener {
            void loginSuccess(User user);
            void loginFailed();
        }
        ```
        
    2. 创建View
    
        登录接口ILoginView,主要是写一些在UI操作的方法
        
        ```
        public interface ILoginView {
            void loginSuccess();
        
            void loginFailed();
        
            void setPbVisiable(int visiable);
        
            void showText(String text);
        
            String getUserName();
        
            String getPassword();
        
        }
        ```
        
        然后LoginActivity实现ILoginView接口, 并实现方法
        
        ```
        public class LoginActivity extends AppCompatActivity implements ILoginView {

            @InjectView(R.id.et_login_name)
            EditText mEtLoginName;
            @InjectView(R.id.et_login_pwd)
            EditText mEtLoginPwd;
            @InjectView(R.id.btn_login_login)
            Button mBtnLoginLogin;
            @InjectView(R.id.progressBar)
            ProgressBar mProgressBar;
        
            private LoginPresenter mLoginPresenter;
        
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_login);
                ButterKnife.inject(this);
                init();
            }
        
            private void init() {
                mLoginPresenter = new LoginPresenter(this);
        
            }
        
            @Override
            public void loginSuccess() {
                setPbVisiable(View.INVISIBLE);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        
            @Override
            public void loginFailed() {
                Toast.makeText(this, "登录失败!", Toast.LENGTH_SHORT).show();
            }
        
            @Override
            public void setPbVisiable(int visiable) {
                mProgressBar.setVisibility(visiable);
                if (visiable == View.VISIBLE) {
                    mBtnLoginLogin.setEnabled(false);
                } else {
                    mBtnLoginLogin.setEnabled(true);
                }
            }
        
            @Override
            public void showText(String text) {
                Toast.makeText(this, text + " 登录成功!", Toast.LENGTH_SHORT).show();
            }
        
            @Override
            public String getUserName() {
                return mEtLoginName.getText().toString();
            }
        
            @Override
            public String getPassword() {
                return mEtLoginPwd.getText().toString();
            }
        
            @OnClick(R.id.btn_login_login)
            public void onClick() {
            }
        }
        ```
    
    3. 创建Presenter类
    
        主要是写一个方法让LoginActivity调用, 比如按下登录按钮就调用这里的login方法.
        ```
        public class LoginPresenter {
            private ILoginView mILoginView;
            private UserModel mUserModel;
            private Handler mHandler = new Handler();
        
            public LoginPresenter(ILoginView ILoginView) {
                mILoginView = ILoginView;
                mUserModel = new UserModel();
            }
        
        
            public void login() {
                mILoginView.setPbVisiable(View.VISIBLE);
                mUserModel.login(mILoginView.getUserName(), mILoginView.getPassword(), new OnLoginListener() {
                    @Override
                    public void loginSuccess(final User user) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mILoginView.showText(user.getUserName());
                                mILoginView.loginSuccess();
                            }
                        });
                    }
        
                    @Override
                    public void loginFailed() {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mILoginView.loginFailed();
                            }
                        });
                    }
                });
            }
        }
        ```
        
	     效果图：
		![这里写图片描述](http://img.blog.csdn.net/20170328205408209?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvd2lsbGlhbWNoZXc=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
	
---

## 总结

- #### 虽然感觉这三大框架的app到处都是, 但是要真正的熟悉里面的逻辑, 还是需要花费一定时间来拆轮子, 不研究这些源码,只会使用是永远不足够的, 好好加油!

- #### 最终效果图:

	![这里写图片描述](http://img.blog.csdn.net/20170328211840980?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvd2lsbGlhbWNoZXc=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast) 

- #### Demo地址(第一次写有点菜): [Retrofit+RxJava+MVP](https://github.com/703692499/RxReOk/tree/master)

	---
