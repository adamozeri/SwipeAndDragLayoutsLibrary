# Swipe and Drag Layouts Library
### Overview
The Layouts Library for Android provides a customizable and flexible solution for implementing swipeable and draggable card stacks in your app. It includes functionality for dragging items to reorder and swiping items to remove them, as well as customizable scaling and stacking behavior.

### Components
#### 1. CustomizableCardAdapter
A RecyclerView adapter for displaying cards with customizable layouts and bindings.

##### Constructor:

```java
public CustomizableCardAdapter(List<T> items, int cardLayoutResId, OnBindViewCallback<T> onBindViewCallback)
```

##### Methods:

```java
onItemMove(int fromPosition, int toPosition) 
removeItem(int position)
```

##### Interface:
```java
public interface OnBindViewCallback<T> {
    void onBindView(View itemView, T item);
}
```
#### 2. DragAndSwipeCallback
A callback for enabling drag-and-drop and swipe-to-dismiss functionalities in a RecyclerView.

##### Constructor:

```java
public DragAndSwipeCallback(CustomizableCardAdapter adapter, SwipeListener swipeListener,
                            boolean dragEnabled, boolean swipeEnabled,
                            boolean enableLeftSwipe, boolean enableRightSwipe,
                            boolean enableUpSwipe, boolean enableDownSwipe)
```
##### Methods:
```java
getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) // Defines movement flags for dragging and swiping.
onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) // Handles item dragging.
onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) // Handles item swiping.
```
#### 3. ItemTouchHelperAdapter
An interface for adapters that support item drag-and-drop and swipe-to-dismiss.

##### Methods:

```java
void onItemMove(int fromPosition, int toPosition);
void removeItem(int position);
```
#### 4. ScalingItemDecoration
An ItemDecoration for scaling items based on their distance from the center of the RecyclerView.

##### Constructor:

```java
public ScalingItemDecoration(float scaleFactor)
```
Methods:
```java
onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) // Applies scaling transformation to items.
```

#### 5. StackLayoutManager
A custom LayoutManager that stacks items with an overlap.

##### Constructor:

```java
public StackLayoutManager(int overlap)
```
##### Methods:
```java
onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) // Layouts and stacks items with overlap.
```
#### 6. SwipeListener
An interface for handling swipe events.

##### Interface:

```java
public interface SwipeListener {
    void onSwiped(int position, int direction);
}
```

### Usage

##### Step 1: Add Maven Repository
Add the following code to the dependencyResolutionManagement section in your project's settings.gradle file:

```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```
##### Step 2: Add Dependency
Add the library to your project’s dependencies
```groovy
 dependencies {
    implementation 'com.github.adamozeri:SwipeAndDragLayoutsLibrary:Tag'
}
```

##### Step 3: Set Up Adapter
Create and configure your adapter with a layout resource and binding callback.

```java
CustomizableCardAdapter<MyItem> adapter = new CustomizableCardAdapter<>(
    items,
    R.layout.card_layout,
    (itemView, item) -> {
        // Bind item to view
    }
);
```

##### Step 4: Configure RecyclerView
Set up your RecyclerView with the StackLayoutManager and the CustomizableCardAdapter.

```java
RecyclerView recyclerView = findViewById(R.id.recyclerView);
StackLayoutManager layoutManager = new StackLayoutManager(20);
recyclerView.setLayoutManager(layoutManager);
recyclerView.setAdapter(adapter);
```

##### Step 5: Add Drag and Swipe Callback
Create and attach the DragAndSwipeCallback to your RecyclerView.

```java
DragAndSwipeCallback callback = new DragAndSwipeCallback(
    adapter,
    (position, direction) -> {
        // Handle swipe event
    },
    true,  // Enable drag
    true,  // Enable swipe
    true,  // Enable left swipe
    true,  // Enable right swipe
    true,  // Enable up swipe
    true   // Enable down swipe
);


ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
touchHelper.attachToRecyclerView(recyclerView);
```
##### Step 6: Add Scaling Decoration
Optionally, add ScalingItemDecoration for scaling effects.

```java
ScalingItemDecoration scalingDecoration = new ScalingItemDecoration(0.5f);
recyclerView.addItemDecoration(scalingDecoration);
```

### Contributing
If you'd like to contribute to the development of this library, please fork the repository and submit a pull request with your changes.

### Contact
For any questions or issues, please contact [adamozeri.dev@gmail.com].
