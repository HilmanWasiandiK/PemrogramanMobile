
�Q
o
SpinnerSelectionTest&com.example.android.phonenumberspinneriterateSpinnerItems2�����̢:��������I
�$android.support.test.espresso.PerformException: Error performing 'scroll to' on view 'Animations or transitions are enabled on the target device.
For more info check: http://goo.gl/qVu1yV

with id: com.example.android.phonenumberspinner:id/label_spinner'.
at android.support.test.espresso.PerformException$Builder.build(PerformException.java:84)
at android.support.test.espresso.base.DefaultFailureHandler.getUserFriendlyError(DefaultFailureHandler.java:81)
at android.support.test.espresso.base.DefaultFailureHandler.handle(DefaultFailureHandler.java:52)
at android.support.test.espresso.ViewInteraction.waitForAndHandleInteractionResults(ViewInteraction.java:312)
at android.support.test.espresso.ViewInteraction.desugaredPerform(ViewInteraction.java:167)
at android.support.test.espresso.ViewInteraction.perform(ViewInteraction.java:110)
at com.example.android.phonenumberspinner.SpinnerSelectionTest.iterateSpinnerItems(SpinnerSelectionTest.java:57)
at java.lang.reflect.Method.invoke(Native Method)
at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
at android.support.test.rule.ActivityTestRule$ActivityStatement.evaluate(ActivityTestRule.java:433)
at org.junit.rules.RunRules.evaluate(RunRules.java:20)
at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
at org.junit.runners.Suite.runChild(Suite.java:128)
at org.junit.runners.Suite.runChild(Suite.java:27)
at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
at org.junit.runner.JUnitCore.run(JUnitCore.java:115)
at android.support.test.internal.runner.TestExecutor.execute(TestExecutor.java:58)
at android.support.test.runner.AndroidJUnitRunner.onStart(AndroidJUnitRunner.java:375)
at android.app.Instrumentation$InstrumentationThread.run(Instrumentation.java:2243)
Caused by: java.lang.RuntimeException: Action will not be performed because the target view does not match one or more of the following constraints:
(view has effective visibility=VISIBLE and is descendant of a: (is assignable from class: class android.widget.ScrollView or is assignable from class: class android.widget.HorizontalScrollView or is assignable from class: class android.widget.ListView))
Target view: "AppCompatSpinner{id=2131296340, res-name=label_spinner, visibility=VISIBLE, width=234, height=48, has-focus=false, has-focusable=true, has-window-focus=true, is-clickable=true, is-enabled=true, is-focused=false, is-focusable=true, is-layout-requested=false, is-selected=false, layout-params=android.support.constraint.ConstraintLayout$LayoutParams@219d5dd, tag=null, root-is-layout-requested=false, has-input-connection=false, x=534.0, y=48.0, child-count=1}"
at android.support.test.espresso.ViewInteraction.doPerform(ViewInteraction.java:238)
at android.support.test.espresso.ViewInteraction.access$100(ViewInteraction.java:62)
at android.support.test.espresso.ViewInteraction$1.call(ViewInteraction.java:149)
at android.support.test.espresso.ViewInteraction$1.call(ViewInteraction.java:146)
at java.util.concurrent.FutureTask.run(FutureTask.java:266)
at android.os.Handler.handleCallback(Handler.java:883)
at android.os.Handler.dispatchMessage(Handler.java:100)
at android.os.Looper.loop(Looper.java:230)
at android.app.ActivityThread.main(ActivityThread.java:7880)
at java.lang.reflect.Method.invoke(Native Method)
at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:526)
at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:1034)java.lang.RuntimeException�$android.support.test.espresso.PerformException: Error performing 'scroll to' on view 'Animations or transitions are enabled on the target device.
For more info check: http://goo.gl/qVu1yV

