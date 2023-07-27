# Functions
## Overview of functions
- Functions can have [decorators](./decorators.md), that wrap the function in extra behaviour.
- Functions can have [generic parameters & constraints]().
- Functions can have [parameters]().
- Functions have a return type.
- Functions can have a [where block]().
- Functions can have a [value guard]().
- Functions have a body.

### Generic parameters & constraints
- Functions can have generic parameters, defined after the function name.
- Generic types must not shadow enclosing an enclosing class's generic type names.
- Generic types can have constraints, defined after the generic type name, or in a `where` block.
- Optional and variadic generic types can be defined.
#### Example
```s++
fn foo[T: Copy + ops::Add](a: &T, b: &T) -> T {
    ret a.copy() + b.copy()
}
```

### Decorators
- See [decorating a method](./decorators.md)

### Parameters
#### Required parameters
- Parameters are listed with their name and type.
- These are parameters that must be given when calling the function.
- Parameters can access other parameters defined before them.
- Required parameters are the first parameters defined.

#### Optional parameters
- Parameters can be optional and have a default value (the type is still required for consistency & upcasting).
- Optional parameters must be defined after all required parameters
- Optional parameters can be accessed as named arguments, and can be given in any order.

#### Variadic parameters
- There can be a single variadic parameter, which must be the last parameter.
- It can either have a variadic type (all types can be different), or a single type that all the arguments must be.
- Fix all parameters to `Num` types: `func[T](...args: Num)`.
- Fix all parameters to the same `T` generic type: `func[T](...args: T)`.
- Allow all parameters to be different generic types: `func[...Ts](...args: Ts)`.
- Effectively the same as Python's `*args`, just replace `*` with `...`.

#### Parameter passing conventions
- Parameters can be passed by value, reference, or mutable reference.
- Passing by value is the default (destructive move).
- Passing by reference is specified with `&`.
- Passing by mutable reference is specified with `&mut`.

### Return type
- Functions must have a return type.
- Returning nothing must return `Void` (`std.Void`) -> return types are not optional.

### Where block
- Additional constraints to generic parameters can be specified in a where block.
- Compile time function selection is based on the where clause & other constraints.
- The where block is optional, and follows the function's return type.
- The where block is specified in brackets: `where [...]`.
- See [Where block type constraints]() for more information.

#### Example of converting into constrained generics:
- `fn foo(a: Num, b: Num) -> Num {}`.
- `fn foo[T, U](a: T, b: U) -> Num where [T: Num, U: Num] {}`.
- Using generics allows multiple constraints to be specified on a single type.

### Value guard
- Value guards are optional and follow the where clause in a function prototype.
- Allows for runtime selection based on the value of parameters.
- Specified as a simplified if-statement.
- If a value guard is used, there must be an unguarded function with an equivalent signature.
- All functions with the same signature and different value guards must return the same type.
  - Compiler must know the return type of a function at compile time.

#### Example:
```s++
fn foo[U, T: ops::Gt[RHS=U]](a: T, b: U) -> Num if a > b {}
```

## Function types
- 3 function types in the STL - `Fn`, `FnMut`, `FnOnce` (same as Rust)
- `FnRef` is the default function type -> `self` is an immutable reference: `self: &Self`
- `FnMut` -> `self` is a mutable reference: `self: &mut Self`
- `Fn` -> `self` is moved into the function (self-consuming)
- Free function / static method -> `self` is not defined => use `FnRef` as it takes no ownership of `self`

## Function overloading
### What differentiates functions => allows for overloading
- Number of parameters
- Types of parameters
- Number of generic type parameters
- Constraints on generic type parameters
- Value guard on functions

### What can't make a function an overload => erroneous function definition
- Return type
- Decorators
- Generic type parameter names
- Parameter names

### Overload conflict resolution (compile time)
- Compile time checks for unknown function resolution
  - 2 functions with the same name and signature
  - 2 functions with the same name and different return type
  - 2 functions with same parameters, one with extra optional parameters

## Partial functions
- Use the placeholder `_` to reserve a parameter for later
- Reserve the variadic slot parameter with a single `_` too -- the compiler knows when variadics are being filled /
  reserved based on function signatures.

```s++
fn add(a: Num, b: Num, c: Num, d: Num = 0) -> Num {
    ret a + b + c
}
    
fn main() {
    let a = add(1, 2, _)
    io::println(a(3))  # 6
    
    let b = add(_, 2, _, _)
    io::println(b(1, 3, 1))  # 7
}
```

## Variadic function, parameter packs
### Variadic function call
Take the following function:
```s++
function func_variadic_helper_0[T](...a: T) -> Void {}
```

It can be called in the following ways:
- `func_variadic_helper_0(...a)` -> calls `func_variadic_helper_0` with a single parameter pack
- `func_variadic_helper_0(a)` -> calls `func_variadic_helper_0` with a single tuple type


## Function calling
- Function calls are specified by the function name followed by parentheses.
- Function arguments can have a parameter passing convention (none, `&`, or `&mut`).
- Normal arguments must be specified in order before anything else.
- Optional parameters can be in any order, as long as they are "named".
- If there are required, optional and variadic parameters, then optional arguments must all be specified, unnamed,
  before the variadic arguments.


## Other information
#### Entry point to a program
```s++
fn main() -> Void {}
```

#### Declaration vs definition
- Functions are never declared, only defined
- Implementation must be provided
- Order of definition doesn't matter in a module

#### Base class resolution
- If two base classes have the same signature for a method, and these classes are inherited into a sub-class, that 
  doesn't override the method, then there is an ambiguity issue
- Any attempt to call the method will result in a compile time error
- 2 ways to resolve:
  - Override the method in the sub-class
  - Upcast the class to the correct super-type, and call the method on that => `upcast<A>(c).foo(1)`

#### Pure functions (TODO)
- Pure functions that:
  - Have no mutable parameters.
  - Don't call any functions that aren't pure.
- All C functions are modelled as impure.
- The compiler knows when a function is "pure" and can apply optimisations.


## Other function features
- [Struct methods](./super-imposition.md)
- [Closure expressions](./closures.md)
