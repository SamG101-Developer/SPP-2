# Control Flow
## Selection
### Unified condition expression
- Combines the `if` and `match` statements into a unified conditional.
- See the [pattern matching section](pattern-matching.md) for more information.

## Iteration
### The `while` Statement
#### Structure
- The `while` statement is used to conditionally and repetitively execute a block of code
- The `while` statement is followed by a condition
- The condition must evaluate to a boolean expression
- The condition is optionally followed by a `loop tag`
- The condition is followed by a block of code
- The block of code is executed if the condition evaluates to `true`
- The block of code is optional -- ie can be `{}`

#### Example:
```s++
while x < y as 'outer_loop {
    do_something(x, y);
}
```

#### Assignment from a `while` statement
- See the [break section]()

### The `for` Statement
#### Structure
- The `for` statement is used to repetitively execute a block of code
- The `for` statement requires n variable identifiers (where n is the number of iterators)
- The `for` statement is followed by an iterable expression
- The iterable expression is optionally followed by a `loop tag`
- The iterable expression is followed by a block of code
- The block of code is executed for each item in the iterable expression
- The block of code is optional -- ie can be `{}`
- The block of code is executed with the variables bound to the current item in the iterable expression
- The bound variable cannot be reassigned within the block of code

#### Example:
```s++
for x, y in z.iter() as 'outer_loop {
    do_something(x, y);
}
```

#### Notes:
- Multiple iterators can be used because of auto-unpacking -- internally, `let` statements bind the variables

#### Assignment from a `for` statement
- See the [break section]()

### The `do-while` Statement
#### Structure
- The `do-while` statement is used to repetitively execute a block of code
- The `do` statement is followed by a block of code
- The block of code is executed at least once
- The block of code is followed by a `while` statement
- The `while` statement is followed by a condition
- The condition must evaluate to a boolean expression
- The condition is optionally followed by a `loop tag`

#### Example:
```s++
do as 'outer_loop {
    do_something(x, y);
} while x < y;
```

#### Assignment from a `do-while` statement
- See the [break section]()


### The `break` Statement
#### Structure
- Break from a loop
- Can specify which loop to break from using a `loop tag`
- Can return a variable out of the loop

#### Example:
```s++
let x, y = while x < y as 'outer_loop {
    if x > z {
        break 'outer_loop x, y;
    }
    x += 1;
};
```

### The `continue` Statement
#### Structure
- Continue to the next iteration of a loop
- Can specify which loop to continue from using a `loop tag`


## Return
### The `return` Statement
#### Structure
- The `return` statement is used to return a value from a function
- The `return` statement is optionally followed by an expression
- The expression is evaluated and returned from the function
- A function can contain multiple `return` statements

#### Example:
```s++
fn foo() -> int {
    return 1;
}
```

#### Notes:
- If the `return` statement is not followed by an expression, then the function must return `std::Void`

### The `yield` Statement
- See [Generators]()
