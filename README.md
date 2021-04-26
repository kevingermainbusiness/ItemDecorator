[![](https://jitpack.io/v/kevingermainbusiness/ItemDecorator.svg)](https://jitpack.io/#kevingermainbusiness/ItemDecorator)
# ItemDecorator
A simple utility class that helps you customize your RecyclerView's ItemTouchHelper.SimpleCallback.onChildDraw behavior

![alt First screenshot](https://github.com/kevingermainbusiness/ItemDecorator/blob/master/screenshots/Screenshot_1619390385.png)
![alt Second screenshot](https://github.com/kevingermainbusiness/ItemDecorator/blob/master/screenshots/Screenshot_1619390396.png)

# How to get this project
**Step 1.** Add the jitpack repository to your ```project build.gradle``` file, like so:
```
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
```
dependencies {
  implementation 'com.github.kevingermainbusiness:ItemDecorator:1.0.4'
}
```
**That's it!**

## Usage
Basic usage is shown below, there's a more elaborate example in the [sample app](https://github.com/kevingermainbusiness/RecyclerViewItemDecorator/tree/master/app) app.
In this case, if you want to set a default background color,text,icon,text color, icon tint color, as you swipe on each sides:
```kotlin
ItemDecorator.Builder(c, recyclerView, viewHolder, dX, actionState)
                    .setDefaultIcon(R.drawable.ic_baseline_delete_24)
                    .setDefaultText("Delete")
                    .setDefaultBgColor(ContextCompat.getColor(this, R.color.purple_200))
                    .setDefaultIconTintColor(ContextCompat.getColor(this, R.color.white))
                    .setDefaultTextColor(ContextCompat.getColor(this, R.color.white))
                    .create().decorate()
```
Or if you just want to specify each values for the background color,text,icon,text color, icon tint color just as seen above in the screenshots:
```kotlin
ItemDecorator.Builder(c, recyclerView, viewHolder, dX, actionState)
                    .setFromStartToEndIcon(R.drawable.ic_baseline_delete_24)
                    .setFromEndToStartIcon(R.drawable.ic_baseline_save_alt_24)
                    .setFromStartToEndText("Delete")
                    .setFromEndToStartText("Save")
                    .setFromStartToEndBgColor(ContextCompat.getColor(this, R.color.purple_200))
                    .setFromEndToStartBgColor(ContextCompat.getColor(this, R.color.teal_200))
                    .setDefaultIconTintColor(ContextCompat.getColor(this, R.color.white))
                    .setDefaultTextColor(ContextCompat.getColor(this, R.color.white))
                    .create().decorate()
```

### Note
You can change the default text size of 14sp of the ItemDecorator, by calling the Builder's ```setDefaultTextSize()```method,
before calling the Builder's ```.create()``` method.
Here's an example, where you want to change the text size to 18 sp:
```kotlin
ItemDecorator.Builder(c, recyclerView, viewHolder, dX, actionState)
                    .setDefaultIcon(R.drawable.ic_baseline_delete_24)
                    .setDefaultText("Delete")
                    .setDefaultBgColor(ContextCompat.getColor(this, R.color.purple_200))
                    .setDefaultIconTintColor(ContextCompat.getColor(this, R.color.white))
                    .setDefaultTextColor(ContextCompat.getColor(this, R.color.white))
                    .setDefaultTextSize(size = 18f)
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

