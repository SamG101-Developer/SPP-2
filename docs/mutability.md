# Mutability
## Variables
- `let x = 1`: `x` is an immutable `std::Num`. It cannot have its value changed. Because it is immutable, `&mut`
  mutable references cannot be taken, meaning that methods of `std::Num` that use `self: &mut Self` cannot be used
  either.
- `let mut x = 1`: `x` is a mutable `std::Num`. It can have its value changed. Because it is mutable, `&mut` mutable
  references can be taken, meaning that methods of `std::Num` that use `self: &mut Self` can be used.

## Members
```s++
cls A {
    mut x: std::Num;
    y: std::Str;
}
```
- `x` can only be changed if the type of `A` being used is `mut`.
- `y` can never be changed, even with a `mut A` type, because it's declared immutable.

## References
- `&Type` immutable references can be taken to any type, and can be used to call methods that take `self: &Self`
- `&mut Type` mutable references can only be taken to mutable types, and can be used to call methods that take
  `self: &mut Self`
- The mutability of a variable can be different to the mutability of the reference:
- `fun function(a: &mut A, mut b: &mut A, c: &A, mut d: &A) -> ...`
  - `a` can not be re-assigned, but can be used to call methods that take `self: &mut Self`
  - `b` can be re-assigned, and can be used to call methods that take `self: &mut Self`
  - `c` can not be re-assigned, and can not be used to call methods that take `self: &mut Self`
  - `d` can be re-assigned, but can not be used to call methods that take `self: &mut Self`
