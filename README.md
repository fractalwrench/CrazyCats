Crazy Cats - Android App Client
======
Crazy Cats is an Android App which uses the Reddit API to display a scrollable list of Cat
images, from the r/cats subreddit.

Explanation
======
The app follows a Model-View-Presenter architecture, and uses Dagger2 + RxJava2.
Instrumentation tests should be run on the mock productFlavor, whereas the JVM tests can be run on
any build variant. The live app (which points at the Reddit API) should be run using the liveDebug
variant.


Future Improvements
======
- Improved error reporting, so that different causes (e.g. No network) led to different messages
for the user.
- Allow the user to select which subreddit is displayed.
- Support infinite scrolling by detecting via a ScrollListener when the user is near the bottom
of the current page of data.
- Add Shared Element Activity transitions for the displayed images.


- Use "Loading", "Content", "Error" wrapper object (see https://tech.instacart
.com/lce-modeling-data-loading-in-rxjava-b798ac98d80)