with id: com.example.android.phonenumberspinner:id/label_spinner'.
at android.support.test.espresso.PerformException$Builder.build(PerformException.java:84)
at android.support.test.espresso.base.DefaultFailureHandler.getUserFriendlyError(DefaultFailureHandler.java:81)
at android.support.test.espresso.base.DefaultFailureHandler.handle(DefaultFailureHandler.java:52)
at android.support.test.espresso.ViewInteraction.waitForAndHandleInteractionResults(ViewInteraction.java:312)
at android.support.test.espresso.ViewInteraction.desugaredPerform(ViewInteraction.java:167)
at android.support.test.espresso.ViewInteraction.perform(ViewInteraction.java:110)
at com.example.android.phonenumberspinner.SpinnerSelectionTest.iterateSpinnerItems(SpinnerSelectionTest.java:57)
at java.lang.reflect.Method.invoke(Native Method)
at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
at android.support.test.rule.ActivityTestRule$ActivityStatement.evaluate(ActivityTestRule.java:433)
at org.junit.rules.RunRules.evaluate(RunRules.java:20)
at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
at org.junit.runners.Suite.runChild(Suite.java:128)
at org.junit.runners.Suite.runChild(Suite.java:27)
at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
at org.junit.runner.JUnitCore.run(JUnitCore.java:115)
at android.support.test.internal.runner.TestExecutor.execute(TestExecutor.java:58)
at android.support.test.runner.AndroidJUnitRunner.onStart(AndroidJUnitRunner.java:375)
at android.app.Instrumentation$InstrumentationThread.run(Instrumentation.java:2243)
Caused by: java.lang.RuntimeException: Action will not be performed because the target view does not match one or more of the following constraints:
(view has effective visibility=VISIBLE and is descendant of a: (is assignable from class: class android.widget.ScrollView or is assignable from class: class android.widget.HorizontalScrollView or is assignable from class: class android.widget.ListView))
Target view: "AppCompatSpinner{id=2131296340, res-name=label_spinner, visibility=VISIBLE, width=234, height=48, has-focus=false, has-focusable=true, has-window-focus=true, is-clickable=true, is-enabled=true, is-focused=false, is-focusable=true, is-layout-requested=false, is-selected=false, layout-params=android.support.constraint.ConstraintLayout$LayoutParams@219d5dd, tag=null, root-is-layout-requested=false, has-input-connection=false, x=534.0, y=48.0, child-count=1}"
at android.support.test.espresso.ViewInteraction.doPerform(ViewInteraction.java:238)
at android.support.test.espresso.ViewInteraction.access$100(ViewInteraction.java:62)
at android.support.test.espresso.ViewInteraction$1.call(ViewInteraction.java:149)
at android.support.test.espresso.ViewInteraction$1.call(ViewInteraction.java:146)
at java.util.concurrent.FutureTask.run(FutureTask.java:266)
at android.os.Handler.handleCallback(Handler.java:883)
at android.os.Handler.dispatchMessage(Handler.java:100)
at android.os.Looper.loop(Looper.java:230)
at android.app.ActivityThread.main(ActivityThread.java:7880)
at java.lang.reflect.Method.invoke(Native Method)
at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:526)
at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:1034)"�

logcatandroid�
�C:\Users\as\Documents\Semester 4\AndroStudRepository - Semester 4\PhoneNumberSpinnerEspresso\app\build\outputs\androidTest-results\connected\V2029 - 10\logcat-com.example.android.phonenumberspinner.SpinnerSelectionTest-iterateSpinnerItems.txt"�

device-infoandroid�
�C:\Users\as\Documents\Semester 4\AndroStudRepository - Semester 4\PhoneNumberSpinnerEspresso\app\build\outputs\androidTest-results\connected\V2029 - 10\device-info.pb"�

device-info.meminfoandroid�
�C:\Users\as\Documents\Semester 4\AndroStudRepository - Semester 4\PhoneNumberSpinnerEspresso\app\build\outputs\androidTest-results\connected\V2029 - 10\meminfo"�

device-info.cpuinfoandroid�
�C:\Users\as\Documents\Semester 4\AndroStudRepository - Semester 4\PhoneNumberSpinnerEspresso\app\build\outputs\androidTest-results\connected\V2029 - 10\cpuinfo�
i
SpinnerSelectionTest&com.example.android.phonenumberspinneruseAppContext2�������:�������"�

logcatandroid�
�C:\Users\as\Documents\Semester 4\AndroStudRepository - Semester 4\PhoneNumberSpinnerEspresso\app\build\outputs\androidTest-results\connected\V2029 - 10\logcat-com.example.android.phonenumberspinner.SpinnerSelectionTest-useAppContext.txt"�

device-infoandroid�
�C:\Users\as\Documents\Semester 4\AndroStudRepository - Semester 4\PhoneNumberSpinnerEspresso\app\build\outputs\androidTest-results\connected\V2029 - 10\device-info.pb"�

device-info.meminfoandroid�
�C:\Users\as\Documents\Semester 4\AndroStudRepository - Semester 4\PhoneNumberSpinnerEspresso\app\build\outputs\androidTest-results\connected\V2029 - 10\meminfo"�

device-info.cpuinfoandroid�
�C:\Users\as\Documents\Semester 4\AndroStudRepository - Semester 4\PhoneNumberSpinnerEspresso\app\build\outputs\androidTest-results\connected\V2029 - 10\cpuinfo*�
c
test-results.logOcom.google.testing.platform.runtime.android.driver.AndroidInstrumentationDriver�
�C:\Users\as\Documents\Semester 4\AndroStudRepository - Semester 4\PhoneNumberSpinnerEspresso\app\build\outputs\androidTest-results\connected\V2029 - 10\testlog\test-results.log 2
text/plain