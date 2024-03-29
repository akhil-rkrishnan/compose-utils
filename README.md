# Compose Utilities

This is a utility library with UI components and General extensions.

I use all the functions included in this library for every project i make. I was importing this as a separate module for each time when i work on a project. Now it's a library, so that if any one needs these functionalities in their project, they can directly import using the gradle dependency from jitpack.

The UI Components are developed in Jetpack compose. So if you need to use the components in a non-compose project, refer [Interoperability APIs](https://developer.android.com/jetpack/compose/interop/interop-apis) to include the Compose UI components in xml.

# Usage
### UI Components
##### Line progress bar
![lpb](https://user-images.githubusercontent.com/30260853/205038531-180d45cb-1f8c-4573-aa7b-8ef495bb2488.gif)

       LineProgressBar(
            modifier = Modifier,
            colors = listOf(Color.Blue, Color.Green, Color.Red, Color.Magenta),
            shuffleGradient = true,
            strokeWidth = 30f
           )

**Modifier** - Modifier of the composable.\
**colors** - List of colors for the line gradient [list size should be >= 2, otherwise ColorListViolation exception will be thrown].\
**shuffleGradient** - If set as true then the color list will be shuffled on each progress iteration.\
**strokeWidth** - Line width [default is set as 10].

##### Circular progress bar
![cpb](https://user-images.githubusercontent.com/30260853/205042595-a048ce40-1b91-4a27-ba11-05814a081ba3.gif)

      CircularProgressBar(
         modifier = Modifier
         colors = listOf(Color.Blue, Color.Black),
         shuffleGradient = true,
         circleStrokeWidth = 20f,
         circleSize = Size(150f, 150f),
         initialStartAngle = 10f,
         initialSweepAngle = 15f,
         step = 5
         )

**Modifier** - Modifier of the composable.\
**colors** - List of colors for the circle gradient [list size should be >= 2, otherwise ColorListViolation exception will be thrown].\
**shuffleGradient** - If set as true then the color list will be shuffled on each progress iteration.\
**circleStrokeWidth** - Stroke width of the circle.\
**circleSize** - Size of the circle.\
**initialStartAngle** - Initial start angle. \
**initialSweepAngle** - Initial sweep angle. \
**step** - Step for the cordinates [higher the value, the faster the intermediate].

### Network Extension
#### Here we included a network extension for api calls.
> Perform Api call
  ```
  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient
                    .Builder().readTimeout(5, TimeUnit.MINUTES)
                    .writeTimeout(5, TimeUnit.MINUTES)
                    .connectTimeout(5, TimeUnit.MINUTES)
                    .build()
            )
            .build()

        val retrofitApi = retrofit.create(ApiInterface::class.java)
        var response: ApiResult<FreeApiModel> //FreeApiModel -> sample response model
        lifecycleScope.launch {
            response = initApiCall {
                ApiResult.Success(retrofitApi.getAllData())
            }

            response.ifSuccess { freeApiModel ->
                // do the operations after api success 
            }
            response.ifFailed { code, message ->
                // do the operations on api failed 
            }
        }
  }

interface ApiInterface {
    @GET("entries")
    suspend fun getAllData(): FreeApiModel
}
  ``` 
- ApiResult<T> --> Generic class which accepts all the types of data
- ApiResult.Success(T) --> This method will return the response model
  - ApiResult<T>.ifSuccess {} : This will be executed if the api call is success (code: 200)
  - ApiResult<T>.ifFailed { code, message -> } : This will be executed if the api call is failed

### General Extensions
#### Here we define some general extensions which can be included in any project
> **Toast**
  ```
  showToast(message = "This is a sample toast message", longToast = true)
  ``` 
- Implemented to show toast messages in a context scope.

> **Launch new activity**
 ```
 openActivity(SampleActivity::class.java, shouldFinish = false) { 
      putString("stringToSampleActivity", "This is a sample string")
  }
  ```

- Implemented to navigate to an activity in a context scope. So we don't need to write `startActivity()` method every time to load new intent.\
  **shouldFinish** - If true, will finish the current activity. \
  **Bundle Scope** - All the data which need to be sent to other activity will be passed as bundle.

> **Launch app settings**
 ```
 openAppSettings()
  ```
- This method will open the app settings in a context scope.

> **Get integer as an ordinal number string**
 ```
 Int?.asOrdinal()
  ``` 
- This method will return the ordinal number string of the integer.

  examples:

       1.asOrdinal() -> 1st 
       12.asOrdinal() -> 12th 
       23.asOrdinal() -> 23rd

> **Avoid crash on monkey testing**
 ```
 Modifier.persistShortKeyForInput(input: TextFieldValue)
  ``` 
- This is a modifier extension used in TextField Composable to avoid crash in monkey testing. The crash can't be reproduced always on manual testing.
  This is often occurred in automated UI testing.

  How to use the extension?:
     ```
    TextField(
         value = textFieldValue,
         modifier = Modifier.persistShortKeyForInput(textFieldValue)
                           .fillMaxWidth()
                           .onFocusEvent { event ->
                             .....
                           } 
          )
    ```



> **Save any type of object to Shared preference**
- Inlcuded functionality for saving user defined class to shared preference.

How to use the extension?
  ```
  fun initPrefs() {
   //init shared preference
   val sharedPreferences = getSharedPreferences("TEMP_PREF", MODE_PRIVATE)
   
   //Method to save the data
   sharedPreferences.saveData("freeModelKey", it)
      
  }

  fun readPrefsData() {
    // Reading data.
   val data : FreeModel? = sharedPreferences.getData("freeModelKey", FreeModel::class.java)
  }

  data class FreeModel(
   val count: Int,
   val entries: List<String>
)


   ```

> **Get rid of writing long log statements**
- Included Timber log extensions with optional parameters. It will accept any data type
  How to use extensions?:
   ```
   val name = "Android"
   // for error log
   name.loge(tag = "tag", filterWord = "name")

   // for debug log
   name.logd(tag = "tag", filterWord = "name")

   // for info log
   name.logi(tag = "tag", filterWord = "name")

   ...

   ```  



# Download
> In your settings.gradle file
```
dependencyResolutionManagement {
    ...
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }    
    }
}
```

> In your app.gradle file
```
dependencies {
    ....
    .....  
    implementation 'com.github.akhil-rkrishnan:compose-utils:1.1.0'
    
    //Note: Please check the release tag for the latest version in the repo and replace the version with the latest tag
}
```

> In your project level build.gradle file
```
...
buildscript {
    ext {
        compose_version = '1.1.1' // mention your compose version
        compile_sdk_version = 31 // mention your compile sdk version
        min_sdk_version = 21 // mention your min sdk version
        target_sdk_version = compile_sdk_version // mention your target sdk version
    }
}
...
```

  
