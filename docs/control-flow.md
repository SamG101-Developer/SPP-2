# Control Flow
## Selection
### Unified condition expressions
- Combines the `if` and `match` statements into a unified conditional.
- See the [**unified conditional expression**](./unified-conditional-expression.md) section for more information.

## Iteration
### Iteration expressions (for, while, do-while, break, continue)
- See the [**iteration expressions**](./iteration-expressions.md) section for more information.

## Return
### The `ret` Statement
#### Structure
- The `ret` statement is used to return a value from a function.
- The `ret` statement is optionally followed by an expression.
- The expression is evaluated and returned from the function.
- A function can contain multiple `ret` statements.

#### Example:
```s++
fn foo() -> Num {
    ret 1;
}
```

#### Notes:
- If the `ret` statement is not followed by an expression, then the function must return `std::Void`
