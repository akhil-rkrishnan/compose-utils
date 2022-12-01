# Compose Utilities

This is a utility library with UI components and General extensions.

I use all the functions included in this library for every project i make. I was importing this as a seperate module for each time when i work on a project. Now it's a library, so that if any one needs these functionalities in their project, they can directly import using the gradle dependency from jitpack.

The UI Components are developed in Jetpack compose. So if you need to use the components in a non-compose project, refer [Interoperability APIs](https://developer.android.com/jetpack/compose/interop/interop-apis) to include the components in xml. 

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
  
  **Modifier** - Modifier of the composable.<br />
  **colors** - List of colors for the line gradient [list size should be >= 2, otherwise ColorListViolation exception will be thrown].<br />
  **shuffleGradient** - If set as true then the color list will be shuffled on each progress iteration.<br />
  **strokeWidth** - Line width [default is set as 10]. <br />
  
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
         
  **Modifier** - Modifier of the composable.<br />
  **colors** - List of colors for the circle gradient [list size should be >= 2, otherwise ColorListViolation exception will be thrown].<br />
  **shuffleGradient** - If set as true then the color list will be shuffled on each progress iteration.<br />
  **circleStrokeWidth** - Stroke width of the circle.<br />
  **circleSize** - Size of the circle.<br />
  **initialStartAngle** - Initial start angle.<br /> 
  **initialSweepAngle** - Initial sweep angle.<br />
  **step** - Step for the cordinates [higher the value, the faster the intermediate].<br />


  
