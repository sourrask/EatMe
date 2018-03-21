Besides for the control panel, nothing should be able to be accessed by anything else. Some classes
do extend ArrayList, thus those methods will be public, but as long as the objects extending it are
unable to be accessed by anything else outside the package, it is fine.

Furthermore, only the controlpanel needs to make sure that no null parameters are passed on. The
internal code from all classes in the package assume that no null will be passed, but if there is, a
NullPointerException will be thrown. The unit tests therefore test that null values throw a
NullPointerException.