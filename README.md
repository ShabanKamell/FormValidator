<p align="center"><a href="https://github.com/ShabanKamell/FormValidator"><img src="https://github.com/ShabanKamell/FormValidator/blob/master/blob/logo.png?raw=true" alt="Gray shape shifter" height="200"/></a></p>
<h1 align="center">FormValidator</h1>
<p align="center">The easiest, most clean Android form validation.</p>

<p align="center">
	<a href="https://jitpack.io/#ShabanKamell/FormValidator"> <img src="https://jitpack.io/v/ShabanKamell/FormValidator.svg" height="20"/></a>
<a href="https://twitter.com/intent/tweet?text=FormValidator,%20a%20declarative%20form%20validation%20for%20Android,%20simple,%20clean,%20and%20customizable.&url=https://github.com/ShabanKamell/FormValidator&hashtags=android,kotlin,java,library,developers"><img src="https://img.shields.io/twitter/url/http/shields.io.svg?style=social" height="20"/></a>
</p><br/><br/>

A declarative Form Validation for Android, simple, clean, and customizable.

Every time you create a form, you need to declare fields and write code for for validating each field in the form, and this results in many ```if else``` and a lot of boilerplate. For these reasons **FormValidator** is here, just declare your fields in XML and its validation and all things will be done for you!

<img src="https://github.com/ShabanKamell/FormValidator/blob/master/blob/master/raw/diagragm.png" height="600">

# Table of contents

- [Usage](#usage)
- [Installation](#installation)
- [Widgets](#widgets)
  - [TextView Widgets](#textview-widgets)
  - [Other Widgets](#other-widgets)
- [Validatable interface](#validatable-interface)
- [Form Layout](#form-layout)
- [TextView Validation](#textview-validation)
- [Credit](#credit)
- [License](#-license)

# Usage
```xml
<com.sha.formvalidator.Form >
    <com.sha.formvalidator.widget.FormEditText 
    	app:validationType="email"
     />
    
    <com.sha.formvalidator.widget.FormCheckBox 
    	app:checkBoxValidation="checked"
     />
    
    <com.sha.formvalidator.widget.FormToggleButton 
    	app:toggleButtonValidation="on"
     />
    
    <com.sha.formvalidator.widget.FormSwitch 
    	app:switchValidation="on"
     />
</com.sha.formvalidator.Form>
```

To trigger validation:

```kotlin
val isFormValid = findViewById<Form>(R.id.form).validate()
```

In case you don't need `Form`, you can use `FormEditText` just like any regular field and you can trigger validation using:

```kotlin
val isValid = emailFormEditText.validate()
``` 

#### Note
You can nest the fields inside `Form` layout layout in any levels you need:

```xml
<com.sha.formvalidator.Form>
  
    <LinearLayout>
         <RelativeLayout>
                 <com.sha.formvalidator.widget.FormEditText 
                	app:validationType="email"
                  />
          </RelativeLayout>
    </LinearLayout>
</com.sha.formvalidator.Form>
```

## Installation

#### Gradle:
```groovy
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}

dependencies {
        // Core
        implementation 'com.github.ShabanKamell.FormValidator:core:x.y.z'
        // RxJava
        implementation 'com.github.ShabanKamell:FormValidator:x.y.z'
}
```

(Please replace x, y and z with the latest version numbers: [![](https://jitpack.io/v/ShabanKamell/FormValidator.svg)](https://jitpack.io/#ShabanKamell/FormValidator))

## Widgets
FormValidator has a collection of different widgets that implement `Validatable`. There're `TextView` widgets and other widgets.

### TextView Widgets

|         **Widget**           |                **Required attributes**             |      **Default**       |
| --------------------------   | -------------------------------------------------- | ---------------------  |
| **FormEditText**             |  see [TextView Validation](#textview-validation)   |        -               |
| **FormAutoCompleteTextView** |  see [TextView Validation](#textview-validation)   |        -               |

### Other Widgets

|         **Widget**           |                **Required attributes**             |      **Default**       |
| --------------------------   | -------------------------------------------------- | ---------------------  |
| **FormCheckBox**             |  checkBoxValidation                                |        checked         |
| **FormRatingBar**            |  ratingBarValidation                               |        required        |
| **FormSeekBar**              |  seekBarValidation                                 |        required        |
| **FormSwitch**               |  switchValidation                                  |        on              |
| **FormToggleButton**         |  toggleButtonValidation                            |        on              |

## Validatable interface
```Validatable``` is a fubctional interface implemented by widgets to support validation.

``` kotlin
interface Validatable {
    // return true if valid, false otherwise.
    fun validate(): Boolean
}
```
## Form Layout
`Form` is a LinearLayout that warps all widgets and provides APIs for triggering validation with options.

Declare `Form` in XML
``` kotlin
<com.sha.formvalidator.Form
        ..
        android:id="@+id/form"
        app:shakeOnError="true"
        app:ignoreHiddenFields="true"
        >
    <com.sha.formvalidator.widget.FormEditText 
    	app:validationType="email"
    ... />
    
    <com.sha.formvalidator.widget.FormCheckBox 
    	app:checkBoxValidation="checked"
    ... />
    
    <com.sha.formvalidator.widget.FormToggleButton 
    	app:toggleButtonValidation="on"
    ... />
    
    <com.sha.formvalidator.widget.FormSwitch 
    	app:switchValidation="on"
    ... />
</com.sha.formvalidator.Form>
   
```
Set options programmatically

``` kotlin
form.options = FormOptions.create {
       validationInterceptor = { .. }
       ignoreFieldsIds = listOf(R.id.etIgnoredId)
       ignoreHiddenFields = true
       shakeOnError = true
}
```

Trigger Validation

``` kotlin
val isValid = form.validate()

// OR
form.validateOnClick(btnValidateForm) { isValid -> ..}
```

## TextView Validation
FormValidator contains rich validators for validating ```TextView```. There're are 2 predefined widgets that inherit from `TextView`: `FormEditText` and `FormAutoCompleteTextView`. see full documentation in  👉 👉 [TextView Validation](https://github.com/ShabanKamell/FormValidator/blob/dev/TEXTVIEW.md)


### See 'sample' module for the full code.

### Credit
 [Android Form EditText](https://github.com/vekexasia/android-edittext-validator).
 
### 🛡 License
<details>
    <summary>
        click to reveal License
    </summary>
    
```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

</details>
