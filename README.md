# Compose Utilities

This is a utility library with UI components and General extensions.

I use all the functions included in this library for every project i make. I was importing this as a separate module for each time when i work on a project. Now it's a library, so that if any one needs these functionalities in their project, they can directly import using the gradle dependency from jitpack.

The UI Components are developed in Jetpack compose. So if you need to use the components in a non-compose project, refer [Interoperability APIs](https://developer.android.com/jetpack/compose/interop/interop-apis) to include the Compose UI components in xml.

# Usage
### UI Components
##### Line progress bar
![lpb](https://user-images.githubusercontent.com/30260853/205038531-180d45cb-1f8c-4573-aa7b-8ef495bb2488.gif)

       LineProgressbar(
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
- This method will return the ordinal number string of the integer. \

  examples:

       1.asOrdinal() -> 1st 
       12.asOrdinal() -> 12th 
       23.asOrdinal() -> 23rd




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
    implementation 'com.github.akhil-rkrishnan:compose-utils:1.0.4'
    
    //Note: Please check the release tag for the latest version in the repo and replace the version with the latest tag
}
```

  
