# S++
![Logo](./docs/_SPP_icon.png)
#### Overview
- Simplicity, Speed & Safety
- Static and Strongly typed
- Compiled
- Object Oriented

#### Features
- Classes, Enums, Functions
- Super-imposing based inheritance
- Generics, Variadic, Type-Constraints
- Binary-operator overloading
- Decorators/Annotations
- Range of statements from Python, Rust, C++
- Modules

#### Safety
- Compile time memory & type safety checks
- 2nd class references for trivial lifetime analysis
- No pointers are usable in the language
- Safe C API for lowest level operations

### Known issues
- Currently the parser requires `;` to follow statement `{}`, but hopefully this won't be the case in the end.
- Iteration is incredibly difficult with 2nd class references -> update to stream-based API, remove the `for` loop.
- Concurrency might need some more idea expansions on.
