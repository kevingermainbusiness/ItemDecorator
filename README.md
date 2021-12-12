[![](https://jitpack.io/v/kevingermainbusiness/ItemDecorator.svg)](https://jitpack.io/#kevingermainbusiness/ItemDecorator)

# ItemDecorator

A simple utility class that helps you customize your RecyclerView's
ItemTouchHelper.SimpleCallback.onChildDraw behavior, when ItemTouchHelper.ACTION_STATE_SWIPE is
triggered.

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
    implementation 'com.github.kevingermainbusiness:ItemDecorator:1.0.12'
}
```

**That's it!**

## Usage

Basic usage is shown below, there's a more elaborate example in
the [sample app](https://github.com/kevingermainbusiness/ItemDecorator/tree/master/app). In this
case, if you want to set a default background color,text,text color,text size,typeface,icon,icon
tint color, as you swipe on each sides:

```kotlin
val colorAlert = ContextCompat.getColor(this@MainActivity, R.color.colorAlert)
val teal200 = ContextCompat.getColor(this@MainActivity, R.color.teal_200)
val defaultWhiteColor = ContextCompat.getColor(this@MainActivity, R.color.white)

ItemDecorator.Builder(c, recyclerView, viewHolder, dX, actionState).set(
    backgroundColorFromStartToLeft = colorAlert,
    backgroundColorFromEndToStart = teal200,
    textFromStartToEnd = getString(R.string.action_delete),
    textFromEndToStart = getString(R.string.action_add_to_fav),
    textColorFromStartToEnd = defaultWhiteColor,
    textColorFromEndToStart = defaultWhiteColor,
    iconTintColorFromStartToEnd = defaultWhiteColor,
    iconTintColorFromEndToStart = defaultWhiteColor,
    textSizeFromStartToEnd = 16f,
    textSizeFromEndToStart = 16f,
    typeFaceFromStartToEnd = Typeface.DEFAULT_BOLD,
    typeFaceFromEndToStart = Typeface.SANS_SERIF,
    iconResIdFromStartToEnd = R.drawable.ic_baseline_delete_24,
    iconResIdFromEndToStart = R.drawable.ic_baseline_done_24
)
```

### Note

You can change the default icon horizontal margin of 16dp of the ItemDecorator by passing your
desired margin for the icons like so:

````kotlin
val desiredValue = 18f
ItemDecorator.Builder(c, recyclerView, viewHolder, dX, actionState).set(
    iconHorizontalMargin = desiredValue,
    backgroundColorFromStartToLeft = colorAlert,
    backgroundColorFromEndToStart = teal200,
    textFromStartToEnd = getString(R.string.action_delete),
    textFromEndToStart = getString(R.string.action_add_to_fav),
    textColorFromStartToEnd = defaultWhiteColor,
    textColorFromEndToStart = defaultWhiteColor,
    iconTintColorFromStartToEnd = defaultWhiteColor,
    iconTintColorFromEndToStart = defaultWhiteColor,
    textSizeFromStartToEnd = 16f,
    textSizeFromEndToStart = 16f,
    iconResIdFromStartToEnd = R.drawable.ic_baseline_delete_24,
    iconResIdFromEndToStart = R.drawable.ic_baseline_done_24
)
````

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

Pull requests are welcome. For major changes, please open an issue first to discuss what you would
like to change.
