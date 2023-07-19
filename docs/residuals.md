# Residuals
#### Examples:
- `std::Opt[T]`
- `std::Ret[T, E]`

### Early return
#### Shorthand
- Use the postfix `?` operator to return early if the result is the residual error value
- The `?` operator can be used on any object that super-imposes the `std::ops::Try` class

#### Else clause
- The `else` clause can be added to a `let` statement to specify a block of code to execute if the value is the residual error value
- Allows for a more complex early return, for example, to return a different error value
