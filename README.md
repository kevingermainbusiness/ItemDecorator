[![](https://jitpack.io/v/kevingermainbusiness/ItemDecorator.svg)](https://jitpack.io/#kevingermainbusiness/ItemDecorator)
# ItemDecorator
A simple utility class that helps you customize your RecyclerView's ItemTouchHelper.SimpleCallback.onChildDraw behavior, 
when ItemTouchHelper.ACTION_STATE_SWIPE is triggered.

![alt First screenshot](https://github.com/kevingermainbusiness/ItemDecorator/blob/master/screenshots/Screenshot_1619456849.png)
![alt Second screenshot](https://github.com/kevingermainbusiness/ItemDecorator/blob/master/screenshots/Screenshot_1619456854.png)

# How to get this project
**Step 1.** Add the jitpack repository to your ```project build.gradle``` file, like so:
```groovy
// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    ext {
    }
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
    }
}
// Place the jitpack repository inside this, like so:
allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}
```

**Step 2.** Add the dependency in your ``` module build.gradle ``` file, like so:
```groovy
dependencies {
  implementation 'com.github.kevingermainbusiness:ItemDecorator:1.0.8'
}
```
**That's it!**

## Usage
Basic usage is shown below, there's a more elaborate example in the [sample app](https://github.com/kevingermainbusiness/ItemDecorator/tree/master/app).
In this case, if you want to set a default background color,text,text color,text size,typeface,icon,icon tint color, as you swipe on each sides:
```kotlin
val colorAlert =
    ContextCompat.getColor(this@MainActivity, R.color.colorAlert)
val defaultWhiteColor =
    ContextCompat.getColor(this@MainActivity, R.color.white)

ItemDecorator.Builder(c, recyclerView, viewHolder, dX, actionState)
    .setDefaultBgColor(colorAlert)
    .setDefaultIcon(R.drawable.ic_baseline_delete_24)
    .setDefaultIconTintColor(defaultWhiteColor)
    .setDefaultText(getString(R.string.action_delete))
    .setDefaultTypeFace(Typeface.DEFAULT_BOLD)
    .setDefaultTextColor(defaultWhiteColor)
    .setDefaultTextSize(size = 16f)
    .create()
    .decorate()
```
Or if you just want to specify each values such as background color,text,text color,text size,typeface,icon, icon tint color, just as seen above in the screenshots:
```kotlin
val colorAlert = ContextCompat.getColor(this@MainActivity, R.color.colorAlert)
val teal200 = ContextCompat.getColor(this@MainActivity, R.color.teal_200)
val defaultWhiteColor = ContextCompat.getColor(this@MainActivity, R.color.white)

ItemDecorator.Builder(c, recyclerView, viewHolder, dX, actionState)
    .setFromStartToEndIconTint(defaultWhiteColor)
    .setFromEndToStartIconTint(defaultWhiteColor)
    .setFromStartToEndTypeface(Typeface.DEFAULT_BOLD)
    .setFromEndToStartTypeface(Typeface.SANS_SERIF)
    .setFromStartToEndTextSize(size = 16f)
    .setFromEndToStartTextSize(size = 16f)
    .setFromStartToEndTextColor(defaultWhiteColor)
    .setFromEndToStartTextColor(defaultWhiteColor)
    .setFromStartToEndIcon(R.drawable.ic_baseline_delete_24)
    .setFromEndToStartIcon(R.drawable.ic_baseline_done_24)
    .setFromStartToEndText(getString(R.string.action_delete))
    .setFromEndToStartText(getString(R.string.action_add_to_fav))
    .setFromStartToEndBgColor(colorAlert)
    .setFromEndToStartBgColor(teal200)
    .create()
    .decorate()
```

### Note
You can change the default icon horizontal margin of 16dp of the ItemDecorator
by calling the Builder's ```setIconHorizontalMargin()``` method before calling the Builder's ```.create()``` method.
Here's an example, where it is changed to 18dp:
```kotlin
ItemDecorator.Builder(c, recyclerView, viewHolder, dX, actionState)
    .setDefaultIcon(R.drawable.ic_baseline_delete_24)
    .setDefaultText("Delete")
    .setDefaultBgColor(ContextCompat.getColor(this, R.color.purple_200))
    .setDefaultIconTintColor(ContextCompat.getColor(this, R.color.white))
    .setDefaultTextColor(ContextCompat.getColor(this, R.color.white))
    .setIconHorizontalMargin(iconHorizontalMargin = 18)
    .create().decorate()
```

## License
Licenced under the MIT Licence
```
Copyright (c) 2021 Kevin Germain

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.
