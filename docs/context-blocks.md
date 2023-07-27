# Context blocks
- Takes from Python's `with` statement with nearly identical syntax.

#### Structure
- Starts with the `with` keyword.
- Followed by an expression.
- The expression's resulting object must super-impose the `std::ops::With` class.
- The expression's resulting object's `__enter__` method is called.
- The `__enter__` method can return a value.
- This value is obtained by the `as alias` additional syntax.
- The statements inside the context block are executed.
- The `__exit__` method is called.

#### Example
```s++
fn test() -> Void {
    with std::File::open("foo.txt") as file {
        file.write("Hello, world!")
        file.read() |> std::print
    }
}
```
- The `open` method will return a `std::File` object.
- The `std::File::__enter__` method will return itself, and additional config checks are made.
- The `std::File::__exit__` method will flush and close the file, and additional config checks are made.