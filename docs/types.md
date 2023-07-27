# Type System
## Overview of the type system
- Strong typing => no implicit type casting
- Static typing => type checking at compile time
- Every value is type-inferred
- Explicit type annotations have to be used for uninitialized variables

### Type names vs variable names
- Type names: must start with a capital letter
- Variable names: must start with a lowercase letter

## Primitives vs Objects
- No primitives in S++
- Everything inherits from `std::Obj` - this is never explicitly stated in code
- Primitive numbers -> `std::Num`
- Primitive booleans -> `std::Bool`
- Primitive strings -> `std::Str`

## Type inference / annotations
- Every value is type-inferred
- Any assignment that has an RHS is always type-inferred, and therefore a type annotation is not allowed
- Any assignment that does not have an RHS must have a type annotation
- Parameter and return types for functions must be annotated
- Class attributes are the same as variables: if they are initialized, they are type-inferred, otherwise they must be annotated

## Supertypes as function parameters
- A class will automatically cast to a super-type if the super-type is used as a parameter type
- Within the function, `std::down_cast[T](...)` can be used to downcast the parameter to the original type

## Type casting and conversion
- Implement methods specific to each type
- The class `std::ToString` is used to convert any type to a string

## Hierarchy casts
#### Upcasting
- Compile time check to make sure that the target class is a base class of the variable type.
- Always yields a valid object => return type is `T`.
- Use `std::up_cast[T](...)` to upcast a variable: `let a = std::upcast[T](b)`

#### Downcasting
- Compile time check to make sure that the target class is a derived class of the variable type.
- Returns a `std::Ret[T, std::CastErr]` type (could be a different derived class).
- Use `std::down_cast[T](...)` to downcast a variable: `let a = std::downcast[T](b)?` (use `?` to handle the error)

#### Cross-casting
- Compile time check to make sure that the target class is a base class of the >0 derived class of the variable type.
- Returns a `std::Ret[T, std::CastErr]` type (could be a different derived class).
- Use `std::cross_cast[T](...)` to cross-cast a variable: `let a = std::crosscast[T](b)?` (use `?` to handle the error)

## Variadic types
- A generic looks like `[...Ts]`, then it is a variadic type -- the pack using this type must be variadic ie `...args: Ts`
- A variadic parameter must use this type -- a single requires or optional parameter cannot be a variadic type
- Variadic types can have the `::n` syntax applied, to get the nth type in the pack (where n is a number)
- Variadic types can be in a `use` statement, but cannot be instantiated (used for type-checking)

### Not allowed
- A non-variadic parameter can not be denoted with a type declared as variadic (makes no sense)
```s++
function func_variadic_3<...Ts>(a: Ts):
    std::print(a);
```

## Tuples
- Compiler builtin
- Can be used to hold a fixed number of values of different types
- Created by wrapping a comma-separated list of types in parentheses
- Allows the `::n` syntax to access the nth type in the tuple
- Also supports the `.n` syntax to access the nth value in the tuple


## Special types
#### `std::Void`
- Compiler built-in (special behavior)
- The "nothing" type
- Cannot be the type of a variable / attribute
- Can be used as a return type for a function
- Can be used as a generic type argument ie `std::Result<std::Void, std::String>`
- Cannot be instantiated
- When `std::void` is used as a generic `T`, methods that use `T` as a parameter type will have that parameter removed from the function signature

#### `Unit type`
- The empty tuple - `()`
- Can act as a placeholder type, but `std::Void` should be preferred


## Type-aliasing
- Use the `use` keyword to alias a type -- `use` is primary used to compile-time aliasing
- Example: `use MyInt = std::Num`
- Example: `use MyVec[T] = std::Vec[T]`
