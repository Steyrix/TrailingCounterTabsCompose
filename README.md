I recently noticed that current Compose has LeadingIconTab composable, which is great. 
But there is no alternative for cases, when I want my icon (or badge) to be trailing.
I created my own implementation of tabs view with trailing counters.

Solution is pretty hacky since it uses offsets and raw numbers, 
but you are free to edit adjusting yourself.

https://github.com/user-attachments/assets/507d3794-a002-426c-a776-fe999829cdfb

