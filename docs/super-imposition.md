# Super-imposition
#### Concept
- Super-imposition relates to the implementation of methods, or other classes, in a class.
- Super-imposition is the process of "imposing" something on top of the current class.
- The class being imposed on must have be defined with the `cls` keyword in the file.

### Methods
#### Implement the class directly, listing the methods
- Methods are defined in the same way as functions.
- Methods can be defined in any order.
- Methods can be defined in multiple implementations of the same class.

```s++
sup Foo {
    fn foo() -> Void {}
    fn bar() -> Void {}
}

sup Foo {
    fn baz() -> Void {}
}
```

#### Compile-time conditionally enable functions with type-constraints
- Functions can be conditionally enabled based on the type of the generic parameters.
- Enable certain functions if the constraints of the generic parameters are satisfied.

```s++
sup Foo[T] {
    fn foo() -> Void {}
    fn bar() -> Void {}
    fn baz() -> Void {}
}

sup Foo[T: Default] {
    fn def() -> Void {}
}
```

### Classes ("inheritance")
- Works in a similar way to methods.
- Allow super-imposing an entire class on top of the current class, acting as a super-class.
- Allow functions to be overridden.

#### Override methods from the super-class
- Required to give explicit control over which methods can be overridden.
- Methods must be decorated with `@meta.virtual` in the super-class to allow a sub-class to override.

```s++
sup Bar {
    @meta.virtual fn foo(self: &Self) -> Void {}
    @meta.virtual fn bar(self: &Self) -> Void {}
}

sup Bar for Foo {
    fn foo(self: &Self) -> Void {...}
    fn bar(self: &Self) -> Void {...}
}
```

#### Compile-time conditionally enable classes with type-constraints
- Classes can be conditionally enabled based on the type of the generic parameters.
- Enable certain classes if the constraints of the generic parameters are satisfied.
- Any type-constraints on the base class must also be on the `sup` definitions when inheriting it.
    - For example if `sup[T: Default] Bar[T]` was the `sup` definition for `Bar`:
    - Then to inherit it as below, it would have to be `sup[T: Default & Copy] Bar[T] for Foo`.

```s++
sup Bar[T] {
    @meta.virtual fn foo(self: &Self) -> Void {}
    @meta.virtual fn bar(self: &Self) -> Void {}
    @meta.virtual fn baz(self: &Self) -> Void {}
}

sup Bar[T: Copy] for Foo {
    fn foo(self: &Self) -> Void {...}
    fn bar(self: &Self) -> Void {...}
    fn baz(self: &Self) -> Void {...}
}
```