### 1

Given this code:

```javascript
for(var i = 100; i >= 0; i--) {
  console.log(...);
}
```

Replace the `...` so that the code outputs the numbers from 0 to 100 ordered.

Don't worry about formatting it in a pretty way. We just care about the numbers being there.

### 2

Create a for loop that iterates up to 100 while outputting "fizz" at multiples of 3, "buzz" at multiples of 5 and "fizzbuzz" at multiples of 3 and 5;

### 3

It's simple—you click on one of three boxes to see what nice thing you've won. You always win something nice. Because we love you.

Here's what we have so far. Something's going wrong though. Can you tell what it is?

```html
<button id="btn-0">Button 1!</button>
<button id="btn-1">Button 2!</button>
<button id="btn-2">Button 3!</button>

<script type="text/javascript">
    var prizes = ['A Unicorn!', 'A Hug!', 'Fresh Laundry!'];
    for (var btnNum = 0; btnNum < prizes.length; btnNum++) {
        // for each of our buttons, when the user clicks it...
        document.getElementById('btn-' + btnNum).onclick = function() {
            // tell her what she's won!
            alert(prizes[btnNum]);
        };
    }
</script>
```

You can try this out [here](https://jsbin.com/ponekuvexu/edit?html,output)!

### 4

What tools and techniques do you use debugging JavaScript code?

### 5

What tools would you use to test your code's functionality?
