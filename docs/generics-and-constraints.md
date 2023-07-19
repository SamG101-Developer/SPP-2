# Generics & Constraints
## Generics
### Overview of generics
- Can be defined over a class or function
- Can be used in the same way as any other type
- Can be constrained to implement certain classes

---

### Generic definitions
#### Classes
```s++
cls Vector[T]:
    ...
```
- The generic `T` is accessible to the entire class
- The generic `T` can be constrained (see below)
- Compile time check to ensure no methods shadow the generic type `T`
- Attributes can use the generic type `T`

<BR>

#### Functions
```s++
fun test_fun<U>(a: U):
    ...
```
- The generic `U` is accessible to the entire function
- The generic `U` can be constrained (see below)
- Parameters, local variables, the return type, etc can use the generic type `U`

<BR>

#### Class methods
```s++
sup[T] Vector[T]:
    @meta::public
    function emplace_back(self: &mut Self, value: T):
        ...
```
- The generic `T` is accessible to the method from the class
- The generic `T` can be constrained specifically for this method with a `where` clause (see below)

<BR>

#### Sup definitions - see [super-imposing]()
```s++
sup[T] Vector[T]:
    ...
```
- Allows for super-imposing a class with a generic type
- Can constrain the generic type `T` to implement methods when certain constraints are satisfied (see below)

<BR>

#### Enum definitions
```s++
enum Foo[T]:
    ...
```

---

### Generic inference
#### Classes
1. If an attribute has the same type as the generic type, the generic type can be inferred from the struct initializer
2. If a static method uses generic type is used as a parameter, the generic type can be inferred from the parameter
3. Otherwise, the generic type must be explicitly defined

```s++
cls Wrapper[T]:
    value: T;
    
sup[T] Wrapper[T]:
    @std::static_method
    pub fun new(value: T):
        let w = Wrapper{value: value};
    
fun main():
    // 1. Inferred from the initializer
    let wrapper = Wrapper{value: 5};
    
    // 2. Inferred from the parameter
    let wrapper1 = Wrapper::new(5);
```

#### Functions
1. If a parameter has the same type as the generic type, the generic type can be inferred from the function call
2. Otherwise, the generic type must be explicitly defined

```s++
fun test_fun[T](a: T):
    ...

fun main():
    // 1. Inferred from the parameter
    test_fun(5);
```

#### Deferred inference
- In languages like Rust, the type parameter for `Vec` can deferred inference until the vector is used.
- This is not the case in S++; the type parameter must be specified when the vector is created.
- This is a design to
  1. Reduce the complexity of the compiler
  2. Enforce a strict and more explicit type system

---
### Optional generic types
- Generics can be optional, by assigning a default type to the generic type
- If the generic type is not specified, the default type is used
- Optional generic types can be assigned in any order

```s++
cls Foo[T=std::Number, U=std::String]:
    a: T;
    b: U;
    
fun main():
    let f = Foo[U=std::Number]{...};
```
- (In this case inference would be used to determine the type of `T` and `U`)

---

### Variadic generics types
- Generics can be variadic, by using the `...` symbol before the generic type
- This allows for a parameter back to contain multiple different types

```s++
cls Foo;
sup Foo:
    pub fun new<...Ts>(...args: Ts);
```

---

## Constraints
#### Overview of constraints
- Constrain a generic type to super-impose other classes
- Chain multiple constraints together with the `+` symbol
- Can either be defined on the generic type, or in a `where` block
- The `where` block is used to handle more complex constraints

<BR>

#### Constraints on the generic type
- List constraints after the generic type, separated by a `+` symbol
- Constraints are checked at compile time
```s++
cls Wrapper[T: std::fmt::Display & std::ops::Add & std::ops::Sub] {
    value: T;
}
    
sup [T: std::fmt::Display & std::ops::Add & std::ops::Sub] Wrapper[T] {
    @std::static_method
    pub fun new(value: T):
        let w = Wrapper{value: value};
}
```

<BR>

#### Constraints on optional and variadic generics work in the same way
- Optional generics: `cls Foo[T:std::Number & std::Display = my::FormattableNumber]`
- Variadic generics: `cls Foo[...Ts: std::Number & std::Display]` => all types in the variadic must implement the constraints

<BR>

#### Constraints in a `where` block
- Allows multiple types to be constrained to the same class
- Allows a method to constrain a class to implement certain methods
- Allows nested types on a generic to be constrained (provided the generic type is constrained such that the nested type exists)

```s++
cls Wrapper[T, U] where [
        T: std::vector,
        T::ValueType, U: std::fmt::Display {
    value: T;
    other: U;
}
```

<BR>

#### Constraints on a `sup` definition
- Any constraints on the class must also be defined on every the `sup` block.
- Allows a class to be super-imposed _only when certain constraints are satisfied on the generic type_.
- For example, if a generic implements the `std::Default`, then more method that utilize the `std::Default` can be used.

```s++
cls Wrapper[T: std::Display] {
    value: T;
}
    
sup[T: std::Display] Wrapper[T] {
    @std::static_method
    pub fun new(value: T) {
        let w = Wrapper{value: value};
    }
}

sup[T: std::Display & std::Default] Wrapper[T] {    
    pub fun new_default() {
        return Wrapper{value: T::__def__()};
    }
}
```
- Only if `T` implements `std::Default` can the `new_default` method be used.