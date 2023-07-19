# Expressions

## Literals
| Literal         | Example         | Description        | Default Type |
|-----------------|-----------------|--------------------|--------------|
| `Base02 Number` | `0b101010`      | Binary number      | `std::Num`   |
| `Base16 Number` | `0xa2`          | Hexadecimal number | `std::Num`   |
| `Base10 Number` | `42.4e+10`      | Decimal number     | `std::Num`   |
| `String`        | `"Hello"`       | String             | `std::Str`   |
| `Char`          | `'a'`           | Character          | `std::Char`  |
| `Bool`          | `true`, `false` | Boolean            | `std::Bool`  |
| `Tuple`         | `(1, 2, 3)`     | Tuple              | `std::Tup`   |
| `Range`         | `1..10`         | Range              | `std::Range` |
- There is `Vec`, `Map` or `Set` literals, as variadic static methods can be used to construct types
- Ie `std::Vec::new(...)`'s signature allows for variadic arguments

## Primary Expressions
| Primary Expression        | Description                                                       | Example        |
|---------------------------|-------------------------------------------------------------------|----------------|
| `Identifier`              | Some variable/function or type                                    | `variable_a`   |
| `Literal`                 | Create a new instance of a type using shorthand                   | `[0, 1, 2]`    |
| `ScopedGenericIdentifier` | Will be a namespaced type / function                              | `std::Str`     |
| `Lambda`                  | Closure function allowing an expression                           | `(x) -> x + 1` |
| `Parenthesized`           | Contain an expression to execute first                            | `(1 + 2)`      |
| `Placeholder`             | Placeholders for match/case, partial functions etc                | `_`            |
| `IfStatement`             | Last expression per branch is the returning value                 |                |
| `MatchStatement`          | Last expression per case is the returning value                   |                |
| `WhileStatement`          | Expression bound the the `break` statement is the returning value |                |
| `ForStatement`            | Expression bound the the `break` statement is the returning value |                |
| `DoWhileStatement`        | Expression bound the the `break` statement is the returning value |                |
| `InnerScope`              | Last expression in the scope is the returning value               |                |

## Operators
### Operator Tables
#### Binary operators: apply to two arguments that are mapped into the \_\_fn__'s parameters
| Binary Operator | Description                                                                 |
|-----------------|-----------------------------------------------------------------------------|
| `=`             | Assignment - Assign right expression to left expression                     |
| `&&`, `&&=`     | Logical AND - Logical AND of two expressions                                |
| `\|\|`, `\|\|=` | Logical OR - Logical OR of two expressions                                  |
| `&`, `&=`       | Bitwise AND - Bitwise AND of two expressions                                |
| `\|`, `\|=`     | Bitwise OR - Bitwise OR of two expressions                                  |
| `^`, `^=`       | Bitwise XOR - Bitwise XOR of two expressions                                |
| `<=>`           | Comparison - Compare two expressions                                        |
| `==`            | Equality - Check if two expressions are equal                               |
| `!=`            | Inequality - Check if two expressions are not equal                         |
| `<`             | Less than - Check if left expression is less than right expression          |
| `>`             | Greater than - Check if left expression is greater than right               |
| `<=`            | Less than or equal - Check if left expression is less than or equal         |
| `>=`            | Greater than or equal - Check if left expression is greater than or         |
| `+`, `+=`       | Addition - Add two expressions                                              |
| `-`, `-=`       | Subtraction - Subtract two expressions                                      |
| `*`, `*=`       | Multiplication - Multiply two expressions                                   |
| `/`, `/=`       | Division - Divide two expressions                                           |
| `%`, `%=`       | Modulo - Modulo of two expressions                                          |
| `\|>`           | Pipe function operator - Pipe the left expression into the right expression |
| `<\|`           | Pipe function operator - Pipe the right expression into the left expression |

<BR>

#### Unary operators
- There are no unary operators in S++
- Unary operators are implemented as methods on the type

<BR>

#### Postfix operators: apply to one argument that is mapped into the \_\_fn__'s parameter
| Postfix Operator | Description                                                      |
|------------------|------------------------------------------------------------------|
| `()`             | Function call - Call a function with the given arguments         |
| `[]`             | Index - Index into a collection with the given index             |
| `{}`             | Initialize a class with the given arguments                      |
| `.`              | Member access - Access a member of an object                     |
| `?`              | Force unwrap - Unwrap an optional object (return error if error) |


### Overloading operators
- Operators can be overloaded by implementing the corresponding class containing the magic methods
- Only the reference operator `&[mut]?` cannot be overloaded for a type
- The function pipe operator must be implemented on the receiving type, not the argument type

| Class                 | Magic methods                       | Description                      |
|-----------------------|-------------------------------------|----------------------------------|
| `std::ops::Add`       | `__add__`, `__add_assign__`         | Binary `+`                       |
| `std::ops::Sub`       | `__sub__`, `__sub_assign__`         | Binary `-`                       |
| `std::ops::Mul`       | `__mul__`, `__mul_assign__`         | Binary `*`                       |
| `std::ops::Div`       | `__div__`, `__div_assign__`         | Binary `/`                       |
| `std::ops::Mod`       | `__mod__`, `__mod_assign__`         | Binary `%`                       |
| `std::ops::And`       | `__and__`, `__and_assign__`         | Binary `&&`                      |
| `std::ops::Or`        | `__or__`, `__or_assign__`           | Binary `\|\|`                    |
| `std::ops::BitNot`    | `__bit_not__`, `__bit_not_assign__` | Unary `~`                        |
| `std::ops::BitAnd`    | `__bit_and__`, `__bit_and_assign__` | Binary `&`                       |
| `std::ops::BitOr`     | `__bit_or__`, `__bit_or_assign__`   | Binary `\|`                      |
| `std::ops::BitXor`    | `__bit_xor__`, `__bit_xor_assign__` | Binary `^`                       |
| `std::ops::Eq`        | `__eq__`                            | Binary `==`                      |
| `std::ops::Ne`        | `__ne__`                            | Binary `!=`                      |
| `std::ops::Lt`        | `__lt__`                            | Binary `<`                       |
| `std::ops::Le`        | `__le__`                            | Binary `<=`                      |
| `std::ops::Gt`        | `__gt__`                            | Binary `>`                       |
| `std::ops::Ge`        | `__ge__`                            | Binary `>=`                      |
| `std::ops::Cmp`       | `__cmp__`                           | Binary `<=>`                     |
| `std::ops::RPipe`     | `__rpip__`                          | Binary `\|>`                     |
| `std::ops::LPipe`     | `__lpip__`                          | Binary `<\|`                     |
| `std::func::FunOnce`  | `__call_once__`                     | Postfix call                     |
| `std::func::FunRef`   | `__call_ref__`                      | Postfix call                     |
| `std::func::FunMut`   | `__call_mut__`                      | Postfix call                     |
| `std::ops::Try[T, E]` | `__try__`                           | Postfix `?`                      |
| `std::ops::Del`       | `__del__`                           | Destructor                       |
| `std::ops::With`      | `__enter__`, `__exit__`             | For the `with` statement         |
| `std::ops::Rng`       | `__next__`                          | For the range literal ie `0..10` |

