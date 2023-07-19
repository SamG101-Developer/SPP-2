# Control Flow
## Selection
### The `if` Statement
#### Structure
- The `if` statement is used to conditionally execute a block of code
- The `if` statement is followed by a condition
- The condition must evaluate to a boolean expression
- The condition is followed by a block of code
- Inline variable definitions can precede the condition
- The block of code is executed if the condition evaluates to `true`
- The block of code is optional -- ie can be `{}`

#### Example:
```s++
if let x = f(), let y = g(), x.a > y.b {
    do_something(x, y);
}
```

### The `elif` Statement
#### Structure
- Same as `if`, just replace `if` with `elif`
- Must follow an `if` statement
- Can be any number of `elif` statements

#### Example:
```s++
if x < y {
    do_something(x, y);
} elif x > y {
    do_something_else(x, y);
} elif x < y / 2 {
    do_something_else_again(x, y);
}
```

### The `else` Statement
#### Structure
- The `else` statement is used to execute a block of code if no other conditions are met
- The `else` statement must follow an `if` or `elif` statement
- The `else` statement is followed by a block of code
- The block of code is executed if no other conditions are met
- The block of code is optional -- ie can be `{}`
- There can only be one `else` statement
- The `else` statement must be the last statement in the selection
- The `else` statement is optional

#### Example:
```s++
if x < y {
    do_something(x, y);
} elif x > y {
    do_something_else(x, y);
} else {
    do_something_else_again(x, y);
}
```

### Assignment from an `if` statement
#### Structure
- If an `if` statement is going to be used to assign a value (known by a preceding `let`), then some conditions must be met
- Each branch must have a final condition, whose types are all the same
- There must be an `else` statement (and multiple optional `elif` statements)

#### Example:
```s++
let x = if y < z {
    1;
} elif y > z {
    2;
} else {
    3;
};
```

#### Notes
- Because the `IfStatement` is a `PrimaryExpression`, it final `}` can be followed by further postfix expressions, 
such as `.foo` or `()`, allowing chaining.


### The `match` Statement
- See [Pattern Matching]()


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
