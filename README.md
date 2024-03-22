I think we can ultimately solve this using recursion, we have a function that will take array of int and a parent view and if I had more time I would be able to handle it.

Here are the steps I would take


1) Check the size of the list

2) if size is 1 we add to the view to the parent making sure to match parent with and wrap content height

3) if size is 2 we add to the view making sure we create 2 child views that are size based on the value in relation to the parent

4) if size is 3 or more we split the list in half and call the the function again recursively



This process should produce a parent UI with a visual tree map

Thank you for your time and your consideration :)
