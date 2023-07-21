# Notice - likely to change in the near future

---
# Enums
- Enums are simple constructs in S++.
- Like C++, they are just a list of named constants.
- However, unlike C++, they are not limited to integers.

#### Example
```s++
enum MyEnum {
    A = 1, B = 2, C = 3,
    X = "Hello", Y = "World"
}
```
- Accessing items from enums must (currently) be done by `&` -- unmovable constants.