# FieldControlledClickListener

This library is aimed to ease controlling views(EditText, Spinner and CheckBox).
It is a really simple library, it only controls EditText if it is empty,
Spinner if its first row is selected, CheckBox if it is checked, nothing more.
You just simply put all the view you want to control alongside with error message
and leave the rest to library.

# Include in your project

```gradle
dependencies {
    compile 'com.layemut.fieldcontrolledclicklistener:fieldcontrolledclicklistener:0.1@aar'
}
```

# Example

```java
  loginView.setOnClickListener(new FieldControlledClickListener(this,
                // Views to be controlled alongside with error messages if control fails
                new Controlee().withView(userCodeView).withErrorMessage("Please enter your user code."),
                new Controlee().withView(newPinView).withErrorMessage("Please enter your new pin."))
                // If control succeeds, do what you want.
                .setControlListener(new FieldControlledClickListener.ControlListener() {
                    @Override
                    public void onControlSuccess() {
                        userCode = userCodeView.getText().toString();
                        newPin = newPinView.getText().toString();
                        sendLoginRequestToAPI();
                    }
                }));
```
Simple as that!

# License

    Copyright 2016 Özcan Candağ

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.