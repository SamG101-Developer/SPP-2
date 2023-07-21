# Variables
#### Declaring a variable
- Declare a variable with the `let` keyword
- Variable default to immutable - use `mut` to make mutable (see [**mutability**](./mutability))
- Provide an expression, and the variable-type will be inferred: `let variable = a.b.c().d`
- Provide no assignment, but a type - variable is unusable until assigned: `let variable: std::number`

#### Multiple variable binding
- Multiple variables can be defined at once through unpacking a tuple
- If values are provided, the types can be different: `let a, b, c = (1, "two", '3')`
- If no values are provided, the types must be the same: `let a, b, c: std::number`

#### More values on RHS than LHS
- If there are more values on the RHS than LHS, then there is an error, as there are too many values to unpack
- For example, `let a, b = (1, 2, 3)` is an error

#### More values on LHS than RHS
- If there are more values on the LHS than RHS, then there is an error, as there are too few values to unpack
- For example, `let a, b, c = (1, 2)` is an error
- Only one level of unpacking is allowed - `let a, b, c = (1, (2, 3))` is an error
  - Design decision to keep the language simple, and remove ambiguities concerning which inner tuple would unpack
  - Use `let a, b, c = (1, 2, 3)`

#### Declaring a variable with no value
- Uninitialized and cannot be used until set
- Requires a type annotation to be provided: `let a: std::number`
- Can be set later with `a = 5`

#### Re-declaring
- Variables can be re-declared with the same or a different type
- Variables re-declared in an inner-scope return to original type/value from outer-scope

```s++
fun main():
    let x = 5;
    
    with temp() {
        let x = x + 10;
        std::io::print("inner scope: {x}");
    };
    
    std::io::print("outer scope: {x}");
```

- Because a redeclaration was used, `x == 15` in the inner scope, but `x == 5` in the outer scope
- If `x = x + 10` had been used instead of `let x = x + 10`, then `x == 15` in both scopes (would need `let mut`)
- Re-declaring also allows a different type to be assigned to `x`
    - Assigning a new value to a `mut` variable requires the type matches the original type
    - If `let a = 5;` is used, then anything on the rhs of `a =` must be a `std::Num`

#### Residuals
- Variables of types super-imposing `std::ops::Try` -- ie `std::Opt[T]`, `std::Ret[T, E]` etc -- can have early return with the `?` operator
- They can also have an `else` block that allows for more manipulation -- see [**residuals**](./residuals)

