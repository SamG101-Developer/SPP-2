# Closures
- A closure is a function that can capture specified environment variables.
- Define with simple syntax: `(x, y) -> x + y`.

## Capturing
- Allow variables to be captured from the environment in which the closure was defined.
- Use the `[...]` syntax to capture variables from the environment.
- Optionally re-assign the captured variables with the `=` operator.
- The captured variables are available in the closure as if they were parameters.
- Captures can use parameter-passing conventions, as they are modelled as fixed parameters.
- Example:
```s++
let c = [&a, &b](x, y) -> (x + a + b) * y;
```

## Lambda Body
- Single expression, where the value generated from the expression is returned.
- Multiline lambdas are implemented by defining a nested function and calling it.
- Multiline lambdas are not supported because there already exists a way to do it.
